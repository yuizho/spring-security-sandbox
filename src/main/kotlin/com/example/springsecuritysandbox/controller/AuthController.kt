package com.example.springsecuritysandbox.controller

import com.example.springsecuritysandbox.domain.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController {

    @GetMapping("/loggedin")
    fun index(model: Model): String {
        // SecurityContextHolderはthread localにユーザの認証情報を保存している
        val authentication = SecurityContextHolder.getContext().authentication
        model.addAttribute("user", authentication.principal as User)
        return "auth/loggedin"
    }
}