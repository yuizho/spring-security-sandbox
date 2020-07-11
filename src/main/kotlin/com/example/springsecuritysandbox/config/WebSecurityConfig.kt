package com.example.springsecuritysandbox.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder

@Configuration
class WebSecurityConfig(
        private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/loggedin")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                // FIXME: encode password
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }
}