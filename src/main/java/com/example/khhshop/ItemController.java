package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
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
    String writePost(@ModelAttribute Item item, Authentication auth) {
        if(auth.isAuthenticated()) {
            itemService.saveItem(item.getTitle(), item.getPrice(), auth.getName());
        } else {
            itemService.saveItem(item.getTitle(), item.getPrice());
        }
        return "redirect:/list/page/1";
    }

    @PostMapping("/save")
    String savePost(@ModelAttribute Item item) {
        itemService.updateItem(item.getTitle(), item.getPrice(), item.getId());
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

    @PostMapping("/search")
    String postSearch(@RequestParam String searchText, Model model) {
        // Item테이블에서 searchText가 들어있는거 찾아서 가져와주세요
        List<Item> items = itemService.searchItems(searchText);
        model.addAttribute("items", items);
        return "list.html";
    }

    @GetMapping("/edit/{id}")
    String edit(Model model, @PathVariable Long id) {
        Optional<Item> result = itemService.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }

    @DeleteMapping("/delete")
    String delete(@RequestParam Integer id) {
        itemService.deleteItem(id);
        return "redirect:/list";
    }
}
