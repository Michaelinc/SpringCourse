/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.web.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Michael
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto implements Serializable{
    
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    
}
