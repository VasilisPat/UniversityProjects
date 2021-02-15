package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Handling;
import model.Book;

public class UpdateBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter writer = response.getWriter();  
         
        String editId = request.getParameter("editId");
        String id = request.getParameter("id");
        String title = request.getParameter("title");  
        String author = request.getParameter("author");  
        String publisher =request.getParameter("publisher");  
        String available = request.getParameter("available");        
        
        Book bok = new Book(Integer.parseInt(id), title, author, publisher, Integer.parseInt(available));
        
        int status = DB_Handling.UpdateEntry(bok, Integer.parseInt(editId));
        
        //Action Status Print
        if(status>0){  
            writer.print("<p>Update Completed!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response);  
        }else{  
            writer.println("<p>Update Error!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response);
        }            
        
        writer.close();
    }
}
