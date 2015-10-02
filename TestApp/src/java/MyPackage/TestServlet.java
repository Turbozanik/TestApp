package MyPackage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class TestServlet extends HttpServlet {

        List <Contact> readList;
	Contact contact;
        String listing;
        
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ContactsDAO contactDAO = new ContactsDAO();
        List <Contact> allItems = new ArrayList<Contact>();
        allItems = contactDAO.readData();
        
        System.out.println(allItems.toString());
        request.setAttribute("items", allItems);
        if(!request.getParameter("item").equals(null))
        request.getRequestDispatcher("listing.jsp").forward(request, response);
    }

        
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
                
                
                
		request.setCharacterEncoding("UTF8");
                response.setCharacterEncoding("UTF8");
		PrintWriter out = response.getWriter();
                
		out.println("Hello<br/>");
                
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			out.println("You are not trying to upload<br/>");
			return;
		}
		out.println("You are trying to upload<br/>");
                

                
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			out.println("Number of fields: " + fields.size());
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				out.println("No fields found");
				return;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					out.println("<td>regular form field</td><td>FIELD NAME: " + fileItem.getFieldName() + 
							"<br/>STRING: " + fileItem.getString()
							);
					out.println("</td>");
				} else {
                                        out.println(fileItem.getString());
                                        contact = new Contact();
                                        readList = new ArrayList<Contact>();
                                        int k = 0;
                                        String [] splited = fileItem.getString().split(",");//data splited
                                        
                                        for(String item : splited)
                                        {
                                           switch(k){
                                               case 1:{
                                               contact.name = item;    
                                               break;
                                               }
                                               case 2:{
                                               contact.sureName = item;
                                               break;
                                               }
                                               case 3:{
                                               contact.Login = item;
                                               break;
                                               }
                                               case 4:{
                                               contact.email = item;
                                               break;
                                               }
                                               case 5:{
                                               contact.phoneNumber = item;
                                               k=0;
                                               break;
                                               }
                                           }
                                            k++;
                                           readList.add(contact);
                                        }
                                        ContactsDAO contactsDAO = new ContactsDAO();
                                        out.println(readList.get(2).getID());
                                    try {
                                        contactsDAO.addData(readList);
                                    } catch (SQLException ex) {
                                        Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}
}