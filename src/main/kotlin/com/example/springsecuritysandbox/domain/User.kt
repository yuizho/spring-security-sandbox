package com.example.springsecuritysandbox.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class User(
        private val username: String,
        private val password: String
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun isEnabled(): Boolean {
        return true;
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}