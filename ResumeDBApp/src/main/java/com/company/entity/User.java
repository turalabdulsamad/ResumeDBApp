/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author dell
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String profileDesc;
    private String phone;
    private String address;
    private Country nationality;
    private Country birthPlace;
    private Date birthDate;
    private List<UserSkill> skills;

    public User(int id, String name, String surname, String email, String phone, String address, String profileDesc,Country nationality, Country birthPlace, Date birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.profileDesc = profileDesc;
        this.phone = phone;
        this.address = address;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
    }
  

    public User(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", profileDesc=" + profileDesc + ", phone=" + phone + ", address=" + address + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", birthDate=" + birthDate + ", skills=" + skills + '}';
    }

  
  



    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

}
