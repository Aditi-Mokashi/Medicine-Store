package MedicineStore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;   

public class WelcomeFrame {  
WelcomeFrame(){
	//creating a frame
    JFrame f = new JFrame("Welcome!");  
    
    //initializing the components of the welcome frame
    JLabel welcomeLabel = new JLabel("Welcome to our store!");
    welcomeLabel.setBounds(500, 30, 300, 60);
    welcomeLabel.setFont(new Font("SANS_SERIF",Font.PLAIN,30));
    f.add(welcomeLabel);
    
    
    JLabel nameLabel = new JLabel("Enter your name : ");
    nameLabel.setFont(new Font("SANS_SERIF",Font.PLAIN,20));
    nameLabel.setBounds(350,150,250,30);
    f.add(nameLabel);
    JTextField userName = new JTextField();
    userName.setBounds(550,150, 250,40);
    userName.setFont(new Font("SANS_SERIF",Font.PLAIN,16));  
    f.add(userName);  
    
    JLabel phoneLabel = new JLabel("Enter your contact no. : ");
    phoneLabel.setFont(new Font("SANS_SERIF",Font.PLAIN,20));
    phoneLabel.setBounds(350,250,250,30);
    f.add(phoneLabel);
    JTextField userPhone = new JTextField();
    userPhone.setFont(new Font("SANS_SERIF",Font.PLAIN,16));
    userPhone.setBounds(550,250, 250,40);  
    f.add(userPhone);  
 
    JButton btnProceed = new JButton("Proceed"); 
    btnProceed.setBounds(550,350,95,30);
    f.add(btnProceed);
    
   
   //setting action listener on the proceed button to proceed with the buying of products
    btnProceed.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){  
    		try {
    			if(userName.getText().length()==0) {
    				JOptionPane.showMessageDialog(f, "Enter your details");
    			}
    			else {
    				JOptionPane.showMessageDialog(f, "Welcome, "+userName.getText()+"! You can explore and buy items further.");
        			new MedicalitemsTab();
        			f.dispose();
    			}
    			
    		} catch (SQLException e1) {
    				e1.printStackTrace();
    			}
    		 
        }  
    });  
   
    //setting the layout of the frame
    f.setSize(1500,900);  
    f.setLayout(null);  
    f.setVisible(true);   
}
public static void main(String[] args) {  
	new WelcomeFrame();  
}
}  