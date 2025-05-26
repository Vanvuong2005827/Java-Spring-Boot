package org.example.btvnbuoi9.commons;

import org.example.btvnbuoi9.base.RestApiV1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(
                "/api/v1",
                HandlerTypePredicate.forAnnotation(RestApiV1.class)
        );
    }
}
