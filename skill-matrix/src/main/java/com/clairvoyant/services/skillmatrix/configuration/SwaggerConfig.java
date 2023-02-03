package com.clairvoyant.services.skillmatrix.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"!prod"})
public class SwaggerConfig {
    public static final String SKILLS_RATING = "Skills Rating";
    public static final String QUALIFICATION_STATUS = "Qualification Status";
    public static final String DESIGNATION = "Designation";

    @Bean
    public Docket apiDocket() {
        ParameterBuilder parameter = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        params.add(parameter.name("Authorization").modelRef(new ModelRef("string")).parameterType("header")
            .required(false).build());
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.clairvoyant.clarise")).paths(PathSelectors.any()).build()
            .tags(new Tag(SKILLS_RATING, "The Skills Rating Apis"),
                new Tag(QUALIFICATION_STATUS, "The Qualification Status Apis"),
                new Tag(DESIGNATION, "The Designation Apis"))
            .pathMapping("").globalOperationParameters(params);
    }
}