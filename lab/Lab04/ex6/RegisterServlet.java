import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isError = false;
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if (request.getParameter(param).isEmpty()) {
				response.getWriter().write("Missing " + param + "\n");
				isError = true;
			}
		}

		if (request.getParameter("gender") == null) {
			isError = true;
			response.getWriter().write("Missing gender\n");
		}

		if (request.getParameterValues("favorite_ide") == null) {
			isError = true;
			response.getWriter().write("Missing favorite IDE\n");
		}

		if (!isError) {
			String[] favorite_ide = request.getParameterValues("favorite_ide");
			request.setAttribute("favorite_ide", favorite_ide);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/info.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}