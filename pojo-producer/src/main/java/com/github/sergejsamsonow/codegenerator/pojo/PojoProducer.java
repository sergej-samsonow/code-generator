package com.github.sergejsamsonow.codegenerator.pojo;

import static org.apache.commons.lang3.StringUtils.capitalize;
import java.util.ArrayList;
import java.util.List;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedBean;
import com.github.sergejsamsonow.codegenerator.api.parser.model.ParsedProperty;
import com.github.sergejsamsonow.codegenerator.api.producer.PartialRendererBasedProducer;
import com.github.sergejsamsonow.codegenerator.api.producer.Renderer;
import com.github.sergejsamsonow.codegenerator.api.producer.sc.SCNewLineAndIndentationFormat;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.ClassFooter;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.ClassHeader;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.Constructor;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.Fields;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.Getter;
import com.github.sergejsamsonow.codegenerator.pojo.renderer.Setter;

public class PojoProducer extends PartialRendererBasedProducer<PojoBean, ParsedBean> {

    public PojoProducer(WriterAccess writer, SCNewLineAndIndentationFormat format) {
        this(writer,
            new ClassHeader(format),
            new Fields(format),
            new Constructor(format),
            new Setter(format),
            new Getter(format),
            new ClassFooter<>(format));
    }

    @SafeVarargs
    public PojoProducer(WriterAccess writer, Renderer<PojoBean>... renderers) {
        super(writer, renderers);
    }

    @Override
    protected PojoBean transform(ParsedBean parsed) {
        List<PojoProperty> properties = new ArrayList<>();
        for (ParsedProperty property : parsed.getProperties()) {
            properties.add(new SimplePojoProperty(property.getName(), property.getType()));
        }
        return new SimplePojoBean(parsed.getNamespace(), parsed.getType(), parsed.getParentType(), properties);
    }

    @Override
    protected String subpath(PojoBean data) {
        String namespace = data.getPackageName();
        String className = data.getClassName();
        String subPath = namespace.replace(".", "/");
        return String.format("%s/%s.java", subPath, capitalize(className));
    }

}
