package com.example.springsecuritysandbox.application

import com.example.springsecuritysandbox.domain.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByName(username) ?: throw UsernameNotFoundException("there is no $username user")
    }
}