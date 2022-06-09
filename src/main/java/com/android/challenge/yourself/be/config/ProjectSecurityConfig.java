package com.android.challenge.yourself.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String AdminRole = "ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //                .anyRequest().permitAll()
                .authorizeRequests()
                .mvcMatchers("/categories").hasRole(AdminRole)
                .mvcMatchers("/create-category").hasRole(AdminRole)
                .mvcMatchers("/challenges/**").hasRole(AdminRole)
                .mvcMatchers("/users/**").hasRole(AdminRole)
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/categories").failureUrl("/login?error=true").permitAll()
                .and().logout().invalidateHttpSession(true).permitAll()
                .and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
