package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends DBConnect {
 
	private Boolean admin;
 
	public Boolean isAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	public Boolean getAdminCredentials(String username, String password){
           
           String query = "SELECT * FROM lms_admin WHERE username = ? and password = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) { 
                	System.out.println("Sucessfully Fetch");
                	return true;
               	}
             }catch (SQLException e) {
            	e.printStackTrace();   
             }
	       return false;
    }
	
	public Boolean getStudentCredentials(String username, String password){
        
        String query = "SELECT * FROM lms_user WHERE user = ? and password = ?;";
         try(PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
             if(rs.next()) { 
            	 System.out.println("Sucessfully Fetch");
             	return true;
            	}
          }catch (SQLException e) {
         	e.printStackTrace();   
          }
	       return false;
 }

}

