package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Handling;
import java.util.ArrayList;
import model.Book;

public class RetrieveEntries extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();  
        
        //HTML Styling Code (Title, Background Color, Alignment)
        writer.print("<head> <title>Books List</title> </head>");
        writer.print("<body bgcolor='E1E3E6'>");
        writer.println("<h1 align='center'>Books List</h1>");
        writer.print("<div align='center'>");
        
        ArrayList<Book> list = DB_Handling.RetrieveAll();
        
        //All Books List Table
        writer.print("<table border='1' width='80%'");  
        writer.print("<tr><th>Book's Id</th><th>Title</th><th>Author</th><th>Publisher</th><th>Available</th><th>Edit</th><th>Delete</th></tr>");  
        
        for(Book bok:list){  
            if(bok.getAvailable()>0){
                writer.print("<tr>"
                       + "<td>" + bok.getId() + "</td> "
                       + "<td>" + bok.getTitle() + "</td>"
                       + "<td>" + bok.getAuthor() + "</td>"
                       + "<td>" + bok.getPublisher() + "</td>"
                       + "<td>" + bok.getAvailable() + "</td>"
                       + "<td><a href='UpdateEntry?editId=" + bok.getId() + "' style='color:green'>Edit</a></td>"
                       + "<td><a href='DeleteEntry?id=" + bok.getId() + "' style='color:red'>Delete</a></td>"
                       + "</tr>");  
            }
        }  
        writer.print("</table>");  
        
        writer.print("</div>");
        writer.print("<h4 align='right'><a href='index.html' style='color:black'>Return to Home Page</a></h4>");
          
        writer.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
