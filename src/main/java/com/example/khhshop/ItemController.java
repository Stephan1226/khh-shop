package com.example.khhshop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("name", "바지");
        model.addAttribute("price", "15000");
        model.addAttribute("stock", "100");
        return "list.html";
    }
}
