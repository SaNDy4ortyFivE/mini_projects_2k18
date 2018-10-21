package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

public class OutBoxController extends SampleController implements Initializable {

	public static String name;
	@FXML
	public Button saymessage;
	
	@FXML
	private Button LOG_out;
	
	@FXML
	private Button viewmessage;
	
	@FXML
	private Button delete;
	
	
	@FXML
	private Button changecompose;
	
	@FXML
	private Button inbox;
	
	@FXML
	private Label account_name;

	@FXML
	private TableView<ModelTable1>table;
	
	
	@FXML
	private TableColumn<ModelTable1,String>col_receiver;
	@FXML
	private TableColumn<ModelTable1,String>col_subject;
	
	@FXML
	public void viewmessage(ActionEvent event) throws IOException{
		
		
		
		MessageController.initdataforaccntnameinview(name);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Message.fxml"));
		Parent tableparent = loader.load();
		
		Scene msgscene = new Scene(tableparent);
		
		MessageController controller = loader.getController();
		controller.initData(table.getSelectionModel().getSelectedItem());
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(msgscene);
		window.show();
		
		
		
		
		
	}
	
	public  static void initDataforaccntnameinINBOX(String s){
		
		System.out.println("this is a test" + s);
		name = s;
		
	}
	
	ObservableList<ModelTable1> obblist = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			System.out.println("THISISATEST");

			account_name.setText(name);
 		
			System.out.println(name);
			
			Connection c = Connect_to_db.getConnection();
			String SQL = "SELECT receiver,subject FROM mail where sender = '" + name + "'";
			
			System.out.println(SQL);
			
			ResultSet rs = c.createStatement().executeQuery(SQL);
			
			while(rs.next()){
				obblist.add(new ModelTable1 (rs.getString("receiver"),rs.getString("subject")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		//System.out.println("ERROR");
		col_receiver.setCellValueFactory(new PropertyValueFactory<>("receiver"));
		col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
		//System.out.println("ERROE");
		table.setItems(obblist);
		
	}

	
	@FXML
	public void changetocompose(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Compose.fxml"));
		Parent tableparent = loader.load();
		
		Scene compscene = new Scene(tableparent);
		
		ComposeController controller = loader.getController();
		
		controller.initDataforaccntnameinINBOX(account_name.getText());
		
		Stage window = (Stage)(((Node) event.getSource()).getScene().getWindow());
		
		window.setScene(compscene);
		window.show();
		
	}
	
	@FXML
	public
	void changetoinbox(ActionEvent event){
	 try {
		 
		    NAME=account_name.getText();
		    System.out.println(NAME);
		    System.out.println("hello there");
		    TableController.initDataforaccntnameinINBOX(NAME);
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
	public void backtomainscreen(ActionEvent event){
		
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
	public void saythedamnmessage(ActionEvent event){
		TextToSpeech tts =  new TextToSpeech();
		 Voice.getAvailableVoices().stream().forEach(System.out::println);
		 tts.setVoice("cmu-rms-hsmm");
		 String say;
		 //Scanner sc = new Scanner(System.in);
		 Connect_to_db saymsg = new Connect_to_db();
		 say = saymsg.get_message(table.getSelectionModel().getSelectedItem().getReceiver(),table.getSelectionModel().getSelectedItem().getSubject());
		 
		 tts.speak(say,1.0f, false,false);
		 //sc.close();
	}


}
