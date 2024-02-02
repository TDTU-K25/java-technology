package dao;

import models.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.persistence.Query;
import java.util.List;

public class ProductDAO implements DAOInterface<Product, Integer> {
	@Override
	public boolean add(Product product) {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(product);
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public List<Product> readAll() {
		Session session = HibernateUtils.getSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		List<Product> productList = query.getResultList();
		return productList;
	}

	@Override
	public Product read(Integer id) {
		Session session = HibernateUtils.getSession();
		Product product = session.get(Product.class, id);
		return product;
	}

	@Override
	public boolean update(Product product) {
		return add(product);
	}

	@Override
	public boolean delete(Integer id) {
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(read(id));
		transaction.commit();
		session.close();
		return true;
	}
}
