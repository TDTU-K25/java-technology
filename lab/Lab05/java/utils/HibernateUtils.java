package utils;

import models.Product;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	public static Session getSession() {
		SessionFactory factory = new Configuration()
			.configure()
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Product.class)
			.buildSessionFactory();
		return factory.openSession();
	}
}
