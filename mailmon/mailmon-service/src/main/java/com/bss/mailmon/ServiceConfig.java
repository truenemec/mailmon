package com.bss.mailmon;

import static java.nio.charset.Charset.forName;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import com.google.common.collect.Lists;

@Configuration
@EnableWebMvc
public class ServiceConfig {
    Logger LOGGER = LoggerFactory.getLogger(ServiceConfig.class);

    @PostConstruct
    public void init() {
        LOGGER.info("Initialization servece layer");
    }

    @Bean
    public AnnotationMethodHandlerAdapter annotationMethodHandlerAdapter() {
        AnnotationMethodHandlerAdapter adapter = new AnnotationMethodHandlerAdapter();

        MappingJackson2HttpMessageConverter json = new MappingJackson2HttpMessageConverter();
        json.setSupportedMediaTypes(Lists.newArrayList(new MediaType("application", "json", forName("utf-8"))));

        HttpMessageConverter<?>[] converters = { json };

        adapter.setMessageConverters(converters);
        return adapter;
    }

}
