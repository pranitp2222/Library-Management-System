package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SignUpModel extends DBConnect {
 
	
	public Boolean putCredentials(String username, String password, String course){
           
           String query = "INSERT INTO lms_user (user, password, course) VALUES (?, ?, ?)";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
              stmt.setString(1, username);
              stmt.setString(2, password);
              stmt.setString(3, course);
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