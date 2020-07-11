package com.example.springsecuritysandbox.domain

interface UserRepository {
    fun findByName(name: String): User?
}