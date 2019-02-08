package com.myproject.myproject;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class controller {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    @PostMapping("/todo")
    public void addTodo(@RequestParam Map map){

    }
}