package com.company.dao.impl;

import com.company.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>, UserRepositoryCustom {

    User findByemail(String email);
    User findByname (String name);
    User findByNameAndSurname(String name, String surname);
}
