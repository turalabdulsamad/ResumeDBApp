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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {

        Integer user_skill_id = rs.getInt("usid");
        int user_id = rs.getInt("id");
        int skill_id = rs.getInt("skill_id");
        String skill_name = rs.getString("skill_name");
        Integer power = rs.getInt("power");

        return new UserSkill(user_skill_id, new User(user_id), new Skill(skill_id, skill_name), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {

        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect();) {

            PreparedStatement stmt = c.prepareStatement("SELECT user_skill.id as usid,user.*,skill.id as"
                    + " skill_id,skill.name as skill_name,user_skill.power as power from user_skill"
                    + " left join user on user_skill.user_id = USER.ID"
                    + " left join skill on skill.id=user_skill.skill_id where user_skill.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                result.add(u);
            }
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeUserSkillById(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from user_skill where id = " + userId);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean insertUserSkill(UserSkill u) {
        try (Connection c = connect();) {

            PreparedStatement stmt = c.prepareStatement("insert into user_skill (skill_id,user_id,power) values (?,?,?)");
            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserSkill(UserSkill u) {
        try {
            Connection c = connect();
            PreparedStatement stmt = c.prepareCall("update user_skill set user_id=?,"
                    + "skill_id = ?,power=? where id=?");
            stmt.setInt(1, u.getUser().getId());
            stmt.setInt(2, u.getSkill().getId());
            stmt.setInt(3, u.getPower());
            stmt.setInt(4, u.getId());
            return stmt.execute();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
