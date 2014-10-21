package com.github.sergejsamsonow.codegenerator.api.producer.sc;

public class CodeFormat {

    private static final String TAB = "\t";
    private static final String UNIX_NL = "\n";
    private static final String WINDOWS_NL = "\r\n";
    private String newLine;
    private String shiftWith;

    private CodeFormat(String newLine, String shiftWith) {
        this.newLine = newLine;
        this.shiftWith = shiftWith;
    }

    public String newLineSequence() {
        return newLine;
    }

    public String shiftSequence() {
        return shiftWith;
    }

    final public static CodeFormat windowsWithTabs() {
        return new CodeFormat(WINDOWS_NL, TAB);
    }

    final public static CodeFormat windowsWithSpaces(int count) {
        return new CodeFormat(WINDOWS_NL, shiftSequence(count));
    }

    final public static CodeFormat unixWithTabs() {
        return new CodeFormat(UNIX_NL, TAB);
    }

    final public static CodeFormat unixWithSpaces(int count) {
        return new CodeFormat(UNIX_NL, shiftSequence(count));
    }

    private static String shiftSequence(int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++)
            builder.append(" ");
        return builder.toString();
    }

}
