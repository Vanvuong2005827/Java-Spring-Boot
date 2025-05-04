package org.example.btvnbuoi7.domain.base;

import org.springframework.core.annotation.AliasFor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RestController
@RequestMapping
public @interface RestApiV1 {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
