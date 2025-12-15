package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MyUserDetailsService myUserDetailsService;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if(auth.isAuthenticated()) {
            return "redirect:/list";
        }
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(
            String username,
            String password,
            String displayName
    ) {
        memberService.register(username, password, displayName);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        if(auth!=null) {
            CustomUser user = (CustomUser) auth.getPrincipal();
            System.out.println(user.displayName);
            System.out.println(user.id);
            return "mypage";
        }
        return "redirect:/list";
    }

    @PostMapping("/login")
    @ResponseBody
    public UserDetails login(
            String username,
            String password,
            String displayName
    ) {
        return myUserDetailsService.loadUserByUsername(username);
    }
}
