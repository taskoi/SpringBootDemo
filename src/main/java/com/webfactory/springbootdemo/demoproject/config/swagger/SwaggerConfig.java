package com.webfactory.springbootdemo.demoproject.config.swagger;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import io.swagger.models.Path;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    Path get = new Path();

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.webfactory.springbootdemo.demoproject"))
                .paths(springBootActuatorJmxPaths())
                .build()
                .apiInfo(metaData())
                .securitySchemes(Lists.newArrayList(oauth()))
                .securityContexts(Lists.newArrayList(securityContext()))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, new ArrayList<>())
                ;
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Demo project\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("Ivan Tasevski", "https://ivan.tasevski.com", "ivan.tasevski@webfactory.mk"))
                .build();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
        registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/api/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private Predicate<String> postPaths() {
        return regex("/api.*");
    }

    private Predicate<String> springBootActuatorJmxPaths() {
        return regex("^/(?!env|restart|pause|resume|refresh).*$");
    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("http://localhost:8080"+"/oauth/authorize", "client", "secret" );
        TokenEndpoint tokenEndpoint = new TokenEndpoint("http://localhost:8080"+"/oauth/token", "token");
        grantTypes.add(new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint));
        return grantTypes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                . forPaths ( PathSelectors . ant ( "/api/**" )) //Configure which urls need to be certified for oauth2
                .build();
    }

    List<SecurityReference> defaultAuth() {
        return Lists.newArrayList(
                new SecurityReference("oauth2", scopes().toArray(new AuthorizationScope[0])));
    }

    private SecurityScheme oauth() {

        return new OAuthBuilder()
                .name("oauth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }

    private List<AuthorizationScope> scopes() {
        List<AuthorizationScope> list = new ArrayList();
        list.add(new AuthorizationScope("read", "Grants read access"));
        list.add(new AuthorizationScope("write", "Grants write access"));
        return list;
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("client", "secret", "realm",                "union", "access_token", ApiKeyVehicle.HEADER, "access_token", ",");

}


}
