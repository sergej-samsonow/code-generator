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
public class SCMethodCodeConcatenatorTest {

    private static final String N = "NL";
    private static final String S = "SW";
    private static final String C = S + S;
    private static final String I = S + C;

    @Mock
    private SCNewLineAndIndentationFormat format;

    private SCMethodCodeConcatenator writer;

    @Before
    public void setUp() {
        Mockito.when(format.newLineSequence()).thenReturn(N);
        Mockito.when(format.shiftSequence()).thenReturn(S);
        writer = new SCMethodCodeConcatenator(format);
    }

    @Test
    public void testAnnotationTemplate() throws Exception {
        writer.annotation("%s", "D");
        assertThat(writer.flush(), equalTo(S + "D" + N));
    }

    @Test
    public void testAnnotationSimple() throws Exception {
        writer.annotation("A");
        assertThat(writer.flush(), equalTo(S + "A" + N));
    }

    @Test
    public void testStart() throws Exception {
        writer.start("B");
        assertThat(writer.flush(), equalTo(S + "B" + N));
    }

    @Test
    public void testStartTemplate() throws Exception {
        writer.start("%s", "C");
        assertThat(writer.flush(), equalTo(S + "C" + N));
    }

    @Test
    public void testMethodCodeWriter() throws Exception {
        SCCodeConcatenator codeWriter = writer.methodCodeWriter();
        codeWriter.line("A");
        assertThat(writer.flush(), equalTo(C + "A" + N));
    }

    @Test
    public void testCode() throws Exception {
        writer.code("A");
        assertThat(writer.flush(), equalTo(C + "A" + N));
    }

    @Test
    public void testCodeTemplate() throws Exception {
        writer.code("%s", "B");
        assertThat(writer.flush(), equalTo(C + "B" + N));
    }

    @Test
    public void testIndentedCodeString() throws Exception {
        writer.indentedCode("A");
        assertThat(writer.flush(), equalTo(I + "A" + N));
    }

    @Test
    public void testIndentedCodeStringObjectArray() throws Exception {
        writer.indentedCode("%s", "B");
        assertThat(writer.flush(), equalTo(I + "B" + N));
    }

    @Test
    public void testEnd() throws Exception {
        writer.end();
        assertThat(writer.flush(), equalTo(S + "}" + N));
    }

    @Test
    public void testEmptyNewLine() throws Exception {
        writer.emptyNewLine();
        assertThat(writer.flush(), equalTo(N));
    }

    @Test
    public void testFlush() throws Exception {
        writer.annotation("A");
        writer.flush();
        writer.annotation("B");
        assertThat(writer.flush(), equalTo(S + "B" + N));
    }
}
