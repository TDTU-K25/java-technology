package org.example.dao;

import org.example.pojo.Manufacture;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ManufactureDAO implements DAOInterface<Manufacture> {

    public static ManufactureDAO getInstance() {
        return new ManufactureDAO();
    }

    public boolean add(Manufacture manufacture) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(manufacture);
        transaction.commit();
        session.close();
        return true;
    }

    public Manufacture get(String id) {
        Session session = HibernateUtil.getSession();
        Manufacture manufacture = session.get(Manufacture.class, id);
        session.close();
        return manufacture;
    }

    public List<Manufacture> getAll() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Manufacture";
        Query query = session.createQuery(hql);
        List<Manufacture> manufactures = query.getResultList();
        return manufactures;
    }

    public boolean remove(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(get(id));
        transaction.commit();
        session.close();
        return true;
    }

    public boolean remove(Manufacture manufacture) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(manufacture);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean update(Manufacture manufacture) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(manufacture);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean isAllManufacturesHaveMoreThan100Employees() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Manufacture WHERE employee <= 100";
        Query query = session.createQuery(hql);
        List<Manufacture> manufactures = query.getResultList();
        return manufactures.size() == 0;
    }

    public int totalEmployeesOfAllManufactures() {
        Session session = HibernateUtil.getSession();
        String hql = "SELECT SUM(employee) FROM Manufacture";
        Query query = session.createQuery(hql);
        Long result = (long) query.getSingleResult();
        return result.intValue();
    }

    public Manufacture getLastManufacturerInUS() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Manufacture WHERE location = :location ORDER BY id DESC";
        Query query = session.createQuery(hql);
        query.setParameter("location", "US");
        List<Manufacture> manufactures = query.getResultList();
        if (manufactures.size() > 0) {
            return manufactures.get(0);
        } else {
            throw new IllegalStateException();
        }
    }
}
