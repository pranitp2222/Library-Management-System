package controllers;



import java.time.LocalDate;
import java.util.Date;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import models.SignUpModel;
import models.adminModel;

public class AdminController {


    @FXML
    private TextField adminBookCode;

    @FXML
    private TextField adminNameAuthor;

    @FXML
    private TextField adminNameBook;
    
    @FXML
    private TextField adminBookRemove;
    
    @FXML
    private DatePicker datepick;
    
    @FXML
    private TextField bookupdate;
    
    private adminModel model;

	public AdminController() {
		model = new adminModel();
		
	}

    @FXML
   void adminLogout() {
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
    

    @FXML
    void addBookToDB() {
    	String bookName = this.adminNameBook.getText();
		String authorName = this.adminNameAuthor.getText();
		String bookCode = this.adminBookCode.getText();
		LocalDate date = this.datepick.getValue();
		System.out.print(date);
		Alert alert;
		if (bookName == null || bookName.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book Name Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
		if (authorName == null || authorName.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Author Name Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
		if (date == null) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Date Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
		if (bookCode == null || bookCode.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book Code Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
		
		addBook(bookName, authorName, bookCode, date);
		adminNameBook.setText("");
		adminNameAuthor.setText("");
		adminBookCode.setText("");
		datepick.setValue(null);

    }
    
    @FXML
    void removebookFromDB( ) {
    	String removeBookCode = this.adminBookRemove.getText();
    	Alert alert;
    	if (removeBookCode == null || removeBookCode.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book Code Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
    	removeBook(removeBookCode);
    	adminBookRemove.setText("");
    }
    
    @FXML
    void updatebookbtn() {
    	String updateBookCode = this.bookupdate.getText();
    	Alert alert;
    	if (updateBookCode == null || updateBookCode.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book Code Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
    	updateBook(updateBookCode);
    	bookupdate.setText("");

    }
    
    @FXML
    void updatebookavail() {
    	String updateBookCode = this.bookupdate.getText();
    	Alert alert;
    	if (updateBookCode == null || updateBookCode.trim().equals("")) {
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book Code Cannot be empty or spaces");
            alert.showAndWait();
			return;
		}
    	updateBookAvail(updateBookCode);
    	bookupdate.setText("");
    }


    
    public void addBook(String bookName, String authorName, String bookCode, LocalDate date) {
		Boolean isValid = model.addBookDetails(bookName, authorName, bookCode, date);
		if (!isValid) {
			Alert alert;
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is not added");
            alert.showAndWait();
		}
		else {
			Alert alert;
			alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is added");
            alert.showAndWait();
			System.out.print("Book is added");
		}
		

}
    public void removeBook(String removeBookCode) {
		Boolean isValid = model.removeBookDetails(removeBookCode);
		if (!isValid) {
			System.out.print("Book is not removed");
			Alert alert;
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is not removed");
            alert.showAndWait();
		}
		else {
			System.out.print("Book is remove");
			Alert alert;
			alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is remove");
            alert.showAndWait();
			

		}
    }
    
    
    public void updateBook(String updateBookCode) {
		Boolean isValid = model.updateBookDetails(updateBookCode);
		if (!isValid) {
			System.out.print("Book is not updated to Unavailable");
			Alert alert;
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is not updated to Unavailable");
            alert.showAndWait();
		}
		else {
			System.out.print("Book is updated to Unavailable");
			Alert alert;
			alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is updated to Unavailable");
            alert.showAndWait();
		}
    }
    
    public void updateBookAvail(String updateBookCode) {
		Boolean isValid = model.updateBookDetailsAvail(updateBookCode);
		if (!isValid) {
			System.out.print("Book is not updated to available");
			Alert alert;
			alert = new Alert(AlertType.ERROR);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is not updated to available");
            alert.showAndWait();
		}
		else {
			System.out.print("Book is updated to available");
			Alert alert;
			alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Admin Message");
            alert.setHeaderText(null);
            alert.setContentText("Book is updated to available");
            alert.showAndWait();
		}
    }

}