package test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ImageUploadServlet
 */
@WebServlet("/uploadHandler")
public class ImageUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static List<String> filesUploaded = new ArrayList<>();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem fileItem : items) {
			    if (!fileItem.isFormField()) {
			    	String file = request.getServletContext().getRealPath("/")+File.separator+"img"+filesUploaded.size()+".png";
			    	System.out.println(file);
			    	filesUploaded.add(file);
			    	File uploadedFile = new File(file);
			    	fileItem.write(uploadedFile);
			    }
			}
		} catch (FileUploadException e) {
			throw new RuntimeException("Critical error uploading file: "+e.getMessage(), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
