import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
	private static Map<String, String> accounts;

	@Override
	public void init() throws ServletException {
		accounts = new HashMap<String, String>();
		accounts.put("admin", "123");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		if (login(request, response)) {
			printWriter.write("Name/Password match");
		} else {
			printWriter.write("Name/Password does not match");
		}
	}

	public boolean login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (accounts.containsKey(username)) {
			return accounts.get(username).equals(password);
		} else {
			return false;
		}
	}
}