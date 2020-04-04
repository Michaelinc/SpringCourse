/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.service.impl;

import com.edu.utp.isc.gia.restuser.service.InterUserService;
import com.edu.utp.isc.gia.restuser.web.dto.Consecutivo;
import com.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 *
 * @author Michael
 */
@Service
public class DefaultUserService implements InterUserService {
    private List<UserDto> users = new ArrayList<>();

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
    }
}
