package com.javatechie.crud.example.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //sifrelerei devredisi yapiyor
        // csrf().disable(). : without permission or role we can update and post
        //  antMatchers("/","index","css/*","/js/*"). // Do not ask password for these links
        http.csrf().disable().
                authorizeRequests().
                antMatchers("/","index","css/*","/js/*").
                permitAll().// Istekleri denetle
                anyRequest(). // tum istekleri
                authenticated().    // Sifreli olarak kullan
                and().
                formLogin().
                and().
                httpBasic();        // Basic kimlik denetimini kullan

    }
}
