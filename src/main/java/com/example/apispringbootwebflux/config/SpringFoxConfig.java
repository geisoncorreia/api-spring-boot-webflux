package com.example.apispringbootwebflux.config;

import com.example.apispringbootwebflux.carro.web.CarRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

import java.sql.Date;
import java.sql.Timestamp;

@Configuration
@EnableSwagger2WebFlux
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.apispringbootwebflux.web"))
                .paths(PathSelectors.any())
                .build();
                /*return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, getDefaultResponseMessage())
                .globalResponseMessage(RequestMethod.POST, getDefaultResponseMessage())
                .globalResponseMessage(RequestMethod.PUT, getDefaultResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, getDefaultResponseMessage())
                .directModelSubstitute(Timestamp.class, Date.class)
//                .tags(new Tag("默认标签", "定义全局默认标签"),getTags())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.plumdo.form"))
                .paths(PathSelectors.any())
                .build();*/

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("Car api")
                .title("Car api")
                .version("1.0.0")
                .build();
    }
}
