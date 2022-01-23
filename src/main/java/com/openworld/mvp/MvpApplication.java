package com.openworld.mvp;

import com.openworld.mvp.bm.router.RouterBE;
import com.openworld.mvp.bm.router.RouterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableWebSecurity
@EnableSwagger2WebMvc
@SpringBootApplication
public class MvpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvpApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RouterRepository routerRepository) {
        return args -> {

            RouterBE routerBE = new RouterBE();
            routerBE.setMacAddress("00:00:00:00:00:00");

            RouterBE routerBE2 = new RouterBE();
            routerBE2.setMacAddress("00:00:00:00:00:01");
            routerRepository.save(routerBE);
            routerRepository.save(routerBE2);

        };
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.openworld.mvp.api"))
                .paths(regex("/api/v1.*")).build();
    }

}
