/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.impl.UserDaoImpl;
import com.company.dao.inter.UserDaoInter;
import com.company.entity.Country;
import com.company.entity.User;

import java.util.List;
import javax.swing.text.html.parser.Entity;

/**
 * @author dell
 */
public class Main {

    public static void main(String[] args) {

               UserDaoInter user = Context.instanceUserDao();
              User u = user.getId(7);
              System.out.println(u.getAddress());    }
}
