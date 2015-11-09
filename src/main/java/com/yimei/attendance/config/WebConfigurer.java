package com.yimei.attendance.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimei.attendance.extension.mapper.Java8TimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@EnableAutoConfiguration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Autowired
    private Environment env;

    @Autowired
    protected ObjectMapper objectMapper;

//    @Autowired
//    private CurrentUserMethodArgumentHandler currentUserMethodArgumentHandler;


    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

    }


    @PostConstruct
    public void setThings() {
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.registerModule(new Java8TimeModule());
    }
}
