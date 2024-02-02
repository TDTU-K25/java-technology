package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product, Integer> {
	private static String url;

	public ProductDAO(String url) {
		ProductDAO.url = url;
	}

	public Integer add(Product item) {
		Connection conn = JDBCUtil.getConnection(url);
		String sql = "insert into product(name, price, color) values(?, ?, ?)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getPrice());
			ps.setString(3, item.getColor());

			int rows = ps.executeUpdate();

			ps.close();
			conn.close();
			return rows;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Product> readAll() {
		Connection conn = JDBCUtil.getConnection(url);
		String sql = "select * from product";
		try {
			Statement stm = conn.createStatement();
			ResultSet resultSet = stm.executeQuery(sql);
			List<Product> productList = new ArrayList<Product>();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				String color = resultSet.getString("color");
				productList.add(new Product(id, name, price, color));
			}
			stm.close();
			conn.close();
			return productList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Product read(Integer id) {
		Connection conn = JDBCUtil.getConnection(url);
		String sql = "select * from product where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			Product product = null;
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				String color = resultSet.getString("color");
				product = new Product(id, name, price, color);
			}
			ps.close();
			conn.close();
			return product;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean update(Product item) {
		Connection conn = JDBCUtil.getConnection(url);
		String sql = "update product set name = ?, price = ?, color = ? where id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, item.getName());
			ps.setInt(2, item.getPrice());
			ps.setString(3, item.getColor());
			ps.setInt(4, item.getId());

			int rows = ps.executeUpdate();

			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean delete(Integer id) {
		Connection conn = JDBCUtil.getConnection(url);
		String sql = "delete from product where id = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			int rows = ps.executeUpdate();

			ps.close();
			conn.close();
			return true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
