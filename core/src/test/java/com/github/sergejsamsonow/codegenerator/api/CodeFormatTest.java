package com.github.sergejsamsonow.codegenerator.api;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import com.github.sergejsamsonow.codegenerator.api.CodeFormat;

public class CodeFormatTest {

    @Test
    public void testWindowsWithTabs() throws Exception {
        CodeFormat format = CodeFormat.windowsWithTabs();
        assertThat(format.shiftWidthSequence(), equalTo("\t"));
        assertThat(format.newLineSequence(), equalTo("\r\n"));
    }

    @Test
    public void testUnixWithTabs() throws Exception {
        CodeFormat format = CodeFormat.unixWithTabs();
        assertThat(format.shiftWidthSequence(), equalTo("\t"));
        assertThat(format.newLineSequence(), equalTo("\n"));
    }

    @Test
    public void testUnixWithSpaces() throws Exception {
        CodeFormat format = CodeFormat.unixWithSpaces(4);
        assertThat(format.shiftWidthSequence(), equalTo("    "));
        assertThat(format.newLineSequence(), equalTo("\n"));
    }

    @Test
    public void testWindowsWithSpaces() throws Exception {
        CodeFormat format = CodeFormat.windowsWithSpaces(4);
        assertThat(format.shiftWidthSequence(), equalTo("    "));
        assertThat(format.newLineSequence(), equalTo("\r\n"));
    }

}
