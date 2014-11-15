package com.github.sergejsamsonow.codegenerator.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedBean;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedProperty;

public class SoyParser {

    final private static Pattern NAMESPACE_PTR = Pattern
        .compile("^\\{namespace\\s+(?<namespace>[^}]+)}.*");
    final private static Pattern JAVADOC_PTR = Pattern.compile(
        "(?<start>/\\*\\*\\s*)(?<content>.*?)(?<end>\\s*\\*/\\s*)",
        Pattern.DOTALL);
    final private static Pattern BEAN_PTR = Pattern.compile(
        ".*Bean\\:\\s+(?<name>[a-zA-Z][a-zA-Z0-9_.]*)(?<params>.*)",
        Pattern.DOTALL);
    final private static Pattern PROPERTIES_PTR = Pattern.compile("@param\\s+"
        + "(?<name>[a-z][a-zA-Z0-9]*)" + "\\s+:\\s+"
        + "(?<type>[a-zA-Z][a-zA-Z0-9_.<>]*)", Pattern.MULTILINE);

    private String content;
    private String namespace;
    private List<String> javadocs;
    private ProducerAccess<ParsedBean> producer;

    public SoyParser(ProducerAccess<ParsedBean> producer) {
        reset();
        this.producer = producer;
    }

    public void parse(String content) {
        this.content = content;
        findNamespace();
        findJavaDocParts();
        extractBeans();
        reset();
    }

    private void reset() {
        namespace = "";
        javadocs = new ArrayList<String>();
    }

    private void findNamespace() {
        Matcher namespaceMatcher = NAMESPACE_PTR.matcher(content);
        if (namespaceMatcher.find()) {
            namespace = namespaceMatcher.group("namespace");
        }
    }

    private void findJavaDocParts() {
        Matcher matcher = JAVADOC_PTR.matcher(content);
        while (matcher.find()) {
            javadocs.add(matcher.group("content"));
        }
    }

    private void extractBeans() {
        for (String javadoc : javadocs) {
            Matcher bean = BEAN_PTR.matcher(javadoc);
            if (!bean.find()) {
                continue;
            }
            producer.newItem(new SimpleParsedBean(namespace, bean.group("name"), null,
                extractProperties(bean.group("params"))));
        }
    }

    private List<ParsedProperty> extractProperties(String content) {
        List<ParsedProperty> properties = new ArrayList<>();
        Matcher matcher = PROPERTIES_PTR.matcher(content.trim());
        while (matcher.find()) {
            properties.add(new SimpleParsedProperty(
                matcher.group("name"), matcher.group("type")));
        }
        return properties;
    }
}
