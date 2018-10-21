package application;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Screen2Controller {

	
	@FXML
	public TextField email;
	
	@FXML
	public PasswordField password;
	
	@FXML
	public PasswordField cpassword;
	
	@FXML
	public TextField id;
	
	@FXML
	public Button submit;
	
	@FXML
	public Button backbutton;
	
	@FXML
	public Label status; 
	
	@FXML
	public void adduser(ActionEvent event){
		String EMAIL,PASSWORD,CPASSWORD,ID;
		EMAIL = email.getText();
		PASSWORD = password.getText();
		CPASSWORD = cpassword.getText();
		ID = id.getText();
		boolean success = false;
		if(EMAIL.isEmpty()||PASSWORD.isEmpty()||CPASSWORD.isEmpty()||ID.isEmpty()){
		status.setText("Please fill all details");
			}
		else{
			if(!PASSWORD.equals(CPASSWORD)){
				status.setText("Passwords dosent match");
			}
			else{
				System.out.println("succesful");
				Connect_to_db addnew = new Connect_to_db();
				success = addnew.adduser(EMAIL,PASSWORD,ID);
				if(success == true){
					try {
						System.out.println("button works");
						Parent newpane = FXMLLoader.load(getClass().getResource("/application/Screen2.fxml"));
						Scene newScene = new Scene(newpane);
						
						
						Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
						
						window.setScene(newScene);
						window.show();
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else{
					status.setText("Error adding to database");
					
				}
					
				
				
			}
		}
	}
	
	@FXML
	public void takeback(ActionEvent event){
		try {
			System.out.println("button works");
			Parent newpane = FXMLLoader.load(getClass().getResource("/application/Screen1.fxml"));
			Scene newScene = new Scene(newpane);
			
			Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
			
			window.setScene(newScene);
			window.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
