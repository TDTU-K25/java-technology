import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
	private static List<Product> products;

	@Override
	public void init() throws ServletException {
		products = new ArrayList<Product>();
		products.add(new Product(1, "iPhone 11", 549));
		products.add(new Product(3, "iPhone 13 Pro", 999));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		JSONObject jsonObject = new JSONObject();
		if (id == null) {
			jsonObject.put("code", 0);
			jsonObject.put("message", "Đọc sản phẩm thành công");
			jsonObject.put("data", products);
			response.getWriter().write(jsonObject.toString());
		} else {
			Product productFound = products.stream().filter(product -> product.getId() == Integer.parseInt(id)).findFirst().orElse(null);
			if (productFound == null) {
				jsonObject.put("code", 2);
				jsonObject.put("message", "Không tìm thấy sản phẩm nào với mã số " + id);
				response.getWriter().write(jsonObject.toString());
			} else {
				jsonObject.put("code", 0);
				jsonObject.put("message", "Đọc sản phẩm thành công");
				jsonObject.put("data", new JSONObject(productFound));
				response.getWriter().write(jsonObject.toString());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String idStr = request.getParameter("id");
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		JSONObject jsonObject = new JSONObject();
		try {
			int id = Integer.parseInt(idStr);
			int price = Integer.parseInt(priceStr);
			if (products.stream().anyMatch(product -> product.getId() == id)) {
				jsonObject.put("code", "2");
				jsonObject.put("message", "id cannot be the same as an existing product");
				response.getWriter().write(jsonObject.toString());
			} else {
				products.add(new Product(id, name, price));
				jsonObject.put("code", "0");
				jsonObject.put("message", "Thêm sản phẩm thành công");
				response.getWriter().write(jsonObject.toString());
			}
		} catch (Exception e) {
			jsonObject.put("code", "1");
			jsonObject.put("message", "id and price must be numeric values");
			response.getWriter().write(jsonObject.toString());
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String idStr = req.getParameter("id");
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		JSONObject jsonObject = new JSONObject();
		try {
			int id = Integer.parseInt(idStr);
			int price = Integer.parseInt(priceStr);
			Product productFound = products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
			if (productFound != null) {
				productFound.setName(name);
				productFound.setPrice(price);
				jsonObject.put("code", "0");
				jsonObject.put("message", "Sửa sản phẩm thành công");
				resp.getWriter().write(jsonObject.toString());
			} else {
				jsonObject.put("code", "2");
				jsonObject.put("message", "id does not exist");
				resp.getWriter().write(jsonObject.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("message", "id and price must be numeric values");
			resp.getWriter().write(jsonObject.toString());
		}

	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		String idStr = req.getParameter("id");
		JSONObject jsonObject = new JSONObject();
		try {
			int id = Integer.parseInt(idStr);
			Product productFound = products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
			if (productFound != null) {
				products.remove(productFound);
				jsonObject.put("code", "0");
				jsonObject.put("message", "Xóa sản phẩm thành công");
				resp.getWriter().write(jsonObject.toString());
			} else {
				jsonObject.put("code", "2");
				jsonObject.put("message", "id does not exist");
				resp.getWriter().write(jsonObject.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("code", "1");
			jsonObject.put("message", "id must be numeric values");
			resp.getWriter().write(jsonObject.toString());
		}
	}
}