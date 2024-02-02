import org.apache.hadoop.hbase.io.hadoopbackport.ThrottledInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(name = "DownloadServlet", value = "/download")
public class DownloadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filename = request.getParameter("file");
		if (filename == null) {
			response.setContentType("text/html");
			response.getWriter().write("File not found");
		} else {
			InputStream resource = getClass().getClassLoader().getResourceAsStream(filename);
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.setContentType("application/octet-stream");
			String speedInKB = request.getParameter("speed");
			OutputStream outputStream = response.getOutputStream();
			ThrottledInputStream throttledInputStream = null;
			if (speedInKB != null) {
				long byteaPerSecond = Long.parseLong(speedInKB); // kb * 1024 -> b
				throttledInputStream = new ThrottledInputStream(resource, byteaPerSecond);
			} else {
				throttledInputStream = new ThrottledInputStream(resource);
			}
			outputStream.write(throttledInputStream.readAllBytes());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}