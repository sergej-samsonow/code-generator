package com.github.sergejsamsonow.codegenerator.pojo.renderer;

import java.util.Set;
import com.github.sergejsamsonow.codegenerator.api.BaseRenderer;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;
import com.github.sergejsamsonow.codegenerator.api.CodeWriter;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;

public class ClassHeader extends BaseRenderer<PojoBean> {

    private CodeWriter writer;
    private PojoBean data;

    public ClassHeader(CodeFormat format) {
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
        if (imports.isEmpty())
            return;
        writer.emptyNewLine();
        for (String current : imports)
            writer.line("import %s;", current);
    }

    private void writeClassDeclaration() {
        writer.emptyNewLine();
        writer.line("public class %s {", data.getClassName());
        writer.emptyNewLine();
    }

    private void cleanUpAfterRendering() {
        data = null;
        writer = null;
    }
}
