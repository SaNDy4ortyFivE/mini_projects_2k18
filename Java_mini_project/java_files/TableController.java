package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import marytts.modules.synthesis.Voice;
import application.TextToSpeech;

public class TableController extends SampleController implements Initializable{
	
	public static String name;
	
	@FXML
	private Button read;
	
	@FXML
	private Button logout;
	
	@FXML
	private Button vmsage;
	
	@FXML
	private Button compose;
	
	@FXML
	private Button outbox;
	
	@FXML
	private Label accntname;

	@FXML
	private TableView<ModelTable>table;
	
	
	@FXML
	private TableColumn<ModelTable,String>col_sender;
	
	@FXML
	private TableColumn<ModelTable,String>col_subject;
	
	@FXML
	public void viewmessage(ActionEvent event) throws IOException{
		
		
		MessageController.initdataforaccntnameinview(name);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Message.fxml"));
		Parent tableparent = loader.load();
		
		Scene msgscene = new Scene(tableparent);
		
		MessageController controller = loader.getController();
		controller.initData1(table.getSelectionModel().getSelectedItem());
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(msgscene);
		window.show();
		
		
		
		
	}
	
	
	//back to login screen;
	@FXML
	public void backtohomescreen(ActionEvent event) throws IOException{
		
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
		
	
	
	public  static void initDataforaccntnameinINBOX(String s){
		
		System.out.println("this is a test" + s);
		name = s;
		
	}
	
	ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			System.out.println("THISISATEST");

			accntname.setText(name);
 		
			System.out.println(name);
			
			Connection c = Connect_to_db.getConnection();
			String SQL = "SELECT sender,subject FROM mail where receiver = '" + name + "'";
			
			System.out.println(SQL);
			
			ResultSet rs = c.createStatement().executeQuery(SQL);
			
			while(rs.next()){
				oblist.add(new ModelTable (rs.getString("sender"),rs.getString("subject")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		col_sender.setCellValueFactory(new PropertyValueFactory<>("sender"));
		col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		
		table.setItems(oblist);
		
	}

	
	@FXML
	public void changetocompose(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Compose.fxml"));
		Parent tableparent = loader.load();
		
		Scene compscene = new Scene(tableparent);
		
		ComposeController controller = loader.getController();
		
		controller.initDataforaccntnameinINBOX(accntname.getText());
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(compscene);
		window.show();
		
	}
	
	@FXML
	public void changetooutbox(ActionEvent event) throws IOException{
		 try {
			 
			    NAME=accntname.getText();
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
	public void say(ActionEvent event){
		TextToSpeech tts =  new TextToSpeech();
		 Voice.getAvailableVoices().stream().forEach(System.out::println);
		 tts.setVoice("cmu-rms-hsmm");
		 String say;
		 //Scanner sc = new Scanner(System.in);
		 Connect_to_db saymsg = new Connect_to_db();
		 say = saymsg.getmessage(table.getSelectionModel().getSelectedItem().getSender(),table.getSelectionModel().getSelectedItem().getSubject());
		 
		 tts.speak(say,1.0f, false,false);
		 //sc.close();
	}
	
	}
