package MedicineStore;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;  

public class MedicalitemsTab {  
	JTable tabletTable,syrupTable, maskTable, soapTable, creamTable, sanitizerTable;
	DefaultTableModel tabletModel,syrupModel,maskModel,soapModel,creamModel,sanitizerModel;

/**
 * @throws SQLException
 */
/**
 * @throws SQLException
 */
MedicalitemsTab() throws SQLException{  
	
	//initializing connection with the MySQL database
	final String DB_URL = "jdbc:mysql://localhost:3306/medicalitems";
	final String DB_USER = "root";
	final String DB_PASS = "host@123";
	Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    java.sql.Statement stmt = conn.createStatement();
 
    //creating a frame
    JFrame f = new JFrame("Medical products");  
    
    //creating a tabbed pane
    JTabbedPane tp = new JTabbedPane(); 
    tp.setBounds(20,20,700,500); 
    
    //initializing components of the frame
    JLabel userOrder = new JLabel("Enter the ID of the item which you want to buy");
    userOrder.setFont(new Font("Arial Black", Font.BOLD, 17));
    userOrder.setBounds(850, 90, 800, 30);
    f.add(userOrder);
    
    JTextField userOrderField = new JTextField();
    userOrderField.setBounds(850, 150, 50, 30);
    f.add(userOrderField);
    
    JButton btnAddtocart = new JButton("Add to cart");
    btnAddtocart.setBounds(850, 200, 100, 30);
    f.add(btnAddtocart);
    //setting the action listener for the 'add to cart button'
    btnAddtocart.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
    			if(Integer.parseInt(userOrderField.getText())>30) {
    				JOptionPane.showMessageDialog(f, "Invalid ID");
    			}
    			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM medical_item where id = ?");
    			pstmt.setInt(1, Integer.parseInt(userOrderField.getText().toString()));
    			ResultSet rsc = pstmt.executeQuery();
    			while(rsc.next()) {
    				CallableStatement cstmt = conn.prepareCall("{call insert_into_cart(?,?,?,?)}");
    				cstmt.setInt(1, rsc.getInt(1));
    				cstmt.setString(2, rsc.getString(2));
    				cstmt.setString(3, rsc.getString(3));
    				cstmt.setFloat(4,rsc.getFloat(4));
    				cstmt.execute();
    				JOptionPane.showMessageDialog(f, "Added to cart");
    				userOrderField.setText("");
    			}
    			
    		}catch(Exception ec) {
    			
    		}
    	}
    });
    
    JButton btnProceedpayment = new JButton("Proceed to order");
    btnProceedpayment.setBounds(855, 400, 150, 30);
    f.add(btnProceedpayment);
    //setting the action listener for the 'proceed to payment button'
    btnProceedpayment.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		try {
				ResultSet rsfinal = stmt.executeQuery("SELECT * FROM cart_items");
				if(rsfinal.next()==false) {
					JOptionPane.showMessageDialog(f, "Cart is empty");
				}
				else {
					new OrderPlacedFrame();
					btnAddtocart.setEnabled(false);
					f.dispose();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    });
    
    //creating panels for the various tabs
    JPanel tabletPanel = new JPanel();
    JPanel syrupPanel = new JPanel(); 
    JPanel maskPanel = new JPanel();
    JPanel soapPanel = new JPanel(); 
    JPanel creamPanel = new JPanel();
    JPanel sanitizerPanel = new JPanel();
    
    //creating objects of the DefaultTableModel class and assigning column names
    tabletModel = new DefaultTableModel(); tabletModel.addColumn("ID"); tabletModel.addColumn("Name"); tabletModel.addColumn("Type"); tabletModel.addColumn("Price (in Rs.)");
    syrupModel = new DefaultTableModel(); syrupModel.addColumn("ID"); syrupModel.addColumn("Name"); syrupModel.addColumn("Type"); syrupModel.addColumn("Price (in Rs.)");
    maskModel = new DefaultTableModel(); maskModel.addColumn("ID"); maskModel.addColumn("Name"); maskModel.addColumn("Type"); maskModel.addColumn("Price (in Rs.)");
    soapModel = new DefaultTableModel(); soapModel.addColumn("ID"); soapModel.addColumn("Name"); soapModel.addColumn("Type"); soapModel.addColumn("Price (in Rs.)");
    creamModel = new DefaultTableModel(); creamModel.addColumn("ID"); creamModel.addColumn("Name"); creamModel.addColumn("Type"); creamModel.addColumn("Price (in Rs.)");
    sanitizerModel = new DefaultTableModel(); sanitizerModel.addColumn("ID"); sanitizerModel.addColumn("Name"); sanitizerModel.addColumn("Type"); sanitizerModel.addColumn("Price (in Rs.)");
    
    //Tablet table and its attributes
	tabletTable = new JTable(tabletModel);
	tabletTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	tabletTable.setFillsViewportHeight(true);
	tabletTable.setRowHeight(60);
	tabletTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	tabletTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	tabletTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	tabletTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	tabletTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//Syrup table and its attributes
	syrupTable = new JTable(syrupModel);
	syrupTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	syrupTable.setFillsViewportHeight(true);
	syrupTable.setRowHeight(60);
	syrupTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	syrupTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	syrupTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	syrupTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	syrupTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//Mask table and its attributes
	maskTable = new JTable(maskModel);
	maskTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	maskTable.setFillsViewportHeight(true);
	maskTable.setRowHeight(60);
	maskTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	maskTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	maskTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	maskTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	maskTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//Soap table and its attributes
	soapTable = new JTable(soapModel);
	soapTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	soapTable.setFillsViewportHeight(true);
	soapTable.setRowHeight(60);
	soapTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	soapTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	soapTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	soapTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	soapTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//Cream table and its attributes
	creamTable = new JTable(creamModel);
	creamTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	creamTable.setFillsViewportHeight(true);
	creamTable.setRowHeight(60);
	creamTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	creamTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	creamTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	creamTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	creamTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//Sanitizer table and its attributes
	sanitizerTable = new JTable(sanitizerModel);
	sanitizerTable.setPreferredScrollableViewportSize(new Dimension(670, 500));
	sanitizerTable.setFillsViewportHeight(true);
	sanitizerTable.setRowHeight(60);
	sanitizerTable.setFont(new Font("Sans serif", Font.BOLD, 16));
	sanitizerTable.getColumnModel().getColumn(0).setPreferredWidth(1);
	sanitizerTable.getColumnModel().getColumn(1).setPreferredWidth(130);
	sanitizerTable.getColumnModel().getColumn(2).setPreferredWidth(70);
	sanitizerTable.getColumnModel().getColumn(3).setPreferredWidth(15);
    
	//adding tables to the Scrollpane objects and then adding to the respective panels
	tabletPanel.add(new JScrollPane(tabletTable));
	syrupPanel.add(new JScrollPane(syrupTable));
	maskPanel.add(new JScrollPane(maskTable));
	soapPanel.add(new JScrollPane(soapTable));
	creamPanel.add(new JScrollPane(creamTable));
	sanitizerPanel.add(new JScrollPane(sanitizerTable));
	

    //retrieving data according to type of product from the database
	//type = tablets
    try {
        ResultSet rs = stmt.executeQuery("SELECT * FROM medical_item where type = 'tablets'");
        while(rs.next()) {
        	String id = String.valueOf(rs.getInt(1));
        	String name = rs.getString(2);
        	String type = rs.getString(3);
        	String price = String.valueOf(rs.getFloat(4));
        	tabletModel.addRow(new Object[]{id,name,type ,price});      
        } 
    }catch(Exception e) {} 
   
 
  //type = syrups
    try {
    
        ResultSet rs1 = stmt.executeQuery("SELECT * FROM medical_item where type = 'syrup'");
        while(rs1.next()) {
        	String id = String.valueOf(rs1.getInt(1));
        	String name = rs1.getString(2);
        	String type = rs1.getString(3);
        	String price = String.valueOf(rs1.getFloat(4));
        	syrupModel.addRow(new Object[]{id,name,type ,price});  
        }
    }catch(Exception e) {}
    
    
  //type = masks
    try {
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM medical_item where type = 'mask'");
        while(rs2.next()) {
        	String id = String.valueOf(rs2.getInt(1));
        	String name = rs2.getString(2);
        	String type = rs2.getString(3);
        	String price = String.valueOf(rs2.getFloat(4));
        	maskModel.addRow(new Object[]{id,name,type ,price}); 
        }
    }catch(Exception e) {}
    
    
  //type = soaps
    try {
        ResultSet rs3 = stmt.executeQuery("SELECT * FROM medical_item where type = 'soap'");
        while(rs3.next()) {
        	String id = String.valueOf(rs3.getInt(1));
        	String name = rs3.getString(2);
        	String type = rs3.getString(3);
        	String price = String.valueOf(rs3.getFloat(4));
        	soapModel.addRow(new Object[]{id,name,type ,price});  
        }
    }catch(Exception e) {}
    
    
   
  //type = creams
    try {
        ResultSet rs4 = stmt.executeQuery("SELECT * FROM medical_item where type = 'cream'");
        while(rs4.next()) {
        	String id = String.valueOf(rs4.getInt(1));
        	String name = rs4.getString(2);
        	String type = rs4.getString(3);
        	String price = String.valueOf(rs4.getFloat(4));
        	creamModel.addRow(new Object[]{id,name,type ,price});  
        }
    }catch(Exception e) {}
    
    
  //type = sanitizers
    try {	
        ResultSet rs5 = stmt.executeQuery("SELECT * FROM medical_item where type = 'sanitizer'");
        while(rs5.next()) {
        	String id = String.valueOf(rs5.getInt(1));
        	String name = rs5.getString(2);
        	String type = rs5.getString(3);
        	String price = String.valueOf(rs5.getFloat(4));
        	sanitizerModel.addRow(new Object[]{id,name,type ,price});  
        }
    }catch(Exception e) {}
    
    
    //adding the panels to the tabbed pane
    tp.add("Tablets",tabletPanel); 
    tp.add("Syrups",syrupPanel);  
    tp.add("Masks",maskPanel); 
    tp.add("Medicated soaps",soapPanel);
    tp.add("Creams",creamPanel);
    tp.add("Sanitizers",sanitizerPanel);
    
    
    //setting attributes to the frame
    f.getContentPane().add(tp);  
    f.setSize(1500,900);  
    f.getContentPane().setLayout(null);  
    f.setVisible(true);  
}  
	public static void main(String[] args) throws SQLException {  
		new MedicalitemsTab();  
	}
}  