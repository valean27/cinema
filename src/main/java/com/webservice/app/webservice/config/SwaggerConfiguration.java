package com.webservice.app.webservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket movieApi(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.webservice.app.webservice"))
                .paths(regex("/rest.*"))
                .build()
                .pathMapping("/docs")
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo info = new ApiInfo("API Description Demo", "API Description Demo","1.0","Terms Of Service", "Valean Stefan", "Apache License Version 2.0", "http://www.apache.org/license.html");
        return info;
    }

}
