package de.ks.kanzashi.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import de.ks.kanzashi.entity.Item;
import de.ks.kanzashi.service.ItemService;

@WebServlet("/itemEdit.html")
public class EditRecordServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Autowired
	private ItemService itemService;
	
	@Override
	public void init() throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setContentType("text/html;charset=UFT-8");
//		System.out.println("old Name " + request.getParameter("id"));
//		System.out.println("new Name " + request.getParameter("name"));
//		System.out.println("price " + request.getParameter("price"));
//		System.out.println("file " + request.getParameter("file"));
//		
//		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
//		String line = null;
//		while ((line = reader.readLine()) != null) {
//		    System.out.println(line);
//		}
//		
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                System.out.println("fieldName " + fieldName);
	                System.out.println("fieldValue " + fieldValue);
	                // ... (do your job here)
	            } else {
	                // Process form file field (input type="file").
	                String fieldName = item.getFieldName();
	                String fileName = FilenameUtils.getName(item.getName());
	                InputStream fileContent = item.getInputStream();
	                //System.out.println("fieldName " + fieldName);
	                //System.out.println("fieldValue " + fileName);
	                // ... (do your job here)
	            }
	        }
	    } catch (FileUploadException e) {
	        
	    }
		
//		int id = Integer.parseInt(request.getParameter("id"));
//		Item item = itemService.findById(id);
//		
//		item.setName(request.getParameter("name"));
//		
//		double price = Double.parseDouble(request.getParameter("price"));
//		item.setPrice(price);
//		
//		itemService.save(item);
//		
//		response.sendRedirect("/item/itemDetails/" + item.getName() + ".html");
	}
}
