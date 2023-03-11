package com.future.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexApiController {
    @PostMapping("/show")
    public String index(){
        return "AAAA";
    }
}
