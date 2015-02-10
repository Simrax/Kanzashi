package de.ks.kanzashi.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import de.ks.kanzashi.entity.Category;
import de.ks.kanzashi.service.CategoryService;

@WebServlet("/categoryEdit.html")
public class CategoryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Autowired
	private CategoryService categoryService;
	
	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = null;	
		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload();
		upload.setHeaderEncoding("UTF-8");
		try {
			FileItemIterator iter = upload.getItemIterator(request);
			while (iter.hasNext()) {
			    FileItemStream item = iter.next();
			    String fieldName = item.getFieldName();
			    InputStream stream = item.openStream();
			    if (item.isFormField()) {
			    	
			    	switch (fieldName) {
					case "name":
						name = Streams.asString(stream, "UTF-8");
						break;
					}
			    } 
			}
		} catch (FileUploadException e) {}
		
		Category category = new Category();
		category.setName(name);
		categoryService.save(category);
		
		response.sendRedirect("/");
	}
}
