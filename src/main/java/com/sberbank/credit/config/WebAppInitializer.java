package com.sberbank.credit.config;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //аналог <import  из xml.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                AppConfig.class,
//                SecurityConfig.class
        };
    }

    //сервлеты
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    //маппинг сервлетов
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{
                //фильтр применяется ко всем сервлетам
                //форсирует кодирвоку в UTF-8
                new CharacterEncodingFilter("UTF-8", true)
        };
    }
}
