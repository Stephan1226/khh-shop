package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        model.addAttribute("items", itemService.findAll());
        return "redirect:/list/page/1";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String writePost(@ModelAttribute Item item) {
        itemService.saveItem(item.getTitle(), item.getPrice());
        return "redirect:/list/page/1";
    }

    @GetMapping("/detail/{id}")
    String detail(Model model, @PathVariable Long id) {

        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()){
            Item item = result.get();
            model.addAttribute("item", item);
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }

    @GetMapping("/list/page/{abc}")
    String getListPage(Model model, @PathVariable Integer abc) {
        Page<Item> result = itemService.findPageBy(abc, 4);
        System.out.println(result.getTotalPages());
        System.out.println(result.hasNext());
        List<Item> items = result.get().toList();
        model.addAttribute("items", items);
        ArrayList<Integer> ids = new ArrayList<>();
        for(int i=1; i<=result.getTotalPages(); i++) {
            ids.add(i);
        }
        model.addAttribute("pageNumbers", ids);
        return "list.html";
    }
}
