package com.example.springsecuritysandbox.infra

import com.example.springsecuritysandbox.domain.User
import com.example.springsecuritysandbox.domain.UserRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class JdbcUserRepository(
        private val jdbcOperations: NamedParameterJdbcOperations
) : UserRepository {
    override fun findByName(name: String): User? {
        return jdbcOperations.queryForObject("""
            select name, password from user where name = :name
        """.trimIndent(),
                mapOf<String, Any>("name" to name),
                { rs, _ ->
                    User(rs.getString("name"), rs.getString("password"))
                }
        )
    }
}