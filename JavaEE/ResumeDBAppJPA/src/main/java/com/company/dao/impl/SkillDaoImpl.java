/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import org.eclipse.persistence.jpa.rs.util.list.MultiResultQueryList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    @Override
    public List<Skill> getAllSkill() {
        EntityManager em = em();
        String jpql = "SELECT S FROM Skill S";

        Query query = em.createQuery(jpql, Skill.class);
        List<Skill> skill = query.getResultList();
        return skill;
    }

    @Override
    public Skill getSkillById(int id) {
        EntityManager em = em();
        Skill skill = em.find(Skill.class, id);
        return skill;

    }

    @Override
    public boolean insertSkill(Skill skill) {

        EntityManager em = em();
        em.getTransaction().begin();
        em.persist(skill);
        em.getTransaction().commit();
        em.close();
        return true;
    }

}
