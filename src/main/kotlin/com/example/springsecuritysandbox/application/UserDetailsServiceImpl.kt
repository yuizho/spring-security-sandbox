package com.example.springsecuritysandbox.application

import com.example.springsecuritysandbox.domain.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl: UserDetailsService {
    // it's just for test (not thread safe)
    private val map = mapOf<String, UserDetails>(
            "yuizho" to UserDetailsImpl("yuizho", BCryptPasswordEncoder().encode("password"))
    )
    override fun loadUserByUsername(username: String): UserDetails {
        return map[username] ?: throw UsernameNotFoundException("there is no $username user")
    }
}