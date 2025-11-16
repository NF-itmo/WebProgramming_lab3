package org.web3.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.web3.models.Point;
import org.web3.models.User;
import org.web3.repository.hibernateSessionUtils.HibernateSessionFactory;

public class UserRepository {
    public void save(User user) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
        tx1.commit();
        session.close();
    }

    public User getById(int id) {
        Session session = HibernateSessionFactory.getSession();
        final User user = session.find(User.class, id);
        session.close();
        return user;
    }

    public User getByUsername(String username) {
        Session session = HibernateSessionFactory.getSession();
        final User user = session.createQuery("from User where username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        session.close();
        return user;
    }
}
