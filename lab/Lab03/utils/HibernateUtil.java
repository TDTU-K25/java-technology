package org.example.utils;

import org.example.pojo.Manufacture;
import org.example.pojo.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static Session getSession() {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Manufacture.class)
                .addAnnotatedClass(Phone.class)
                .buildSessionFactory();
        return factory.openSession();
    }
}
