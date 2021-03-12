package com.example.demo.service;

import com.example.demo.model.Entity.User;
import com.example.demo.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllUser();

    String deleteUser(Integer id);

    ResponseEntity<?> addUser(UserDTO inputUserDTO);

    ResponseEntity<?> getDetail(Integer id);

    List<UserDTO> search(String keyWord);

    ResponseEntity<?> update(UserDTO inputUserDTO, Integer id);

}
