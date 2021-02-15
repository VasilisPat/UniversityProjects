package bookshop;

import database.DB_Handling;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter writer = response.getWriter();  
         
        //Requested Id & Book Amount
        String id = request.getParameter("id");
        String amount = request.getParameter("amount");        
        
        int status = DB_Handling.OrderEntry(Integer.parseInt(id), Integer.parseInt(amount));
        
        //Action Status Print
        if(status>0){  
            writer.print("<p>Order Successful!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response); 
        }else if(status==0){  
            writer.println("<p>Out of Stock!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response); 
        }else{
            writer.println("<p>Order Error!</p>");  
            request.getRequestDispatcher("RetrieveEntries").include(request, response); 
        }
        
        writer.close();
    }
}
