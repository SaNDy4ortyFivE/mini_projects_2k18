package temp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Anagram_checker extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField word1text;
	private JTextField word2text;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Anagram_checker frame = new Anagram_checker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Anagram_checker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 216);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWord = new JLabel("Word 1:");
		lblWord.setBounds(27, 11, 73, 25);
		contentPane.add(lblWord);
		
		JLabel lblWord_1 = new JLabel("Word  2:");
		lblWord_1.setBounds(27, 47, 73, 25);
		contentPane.add(lblWord_1);
		
		word1text = new JTextField();
		word1text.setBounds(110, 13, 346, 20);
		contentPane.add(word1text);
		word1text.setColumns(10);
		
		word2text = new JTextField();
		word2text.setBounds(110, 49, 346, 20);
		contentPane.add(word2text);
		word2text.setColumns(10);
		
		JLabel manipulation_label = new JLabel("");
		manipulation_label.setBounds(27, 147, 429, 20);
		contentPane.add(manipulation_label);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//input strings
				String word1,word2;
				word1 = word1text.getText();
				word2= word2text.getText();
				
				if(word1.length()!=word2.length()){
					
					//show message as not anagram
					JOptionPane.showMessageDialog(null,"Both words are not Anagram of Each other");
				}
				else{
					//removing the whitespace
					word1.replaceAll("\\s","");
					word2.replaceAll("\\s","");
					
					
					//converting string to char array 
					
					char[] word1array = word1.toCharArray();
					char[] word2array = word2.toCharArray();
					
					System.out.println(word1array);
					System.out.println(word2array);
					
					//sorting the array
					Arrays.sort(word1array);
					Arrays.sort(word2array);
					
					System.out.println(word1array);
					System.out.println(word2array);
					
					//comparing both the arrays
					if(Arrays.equals(word1array, word2array)){
						JOptionPane.showMessageDialog(null,"Words are Anagram");
					}else{
						JOptionPane.showMessageDialog(null,"Both words are not Anagram of Each other");
						
						//program for manipulation
						
						int count = 0; 
						  
				        // store the count of character 
				        int char_count[] = new int[26]; 
				  
				        // iterate though the first String and update  
				        // count 
				        for (int i = 0; i < word1.length(); i++)  
				            char_count[word1.charAt(i) - 'a']++;         
				  
				        // iterate through the second string 
				        // update char_count. 
				        // if character is not found in char_count 
				        // then increase count 
				        for (int i = 0; i < word2.length(); i++)  {
				            if (char_count[word2.charAt(i) - 'a']-- <= 0) 
				                count++;
					
				        }
				        
				        //printing the count required
				        
				        manipulation_label.setText("Number of Manipulations required without deleting characters:"+String.valueOf(count));
					}   
				}
				
			}
		});
		btnCheck.setBounds(91, 106, 89, 23);
		contentPane.add(btnCheck);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//clear all the text field
				word1text.setText("");
				word2text.setText("");
				
			}
		});
		btnClear.setBounds(228, 106, 89, 23);
		contentPane.add(btnClear);
		
		
	}
}
