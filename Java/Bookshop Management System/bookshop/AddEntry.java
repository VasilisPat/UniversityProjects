package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;

public class AddEntry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();
        
        //HTML Styling Code (Title, Background Color, Alignment)
        writer.print("<head> <title>Add Book</title> </head>");
        writer.print("<body bgcolor='E1E3E6'>");
        writer.println("<h1 align='center'>Add Book Entry</h1>");
        writer.print("<div align='center'>");
        
        //Empty Book object to fill spaces with default values
        Book bok = new Book();
        
        //User Add Form
        writer.print("<form action='SaveBook' method='post'>");
        writer.print("<table>");
        writer.print("<tr><td>Book's Id:</td><td><input type='text' name='id' value='" + bok.getId() + "'/></td></tr>");
        writer.print("<tr><td>Title:</td><td><input type='text' name='title' value='" + bok.getTitle() + "'/></td></tr>");
        writer.print("<tr><td>Author:</td><td><input type='text' name='author' value='" + bok.getAuthor() + "'/></td></tr>");
        writer.print("<tr><td>Publisher:</td><td><input type='text' name='publisher' value='" + bok.getPublisher() + "'/></td></tr>");
        writer.print("<tr><td>Available:</td><td><input type='text' name='available' value='" + bok.getAvailable() + "'/></td></tr>");
        writer.print("<tr><td align='right' colspan='2'><input type='submit' value='Add'/></td></tr>");
        writer.print("</table>");
        writer.print("</form>");
        
        writer.print("</div>");
        writer.print("<h4 align='right'><a href='index.html' style='color:black'>Return to Home Page</a></h4>");
        writer.print("</body>");

        writer.close();
    }
}
