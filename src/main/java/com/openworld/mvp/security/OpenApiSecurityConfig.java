package com.openworld.mvp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.OAuth2SchemeBuilder;
import springfox.documentation.service.*;

import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;

@Configuration
@EnableSwagger2
public class OpenApiSecurityConfig implements WebMvcConfigurer {

    @Value("${keycloak.auth-server-url}")
    String authServerUrl;
    @Value("${keycloak.realm}")
    String realm;
    @Value("${keycloak.resource}")
    private String clientId;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Autowired
    void addSecurity(Docket docket) {
        docket
                .securitySchemes(Collections.singletonList(oAuth()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private SecurityScheme oAuth() {
        return new OAuth("petstore_auth", List.of(new AuthorizationScope("openworldscope", "openworldscope")),
                List.<GrantType>of(new ImplicitGrant(new LoginEndpoint("http://localhost:8080/auth/realms/openworld/protocol/openid-connect/auth"), "tokenName")));

    }


    private OAuth2Scheme authenticationScheme() {
        return new OAuth2SchemeBuilder("implicit")
                .name("my_oAuth_security_schema")
                .authorizationUrl(authServerUrl + "/realms/" + realm)
                .scopes(authorizationScopes())
                .build();
    }

    private List<AuthorizationScope> authorizationScopes() {
        return asList(
                new AuthorizationScope("read_access", "read data"),
                new AuthorizationScope("write_access", "modify data")
        );
    }

    private SecurityContext securityContext() {
        return SecurityContext.
                builder().
                securityReferences(readAccessAuth())
                .operationSelector(operationContext -> HttpMethod.GET.equals(operationContext.httpMethod()))
                .build();
    }

    private List<SecurityReference> readAccessAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScopes().get(0)};
        return Collections.singletonList(
                new SecurityReference("my_oAuth_security_schema", authorizationScopes)
        );
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .realm(realm)
                .appName(clientId)
                .scopeSeparator(",")
                .additionalQueryStringParams(null)
                .useBasicAuthenticationWithAccessCodeGrant(false)
                .build();
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/csrfAttacker.html");
    }
}
