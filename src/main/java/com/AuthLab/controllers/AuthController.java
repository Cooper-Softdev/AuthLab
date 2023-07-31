package com.AuthLab.controllers;

import com.AuthLab.models.HashUser;
import com.AuthLab.repos.HashUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    HashUserRepository hashUserRepository;

    @GetMapping("/login")
    public String getLogin() {
        return "login.html";
    }

    @PostMapping("/login")
    public RedirectView login(HttpServletRequest request, String hashName, String hashPass) {

        HashUser hashUser = hashUserRepository.findHashUserByHashName(hashName);

        if(hashUser == null) {
            return new RedirectView("/login");
        }

        if(!BCrypt.checkpw(hashPass, hashUser.getHashPass())) {
            return new RedirectView("/login");
        }

        HttpSession session = request.getSession();
        session.setAttribute("hashName", hashName);

        return new RedirectView("/hashy");
    }

    @PostMapping("/signup")
    public RedirectView signup(String hashName, String hashPass) {

        String hashedPassword = BCrypt.hashpw(hashPass, BCrypt.gensalt(10));

        HashUser newUser = new HashUser(hashName, hashedPassword);
        hashUserRepository.save(newUser);

        return new RedirectView("/login");
    }

    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new RedirectView("/login");
    }

}

