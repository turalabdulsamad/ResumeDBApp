/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.impl.UserRepositoryCustom;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dell
 * <p>
 * import com.company.dao.inter.AbstractDAO;
 * import com.company.dao.impl.UserRepositoryCustom;
 * import com.company.main.Context;
 * <p>
 * import javax.persistence.EntityManager;
 */
@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

@Autowired
@Qualifier("userDaoImpl1")
private UserRepositoryCustom userRepositoryCustom;

    @Override
    public List<User> getAll(String name, String surname, String address) {

        return userRepositoryCustom.getAll(name,surname,address);
    }

    @Override
    public boolean updateUser(User u) {
        return userRepositoryCustom.updateUser(u);
    }

    @Override
    public User getId(int id) {

      return userRepositoryCustom.getId(id);
    }


    @Override
    public boolean removeUser(int id) {
        return userRepositoryCustom.removeUser(id);

    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {

       return userRepositoryCustom.addUser(u);
    }

@Override
public User findByEmail(String email) {

    return userRepositoryCustom.findByEmail(email);

}
    @Override
    public User findByEmailAndPassword(String email, String password) {

        return userRepositoryCustom.findByEmailAndPassword(email,password);

    }

}