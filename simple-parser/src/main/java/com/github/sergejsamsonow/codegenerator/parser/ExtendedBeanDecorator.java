package com.github.sergejsamsonow.codegenerator.parser;

import java.util.Set;
import com.github.sergejsamsonow.codegenerator.api.ProducerAccess;

public class ExtendedBeanDecorator implements ProducerAccess<ParsedBean> {

    private Set<String> extended;
    private ProducerAccess<ParsedBean> producer;

    public ExtendedBeanDecorator(ProducerAccess<ParsedBean> producer, Set<String> extended) {
        if (producer == null) {
            throw new IllegalArgumentException("Producer object is null!");
        }
        this.producer = producer;
        this.extended = extended;
    }

    @Override
    public void newItem(ParsedBean parsed) {
        delegate(isInExtended(parsed) ? addBaseSuffix(parsed) : parsed);
    }

    private void delegate(ParsedBean parsed) {
        producer.newItem(parsed);
    }

    private SimpleParsedBean addBaseSuffix(ParsedBean parsed) {
        return new SimpleParsedBean(parsed.getNamespace(),
            parsed.getType() + "Base", parsed.getParentType(), parsed.getProperties());
    }

    private boolean isInExtended(ParsedBean parsed) {
        return extended.contains(parsed.getNamespace() + "." + parsed.getType());
    }
}
