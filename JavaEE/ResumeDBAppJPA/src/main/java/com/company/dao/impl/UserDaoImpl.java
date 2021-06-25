/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author dell
 * <p>
 * import com.company.dao.inter.AbstractDAO;
 * import com.company.dao.inter.UserDaoInter;
 * import com.company.main.Context;
 * <p>
 * import javax.persistence.EntityManager;
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    EntityManager em;


    @Override
    public List<User> getAll(String name, String surname, String address) {
        EntityManager em = em();

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
        EntityManager entityManager = em();
        entityManager.getTransaction().begin();

        entityManager.merge(u);

        entityManager.getTransaction().commit();
        em.close();
        return true;

    }

    @Override
    public User getId(int id) {
        EntityManager entityManager = em();

        User user = entityManager.find(User.class, id);

        return user;
    }


    @Override
    public boolean removeUser(int id) {
        EntityManager entityManager = em();

        User user = entityManager.find(User.class, id);
        entityManager.getTransaction().begin();

        entityManager.remove(user);

        entityManager.getTransaction().commit();
        em.close();

        return true;

    }

    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addUser(User u) {

        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        EntityManager entityManager = em();

        entityManager.getTransaction().begin();

        entityManager.persist(u);


        entityManager.getTransaction().commit();
        em.close();

        return true;
    }
////    Named Query
//    @Override
//    public User findByEmail(String email) {
//        EntityManager em = em();
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
    EntityManager em = em();

    Query query = em.createNativeQuery("select * from user where email= ?",User.class);
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
//        EntityManager em = em();
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

        EntityManager em = em();

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