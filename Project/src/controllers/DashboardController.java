package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.availableBooks;
import models.returnBook;
import models.DBConnect;

public class DashboardController implements Initializable {

    @FXML
    private TableColumn<availableBooks, String> author_col;

    @FXML
    private Button availBook_btn;

    @FXML
    private AnchorPane available_form;

    @FXML
    private TableColumn<availableBooks, String> bookName_col;

    @FXML
    private TableColumn<availableBooks, String> booktype_col;

    @FXML
    private Button issuebook_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button returnBook_btn;

    @FXML
    private Button save_btn;

    @FXML
    private TableColumn<availableBooks, String> subject_col;
    
    @FXML
    private TableView<availableBooks> tableView;
    
    
    @FXML
    private AnchorPane issuebook_form;
    
    @FXML
    private Label ibookAuthor;

    @FXML
    private Label ibookCode;

    @FXML
    private Label ibookName;

    @FXML
    private TextField issueFirstN;

    @FXML
    private TextField issueLastN;
    
    @FXML
    private TextField bookcode;
    
    @FXML
    private Label issueDate;
    
    
    @FXML
    private TextField issueStdId;
    
    @FXML
    private TableColumn<returnBook, String> returnBookCode;

    @FXML
    private TableView<returnBook> returnBookTable;

    @FXML
    private TableColumn<returnBook, String> returnFName;

    @FXML
    private TableColumn<returnBook, String> returnIssueDate;

    @FXML
    private TableColumn<returnBook, String> returnLName;

    @FXML
    private TableColumn<returnBook, String> returnSID;
    
    @FXML
    private Label ibookDate;

