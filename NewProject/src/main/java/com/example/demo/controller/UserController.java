package com.example.demo.controller;


import com.example.demo.model.Entity.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
//localhost:8080/
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> findAllUser(){
        return userService.findAllUser();
    }

    //may not need to declare params = "id"
    @DeleteMapping(value = "/delete", params = "id")
    public String deleteUser(@RequestParam Integer id){
        return userService.deleteUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody UserDTO inputUserDTO){
        return userService.addUser(inputUserDTO);
    }

    @GetMapping("/getDetail")
    public ResponseEntity<?> getDetail(@RequestParam Integer id){
        return userService.getDetail(id);
    }

    @GetMapping(value = "/search", params = "keyWord")
    public List<UserDTO> search(@RequestParam String keyWord){
        return userService.search(keyWord);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserDTO inputUserDTO, @RequestParam Integer id){
        return userService.update(inputUserDTO, id);
    }

}
