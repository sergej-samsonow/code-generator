package com.github.sergejsamsonow.codegenerator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.api.parser.ParsedProperty;

public class SimpleParser {

    private static final int NAMESPACE_CONTENT = 1;
    private static final int TYPE_NAME_CONTENT = 1;
    private static final int PARENT_TYPE_CONTENT = 2;
    private static final int PROPERTY_NAME_CONTENT = 1;
    private static final int PROPERTY_TYPE_CONTENT = 2;

    private static final Pattern NPTR = Pattern.compile("^Namespace\\s*:\\s*([a-z.]*)$");
    private static final Pattern TPTR = Pattern.compile("^([a-zA-Z0-9.]+)(?:\\s+extends\\s+([a-zA-Z0-9.]+))?$");
    private static final Pattern PPTR = Pattern.compile("^([a-z][A-Za-z0-9]*)\\s*:\\s*([A-Za-z0-9.<>]+)$");

    private ProducerAccess<ParsedBean> producer;
    private String namespace;
    private Matcher matcher;
    private String beanType;
    private String parentType;
    private List<ParsedProperty> properties;

    public SimpleParser(ProducerAccess<ParsedBean> producer) {
        this.producer = producer;
        this.namespace = "";
        this.beanType = null;
        this.parentType = null;
        this.properties = new ArrayList<>();
    }

    public void parse(String content) {
        namespace = "";
        for (String line : content.split("\\r\\n|\\n")) {
            resetMatcher();
            if (namspaceDeclarationFound(line)) {
                processNamespaceDeclaration();
            }
            else if (beanDeclarationFound(line)) {
                processBeanDeclaration();
            }
            else if (propertyDeclarationFound(line)) {
                processPropertyDeclaration();
            }
        }
        packBeanIfExists();
    }

    private void resetMatcher() {
        matcher = null;
    }

    private boolean namspaceDeclarationFound(String line) {
        matcher = NPTR.matcher(line);
        return matcher.find();
    }

    private boolean beanDeclarationFound(String line) {
        matcher = TPTR.matcher(line);
        return matcher.find();
    }

    private boolean propertyDeclarationFound(String line) {
        matcher = PPTR.matcher(line);
        return matcher.find();
    }

    private void processNamespaceDeclaration() {
        namespace = matcher.group(NAMESPACE_CONTENT);
    }

    private void processBeanDeclaration() {
        packBeanIfExists();
        beanType = matcher.group(TYPE_NAME_CONTENT);
        parentType = matcher.group(PARENT_TYPE_CONTENT);
    }

    private void processPropertyDeclaration() {
        properties.add(new SimpleParsedProperty(
            matcher.group(PROPERTY_NAME_CONTENT), matcher.group(PROPERTY_TYPE_CONTENT)));
    }

    private void addNewItem() {
        producer.newItem(new SimpleParsedBean(namespace, beanType, parentType, new ArrayList<>(properties)));
    }

    private void cleanUp() {
        beanType = null;
        parentType = null;
        properties.clear();
    }

    private void packBeanIfExists() {
        if (beanType != null) {
            addNewItem();
            cleanUp();
        }
    }

}
