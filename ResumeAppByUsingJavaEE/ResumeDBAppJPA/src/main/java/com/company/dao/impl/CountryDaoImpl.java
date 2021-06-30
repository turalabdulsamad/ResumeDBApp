/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    public Country getCountry(int id) {
        EntityManager em = em();
        Query query = em.createQuery("SELECT C FROM Country C WHERE id=:id");
        query.setParameter("id",id);
        Country country=(Country) query.getSingleResult();

        return country;

    }

    @Override
    public List<Country> getAllCountry() {

        EntityManager em = em();

        String jpql = "SELECT c from Country c";

        Query query = em.createQuery(jpql,Country.class);

        return query.getResultList();

    }

}
