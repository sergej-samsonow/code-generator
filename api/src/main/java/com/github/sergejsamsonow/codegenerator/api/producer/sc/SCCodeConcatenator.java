package com.github.sergejsamsonow.codegenerator.api.producer.sc;

public class SCCodeConcatenator {

    private SCNewLineAndIndentationFormat format;

    private int indent;
    private String shift;
    private String newline;
    private StringBuilder builder;

    public SCCodeConcatenator(SCNewLineAndIndentationFormat format) {
        this(format, 0);
    }

    public SCCodeConcatenator(SCNewLineAndIndentationFormat format, int indent) {
        this(format, 0, null);
    }

    public SCCodeConcatenator(SCNewLineAndIndentationFormat format, int indent, StringBuilder builder) {
        this.format = format;
        this.indent = indent;
        this.builder = builder == null ? new StringBuilder() : builder;
        this.newline = format.newLineSequence();

        StringBuilder sequenceBuilder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sequenceBuilder.append(format.shiftSequence());
        }
        this.shift = sequenceBuilder.toString();
    }

    public void addRaw(String data) {
        builder.append(data);
    }

    public SCCodeConcatenator indent() {
        return new SCCodeConcatenator(format, indent + 1, builder);
    }

    public void line(String line) {
        addRaw(String.format("%s%s%s", shift, line, newline));
    }

    public void line(String format, Object... data) {
        line(String.format(format, data));
    }

    public void indentedLine(String line) {
        indent().line(line);
    }

    public void indentedLine(String format, Object... data) {
        indent().line(format, data);
    }

    public void emptyNewLine() {
        builder.append(newline);
    }

    public String flush() {
        String result = builder.toString();
        builder.setLength(0);
        return result;
    }
}
