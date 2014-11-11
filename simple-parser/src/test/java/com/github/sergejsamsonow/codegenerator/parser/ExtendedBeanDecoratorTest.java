package com.github.sergejsamsonow.codegenerator.parser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedBean;

@RunWith(MockitoJUnitRunner.class)
public class ExtendedBeanDecoratorTest {

    private static final String NAMESPACE = "frontend";
    private static final String TYPE = "Form";
    private static final String PARENT = "Parent";
    private static final String EXTENDED = "frontend.Form";

    @Mock
    private Set<String> extended;

    @Mock
    private ParsedBean parsed;

    @Mock
    private ProducerAccess<ParsedBean> producer;

    private ExtendedBeanDecorator decorator;

    @Before
    public void setUp() {
        when(parsed.getProperties()).thenReturn(Collections.emptyList());
        when(parsed.getNamespace()).thenReturn(NAMESPACE);
        when(parsed.getType()).thenReturn(TYPE);
        when(parsed.getParentType()).thenReturn(PARENT);

        decorator = new ExtendedBeanDecorator(producer, extended);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExtendedBeanDecoratorNullProducer() throws Exception {
        new ExtendedBeanDecorator(null, null);
    }

    @Test
    public void testExtendedBeanDecoratorNullExtended() throws Exception {
        decorator = new ExtendedBeanDecorator(producer, null);
        decorator.newItem(parsed);
    }

    @Test
    public void testNewItemDelegate() throws Exception {
        when(extended.contains(EXTENDED)).thenReturn(false);
        decorator.newItem(parsed);
        verify(extended).contains(EXTENDED);
        verify(producer).newItem(parsed);
    }

    @Test
    public void testNewItemRenameIfInExtended() throws Exception {
        when(extended.contains(EXTENDED)).thenReturn(true);
        doAnswer(invocation -> {
            ParsedBean bean = (ParsedBean) invocation.getArguments()[0];
            assertThat(bean.getNamespace(), equalTo(NAMESPACE));
            assertThat(bean.getType(), equalTo(TYPE + "Base"));
            assertThat(bean.getParentType(), equalTo(PARENT));
            assertThat(bean.getProperties(), equalTo(Collections.emptyList()));
            return null;
        }).when(producer).newItem(any());
        decorator.newItem(parsed);
        verify(producer).newItem(any());
    }

}
