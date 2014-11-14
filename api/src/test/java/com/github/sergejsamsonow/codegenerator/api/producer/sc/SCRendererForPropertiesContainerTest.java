package com.github.sergejsamsonow.codegenerator.api.producer.sc;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SCRendererForPropertiesContainerTest {

    @Mock
    private PropertiesContainer<String> bean;
    private static final String START = "before";
    private static final String END = "after";

    private static class FirstLast {

        public String proprety;
        public boolean first;
        public boolean last;

        public FirstLast(String property, boolean first, boolean last) {
            this.proprety = property;
            this.first = first;
            this.last = last;
        }

        @Override
        public int hashCode() {
            return Objects.hash(proprety, first, last);
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof FirstLast)) {
                return false;
            }
            FirstLast casted = (FirstLast) object;
            return Objects.equals(proprety, casted.proprety)
                && Objects.equals(first, casted.first)
                && Objects.equals(last, casted.last);
        }
    }

    private static class Wraper extends SCRendererForPropertiesContainer<String, PropertiesContainer<String>> {

        public List<String> order = new ArrayList<>();
        public List<FirstLast> firstLast = new ArrayList<>();

        public Wraper(SCNewLineAndIndentationFormat format) {
            super(format);
        }

        @Override
        protected void writeBeforePropertiesIteration() {
            order.add(START);
        }

        @Override
        protected void writePropertyCode(String property) {
            order.add(property);
            firstLast.add(new FirstLast(property, isFirst(), isLast()));
        }

        @Override
        protected void writeAfterPropertiesIteration() {
            order.add(END);
        }
    }

    private Wraper wraper;

    @Before
    public void setUp() {
        when(bean.getProperties()).thenReturn(Collections.emptyList());
        wraper = new Wraper(SCNewLineAndIndentationFormat.unixWithSpaces(2));
    }

    @Test
    public void testRenderBeforeAfterMethods() throws Exception {
        wraper.render(bean);
        assertThat(wraper.order, equalTo(asList(START, END)));
    }

    @Test
    public void testRenderBeforeAfterWithPropertyMethods() throws Exception {
        when(bean.getProperties()).thenReturn(asList("one", "two"));
        wraper.render(bean);
        assertThat(wraper.order, equalTo(asList(START, "one", "two", END)));
    }

    @Test
    public void testRenderOneItemTest() throws Exception {
        when(bean.getProperties()).thenReturn(asList("one"));
        wraper.render(bean);
        assertThat(wraper.firstLast, equalTo(asList(new FirstLast("one", true, true))));
    }

    @Test
    public void testRenderTwoItemsTest() throws Exception {
        when(bean.getProperties()).thenReturn(asList("one", "two"));
        wraper.render(bean);
        assertThat(wraper.firstLast, equalTo(asList(
            new FirstLast("one", true, false), new FirstLast("two", false, true))));
    }

    @Test
    public void testRenderThreeItemsTest() throws Exception {
        when(bean.getProperties()).thenReturn(asList("one", "two", "three"));
        wraper.render(bean);
        assertThat(wraper.firstLast, equalTo(asList(
            new FirstLast("one", true, false),
            new FirstLast("two", false, false),
            new FirstLast("three", false, true))));
    }
}
