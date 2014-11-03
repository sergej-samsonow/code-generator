package com.github.sergejsamsonow.codegenerator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;

public class SimpleParser {

    private static final Pattern NPTR = Pattern.compile("^Namespace\\s*:\\s*([a-z.]*)$");
    private static final Pattern TPTR = Pattern.compile("^([a-zA-Z0-9.]+)$");
    private static final Pattern PPTR = Pattern.compile("^([a-z][A-Za-z0-9]*)\\s*:\\s*([A-Za-z0-9.<>]+)$");

    private ProducerAccess<ParsedBean> producer;
    private String namespace;
    private Matcher matcher;
    private String beanType;
    private List<ParsedProperty> properties;

    public SimpleParser(ProducerAccess<ParsedBean> producer) {
        this.producer = producer;
        this.namespace = "";
        this.beanType = null;
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
        namespace = matcher.group(1);
    }

    private void processBeanDeclaration() {
        packBeanIfExists();
        beanType = matcher.group(1);
    }

    private void processPropertyDeclaration() {
        properties.add(new ParsedProperty(matcher.group(1), matcher.group(2)));
    }

    private void packBeanIfExists() {
        if (beanType != null) {
            producer.newItem(new ParsedBean(namespace, beanType, new ArrayList<>(properties)));
            beanType = null;
            properties.clear();
        }
    }

}
