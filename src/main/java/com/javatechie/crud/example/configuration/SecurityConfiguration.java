package com.javatechie.crud.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    // Dependency injection
    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder){
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //sifrelerei devredisi yapiyor
        // csrf().disable(). : without permission or role we can update and post
        //  antMatchers("/","index","css/*","/js/*"). // Do not ask password for these links
        http.csrf().disable().
                authorizeRequests().
                antMatchers("/","index","css/*","/js/*").
                // Role based authentication
                permitAll().// Istekleri denetle
                anyRequest(). // tum istekleri
                authenticated().    // Sifreli olarak kullan
                and().
                formLogin().
                and().
                httpBasic();        // Basic kimlik denetimini kullan

    }
// Sifreye encoder eklenmesi gerekiyor
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // You can define user manually
        //UserDetails user1 = User.builder().username("user").password(passwordEncoder.encode("1234")).roles("USER").build();
        UserDetails user1 = User.builder().username("user").password(passwordEncoder.encode("1234"))
                //.roles("USER").build();
                .authorities(KisiRole.USER.name()).build();// Olusturulan rolun kullanilmasi

        UserDetails admin1 = User.builder().username("admin").password(passwordEncoder.encode("5678"))
                //.roles("ADMIN").build();
                .authorities(KisiRole.ADMIN.name()).build();

        return new InMemoryUserDetailsManager(user1,admin1);
    }
}
