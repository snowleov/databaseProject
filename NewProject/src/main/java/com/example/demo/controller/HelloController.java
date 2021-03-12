package com.example.demo.controller;

import com.example.demo.model.UserTest;
import org.springframework.web.bind.annotation.*;

@RestController
//localhost:8080/
@RequestMapping("/")
public class HelloController {


    //test DoGET, input: "name" and "age"
    @GetMapping("/testGet")
    public String testGet(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") Integer age
    ){
        return String.format("Name is %s. Age is %s", name, age);
    }

    //test DoPOST, input is Json as an object => mapping to UserTest class(name and age)
    @PostMapping("/testPost")
    public UserTest testPost(
            @RequestBody UserTest user
    ){
        return user;
    }

}
