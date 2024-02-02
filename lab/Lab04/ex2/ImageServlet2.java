import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "ImageServlet2", value = "/image2")
public class ImageServlet2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Content-Disposition", "attachment; filename=cat.jpg");
		response.setContentType("application/octet-stream");
		InputStream resource = getClass().getClassLoader().getResourceAsStream("cat.jpg");
		byte[] bytes = resource.readAllBytes();
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}