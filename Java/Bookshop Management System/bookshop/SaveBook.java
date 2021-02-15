package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Handling;

public class SaveBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter writer = response.getWriter();  
        
        String id = request.getParameter("id");
        String title = request.getParameter("title");  
        String author = request.getParameter("author");  
        String publisher =request.getParameter("publisher");  
        String available = request.getParameter("available");   
        
        int status = DB_Handling.AddEntry(Integer.parseInt(id), title, author, publisher, Integer.parseInt(available));
        
        //Action Status Print
        if(status>0){  
            writer.print("<p>Insert Completed!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response);  
        }else{  
            writer.println("<p>Insert Error!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response);
        }            
        
        writer.close();
    }
}
