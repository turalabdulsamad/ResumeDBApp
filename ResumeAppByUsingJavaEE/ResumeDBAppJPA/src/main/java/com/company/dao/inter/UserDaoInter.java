/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.List;

/**
 * @author dell
 */
public interface UserDaoInter {

    public List<User> getAll(String name, String surname, String address);

    public boolean updateUser(User u);

    public User getId(int id);

    public User findByEmail(String email);

    public boolean removeUser(int id);

    public boolean addUser(User u);

    public User findByEmailAndPassword(String email, String password);


//    public User findByEmailAndPassword(String email, String password);

}
