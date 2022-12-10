package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.LoginModel;

public class LoginController implements Initializable{


    @FXML
    private Label PasswordLable;

    @FXML
    private Button loginButton;
    
    @FXML
    private Button signUpBtn;

    @FXML
    private Label userLable;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userTxt;
    
    @FXML
    private Label error;
    
    

    @FXML
    private TextField signPass;

    
    @FXML
    private ComboBox<String> userType;
    
    private ObservableList < String > usrTypes;

    

    
    private LoginModel model;

	public LoginController() {
		model = new LoginModel();
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		usrTypes = FXCollections.observableArrayList("Admin", "Student");
		userType.setItems(usrTypes);
		userType.getSelectionModel();
		
	}

    @FXML
    void login() {

    	error.setText("");
		String username = this.userTxt.getText();
		String password = this.userPassword.getText();
		String usertype = this.userType.getValue();
		System.out.println(usertype);

		// Validations
		if (username == null || username.trim().equals("")) {
			error.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			error.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			error.setText("User name / Password Cannot be empty or spaces");
			return;
		}
		if (usertype == null ) {
			error.setText("Select user type");
			return;
		}
		else {
			error.setText("Login incorrect!");
			
		}
		
		
		if(usertype == "Admin") 
		{
		
		System.out.println(usertype);
			
		checkAdminCredentials(username, password);
		
		}
		
		else if(usertype == "Student") 
		{
			System.out.println(usertype);
			checkStudentCredentials(username, password, usertype);
		}
		else {
			System.out.print("Please Select User Type");
		}

    }
    
    @FXML
    void signup() throws IOException {
    	try {
    	AnchorPane root;
    	root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/SignupView.fxml"));
		Main.stage.setTitle("SignUp");
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
    	}
		catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
    	

    }
    
    
    public void checkAdminCredentials(String username, String password) {
    	try {
		Boolean isValid = model.getAdminCredentials(username, password);
		if (!isValid) {
			error.setText("User does not exist!");
			userTxt.setText("");
		    userPassword.setText("");
			userType.setValue(null);
			return;
		}
		else {
			AnchorPane root;
			root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/AdminView.fxml"));
			Main.stage.setTitle("Admin View");
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
		}
    } catch (Exception e) {
		System.out.println("Error occured while inflating view: which one " + e);
	}
		

	}
    
    public void checkStudentCredentials(String username, String password, String usertype) {
		Boolean isValid = model.getStudentCredentials(username, password);
		if (!isValid) {
			error.setText("User does not exist!");
			userTxt.setText("");
		    userPassword.setText("");
			userType.setValue(null);
			return;
		}
		
		
		try {
			AnchorPane root = null;
			if (isValid) {
				// If user is admin, inflate admin view
				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/DashboardView.fxml"));
				Main.stage.setTitle("Student View");
				System.out.println("Student Sucessfully login");
			} else {
				// If user is customer, inflate customer view
//				root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/ClientView.fxml"));
//				// ***Set user ID acquired from db****
//				//int user_id = 1; // hard coded for testing purposes only!!
//				// ClientController.setUser(user_id);
//				Main.stage.setTitle("Client View");
			}
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
		} catch (Exception e) {
			System.out.println("Error occured while inflating view: student one " + e);
		}

	}

	
}
