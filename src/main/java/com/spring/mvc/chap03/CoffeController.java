package com.spring.mvc.chap03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coffee")
public class CoffeController {
    @GetMapping("/order")
    public String order(){
        return "chap03/coffee-form";
    }
    @PostMapping("/result")
    public String result(String menu, int price, Model model){

        return "chap03/coffee-result";
    }
}
