package models;

import java.sql.Date;



public class availableBooks {
    
    private final String name;
    private final String author;
    private final String bookType;
    private final Date date;
    
    public availableBooks(String name, String author, String bookType, Date date){
        this.name = name;
        this.author = author;
        this.bookType = bookType;
        this.date = date;
    }
    
    public String getName(){
        return name;
    }
    public String getAuthor(){
        return author;
    }
    public String getBookType(){
        return bookType;
    }
    public Date getDate(){
        return date;
    }

    
    
}