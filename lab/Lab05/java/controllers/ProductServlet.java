package controllers;

import dao.ProductDAO;
import models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products/*")
public class ProductServlet extends HttpServlet {
	private final ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String action = getAction(uri);
		switch (action) {
			case "edit" -> doPut(request, response);
			case "delete" -> doDelete(request, response);
			default -> {
				List<Product> productList = productDAO.readAll();
				request.setAttribute("productList", productList);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/products/");
				requestDispatcher.forward(request, response);
			}
		}
	}

	private String getAction(String requestURI) {
		String[] parts = requestURI.split("/");
		return parts[parts.length - 1];
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		Product product = new Product(name, Integer.parseInt(priceStr));
		productDAO.add(product);
		response.sendRedirect("/products/");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		Product productFound = productDAO.read(Integer.parseInt(idStr));
		productFound.setName(name);
		productFound.setPrice(Integer.parseInt(priceStr));
		productDAO.update(productFound);
		resp.sendRedirect("/products/");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		productDAO.delete(Integer.parseInt(req.getParameter("id")));
		resp.sendRedirect("/products/");
	}
}