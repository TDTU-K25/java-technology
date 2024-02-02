package dao;

import java.util.List;

public interface DAOInterface<T, K> {
	boolean add(T t);

	List<T> readAll();

	T read(K id);

	boolean update(T t);

	boolean delete(K id);
}
