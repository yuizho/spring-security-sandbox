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
                .anyRequest().access("isAuthenticated() and hasAuthority('ADMIN')")

                .and()
                .formLogin()
                .defaultSuccessUrl("/loggedin")

                // remember me機能
                // よりセキュアな機能の場合はmatcherにfullyAuthenticatedを指定して
                // remember状態でもパスワードの入力を強制したりできる
                // https://qiita.com/opengl-8080/items/7c34053c74448d39e8f5#remember-me-%E3%81%AB%E3%82%88%E3%82%8B%E3%83%AD%E3%82%B0%E3%82%A4%E3%83%B3%E3%81%AE%E5%A0%B4%E5%90%88%E3%81%AF%E9%87%8D%E8%A6%81%E3%81%AA%E5%87%A6%E7%90%86%E3%81%AE%E5%AE%9F%E8%A1%8C%E3%82%92%E8%A8%B1%E5%8F%AF%E3%81%97%E3%81%AA%E3%81%84
                .and()
                .rememberMe().key("uniqueAndSecret")

                .and()
                .exceptionHandling().accessDeniedPage("/access_denied.html")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                // FIXME: encode password
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }
}