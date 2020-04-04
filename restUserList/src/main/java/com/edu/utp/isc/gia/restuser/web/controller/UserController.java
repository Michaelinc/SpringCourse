/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.web.controller;

import com.edu.utp.isc.gia.restuser.service.impl.DefaultUserService;
import com.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michael
 */
@RestController
@RequestMapping("user")
public class UserController {
    
    private DefaultUserService userService;

    public UserController(DefaultUserService userService) {
        this.userService = userService;
    }
    
    @PostMapping() // POST http://localhost:8080/user
    public ResponseEntity<?> save(@RequestBody UserDto user) throws Throwable{
        UserDto usr = userService.save(user);
        if(usr == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usr);
    }
    
    @PostMapping("/{user}/rol") // POST http://localhost:8080/user/1/rol
    public UserDto saveUserRol(@PathVariable("user")String id, UserDto user){
        return null;
    }
    
    @GetMapping()
    public ResponseEntity<?> listAll() throws Throwable{
        List<UserDto> lista = userService.getAll();
         if(lista == null || lista.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(lista);

    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") Long id) throws Throwable{
        UserDto usr = userService.getOne(id);
         if(usr == null){
            return ResponseEntity.status(HttpStatus. NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(usr);

    }

}
