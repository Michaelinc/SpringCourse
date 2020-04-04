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
         user.setId(Consecutivo.generarConsecutivo((ArrayList<UserDto>) users));
         user.setUsername(user.getUsername().toLowerCase());
         users.add(user);
         return user;
     }
     
    @Override
     public List<UserDto> getAll() {
        return users;
    }

    @Override
    public UserDto getOne(Long id) {
        for(UserDto u : users){
            if(Objects.equals(u.getId(), id)){
                return u;
            }
        }
        return null;
    }

    @Override
    public UserDto update(Long id, UserDto user) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean delete(Long id) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
