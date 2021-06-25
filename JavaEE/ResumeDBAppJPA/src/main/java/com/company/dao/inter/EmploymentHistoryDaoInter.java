/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.util.List;

/**
 *
 * @author dell
 */
public interface EmploymentHistoryDaoInter {
     public EmploymentHistory getAllEmploymentHistoryByUserId(int userId);
     public boolean updateUserEmpHis(User user);
     
}
