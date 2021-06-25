/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;

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

    private Country getCountry(ResultSet rs) throws Exception {
        Integer id = rs.getInt("id");
        String country_name = rs.getString("name");
        String nationality = rs.getString("nationality");
        return new Country(id, country_name, nationality);

    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> list = new ArrayList<>();
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from country");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Country country = getCountry(rs);
                list.add(country);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
