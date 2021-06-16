/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
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
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String profileDesc = rs.getString("profile_description");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthdate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthplaceId, birthplaceStr, null);

        return new User(id, name, surname, email, phone, address, profileDesc, nationality, birthplace, birthdate);
    }

    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDesc = rs.getString("profile_description");
        int nationalityId = rs.getInt("nationality_id");
        int birthplaceId = rs.getInt("birthplace_id");
        Date birthdate = rs.getDate("birthdate");


        return new User(id, name, phone,surname, email, profileDesc,birthdate,null,null);
    }

    @Override
    public List<User> getAll(String name,String surname,String address) {
        List<User> result = new ArrayList<>();
        try (Connection c = connect();) {
            String mysql ="SELECT"
                    + "	u.*,"
                    + "	n.nationality,"
                    + "	c.NAME AS birthplace"
                    + " FROM"
                    + "	USER u"
                    + "	LEFT JOIN country n ON u.nationality_id = n.id"
                    + "	LEFT JOIN country c ON u.birthplace_id = c.id where 1=1";

            if(name!=null&&!name.trim().isEmpty()){
                mysql+= " and u.name=? ";

            }
            if(surname!=null&&!surname.trim().isEmpty()){
                mysql+=" and u.surname=? ";

            }
            if(address!=null&&!address.trim().isEmpty()){
                mysql+=" and u.address=? ";
            }

            PreparedStatement stmt = c.prepareStatement(mysql);
            int i =1;
            if(name!=null&&!name.trim().isEmpty()){
                stmt.setString(i,name);
                i++;
            }
            if(surname!=null&&!surname.trim().isEmpty()){
                stmt.setString(i,surname);
                i++;

            }
            if(address!=null&&!address.trim().isEmpty()){
                stmt.setString(i,address);
            }

            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?,"
                    + " surname=?,email=?,phone=?,address=?,"
                    + " profile_description=?,birthdate=?,birthplace_id=?,nationality_id=? where id =?");

            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());

            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getAddress());
            stmt.setString(6, u.getProfileDesc());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
            stmt.setInt(10, u.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public User getId(int id) {
        User user = null;

        try (Connection c = connect();) {

            PreparedStatement stmt = c.prepareCall("SELECT u.*, n.nationality, c.NAME as birthplace"
                    + " FROM USER u "
                    + "LEFT JOIN country n ON u.nationality_id = n.id "
                    + "LEFT JOIN country c ON u.birthplace_id = c.id "
                    + " WHERE u.id = ?");

            stmt.setInt(1, id);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                user = getUser(rs);

                return user;}
            }catch (Exception ex) {
            ex.printStackTrace();
        }
            return null;
        }

        @Override
        public boolean removeUser(int id) {
        try (Connection c = connect();) {
                Statement stmt = c.createStatement();
                return stmt.execute("delete from user where id=" + id);
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect();) {
                PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,email,phone,"
                        + "address,profile_description,birthdate,birthplace_id,"
                        + "nationality_id) values (?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            
            stmt.setString(3, u.getEmail());
            
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getAddress());
            stmt.setString(6, u.getProfileDesc());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getNationality().getId());
           

            return stmt.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect();){
            PreparedStatement stmt = c.prepareStatement("select * from user u where u.email=? and u.password=?");
            stmt.setString(1,email);
            stmt.setString(2,password);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
               result = getUserSimple(rs);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return result;
    }

}
