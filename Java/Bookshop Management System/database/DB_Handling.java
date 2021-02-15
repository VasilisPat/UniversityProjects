package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Book;

public class DB_Handling {

    private static Connection conn = null;
    private static ResultSet rs = null;
    private static PreparedStatement pst = null;

    public static void ConnectToDB() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/netprogFinalProject", "patelis", "12345");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
        }
    }
    
    public static ArrayList<Book> RetrieveAll() {
        Book temp;
        DB_Handling.ConnectToDB();
        ArrayList<Book> list = new ArrayList<>();
        try{
            pst = conn.prepareStatement("SELECT * FROM books");
            rs = pst.executeQuery();
            while(rs.next()){
                temp = new Book();
                temp.setId(rs.getInt(1));
                temp.setTitle(rs.getString(2));
                temp.setAuthor(rs.getString(3));
                temp.setPublisher(rs.getString(4));
                temp.setAvailable(rs.getInt(5));
                list.add(temp);
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return list;
    }
    
    public static Book SearchBook(int id){
        DB_Handling.ConnectToDB();
        Book temp = new Book();
        try{
            pst = conn.prepareStatement("SELECT * FROM books WHERE BookID=?");
            pst.setInt(1,id);
            rs = pst.executeQuery();
            if(rs.next()){
                temp.setId(rs.getInt(1));
                temp.setTitle(rs.getString(2));
                temp.setAuthor(rs.getString(3));
                temp.setPublisher(rs.getString(4));
                temp.setAvailable(rs.getInt(5));
            }
        }catch(SQLException ex) {
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return temp;
    }
    
    public static int OrderEntry(int id, int amount){
        //Check if amount requested is a negative number
        if(amount<0){
            return -1;
        }
        int status = 0;
        //Search for book given by user
        Book temp = new Book();
        temp = SearchBook(id);
        DB_Handling.ConnectToDB();
        //Calculate new number of available books and check for availability
        int newAvailable = temp.getAvailable() - amount; 
        if(newAvailable<0){
            DB_Handling.Exit();
            return status;
        } 
        try{
            pst = conn.prepareStatement("UPDATE books SET available=? WHERE BookID=?");
            pst.setInt(1, newAvailable);
            pst.setInt(2, id);
            status = pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return status;
    }
    
    public static int AddEntry(int id, String title, String author, String publisher, int available) {
        int status = 0;
        DB_Handling.ConnectToDB();
        try{
            pst = conn.prepareStatement("INSERT INTO books VALUES (?,?,?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setString(4, publisher);
            pst.setInt(5, available);
            status = pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return status;
    }
    
    public static int UpdateEntry(Book temp, int editId) {
        int status = 0;
        DB_Handling.ConnectToDB();
        try{
            pst = conn.prepareStatement("UPDATE books SET BookID=?, Title=?, Author=?, Publisher=?, Available=? WHERE BookID=?");
            pst.setInt(1, temp.getId());
            pst.setString(2, temp.getTitle());
            pst.setString(3, temp.getAuthor());
            pst.setString(4, temp.getPublisher());
            pst.setInt(5, temp.getAvailable());
            pst.setInt(6, editId);
            status = pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return status;
    }
    
    public static int DeleteEntry(int id) {
        int status = 0;
        DB_Handling.ConnectToDB();
        try{
            pst = conn.prepareStatement("DELETE FROM books WHERE BookID=?");
            pst.setInt(1, id);
            status = pst.executeUpdate();
        }catch(SQLException ex) {
            System.out.println(ex);
        }
        DB_Handling.Exit();
        return status;
    }
    
    public static void Exit() {
        try{
            if(rs != null){
                rs.close();
            }
            if(pst != null){
                pst.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
    
}
