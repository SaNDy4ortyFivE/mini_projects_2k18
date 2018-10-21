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

public class ChangepasswordController {

	@FXML
	private Button change;
	
	@FXML
	private TextField idfield;
	
	@FXML
	private PasswordField passbox;
	
	@FXML
	private PasswordField cpassbox;
	
	@FXML
	private Label  warning;
	
	@FXML
	public void change_password(ActionEvent event) throws IOException{
		String id,pass,cpass;
		id = idfield.getText();
		pass = passbox.getText();
		cpass = cpassbox.getText();
		
		
		if(id.isEmpty()||pass.isEmpty()||cpass.isEmpty()){
			warning.setText("please fill all the details");
		}
		else{
			if(pass.equals(cpass)){
				
				//Connect_to_db;
				Connect_to_db cp = new Connect_to_db();

				boolean succes = cp.changepasswd(id, pass);
				
				if(succes==true){
					System.out.println("button works");
					Parent newpane = FXMLLoader.load(getClass().getResource("/application/Screen1.fxml"));
					Scene newScene = new Scene(newpane);
					
					Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
					
					window.setScene(newScene);
					window.show();
				}else{
					warning.setText("Internal error occured/Id not found");
				}
				
			}else{
				warning.setText("Passwords dosent match");
			}
		}
		
		
	}
	
	
	
	
	
}
