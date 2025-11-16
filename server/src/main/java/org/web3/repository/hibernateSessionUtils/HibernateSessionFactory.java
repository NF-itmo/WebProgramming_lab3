package org.web3.repository.hibernateSessionUtils;

import org.hibernate.Session;

public class HibernateSessionFactory {
    public static Session getSession() {
        return HibernateSessionFactorySingleton
                .getSessionFactory()
                .openSession();
    }
}