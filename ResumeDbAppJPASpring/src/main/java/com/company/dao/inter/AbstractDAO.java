package com.company.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author sarkhanrasullu
 */
public abstract class AbstractDAO {

    public Connection connect() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }

    private static EntityManagerFactory emf = null;

    public EntityManager em() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("resumeappPU");
        }
        EntityManager entitymanager = emf.createEntityManager();
        return entitymanager;
    }

    public static void closeEmf() {
        emf.close();
    }
}