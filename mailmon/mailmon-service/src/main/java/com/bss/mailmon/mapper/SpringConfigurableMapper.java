package com.bss.mailmon.mapper;

import java.util.Map;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringConfigurableMapper extends ConfigurableMapper {

    private MapperFactory factory;

    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        factory = factoryBuilder.mapNulls(false).build();
    }

    @Override
    protected void configure(final MapperFactory factory) {
        this.factory = factory;
    }

    @Autowired
    public void setApplicationContext(final ApplicationContext applicationContext) {
        addAllSpringBeans(applicationContext);
    }

    private void addAllSpringBeans(final ApplicationContext applicationContext) {
        @SuppressWarnings("rawtypes")
        final Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        for (final Converter<?, ?> converter : converters.values()) {
            addConverter(converter);
        }

        @SuppressWarnings("rawtypes")
        final Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (final Mapper<?, ?> mapper : mappers.values()) {
            addMapper(mapper);
        }
    }

    public void addConverter(final Converter<?, ?> converter) {
        factory.getConverterFactory().registerConverter(converter);
    }

    public void addMapper(final Mapper<?, ?> mapper) {
        factory.classMap(mapper.getAType(), mapper.getBType()).byDefault().register();
    }
}