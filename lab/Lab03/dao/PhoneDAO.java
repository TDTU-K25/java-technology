package org.example.dao;

import org.example.pojo.Phone;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PhoneDAO implements DAOInterface<Phone> {
    public static PhoneDAO getInstance() {
        return new PhoneDAO();
    }

    public boolean add(Phone phone) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(phone);
        transaction.commit();
        session.close();
        return true;
    }

    public Phone get(String id) {
        Session session = HibernateUtil.getSession();
        Phone phone = session.get(Phone.class, id);
        session.close();
        return phone;
    }

    public List<Phone> getAll() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Phone";
        Query query = session.createQuery(hql);
        List<Phone> phones = query.getResultList();
        return phones;
    }

    public boolean remove(String id) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(get(id));
        transaction.commit();
        session.close();
        return true;
    }

    public boolean remove(Phone phone) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.remove(phone);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean update(Phone phone) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(phone);
        transaction.commit();
        session.close();
        return true;
    }

    public Phone getPhoneHighestPrice() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Phone ORDER BY price DESC";
        Query query = session.createQuery(hql);
        List<Phone> phones = query.getResultList();
        return phones.size() > 0 ? phones.get(0) : null;
    }

    public List<Phone> getAllSortByCountryAndDescPrice() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Phone ORDER BY country, price DESC";
        Query query = session.createQuery(hql);
        List<Phone> phones = query.getResultList();
        return phones;
    }

    public boolean isExistPhonePriceAbove50Million() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Phone WHERE price > 50000000";
        Query query = session.createQuery(hql);
        List<Phone> phones = query.getResultList();
        return phones.size() > 0;
    }

    public Phone getPhonePinkColorAndPriceOver15Million() {
        Session session = HibernateUtil.getSession();
        String hql = "FROM Phone WHERE color = :color AND price > :price";
        Query query = session.createQuery(hql);
        query.setParameter("color", "Pink");
        query.setParameter("price", 15000000);
        List<Phone> phones = query.getResultList();
        return phones.size() > 0 ? phones.get(0) : null;
    }
}
