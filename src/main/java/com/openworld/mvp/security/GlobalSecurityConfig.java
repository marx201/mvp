package com.openworld.mvp.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    private Environment env;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String localOriginFrontend = env.getProperty("local.origin.frontend");
        String remoteOriginFrontend = env.getProperty("remote.origin.frontend");

        List<String> origins = new ArrayList<>();

        if ((localOriginFrontend != null
                && !localOriginFrontend.isEmpty())
                &&
                (remoteOriginFrontend != null && !remoteOriginFrontend.isEmpty())
        ) {
            origins.add(remoteOriginFrontend);
            origins.add(localOriginFrontend);
        }

        http
                .cors().configurationSource(request -> {
                    CorsConfiguration cors = new CorsConfiguration();
                    cors.setAllowedOriginPatterns(origins);
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    cors.setAllowedHeaders(List.of("*"));
                    cors.setAllowCredentials(true);
                    return cors;
                }).and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .mvcMatchers("/api/v1/**").hasAuthority("SCOPE_openworldscope")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt();

    }

    @Autowired
    void configureGlobal(AuthenticationManagerBuilder auth) {
        KeycloakAuthenticationProvider provider = keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(authoritiesMapper());
        auth.authenticationProvider(provider);
    }

    GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setPrefix("ROLE_"); // Spring Security adds a prefix to the authority/role names (we use the default here)
        mapper.setConvertToUpperCase(true); // convert names to uppercase
        mapper.setDefaultAuthority("ROLE_ANONYMOUS"); // set a default authority
        return mapper;
    }
}