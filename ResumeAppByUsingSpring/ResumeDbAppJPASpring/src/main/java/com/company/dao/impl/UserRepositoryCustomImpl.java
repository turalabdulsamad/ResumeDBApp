/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author dell
 * <p>
 * import com.company.dao.inter.AbstractDAO;
 * import com.company.dao.impl.UserRepositoryCustom;
 * import com.company.main.Context;
 * <p>
 * import javax.persistence.EntityManager;
 */
@Repository("userDaoImpl1")
@Transactional
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager em;


    @Override
    @Cacheable(value="users")
    public List<User> getAll(String name, String surname, String address) {

        String jpql = "select u from User u where 1=1";

//            String sql = "select "
//                    + "  u.*,  "
//                    + "  n.nationality, "
//                    + "  c.name as birthplace  "
//                    + "from user u "
//                    + "left join country n on u.nationality_id = n.id "
//                    + "left join country c on u.birthplace_id = c.id where 1=1 ";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name ";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname ";
        }

        if (address != null) {
            jpql += " and u.address=:address";
        }

        Query query = em.createQuery(jpql, User.class);

        if (name != null && !name.trim().isEmpty()) {
            query.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            query.setParameter("surname", surname);
        }

        if (address != null && !address.trim().isEmpty()) {
            query.setParameter("address", address);
        }

        return query.getResultList();
    }

    @Override
    public boolean updateUser(User u) {

        em.merge(u);

        return true;

    }

    @Override
    public User getId(int id) {

        User user = em.find(User.class, id);

        return user;
    }


    @Override
    @CacheEvict(value = "users",allEntries = true)
    public boolean removeUser(int id) {

        User user = em.find(User.class, id);

        em.remove(user);


        return true;

    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {

        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));

        em.persist(u);

        return true;
    }
////    Named Query
//    @Override
//    public User findByEmail(String email) {
//
//
//
//        Query query = em.createNamedQuery("User.findByEmail",User.class);
//        query.setParameter("email",email);
//
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }
//  Native SQL
@Override
public User findByEmail(String email) {

    Query query = em.createNativeQuery("select * from user where email= ?", User.class);
    query.setParameter(1,email);

    List<User> list = query.getResultList();
    if (list.size() == 1) {
        return list.get(0);
    }

    return null;
}
//Criteria Builder
//    @Override
//    public User findByEmail(String email) {
//
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> q1 = cb.createQuery(User.class);
//        Root<User> postRoot = q1.from(User.class);
//        CriteriaQuery<User> q2 = q1
//                .where(cb.equal(postRoot.get("email"), email));
//
//
//        Query query = em.createQuery(q2);
//
//        List<User> list = query.getResultList();
//        if (list.size() == 1) {
//            return list.get(0);
//        }
//
//        return null;
//    }


    @Override
    public User findByEmailAndPassword(String email, String password) {


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1
                .where(cb.equal(postRoot.get("email"), email), cb.equal(postRoot.get("password"), password));


        Query query = em.createQuery(q2);

        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }

        return null;
    }

}