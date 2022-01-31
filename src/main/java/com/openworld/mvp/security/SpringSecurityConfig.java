package com.openworld.mvp.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private Environment env;

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };
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
                }).and().authorizeRequests()
                .antMatchers(AUTH_WHITELIST)
                .permitAll()
                .mvcMatchers("/api/v1/**").hasAuthority("SCOPE_openworldscope")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt();

    }
}