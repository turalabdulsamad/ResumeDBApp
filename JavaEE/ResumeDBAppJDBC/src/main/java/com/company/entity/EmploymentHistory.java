/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import java.sql.Date;

/**
 *
 * @author dell
 */
public class EmploymentHistory {
    private Integer id;
    private String header;
    private Date start_date;
    private Date end_date;
    private String job_description;
    private User user;

    public EmploymentHistory() {
    }

    public EmploymentHistory(Integer id, String header, Date start_date, Date end_date, String job_description, User user) {
        this.id = id;
        this.header = header;
        this.start_date = start_date;
        this.end_date = end_date;
        this.job_description = job_description;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "EmploymentHistory{" + "id=" + id + ", header=" + header + ", start_date=" + start_date + ", end_date=" + end_date + ", job_description=" + job_description + ", user=" + user + '}';
    }
    
   
}
