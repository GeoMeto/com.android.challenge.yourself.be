package com.android.challenge.yourself.be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
    private final String AdminRole = "ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/categories").hasRole(AdminRole)
                .mvcMatchers("/challenges").hasRole(AdminRole)
                .mvcMatchers("/categories").hasRole(AdminRole)
                .mvcMatchers("/categories").hasRole(AdminRole)
//                .anyRequest().permitAll()
                .and().formLogin()
                .defaultSuccessUrl("/categories")
                .and().logout().invalidateHttpSession(true)
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("USER")
                .and()
                .withUser("admin").password("54321").roles("USER", "ADMIN")
                .and().passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
