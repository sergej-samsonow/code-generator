package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import java.util.Set;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCCodeConcatenator;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCRenderer;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

public class ClassHeader extends SCRenderer<PojoBean> {

    private SCCodeConcatenator writer;
    private PojoBean data;

    public ClassHeader(SCNewLineAndIndentationFormat format) {
        super(format);
    }

    @Override
    protected void render() {
        prepareForRendering();
        writePackageLine();
        writeImports();
        writeClassDeclaration();
        cleanUpAfterRendering();
    }

    private void prepareForRendering() {
        writer = getCodeWriter();
        data = getData();
    }

    private void writePackageLine() {
        writer.line("package %s;", data.getPackageName());
    }

    private void writeImports() {
        Set<String> imports = data.getImports();
        if (imports.isEmpty()) {
            return;
        }
        writer.emptyNewLine();
        imports.stream().sorted().forEach(current -> writer.line("import %s;", current));
    }

    private void writeClassDeclaration() {
        writer.emptyNewLine();
        String parentClassName = data.getParentClass();
        String className = data.getClassName();
        if (isEmpty(parentClassName)) {
            writer.line("public class %s {", className);
        }
        else {
            writer.line("public class %s extends %s {", className, parentClassName);
        }
        writer.emptyNewLine();
    }

    private void cleanUpAfterRendering() {
        data = null;
        writer = null;
    }
}
