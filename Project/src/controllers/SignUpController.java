package controllers;


import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.SignUpModel;

public class SignUpController {

    @FXML
    private Button insertSignUp;

    @FXML
    private PasswordField signPass;


    @FXML
    private TextField usersign;
    
    @FXML
    private Label signerror;
    
    @FXML
    private PasswordField signrepass;

    @FXML
    private TextField usercourse;

    
    private SignUpModel model;

	public SignUpController() {
		model = new SignUpModel();
		
	}

	
	
	 public void insertSignUp() {
		String username = this.usersign.getText();
		String password = this.signPass.getText();
		String course = this.usercourse.getText();
		String repass = this.signrepass.getText();
	    
		
		if (username == null || username.trim().equals("")) {
			signerror.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			signerror.setText("Password Cannot be empty or spaces");
			return;
		}
		if (repass == null || repass.trim().equals("")) {
			signerror.setText("Password Cannot be empty or spaces");
			return;
		}
		if(! password.equals(repass)) {
			signerror.setText("Password and Re-Password is mismatch");
			return;
		}
		
		if (course == null || course.trim().equals("")) {
			signerror.setText("Password Cannot be empty or spaces");
			return;
		}
		
		checkCredentials(username, password, course);
	 }
	 
	 
	  public void loginback() throws IOException{
	    	try {
	        	AnchorPane root;
	        	root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
	    		Main.stage.setTitle("Login");
	    		Scene scene = new Scene(root);
	    		Main.stage.setScene(scene);
	        	}
	    		catch (Exception e) {
	    			System.out.println("Error occured while inflating view: " + e);
	    		}
	        	

	    }
	
	 public void checkCredentials(String username, String password, String course) {
			Boolean isValid = model.putCredentials(username, password, course);
			if (!isValid) {
				System.out.print("Error in inserting");
				Alert alert;
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Error in SignUp! ");
                alert.showAndWait();
                usersign.setText("");
        		signPass.setText("");
        		usercourse.setText("");
        		signrepass.setText("");
			}
			else {
				System.out.print("Inserted.....");
				Alert alert;
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully SignUp! ");
                alert.showAndWait();
                usersign.setText("");
        		signPass.setText("");
        		usercourse.setText("");
        		signrepass.setText("");
                
			}

}
}
