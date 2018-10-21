package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

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
import javafx.stage.Stage;
import marytts.modules.synthesis.Voice;

public class MessageController implements Initializable {
	
	private ModelTable selectedmsage;
	
	private ModelTable1 selected_message;
	
	private static String name;
	
	@FXML
	private Label label1;
	
	@FXML
	private Label label2;
	
	@FXML
	private Label sender_name;
	
	@FXML
	private Label subject_subject;
	
	@FXML
	private Button deletemessage;
	
	@FXML
	private Label msgarea;
	
	@FXML
	private Label account__name;

	
	@FXML
	private Button goback;
	
	@FXML
	private Button inbox;
	
	@FXML
	private Button outbox;
	
	@FXML
	private Button compose;
	
	@FXML
	private Button LOGOUT;
	
	@FXML
	private void goback(ActionEvent event){
		
			 try {
					Parent newpane = FXMLLoader.load(getClass().getResource("/application/Inbox.fxml"));
					Scene newScene = new Scene(newpane);
					
					Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
					
					window.setScene(newScene);
					window.show();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
	}
	
	public void initData(ModelTable1 modelTable1){
		
		selected_message = modelTable1 ;
		
		account__name.setText(MessageController.name);
		
		String text1=selected_message.getSubject();
		System.out.println(text1);
		//msg.setText(selectedmsage.getSubject());
		String text2=selected_message.getReceiver();
		System.out.println(text2);
		
		label1.setText("RECEIVER:");
		label2.setText("SUBJECT:");
		
		sender_name.setText(text2);
		subject_subject.setText(text1);
		
		Connect_to_db gm = new Connect_to_db();
		
		msgarea.setText(gm.get_message(text2, text1));
		
		TextToSpeech tts =  new TextToSpeech();
		 Voice.getAvailableVoices().stream().forEach(System.out::println);
		 tts.setVoice("cmu-rms-hsmm");
		 String say;
		 //Scanner sc = new Scanner(System.in);
		 say = msgarea.getText();
		 tts.speak(say,1.0f, false,false);
		 //sc.close();
		
		
	}
	
	public void initData1(ModelTable modelTable){
		selectedmsage = modelTable ;
		
		account__name.setText(MessageController.name);
		
		String text1=selectedmsage.getSubject();
		System.out.println(text1);
		//msg.setText(selectedmsage.getSubject());
		String text2=selectedmsage.getSender();
		System.out.println(text2);
		
		label1.setText("SENDER:");
		label2.setText("SUBJECT:");
		
		sender_name.setText(text2);
		subject_subject.setText(text1);
		
		Connect_to_db gm = new Connect_to_db();
		
		msgarea.setText(gm.getmessage(text2, text1));
		
		/*TextToSpeech tts =  new TextToSpeech();
		 Voice.getAvailableVoices().stream().forEach(System.out::println);
		 tts.setVoice("cmu-rms-hsmm");
		 String say;
		 //Scanner sc = new Scanner(System.in);
		 say = msgarea.getText();
		 tts.speak(say,1.0f, false,false);*/
		 //sc.close();
		
		
	}
	
	
	@FXML
	public void changetoinbox(ActionEvent event){
		
		 try {
			 
			 
			    String NAME=MessageController.name;
			    //System.out.println(NAME);
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
	public void changetooutbox(ActionEvent event){
		
		try {
			 
		    String NAME=MessageController.name;
		    System.out.println(NAME);
		    System.out.println("hello there");
		    OutBoxController.initDataforaccntnameinINBOX(NAME);
		 	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Outbox.fxml"));
			Parent tableparent = loader.load();
			
			Scene tablescene = new Scene(tableparent);
			
			OutBoxController controller = loader.getController();
			
			
			Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
			
			window.setScene(tablescene);
			window.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}
	

	@FXML
	public void changetocompose(ActionEvent event) throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Compose.fxml"));
		Parent tableparent = loader.load();
		
		Scene compscene = new Scene(tableparent);
		
		ComposeController controller = loader.getController();
		
		controller.initDataforaccntnameinINBOX("sandamkaran3030");
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(compscene);
		window.show();
		
		
		
	}
	
	
	@FXML
	public void changetomainscreen(ActionEvent event){
		
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
	
	@FXML
	public void delete_message(ActionEvent event){
		Connect_to_db del = new Connect_to_db();
		String temp = label1.getText();
		boolean succes;
		if(temp.equals("SENDER:")){
			
			succes=del.del_message_for_inbox(sender_name.getText(),account__name.getText(),msgarea.getText());
			if(succes==true){
				msgarea.setText("mail deleted");
			}
			else{
				msgarea.setText("cannnot delete");
			}
			
		}else{
			succes=del.del_message_for_outbox(account__name.getText(),sender_name.getText(),msgarea.getText());
			if(succes==true){
				msgarea.setText("mail deleted");
			}
			else{
				msgarea.setText("cannnot delete");
			}
			
		}
	}
	
	
	public static void initdataforaccntnameinview(String name){
		
		MessageController.name = name;
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
