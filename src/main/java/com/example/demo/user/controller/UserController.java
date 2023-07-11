package com.example.demo.user.controller;

import com.example.demo.user.model.UserModel;
import com.example.demo.user.repository.GenericRepository;
import com.example.demo.user.repository.UserRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <List<UserModel>> getAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.findAll());
    }

    @RequestMapping(value = "/getOneById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <UserModel> getOne(@RequestParam(value = "id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.findById(id).orElseThrow());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <UserModel> create(@RequestBody UserModel userModel){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.save(userModel));
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <UserModel> update(@RequestBody UserModel userModel,
                                             @RequestParam(value = "id") Long id){
        userModel.setId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userRepository.save(userModel));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") Long id){
        userRepository.deleteById(id);
    }
}
