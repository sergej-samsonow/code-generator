package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class SCNewLineAndIndentationFormatTest {

    @Test
    public void testWindowsWithTabs() throws Exception {
        SCNewLineAndIndentationFormat format = SCNewLineAndIndentationFormat.windowsWithTabs();
        assertThat(format.shiftSequence(), equalTo("\t"));
        assertThat(format.newLineSequence(), equalTo("\r\n"));
    }

    @Test
    public void testUnixWithTabs() throws Exception {
        SCNewLineAndIndentationFormat format = SCNewLineAndIndentationFormat.unixWithTabs();
        assertThat(format.shiftSequence(), equalTo("\t"));
        assertThat(format.newLineSequence(), equalTo("\n"));
    }

    @Test
    public void testUnixWithSpaces() throws Exception {
        SCNewLineAndIndentationFormat format = SCNewLineAndIndentationFormat.unixWithSpaces(4);
        assertThat(format.shiftSequence(), equalTo("    "));
        assertThat(format.newLineSequence(), equalTo("\n"));
    }

    @Test
    public void testWindowsWithSpaces() throws Exception {
        SCNewLineAndIndentationFormat format = SCNewLineAndIndentationFormat.windowsWithSpaces(4);
        assertThat(format.shiftSequence(), equalTo("    "));
        assertThat(format.newLineSequence(), equalTo("\r\n"));
    }

}
