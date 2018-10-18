package com.icloud.m.dimas.software.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityApplication extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthJdbcProvider authProvider;

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .and().logout().permitAll();
    }
}
