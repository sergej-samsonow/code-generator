package com.github.sergejsamsonow.codegenerator.pojo;

import static org.apache.commons.lang3.StringUtils.capitalize;
import java.util.ArrayList;
import java.util.List;
import com.github.sergejsamsonow.codegenerator.api.PartialRendererProducer;
import com.github.sergejsamsonow.codegenerator.api.Renderer;
import com.github.sergejsamsonow.codegenerator.api.WriterAccess;
import com.github.sergejsamsonow.codegenerator.parser.ParsedBean;
import com.github.sergejsamsonow.codegenerator.parser.ParsedProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.PojoProperty;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoBean;
import com.github.sergejsamsonow.codegenerator.pojo.model.SimplePojoProperty;

public class PojoProducer extends PartialRendererProducer<PojoBean, ParsedBean> {

    private WriterAccess writer;

    @SafeVarargs
    public PojoProducer(WriterAccess writer, Renderer<PojoBean>... renderers) {
        super(renderers);
        this.writer = writer;
    }

    @Override
    protected PojoBean transform(ParsedBean parsed) {
        List<PojoProperty> properties = new ArrayList<>();
        for (ParsedProperty property : parsed.getProperties())
            properties.add(new SimplePojoProperty(property.getName(), property.getType()));
        return new SimplePojoBean(parsed.getNamespace(), parsed.getType(), properties);
    }

    @Override
    protected void write(PojoBean data, String code) {
        String namespace = data.getPackageName();
        String className = data.getClassName();
        String subPath = namespace.replace(".", "/");
        writer.write(String.format("%s/%s.java", subPath, capitalize(className)), code);
    }

}
