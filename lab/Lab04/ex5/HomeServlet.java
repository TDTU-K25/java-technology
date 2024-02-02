import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		String url = "";
		if (page != null) {
			switch (page) {
				case "contact":
					url = "/contact.jsp";
					break;
				case "about":
					url = "/about.jsp";
					break;
				case "help":
					url = "/help.jsp";
					break;
				default:
					response.getWriter().write("<h1>Welcome to our website</h1>");
					return;
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
			requestDispatcher.forward(request, response);
		} else {
			response.getWriter().write("<h1>Welcome to our website</h1>");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}