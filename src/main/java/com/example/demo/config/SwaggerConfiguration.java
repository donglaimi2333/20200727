package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

/**
 * swagger config
 * @author dongyinuo
 * @date 2019-02-19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
private String swaggerSwicth;
    @Bean
    @Profile({"dev"})
    public Docket createRestApi(ServletContext servletContext) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathProvider(new RelativePathProvider(servletContext){
                	@Override
                	public String getApplicationBasePath(){
                		return "/api" + super.getApplicationBasePath();
                	}
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edianzu.document.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("文件共享系统接口文档")
                .version("1.0")
                .build();
    }

}
