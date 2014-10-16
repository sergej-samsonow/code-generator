package com.github.sergejsamsonow.codegenerator.pojo.model;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SimplePojoProperty implements PojoProperty {

    private boolean list;
    private String name;
    private String initCode;
    private Set<String> importedTypes;
    private String declarationType;
    private String getterName;
    private String setterName;

    public SimplePojoProperty(String parsedName, String parsedType) {
        createNameRelatedEntries(parsedName);
        createTypeRelatedEntries(parsedType);
    }

    protected void createTypeRelatedEntries(String parsedType) {
        importedTypes = new HashSet<>();
        list = startsWith(parsedType, "List<");
        if (list)
            createListType(parsedType);
        else
            createScalarType(parsedType);
        if (importedTypes.isEmpty())
            importedTypes = Collections.emptySet();
    }

    protected void createScalarType(String parsedType) {
        createDeclarationType(parsedType);

        // set init code
        switch (declarationType) {
            case "String":
                initCode = "\"\"";
                break;
            case "Boolean":
                initCode = "false";
                break;
            case "Integer":
                initCode = "0";
                break;
            case "Float":
                initCode = "0.0F";
                break;
            case "Long":
                initCode = "0L";
                break;
            case "Double":
                initCode = "0.0D";
                break;
            default:
                initCode = String.format("new %s()", declarationType);
                break;
        }
    }

    protected void createListType(String parsedType) {
        importedTypes = new HashSet<>();
        importedTypes.add("java.util.List");
        importedTypes.add("java.util.ArrayList");
        createDeclarationType(extractListContentType(parsedType));
        declarationType = "List<" + declarationType + ">";
        initCode = "new ArrayList<>()";
    }

    protected void createDeclarationType(String content) {
        if (content.matches("^[a-z].*")) {
            importedTypes.add(content);
            declarationType = substringAfterLast(content, ".");
        }
        else
            declarationType = content;
    }

    protected String extractListContentType(String parsedType) {
        String content = "";
        content = parsedType.replace("List<", "");
        content = content.replace(">", "");
        return content;
    }

    protected void createNameRelatedEntries(String parsedName) {
        String capitalized = capitalize(parsedName);
        name = parsedName;
        setterName = "set" + capitalized;
        getterName = "get" + capitalized;
    }

    @Override
    public boolean isList() {
        return list;
    }

    @Override
    public String getInitCode() {
        return initCode;
    }

    @Override
    public Set<String> getImportedTypes() {
        return importedTypes;
    }

    @Override
    public String getDeclarationType() {
        return declarationType;
    }

    @Override
    public String getGetterName() {
        return getterName;
    }

    @Override
    public String getSetterName() {
        return setterName;
    }

    @Override
    public String getFieldName() {
        return name;
    }

}
