package com.example.demo.config;

import com.example.demo.cityloader.CityLoaderFromFile;
import com.example.demo.cityloader.ICityLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class ApplicationConfiguration {
    @Bean
    public ICityLoader getCityLoader() {
        return new CityLoaderFromFile();
    }

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select().paths(regex(
                "/api/city-connection.*")).build();
    }

    @SuppressWarnings("deprecation")
    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Sample Code").description("API reference guide for developers")
                .termsOfServiceUrl("http://localhost:8080/").version("1.0").build();
    }
}


