package model;

public class Book {
    
    private int id, available;
    private String title, author, publisher;
    
    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.publisher = "";
        this.available = 0;
    }

    public Book(int id, String title, String author, String publisher, int available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.available = available;
    }
    
    //Getters
    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }
    
    public String getPublisher() { return publisher; }

    public int getAvailable() { return available; }

    
    //Setters
    public void setId(int id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }

    public void setAuthor(String author) { this.author = author; }
    
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public void setAvailable(int available) { this.available = available; } 
    
}
