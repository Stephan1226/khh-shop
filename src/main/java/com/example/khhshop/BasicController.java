package com.example.khhshop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final StoreService storeService;

    @GetMapping("/")
    @ResponseBody
    String hello() {
        return "안녕하세요";
    }

    @GetMapping("/about")
    @ResponseBody
    String about() {
        return "Java + Spring Boot로 만드는 쇼핑몰 서비스";
    }

    @GetMapping("/html")
    @ResponseBody
    String html() {
        return "<h4>html 적용이 되나?</h4>";
    }

    @GetMapping("/html1")
    String html1() {
        return "index.html";
        //스트링으로 반환했을 땐 정적 리소스 저장소인 static에서 찾기 때문에 templates의 파일은 불러올 수 없음.
    }

    @GetMapping("/date")
    @ResponseBody
    String date() {
        Date date = new Date();
        return date.toString();
    }

    @GetMapping("/store")
    @ResponseBody
    String store() {
        List<Store> stores = storeService.findAll();
        return stores.toString();
    }
}
