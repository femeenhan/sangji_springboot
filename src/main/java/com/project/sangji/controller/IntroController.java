package com.project.sangji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/intro")
public class IntroController {
    @GetMapping("/greeting")
    public void greeting() {
    }
    @GetMapping("/person")
    public void person() {
    }
    @GetMapping("/office")
    public void office() {
    }
    @GetMapping("/location")
    public void location() {
    }
}
