package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ComposeController implements Initializable{

@FXML
private Button back;
	
@FXML
private Button clear;
	
@FXML
private TextField to;

@FXML
private TextField subject;

@FXML
private TextArea message;

@FXML
private Label warn;

@FXML
private Button send;

@FXML
private Label accountname;


@FXML
public void clearbutton(ActionEvent event){
	message.setText("");
	to.setText("");
}

@FXML
public void tosend(ActionEvent event){
	String TO,SUB,MSG,WARN,status;
	TO = to.getText();
	SUB = subject.getText();
	MSG = message.getText();
	Connect_to_db  sm = new Connect_to_db();
	status = sm.sendmail(accountname.getText(),TO,MSG,SUB);
	if(status.equals("true")){
		System.out.println("Message SENT");
	}else{
		System.out.println("Error occured");
		WARN = "ERROR OCCURED";
		warn.setText(WARN);
	}
}


public void initDataforaccntnameinINBOX(String s){
	String temp = s;
	accountname.setText(temp);
}

@FXML
public void takebck(ActionEvent event){
	 try {
		 

		    //System.out.println(NAME);
		    System.out.println("hello there");
		    TableController.initDataforaccntnameinINBOX(accountname.getText());
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


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}


}
