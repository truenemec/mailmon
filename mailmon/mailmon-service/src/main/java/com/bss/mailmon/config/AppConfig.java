package com.bss.mailmon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bss.mailmon.ProfilingHandlerBeanPostProcessor;

@Configuration
@EnableWebMvc
public class AppConfig {
    @Bean
    public static ProfilingHandlerBeanPostProcessor profilingHandlerBeanPostProcessor() {
        return new ProfilingHandlerBeanPostProcessor();
    }
}
