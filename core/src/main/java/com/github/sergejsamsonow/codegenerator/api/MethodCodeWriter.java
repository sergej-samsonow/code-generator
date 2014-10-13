package com.github.sergejsamsonow.codegenerator.api;

public class MethodCodeWriter {

    private CodeWriter method;
    private CodeWriter code;

    public MethodCodeWriter(CodeFormat format) {
        this(format, null);
    }

    public MethodCodeWriter(CodeFormat format, StringBuilder builder) {
        method = new CodeWriter(format, 1, builder);
        code = method.indent();
    }

    public void annotation(String annotation) {
        method.line(annotation);
    }

    public void annotation(String annotationTemplate, Object... data) {
        method.line(annotationTemplate, data);
    }

    public void start(String methodSignature) {
        method.line(methodSignature);
    }

    public void start(String methodSignatureTemplate, Object... data) {
        method.line(methodSignatureTemplate, data);
    }

    public CodeWriter methodCodeWriter() {
        return code;
    }

    public void code(String codeLine) {
        code.line(codeLine);
    }

    public void code(String codeLineTemplate, Object... data) {
        code.line(codeLineTemplate, data);
    }

    public void indentedCode(String codeLine) {
        code.indentedLine(codeLine);
    }

    public void indentedCode(String codeLineTemplate, Object... data) {
        code.indentedLine(codeLineTemplate, data);
    }

    public void end() {
        method.line("}");
    }

    public void emptyNewLine() {
        method.emptyNewLine();
    }

    public String flush() {
        return method.flush();
    }

}
