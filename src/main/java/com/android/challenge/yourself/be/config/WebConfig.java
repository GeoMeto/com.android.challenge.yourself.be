package com.android.challenge.yourself.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/challenges").setViewName("challenges");
        registry.addViewController("/sharings").setViewName("sharings");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/login").setViewName("login");
    }
}
