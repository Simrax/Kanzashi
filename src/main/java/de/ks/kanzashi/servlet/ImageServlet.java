package de.ks.kanzashi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import de.ks.kanzashi.service.ItemImageService;

@WebServlet("/image.html")
public class ImageServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ItemImageService itemImageService;
	
	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			byte[] image = itemImageService.loadImage(id);
			response.setContentType("image/jpeg");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(image);
			outputStream.close();
		} catch (Exception e) {}
	}
}
