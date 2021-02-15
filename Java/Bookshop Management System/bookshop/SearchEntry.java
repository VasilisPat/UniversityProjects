package bookshop;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchEntry extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter writer = response.getWriter();
        
        //HTML Styling Code (Title, Background Color, Alignment)
        writer.print("<head> <title>Search Book</title> </head>");
        writer.print("<body bgcolor='E1E3E6'>");
        writer.println("<h1 align='center'>Search Book</h1>");
        writer.print("<div align='center'>");
        
        //User Search Form
        writer.print("<form action='SearchBook' method='get'>");
        writer.print("<table>");
        writer.print("<tr><td>Book's Id:</td><td><input type='text' name='id' value=''/></td></tr>");
        writer.print("<tr><td align='right' colspan='2'><input type='submit' value='Search'/></td></tr>");
        writer.print("</table>");
        writer.print("</form>");
        
        writer.print("</div>");
        writer.print("<h4 align='right'><a href='index.html' style='color:black'>Return to Home Page</a></h4>");
        writer.print("</body>");
        
        writer.close();
    }

}
