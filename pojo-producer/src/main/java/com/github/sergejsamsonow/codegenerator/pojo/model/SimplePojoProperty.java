package com.github.sergejsamsonow.codegenerator.pojo.model;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class SimplePojoProperty implements PojoProperty {

    private boolean list;
    private boolean containsSimpleType;
    private String containedType;
    private String name;
    private String initCode;
    private Set<String> importedTypes;
    private String declarationType;
    private String getterName;
    private String setterName;

    private static final Map<String, String> SIMPLE_TYPES;
    private static final Pattern CONTAINS_SIMPLE_TYPE;
    static {
        Map<String, String> simpleTypeWithInitCode = new HashMap<>();
        simpleTypeWithInitCode.put("String", "\"\"");
        simpleTypeWithInitCode.put("Boolean", "false");
        simpleTypeWithInitCode.put("Integer", "0");
        simpleTypeWithInitCode.put("Float", "0.0F");
        simpleTypeWithInitCode.put("Long", "0L");
        simpleTypeWithInitCode.put("Double", "0.0D");
        CONTAINS_SIMPLE_TYPE = Pattern.compile(
            "^" + join(simpleTypeWithInitCode.keySet(), "|") + "$");
        SIMPLE_TYPES = Collections.unmodifiableMap(simpleTypeWithInitCode);
    }

    public SimplePojoProperty(String parsedName, String parsedType) {
        createNameRelatedEntries(parsedName);
        createTypeRelatedEntries(parsedType);
    }

    protected void createTypeRelatedEntries(String parsedType) {
        importedTypes = new HashSet<>();
        list = startsWith(parsedType, "List<");
        if (list) {
            createListType(parsedType);
        }
        else {
            createScalarType(parsedType);
        }
        if (importedTypes.isEmpty()) {
            importedTypes = Collections.emptySet();
        }
    }

    protected void createScalarType(String parsedType) {
        createDeclarationType(parsedType);
        if (SIMPLE_TYPES.containsKey(declarationType)) {
            initCode = SIMPLE_TYPES.get(declarationType);
        }
        else {
            initCode = String.format("new %s()", declarationType);
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
        else {
            declarationType = content;
        }
        containsSimpleType = CONTAINS_SIMPLE_TYPE.matcher(declarationType).find();
        containedType = declarationType;
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
    public boolean isSimpleTypeContainer() {
        return containsSimpleType;
    }

    @Override
    public String getInitCode() {
        return initCode;
    }

    @Override
    public String getContainedType() {
        return containedType;
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
        if (!(object instanceof PojoProperty)) {
            return false;
        }
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
