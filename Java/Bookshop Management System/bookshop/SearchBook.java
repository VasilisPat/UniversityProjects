package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB_Handling;
import model.Book;

public class SearchBook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();
        
        //HTML Styling Code (Title, Background Color, Alignment)
        writer.print("<head> <title>Search Book</title> </head>");
        writer.print("<body bgcolor='E1E3E6'>");
        writer.println("<h1 align='center'>Search Book</h1>");
        writer.print("<div align='center'>");
        
        String reqId = request.getParameter("id");

        Book bok = DB_Handling.SearchBook(Integer.parseInt(reqId));
        
        //Information Printout Form
        writer.print("<form action='' method=''>");
        writer.print("<table>");
        writer.print("<tr><td>Book Id:</td><td><input type='text' name='id' value='" + bok.getId() + "' readonly/></td></tr>");
        writer.print("<tr><td>Title:</td><td><input type='text' name='title' value='" + bok.getTitle() + "' readonly/></td></tr>");
        writer.print("<tr><td>Author:</td><td><input type='text' name='author' value='" + bok.getAuthor() + "' readonly/></td></tr>");
        writer.print("<tr><td>Publisher:</td><td><input type='text' name='publisher' value='" + bok.getPublisher() + "' readonly/></td></tr>");
        writer.print("<tr><td>Available:</td><td><input type='text' name='available' value='" + bok.getAvailable() + "' readonly/></td></tr>");
        writer.print("</table>");
        writer.print("</form>");
        
        writer.print("</div>");
        writer.print("<h4 align='right'><a href='index.html' style='color:black'>Return to Home Page</a></h4>");
        writer.print("</body>");

        writer.close();
    }
}
