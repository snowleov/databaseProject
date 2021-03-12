package com.example.demo.service.impl;

import com.example.demo.model.Entity.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    //find all user in table and filter it through modelMapper
    @Override
    public List<UserDTO> findAllUser(){
        List<User> users;
        users = userRepository.findAll(Sort.by("fullname"));

        //create and configure modelMapper object
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        //create list of userDTOS for mapping
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user:users){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public String deleteUser(Integer id){
        try{
            User optionalUser = userRepository.findById(id).orElse(null);
            if (optionalUser!=null){
                userRepository.deleteById(id);
                return "Deleted successfully";
            }
            else{
                return "Id: " + id + " not found!";
            }
        }
        catch (Exception e){
            return e.toString();
        }
    }

    @Override
    public ResponseEntity<?> addUser(UserDTO inputUserDTO){
        try{

            //create modelMapper object
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            //map to user and add Job description
            User user = modelMapper.map(inputUserDTO, User.class);
            //user.setJob("employee");

            userRepository.save(user);

            return ResponseEntity.ok(user);

        }
        catch (Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }

    @Override
    public ResponseEntity<?> getDetail(Integer id){
        try{
            User optionalUser = userRepository.findById(id).orElse(null);
            if(optionalUser != null){
                return ResponseEntity.ok(optionalUser);
            }
            else{
                return ResponseEntity.ok("id: " + id + " not found");
            }
        }
        catch(Exception e){
            return ResponseEntity.ok(e.toString());
        }
    }

    //search by full name
    @Override
    public List<UserDTO> search(String keyWord){

        List<User> users;
        users = userRepository.findUserByFullnameLike("%"+keyWord+"%");

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user:users){
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public ResponseEntity<?> update(UserDTO inputUserDTO, Integer id){

        try {
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

            User oldUser = userRepository.findById(id).orElse(null);

            if(oldUser!=null){

                User user = modelMapper.map(inputUserDTO, User.class);
                user.setId(id);
                userRepository.save(user);
                return ResponseEntity.ok(user);

            }
            else{
                return ResponseEntity.ok("id not found");
            }
        }
        catch(Exception e){
            return ResponseEntity.ok(e.toString());
        }

    }

}
