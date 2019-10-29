package com.pareto.spock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class Application extends WebSecurityConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // TODO: (ID) Implementar a segurança
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // TODO: (ID) Revisar
                .anonymous().and()
                .authorizeRequests().antMatchers("/**").permitAll()
                .anyRequest().authenticated();
    }
}