    @FXML
    private AnchorPane returnbook_view;

  
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	 showAvailableBooks();
    	 availbtn();
    	 displayDate();
    	 showBookReturn();

		
	}
    
    public ObservableList<availableBooks> dataList() {

        ObservableList<availableBooks> listBooks = FXCollections.observableArrayList();
        String status = "available";
        String sql = "SELECT * FROM lms_book WHERE available = '" + status + "'";

      
         connect = DBConnect.getConnection();
        
        try {

            availableBooks aBooks;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                aBooks = new availableBooks(result.getString("title"),
                        result.getString("author"), result.getString("type"),
                         result.getDate("publish"));

                listBooks.add(aBooks);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listBooks;
    }
    
    
    
    private ObservableList<availableBooks> listBook;

    public void showAvailableBooks() {

       listBook = dataList();

        bookName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        booktype_col.setCellValueFactory(new PropertyValueFactory<>("bookType"));
        subject_col.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableView.setItems(listBook);

    }

    
    
    
    public void logout() throws IOException {
    	try {
    	AnchorPane root;
    	root = (AnchorPane) FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
		Main.stage.setTitle("SignUp");
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
    	}
		catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
    }
    
    public void getbookCode() {


        String sql = "SELECT * FROM lms_book WHERE type = '" + bookcode.getText() + "'";

        connect = DBConnect.getConnection();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;

            Alert alert;

            if (bookcode.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the book code.");
                alert.showAndWait();

            } else {

                while (result.next()) {

                	ibookName.setText(result.getString("title"));
                	ibookAuthor.setText(result.getString("author"));
                	ibookCode.setText(result.getString("type"));
                	ibookDate.setText(result.getString("publish"));
                    check = true;
                }

                if (!check) {
                	alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Book not found.");
                    alert.showAndWait();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void clearIssue() {
    	
    	ibookName.setText("");
    	ibookAuthor.setText("");
        ibookDate.setText("");
        ibookCode.setText("");
        issueLastN.setText("");
        issueFirstN.setText("");
        issueStdId.setText("");
        bookcode.setText("");
        
        

    }
    
    public void displayDate() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new java.util.Date());
        issueDate.setText(date);
    }
    
    public void issuebookBtn() {
    	
    	Date date = new Date();
    	
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO lms_issue_return VALUES (?,?,?,?,?,?,?,?,?)";

        connect = DBConnect.getConnection();

        try {

            Alert alert;

            if ( issueStdId.getText().isEmpty() || issueFirstN.getText().isEmpty()
                    || issueLastN.getText().isEmpty()) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please type complete Student Details");
                alert.showAndWait();
            } 
            else if (bookcode.getText().isEmpty()) 
            {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the book you want to take.");
                alert.showAndWait();
            } 
            else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, ibookCode.getText());
                prepare.setString(2, issueStdId.getText());
                prepare.setString(3, issueFirstN.getText());
                prepare.setString(4, issueLastN.getText());
                prepare.setDate(5, sqlDate);
                String check = "Not Return";
                prepare.setString(6, check);
                prepare.setString(7, ibookName.getText());
                prepare.setString(8, ibookAuthor.getText());
                prepare.setString(9, ibookDate.getText());
               
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successful!y take the book!");
                alert.showAndWait();

                
                String book = ibookCode.getText();
                makeUnavailable(book);
                showAvailableBooks();
                clearIssue();
                

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void makeUnavailable(String book) {
    	
    	String sql = "UPDATE lms_book SET available = 'Not available' WHERE type = '" + book + "'";
    	connect = DBConnect.getConnection();
    	try {
    		statement = connect.createStatement();
            statement.executeUpdate(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    	
    }
    
    public ObservableList<returnBook> returnBook() {

        ObservableList<returnBook> bookReturnData = FXCollections.observableArrayList();

        String checkBk = "Not Return";

        String sql = "SELECT * FROM lms_issue_return WHERE checkBook = '" + checkBk + "'";

        connect = DBConnect.getConnection();

        try {

            returnBook rBook;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                rBook = new returnBook(result.getString("Bid"), result.getString("Sid"),
                        result.getString("Fname"), result.getString("Lname"),
                        result.getDate("date"));
                bookReturnData.add(rBook);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookReturnData;

    }
    
    ObservableList<returnBook> retBook;

    public void showBookReturn() {

        retBook = returnBook();

        returnBookCode.setCellValueFactory(new PropertyValueFactory<>("bookcode"));
        returnSID.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        returnFName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        returnLName.setCellValueFactory(new PropertyValueFactory<>("lname"));
        returnIssueDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        returnBookTable.setItems(retBook);

    }
    
    @FXML
    public void rBook() {
    	  returnBook returnbook = returnBookTable.getSelectionModel().getSelectedItem();
    	  String bookCode = returnbook.getBookcode();
    	  String sql = "UPDATE lms_issue_return SET checkBook = 'Returned' WHERE Bid = '" + returnbook.getBookcode() + "'";

    	  connect = DBConnect.getConnection();

          Alert alert;

          try {

                  statement = connect.createStatement();
                  statement.executeUpdate(sql);

                  alert = new Alert(AlertType.INFORMATION);
                  alert.setTitle("Admin Message");
                  alert.setHeaderText(null);
                  alert.setContentText("Successfully returned!");
                  alert.showAndWait();

                  showBookReturn();
                  makeAvailable(bookCode);
                  showAvailableBooks();

              
          } catch (Exception e) {
              e.printStackTrace();
          }

    }
    
    public void makeAvailable(String bookCode) {
    	String sql = "UPDATE lms_book SET available = 'available' WHERE type = '" + bookCode + "'";
    	connect = DBConnect.getConnection();
    	try {
    		statement = connect.createStatement();
            statement.executeUpdate(sql);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
    }
    
    @FXML
    public void availbtn() {
    	available_form.setVisible(true);
    	issuebook_form.setVisible(false);
    	returnbook_view.setVisible(false);
    	
        

    }

    @FXML
    public void issuebtn() {
    	issuebook_form.setVisible(true);
        available_form.setVisible(false);
    	returnbook_view.setVisible(false);
    
    	
    }
    
    @FXML
    public void return_btn() {
    	returnbook_view.setVisible(true);
    	issuebook_form.setVisible(false);
        available_form.setVisible(false);
        showBookReturn();
    }

}



