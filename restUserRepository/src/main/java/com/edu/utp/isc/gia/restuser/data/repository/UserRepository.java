/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edu.utp.isc.gia.restuser.data.repository;

import com.edu.utp.isc.gia.restuser.data.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Michael
 */
public interface UserRepository extends CrudRepository<User, Long>{
    
}
