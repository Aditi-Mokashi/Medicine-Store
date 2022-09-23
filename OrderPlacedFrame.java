package MedicineStore;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderPlacedFrame {
	JTable table;
	DefaultTableModel defaultTableModel;
	JLabel finalPrice,rupee,finalPriceInRupees;
	JButton payment;
	
	
OrderPlacedFrame() throws SQLException{
	
	//creating a frame
	JFrame f = new JFrame("Payment");
	
	//initializing the database connection with MySQL database
	final String DB_URL = "jdbc:mysql://localhost:3306/medicalitems";
	final String DB_USER = "root";
	final String DB_PASS = "host@123";
	
	//initializing components of the frame
	finalPrice = new JLabel("Your total price is");
	finalPrice.setFont(new Font("Arial Black", Font.BOLD, 17));
	finalPrice.setBounds(220, 350, 200, 50);
	f.add(finalPrice);
	
	rupee = new JLabel("Rs");
	rupee.setFont(new Font("Arial Black", Font.BOLD, 17));
	rupee.setBounds(220,390,100,50);
	f.add(rupee);
	
	finalPriceInRupees = new JLabel();
	finalPriceInRupees.setFont(new Font("Arial Black", Font.BOLD, 17));
	finalPriceInRupees.setBounds(260,390,150,50);
	f.add(finalPriceInRupees);
	
	payment = new JButton("Place order");
	payment.setBounds(280,480,200,30);
	f.add(payment);
	
	//setting the action listener for the 'payment' button
	payment.addActionListener(new ActionListener(){  
    	public void actionPerformed(ActionEvent e){  
    		Connection conn;
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
				java.sql.Statement stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM cart_items");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		JOptionPane.showMessageDialog(f, "Your order has been placed");
    		new WelcomeFrame();
    		f.dispose();
    	}
    }); 
	
	//creating object of the DefaultTableModel and setting the columns & attributes
	defaultTableModel = new DefaultTableModel();
	table = new JTable(defaultTableModel);
	table.setPreferredScrollableViewportSize(new Dimension(300, 100));
    table.setFillsViewportHeight(true);
    table.setRowHeight(30);
    table.setFont(new Font("Sans serif", Font.BOLD, 14));
	f.add(new JScrollPane(table));
	defaultTableModel.addColumn("Name");
    defaultTableModel.addColumn("Price");
	
   //retrieving values from the database of the cart table
    try {
    	float finalPr = (float) 0.0;
    	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        java.sql.Statement stmt = conn.createStatement();
    	 ResultSet rs = stmt.executeQuery("SELECT name,price FROM cart_items");
    	 while(rs.next()) {
    		 String name = rs.getString("name");
    		 String price = String.valueOf(rs.getFloat("price"));
    		 defaultTableModel.addRow(new Object[]{name, price});
    		 finalPr = finalPr + Float.parseFloat(price);
    		 String userPrice = String.valueOf(finalPr);
    		 finalPriceInRupees.setText(userPrice);
    	 }
    }
    	 catch(Exception e) {}   
    
    //setting the attributes of the frame
	f.setSize(500,600);  
	f.setVisible(true); 
	 
}


public static void main(String args[]) throws SQLException {
	new OrderPlacedFrame();
}
}
