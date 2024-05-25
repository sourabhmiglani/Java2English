package com.example.JavaToEnglish;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/get")
    public String sayHi(@RequestParam String name){
        return "Hi " + name;
    }

}
