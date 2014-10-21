package com.github.sergejsamsonow.codegenerator.pojo.model;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PojoProperty))
            return false;
        PojoProperty casted = (PojoProperty) object;
        return Objects.equals(isList(), casted.isList())
            && Objects.equals(getInitCode(), casted.getInitCode())
            && Objects.equals(getImportedTypes(), casted.getImportedTypes())
            && Objects.equals(getDeclarationType(), casted.getDeclarationType())
            && Objects.equals(getGetterName(), casted.getGetterName())
            && Objects.equals(getSetterName(), casted.getSetterName())
            && Objects.equals(getFieldName(), casted.getFieldName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isList(), getInitCode(), getImportedTypes(), getDeclarationType(), getGetterName(),
            getSetterName(), getFieldName());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SimplePojoProperty (");
        builder.append("list: " + isList());
        builder.append(", ");
        builder.append("initCode: " + getInitCode());
        builder.append(", ");
        builder.append("importedTypes: " + Objects.toString(getImportedTypes()));
        builder.append(", ");
        builder.append("declarationType: " + getDeclarationType());
        builder.append(", ");
        builder.append("getterName: " + getGetterName());
        builder.append(", ");
        builder.append("setterName: " + getSetterName());
        builder.append(", ");
        builder.append("fieldName: " + getFieldName());
        builder.append(")");
        return builder.toString();
    }
}
