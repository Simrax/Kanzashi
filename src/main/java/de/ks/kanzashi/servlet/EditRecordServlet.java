package de.ks.kanzashi.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
		int id = 0;
		String name = null;
		double price = 0;
		byte[] imageByte = null;
		
		try {
	        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (item.isFormField()) {
	                // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
	                String fieldName = item.getFieldName();
	                String fieldValue = item.getString();
	                
	                switch (fieldName) {
					case "id": 
						id = Integer.parseInt(fieldValue);
						break;
					case "name": 
						name = fieldValue;
						break;
					case "price": 
						price = Double.parseDouble(fieldValue);
						break;
					}
	            } else {
	                // Process form file field (input type="file").                
	                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	                byte[] chunk = new byte[4096];
	    	        int bytesRead;
	    	        InputStream stream = item.getInputStream();
	    
	    	        while ((bytesRead = stream.read(chunk)) > 0) {
	    	            outputStream.write(chunk, 0, bytesRead);
	    	        }
	    	        
	    	        imageByte = outputStream.toByteArray();
	            }
	        }
	    } catch (FileUploadException e) {
	        
	    }
		
		Item item = itemService.findById(id);
		
		item.setName(name);
		item.setPrice(price);
		
		if(imageByte != null)
			item.getItemImage().setImage(imageByte);
		
		itemService.save(item);
		
		response.sendRedirect("/item/itemDetails/" + item.getName() + ".html");
	}
}
