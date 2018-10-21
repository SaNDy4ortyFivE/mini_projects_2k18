package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect_to_db {
	public boolean checkUser(String email,String password){
		Connection c = null;
		Statement stmt = null;
		String passwd = null;
		boolean isUser = false;
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	         stmt = c.createStatement();
	         String sql1 = "SELECT password from userinfo where email ='" + email + "'";
	         ResultSet rs = stmt.executeQuery(sql1);
	         if(rs.next()){
	        	  passwd = rs.getString("password");
	         }
	         if(passwd.equals(password)){
	        	 isUser = true;
	         }
	

	         
		}catch(Exception e){
			e.printStackTrace();
		}
		return isUser;
	}
	
	
	public boolean adduser(String email,String passwd,String id){
		Connection c = null;
		Statement stmt = null;
		boolean success = false;
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	         
	         stmt = c.createStatement();
	         String sql1 = "INSERT INTO userinfo VALUES('" + email + "','" + passwd + "','" + id + "');"; 
	         System.out.println(sql1);
	         int useradded  = stmt.executeUpdate(sql1);
	         if(useradded == 1){
	        	 success = true;
	         }
	

	         
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
	}

	
	public static Connection getConnection() throws SQLException{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
		
		return con;
	}

	
	public String sendmail(String sender,String receiver,String mail,String subject){
		
		Connection c = null;
		Statement stmt = null;
		String  success = "false";
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "INSERT INTO mail VALUES('" + sender + "','" + receiver + "','" + mail + "','" + subject + "')"; 
	         System.out.println(sql1);
	         int temp = stmt.executeUpdate(sql1);
	         if(temp == 1){
	        	 success = "true";
	         }
	

	         
		}catch(Exception e){
			e.printStackTrace();
		}
		return success;
		
	}
	
	public String getmessage(String sender,String subject){
		Connection c = null;
		Statement stmt = null;
		String message="";
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "SELECT message FROM mail where sender='" + sender +"' and subject='" + subject +"'";
	         System.out.println(sql1);
	         ResultSet rs = stmt.executeQuery(sql1);
	         if(rs.next()){
	        	 message = rs.getString("message");
	        	 System.out.println(message);
	         }
	

	         
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
		
		
		
	}
	
	public boolean del_message_for_inbox(String sender,String receiver,String text){
		Connection c = null;
		Statement stmt = null;
		boolean succes=false;
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "UPDATE mail set receiver='' WHERE sender='" + sender + "' and receiver='" + receiver + "' and message='" + text + "';";
	         System.out.println(sql1);
	         int temp = stmt.executeUpdate(sql1);
	         if(temp==1){
	        	 succes=true;
	         }else{
	        	 succes=false;
	         }
	
	         
	         
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return succes;
	}
	
	
	public String get_message(String receiver,String subject){
		Connection c = null;
		Statement stmt = null;
		String message="";
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "SELECT message FROM mail where receiver='" + receiver +"' and subject='" + subject +"'";
	         System.out.println(sql1);
	         ResultSet rs = stmt.executeQuery(sql1);
	         if(rs.next()){
	        	 message = rs.getString("message");
	        	 System.out.println(message);
	         }
	

	         
		}catch(Exception e){
			e.printStackTrace();
		}
		return message;
		
		
		
	}
	
	public boolean del_message_for_outbox(String sender,String receiver,String text){
		Connection c = null;
		Statement stmt = null;
		boolean succes=false;
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "UPDATE mail set receiver='' WHERE sender='" + sender + "' and receiver='" + receiver + "' and message='" + text + "';";
	         System.out.println(sql1);
	         int temp = stmt.executeUpdate(sql1);
	         if(temp==1){
	        	 succes=true;
	         }else{
	        	 succes=false;
	         }
	
	         
	         
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return succes;
	}
	
	
	public boolean changepasswd(String id,String pass){
		Connection c = null;
		Statement stmt = null;
		boolean succes=false;
		try{
			Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/User","postgres", "root");
	       
	         stmt = c.createStatement();
	         String sql1 = "UPDATE userinfo SET password='"+pass+"' WHERE id='"+id+"';";
	         System.out.println(sql1);
	         int temp = stmt.executeUpdate(sql1);
	         if(temp==1){
	        	 succes=true;
	         }else{
	        	 succes=false;
	         }
	
	         
	         
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return succes;
	}
	

}


