/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
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
 *
 * @author dell
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        Integer id = rs.getInt("id");
        String skill_name = rs.getString("name");
        return new Skill(id, skill_name);
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> list = new ArrayList<>();

        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from skill");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Skill skill = getSkill(rs);
                list.add(skill);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean insertSkill(Skill skill) {
        try (Connection c = connect();) {

            PreparedStatement stmt = c.prepareStatement("insert skill (name) values (?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            stmt.execute();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
