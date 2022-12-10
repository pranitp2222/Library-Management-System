package models;

import java.sql.Date;

public class returnBook {
	private final String bookcode;
    private final String studentid;
    private final String fname;
    private final String lname;
    private final Date date;
    
    
    public returnBook(String bookcode, String studentid, String fname, String lname, Date date){
        this.bookcode = bookcode;
        this.studentid = studentid;
        this.fname = fname;
        this.lname = lname;
        this.date = date;
    }
    
    public String getBookcode(){
        return bookcode;
    }
    public String getStudentid(){
        return studentid;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public Date getDate(){
        return date;
    }
}
