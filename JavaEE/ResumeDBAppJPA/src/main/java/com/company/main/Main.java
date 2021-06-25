/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.*;
import com.company.entity.UserSkill;

/**
 * @author dell
 */
public class Main {

    public static void main(String[] args) {
        UserSkillDaoInter sk = Context.instanceUserSkillDao();
        UserDaoInter user = Context.instanceUserDao();
        SkillDaoInter skill = Context.instanceSkillDao();
        UserSkill skilluser = sk.getUserSkillById(66);
        skilluser.setPower(9);
        sk.updateUserSkill(skilluser);
        }

        }




