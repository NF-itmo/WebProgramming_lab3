package org.web3.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.web3.models.Point;
import org.web3.models.User;
import org.web3.repository.hibernateSessionUtils.HibernateSessionFactory;

import java.util.List;

public class PointRepository {
    public void save(Point point) {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx1 = session.beginTransaction();
        session.persist(point);
        tx1.commit();
        session.close();
    }

    public List<Point> getByUserId(int userId, int length, int start) {
        Session session = HibernateSessionFactory.getSession();
        final List<Point> points = session.createQuery("from Point where user.id = :userId order by timestamp desc", Point.class)
                .setParameter("userId", userId)
                .setFirstResult(start)
                .setMaxResults(length)
                .list();
        session.close();
        return points;
    }
}

