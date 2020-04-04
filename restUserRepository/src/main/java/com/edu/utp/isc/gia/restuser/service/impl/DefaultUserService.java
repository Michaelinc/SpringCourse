/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.service.impl;

import com.edu.utp.isc.gia.restuser.data.entity.User;
import com.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import com.edu.utp.isc.gia.restuser.service.InterUserService;
import com.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michael
 */
@Service
public class DefaultUserService implements InterUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public DefaultUserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    
    @Override
    public UserDto save(UserDto user) throws Throwable {
        if (user == null) {
            throw new InvalidParameterException("Parametro invalido!");
        }
        User usr;
        user.setUsername(user.getUsername().toLowerCase());
        usr = userRepository.save(modelMapper.map(user, User.class));
        if (usr != null) {
            return modelMapper.map(usr, UserDto.class);
        }
        return null;
    }

    @Override
    public UserDto getOne(Long id) throws Throwable {
        Optional<User> usr = userRepository.findById(id);
        if (usr.isPresent()) {
            return modelMapper.map(usr.get(), UserDto.class);
        }
        return null;    
    }

    @Override
    public List<UserDto> getAll() throws Throwable {
        List<User> lista = (List<User>) userRepository.findAll();
        List<UserDto> users = new ArrayList<>();

        if (!lista.isEmpty() || lista != null) {
            for (User u : lista) {
                users.add(modelMapper.map(u, UserDto.class));
            }
            return users;
        }
        return null;
    }

    @Override
    public UserDto update(Long id, UserDto user) throws Throwable {
        if (userRepository.existsById(id)) {
            user.setId(id);
            User u = userRepository.save(modelMapper.map(user, User.class));
            if (u != null) {
                return modelMapper.map(u, UserDto.class);
            }
            throw new SQLException("No se puedo guardar");
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) throws Throwable {
        if (id == null) {
            throw new InvalidParameterException("Parametro invalido!");
        } else {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
    }    
    /*private List<UserDto> users = new ArrayList<>();

    @Override
    public UserDto save(UserDto user) throws Throwable {
        if(user == null){
            throw new Exception("Usuario Invalido");
        }
         user.setId(Consecutivo.generarConsecutivo((ArrayList<UserDto>) users));
         user.setUsername(user.getUsername().toLowerCase());
         users.add(user);
         return user;
     }
     
    @Override
     public List<UserDto> getAll() throws Throwable{
        return users;
    }

    @Override
    public UserDto getOne(Long id) throws Throwable{
         if (id == null) {
            throw new Exception("Identificador Invalido");
        }
        for(UserDto u : users){
            if(Objects.equals(u.getId(), id)){
                return u;
            }
        }
         return null;   

    }

    @Override
    public UserDto update(Long id, UserDto user) throws Throwable {
        if (id == null) {
            throw new Exception("Identificador Invalido");
        }else if( user == null){
            throw new Exception("Usuario Invalido");
        }
        UserDto us = getOne(id);
        if (us != null) {
            user.setId(id);
            users.set(users.indexOf(us), user);
            return getOne(id);
        }
        return null;
    }

    @Override
    public Boolean delete(Long id) throws Throwable {
        if (id == null) {
            throw new Exception("Identificador nulo");
        }
        Boolean remove = users.remove(getOne(id));
        return remove;
    }*/
    
}
