package com.github.sergejsamsonow.codegenerator.api.producer.sc;

public class SCMethodCodeConcatenator {

    private SCCodeConcatenator method;
    private SCCodeConcatenator code;

    public SCMethodCodeConcatenator(SCNewLineAndIndentationFormat format) {
        this(format, null);
    }

    public SCMethodCodeConcatenator(SCNewLineAndIndentationFormat format, StringBuilder builder) {
        method = new SCCodeConcatenator(format, 1, builder);
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

    public SCCodeConcatenator methodCodeWriter() {
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
