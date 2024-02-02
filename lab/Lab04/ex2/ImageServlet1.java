import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;

@WebServlet(name = "ImageServlet1", value = "/image1")
public class ImageServlet1 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		InputStream resource = getClass().getClassLoader().getResourceAsStream("dog.jpg");
		byte[] bytes = resource.readAllBytes();
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(bytes);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}