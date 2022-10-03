package com.community.dev.web;

import com.community.dev.config.auth.SessionUser;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    private final HttpSession httpSession;

    public ViewController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/social")
    public String socialSuccess(Model model,
        @RequestParam(value = "provider", required = false) String provider,
        @RequestParam(value = "oauthId", required = false) String oauthId
    ) {

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }
        model.addAttribute("provider", provider);
        model.addAttribute("oauthId", oauthId);
        return "index";
    }
}