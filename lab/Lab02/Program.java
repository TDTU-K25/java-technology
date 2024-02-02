package org.example;

import java.util.List;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Invalid Connection URL");
			return;
		}

		String url = args[0];
		String tableName = "product";
		if (JDBCUtil.isTableExists(tableName, url)) {
			JDBCUtil.deleteTable(tableName, url);
			JDBCUtil.createTableProduct(url);
		} else {
			JDBCUtil.createTableProduct(url);
		}

		ProductDAO productDAO = new ProductDAO(url);
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		do {
			System.out.println("1. Read all products");
			System.out.println("2. Read detail of a product by id");
			System.out.println("3. Add a new product");
			System.out.println("4. Update a product");
			System.out.println("5. Delete a product by id");
			System.out.println("6. Exit");
			System.out.println();
			System.out.print("Your choice:");
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1 -> readAllProducts(productDAO);
				case 2 -> readProductByID(productDAO, sc);
				case 3 -> addProduct(productDAO, sc);
				case 4 -> updateProduct(productDAO, sc);
				case 5 -> deleteProduct(productDAO, sc);
			}
		} while (choice != 6);
		sc.close();
	}

	private static void readAllProducts(ProductDAO productDAO) {
		List<Product> productList = productDAO.readAll();
		productList.forEach(System.out::println);
	}

	private static void readProductByID(ProductDAO productDAO, Scanner sc) {
		System.out.print("Enter product id:");
		int id = sc.nextInt();
		System.out.println(productDAO.read(id));
	}

	private static void addProduct(ProductDAO productDAO, Scanner sc) {
		System.out.print("Enter product name:");
		String name = sc.nextLine();
		System.out.print("Enter product price:");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter product color:");
		String color = sc.nextLine();
		Product product = new Product(name, price, color);
		if (productDAO.add(product) == 1) {
			System.out.println("Add product success");
		}
	}

	private static void updateProduct(ProductDAO productDAO, Scanner sc) {
		System.out.print("Enter product id:");
		int id = sc.nextInt();
		sc.nextLine();
		Product product = productDAO.read(id);
		System.out.println(product);

		System.out.print("Enter product name:");
		String name = sc.nextLine();
		System.out.print("Enter product price:");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter product color:");
		String color = sc.nextLine();
		product.setName(name);
		product.setPrice(price);
		product.setColor(color);

		if (productDAO.update(product)) {
			System.out.println("Update product success");
		}
	}

	private static void deleteProduct(ProductDAO productDAO, Scanner sc) {
		System.out.print("Enter product id:");
		int id = sc.nextInt();
		if (productDAO.delete(id)) {
			System.out.println("Delete product success");
		}
	}
}
