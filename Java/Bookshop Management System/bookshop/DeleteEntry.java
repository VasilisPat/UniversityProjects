package bookshop;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Handling;
import java.io.PrintWriter;

public class DeleteEntry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();  
        
        //Requested Id for Delete
        String reqId = request.getParameter("id");
        
        int status = DB_Handling.DeleteEntry(Integer.parseInt(reqId));
        
        //Action Status Print
        if(status>0){             
            writer.print("<p>Delete Completed!</p>");
            request.getRequestDispatcher("RetrieveEntries").include(request, response);  
        }else{            
            writer.print("<p>Delete Error!</p>");
            request.getRequestDispatcher("RetrieveEntries").include(request, response);  
        }
        
        writer.close();
    }

}
