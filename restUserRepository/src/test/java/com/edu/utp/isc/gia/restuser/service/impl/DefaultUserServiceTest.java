/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.service.impl;

import com.edu.utp.isc.gia.restuser.data.entity.User;
import com.edu.utp.isc.gia.restuser.data.repository.UserRepository;
import com.edu.utp.isc.gia.restuser.web.dto.UserDto;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Michael
 */
public class DefaultUserServiceTest {
    
    public DefaultUserServiceTest() {
    }

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        modelMapper = new ModelMapper();
    }

    @Test
    public void testSaveWhen_allOk_resullOk() throws Throwable {
        System.out.println("save");
        User resulted = new User(1L, "michael", "123", "Michael Rendon", "email.com");
        when(userRepository.save(any(User.class))).thenReturn(resulted);

        //Input
        UserDto user = new UserDto(null, "MICHAEL", "123", "Michael Rendon", "email.com");

        //Target
        DefaultUserService instance = new DefaultUserService(userRepository, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "michael", "123", "Michael Rendon", "email.com");

        //Test
        UserDto result = instance.save(user);

        //Validation
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
    }
    
    @Test
    public void testSaveWhen_userNull_resultException() throws Throwable {
        System.out.println("saveException");

        //Input
        UserDto user = null;

        //Target
        DefaultUserService instance = new DefaultUserService(userRepository, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "michael", "123", "Michael Rendon", "email.com");

        //Test
        InvalidParameterException assertThrows = Assertions.assertThrows(InvalidParameterException.class, () -> {
            instance.save(user);
        });
         assertEquals(assertThrows.getMessage(),"Parametro invalido!");
    }
    

    @Test
    public void testGetOne_when_allOk_resultOk() throws Exception, Throwable {
        System.out.println("getOne");
        //Input
        Long id = 1L;
        User resulted = new User(1L, "michael", "123", "Michael Rendon", "email.com");
        when(userRepository.findById(id)).thenReturn(Optional.of(resulted));
        
        //Terget
        DefaultUserService instance = new DefaultUserService(userRepository, modelMapper);

        //Expected
        UserDto expResult = new UserDto(1L, "michael", "123", "Michael Rendon", "email.com");

        //Test
        UserDto result = instance.getOne(id);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getEmail(), result.getEmail());
    }

    @Test
    public void testGetAll() throws Exception, Throwable {
        System.out.println("getAll");
        List<User> lista = new ArrayList<>();
        lista.add(new User(1L, "michael", "123", "Michael Rendon", "email.com"));
        when(userRepository.findAll()).thenReturn(lista);
        DefaultUserService instance = new DefaultUserService(userRepository, modelMapper);
        
        List<UserDto> lista2 = new ArrayList<>();
        lista2.add(new UserDto(1L, "michael", "123", "Michael Rendon", "email.com"));
        
        List<UserDto> expResult = lista2;
        List<UserDto> result = instance.getAll();
        for(int i = 0 ; i < (expResult.size()+result.size())/2; i++){
            assertEquals(expResult.get(0).getId(), result.get(0).getId());
        }
    }
/*
    @Test
    public void testUpdate() throws Exception, Throwable {
        System.out.println("update");
        Long id = null;
        UserDto user = null;
        DefaultUserService instance = new DefaultUserService();
        UserDto expResult = null;
        UserDto result = instance.update(id, user);
        assertEquals(expResult, result);
    }

    @Test
    public void testDelete() throws Exception, Throwable {
        System.out.println("delete");
        Long id = null;
        DefaultUserService instance = new DefaultUserService();
        Boolean expResult = null;
        Boolean result = instance.delete(id);
        assertEquals(expResult, result);
    }
    */
}
