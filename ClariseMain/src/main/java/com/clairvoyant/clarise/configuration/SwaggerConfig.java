package com.clairvoyant.clarise.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile({"!prod"})
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        ParameterBuilder parameter = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        params.add(parameter.name("Authorization").modelRef(new ModelRef("string")).parameterType("header").required(false).build());
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clairvoyant.clarise")).paths(PathSelectors.any()).build().pathMapping("").globalOperationParameters(params);
    }
}