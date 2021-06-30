/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dell
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet rs) throws Exception {

        String header = rs.getString("header");
        String jobDescription = rs.getString("job_description");
        Date start_date = rs.getDate("start_date");
        Date end_date = rs.getDate("end_date");
        int user_id = rs.getInt("user_id");
        return new EmploymentHistory(null, header, start_date, end_date, jobDescription, new User(user_id));

    }

    @Override
    public EmploymentHistory getAllEmploymentHistoryByUserId(int userId) {

        try (Connection c = connect();) {

            PreparedStatement stmt = c.prepareStatement("select * from employment_history where user_id=?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                EmploymentHistory emp = getEmploymentHistory(rs);
                return emp;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       return null;
    }

    @Override
    public boolean updateUserEmpHis(User user) {
return true;
    }
}
