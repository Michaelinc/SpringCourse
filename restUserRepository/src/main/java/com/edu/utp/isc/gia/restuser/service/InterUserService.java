/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.service;

import com.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.util.List;

/**
 *
 * @author Michael
 */
public interface InterUserService {
    UserDto save(UserDto user) throws Throwable;
    UserDto getOne(Long id) throws Throwable;
    List<UserDto> getAll() throws Throwable;
    UserDto update(Long id, UserDto user) throws Throwable;
    Boolean delete(Long id) throws Throwable;
}
