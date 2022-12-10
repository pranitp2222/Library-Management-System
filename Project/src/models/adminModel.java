package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class adminModel extends DBConnect {
	
	public Boolean addBookDetails(String bookName, String authorName, String bookCode, LocalDate date){
        
        String query = "INSERT INTO lms_book (title, author, type, publish, available) VALUES (?, ?, ?, ?, ?)";
         try(PreparedStatement stmt = connection.prepareStatement(query)) {
           stmt.setString(1, bookName);
           stmt.setString(2, authorName);
           stmt.setString(3, bookCode);
           stmt.setObject(4, date);
           stmt.setString(5, "available");
           int status = stmt.executeUpdate();
           if(status == 1) {
           	return true;
           }
            
         }catch (SQLException e) {
        	e.printStackTrace();   
         }	       
         
         return false;	
 }
	
public Boolean removeBookDetails(String removeBookCode){
        
        String query = "DELETE FROM lms_book WHERE type = ?";
         try(PreparedStatement stmt = connection.prepareStatement(query)) {
           stmt.setString(1, removeBookCode);
           int status = stmt.executeUpdate();
           if(status == 1) {
           	return true;
           }
            
         }catch (SQLException e) {
        	e.printStackTrace();   
         }	       
         
         return false;	
 }

public Boolean updateBookDetails(String updateBookCode){
	
	String query = "UPDATE lms_book SET available = 'Not available' WHERE type = '" + updateBookCode + "'";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
        int status = stmt.executeUpdate();
        if(status == 1) {
        	return true;
        }
         
      }catch (SQLException e) {
     	e.printStackTrace();   
      }	       
      
      return false;	
}

public Boolean updateBookDetailsAvail(String updateBookCode){
	
	String query = "UPDATE lms_book SET available = 'available' WHERE type = '" + updateBookCode + "'";
	try(PreparedStatement stmt = connection.prepareStatement(query)) {
        int status = stmt.executeUpdate();
        if(status == 1) {
        	return true;
        }
         
      }catch (SQLException e) {
     	e.printStackTrace();   
      }	       
      
      return false;	
}
}
