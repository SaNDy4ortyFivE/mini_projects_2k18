package application;


import java.io.IOException;


import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController {

	public String NAME;
	
	@FXML
	public Button forgotpassword;
	
	@FXML
	public Button button1;
	
	@FXML
	public Button signup;
	
	@FXML
	public TextField email;
	
	@FXML
	public PasswordField password;
	
	@FXML
	public Label status;
	
	
	
	
	@FXML
	private void btnlogin(ActionEvent event){
		boolean isUser = false;
		System.out.println("button works");
		String emaill = email.getText();
		String passwd = password.getText();
		System.out.println(emaill + "" + passwd);
		Connect_to_db authenticate = new Connect_to_db();
		if(emaill.isEmpty()||passwd.isEmpty()){
			status.setText("Please fill the details");
		}
		else{
		 isUser = authenticate.checkUser(emaill, passwd);
		 if(isUser == true){
			 
			 try {
				 
				 
				    NAME=email.getText();
				    System.out.println(NAME);
				    System.out.println("hello there");
				    TableController.initDataforaccntnameinINBOX(NAME);
				 	
				    System.out.println("Inbox pressed");
				    FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("Inbox.fxml"));
					Parent tableparent = loader.load();
					
					Scene tablescene = new Scene(tableparent);
					
					TableController controller = loader.getController();
					
					
					Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
					
					window.setScene(tablescene);
					window.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
		 
		 else{
			 status.setText("username or password invalid");
		 }
		
		
		
		}
	}

	
	@FXML
	public void adduser(ActionEvent event){
		try {
			System.out.println("button works");
			Parent newpane = FXMLLoader.load(getClass().getResource("/application/Sample2.fxml"));
			Scene newScene = new Scene(newpane);
			
			Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
			
			window.setScene(newScene);
			window.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	public void changetoinbox(ActionEvent event){
		 try {
			 
			 
			    NAME=email.getText();
			    System.out.println(NAME);
			    System.out.println("hello there");
			    TableController.initDataforaccntnameinINBOX(NAME);
			 	
			    System.out.println("Inbox pressed");
			    FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("Inbox.fxml"));
				Parent tableparent = loader.load();
				
				Scene tablescene = new Scene(tableparent);
				
				TableController controller = loader.getController();
				
				
				Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
				
				window.setScene(tablescene);
				window.show();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@FXML
	public void changepassword(ActionEvent event) throws IOException{
		//MessageController.initdataforaccntnameinview(name);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("cpasswordscreen.fxml"));
		Parent tableparent = loader.load();
		
		Scene msgscene = new Scene(tableparent);
		
		//MessageController controller = loader.getController();
		//controller.initData1(table.getSelectionModel().getSelectedItem());
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(msgscene);
		window.show();
	}

}
