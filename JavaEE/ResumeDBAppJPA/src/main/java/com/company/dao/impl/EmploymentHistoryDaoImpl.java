/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.Country;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dell
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    @Override
    public EmploymentHistory getAllEmploymentHistoryByUserId(int userId) {

        EntityManager em = em();

        String jpql = "SELECT empHist from EmploymentHistory empHist WHERE userId=:userId";
        Query query = em.createQuery(jpql, EmploymentHistory.class);
        query.setParameter("userId", userId);

        EmploymentHistory empHist = (EmploymentHistory) query.getSingleResult();

        return empHist;
    }

    @Override
    public boolean updateUserEmpHis(User user) {
        return true;
    }
}
