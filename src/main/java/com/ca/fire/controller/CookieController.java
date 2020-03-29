package com.ca.fire.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CookieController {

    @GetMapping("/username")
    public String setCookie(HttpServletResponse response) {
        Cookie username = new Cookie("username", "");
        username.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(username);
        return "user";
    }

    @GetMapping("/user")
    public String readCookie(@CookieValue(value = "username", defaultValue = "tom") String username) {

        return "user";
    }

    @GetMapping("/cookies")
    public String readAllCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        return "user";
    }

}
