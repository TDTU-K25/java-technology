package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

public class UserDAO implements DAOInterface<User, Integer> {


	@Override
	public boolean add(User user) {
		if (isExistEmail(user.getEmail())) {
			return false;
		}
		Session session = HibernateUtils.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(user);
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public List<User> readAll() {
		return null;
	}

	@Override
	public User read(Integer id) {
		return null;
	}

	@Override
	public boolean update(User user) {
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		return false;
	}

	public User findByEmail(String email) {
		Session session = HibernateUtils.getSession();
		String hql = "FROM User WHERE email = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		User user = (User) query.getSingleResult();
		session.close();
		return user;
	}

	private boolean isExistEmail(String email) {
		try {
			return findByEmail(email) != null;
		} catch (NoResultException e) {
			return false;
		}
	}

	public User findByEmailAndPassword(String username, String password) {
		Session session = HibernateUtils.getSession();
		String hql = "FROM User WHERE email = :email AND password = :password";
		Query query = session.createQuery(hql);
		query.setParameter("email", username);
		query.setParameter("password", password);
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		} finally {
			session.close();
		}
	}
}
