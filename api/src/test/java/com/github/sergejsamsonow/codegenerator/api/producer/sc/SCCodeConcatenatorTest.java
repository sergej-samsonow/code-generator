package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SCCodeConcatenatorTest {

    private static final String NL = "N";
    private static final String SW = "T";

    @Mock
    private SCNewLineAndIndentationFormat format;

    private SCCodeConcatenator writer;

    @Before
    public void setUp() {
        Mockito.when(format.newLineSequence()).thenReturn(NL);
        Mockito.when(format.shiftSequence()).thenReturn(SW);
        writer = new SCCodeConcatenator(format);
    }

    @Test
    public void testAddRaw() {
        writer.addRaw("A");
        writer.addRaw("B");
        assertThat(writer.flush(), equalTo("AB"));
    }

    @Test
    public void testLineFormatedLine() {
        writer.line("%s", "C");
        assertThat(writer.flush(), equalTo("C" + NL));
    }

    @Test
    public void testLineSimpleLine() {
        writer.line("A");
        writer.line("B");
        assertThat(writer.flush(), equalTo("A" + NL + "B" + NL));
    }

    @Test
    public void testIndent() throws Exception {
        SCCodeConcatenator indented = writer.indent();
        indented.line("A");
        assertThat(indented.flush(), equalTo(SW + "A" + NL));
    }

    @Test
    public void testIndentFlushParent() throws Exception {
        SCCodeConcatenator indented = writer.indent();
        indented.line("A");
        assertThat(writer.flush(), equalTo(SW + "A" + NL));
    }

    @Test
    public void testIndentFlushChild() throws Exception {
        SCCodeConcatenator indented = writer.indent();
        writer.line("A");
        assertThat(indented.flush(), equalTo("A" + NL));
    }

    @Test
    public void testIndentedLineSimple() throws Exception {
        writer.indentedLine("A");
        assertThat(writer.flush(), equalTo(SW + "A" + NL));
    }

    @Test
    public void testIndentedLineList() throws Exception {
        writer.indentedLine("%s", "C");
        assertThat(writer.flush(), equalTo(SW + "C" + NL));
    }

    @Test
    public void testEmptyNewLine() throws Exception {
        writer.emptyNewLine();
        assertThat(writer.flush(), equalTo(NL));
    }

    @Test
    public void testFlush() throws Exception {
        writer.addRaw("A");
        writer.flush();
        writer.addRaw("B");
        assertThat(writer.flush(), equalTo("B"));
    }

}
