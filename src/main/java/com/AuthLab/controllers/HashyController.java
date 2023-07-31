package com.AuthLab.controllers;
import com.AuthLab.models.Hashy;
import com.AuthLab.models.HashUser;
import com.AuthLab.repos.HashyRepository;
import com.AuthLab.repos.HashUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HashyController {
    @Autowired
    HashUserRepository hashUserRepository;
    @Autowired
    HashyRepository hashyRepository;

    @GetMapping("/hashy")
    public String getHashies(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();

        Object hashNameAttribute = session.getAttribute("hashName");

        if(hashNameAttribute == null) {
            return "redirect:/";
        }

        String hashName = hashNameAttribute.toString();
        HashUser currentHashUser = hashUserRepository.findHashUserByHashName(hashName);

        model.addAttribute("currentHashUser", currentHashUser);

        return "hashy.html";
    }

    @PostMapping("/post-hashy")
    public RedirectView addHashyFromForm(HttpServletRequest request, String hashName, String hashContent){
        HttpSession session = request.getSession();

        Object hashNameAttribute = session.getAttribute("hashName");

        if(hashNameAttribute == null) {
            return new RedirectView("/hashy");
        }

        String sessionHashName = hashNameAttribute.toString();
        HashUser currentHashUser = hashUserRepository.findHashUserByHashName(sessionHashName);

        Hashy newHashy = new Hashy(hashName, hashContent, currentHashUser);
        hashyRepository.save(newHashy);

        return new RedirectView("/hashy");
    }

}

