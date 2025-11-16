package org.web3.repository.hibernateSessionUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactorySingleton {
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure()
                        .buildSessionFactory();
            } catch (Exception e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}
