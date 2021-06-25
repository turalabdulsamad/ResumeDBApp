/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {
//

    @Override
    public UserSkill getUserSkillById(int id) {
        EntityManager em = em();
        UserSkill userSkill = em.find(UserSkill.class, id);
        return userSkill;
    }


    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        EntityManager em = em();
        String jpql = "SELECT userSkill FROM UserSkill userSkill";
        Query query = em.createQuery(jpql, UserSkill.class);
        List<UserSkill> skill = query.getResultList();
        return skill;

    }

    @Override
    public boolean removeUserSkillById(int id) {

        EntityManager em = em();
        UserSkill userSkill = em.find(UserSkill.class, id);
        em.getTransaction().begin();
        em.remove(userSkill);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean insertUserSkill(UserSkill skill) {

        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        EntityManager em = em();
        em.getTransaction().begin();
        em.merge(u);
        em.getTransaction().commit();
        return true;

    }
}