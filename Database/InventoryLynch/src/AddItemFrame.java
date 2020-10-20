import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.NumberFormat;
import java.util.Currency;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddItemFrame extends JFrame {

	private JPanel contentPane;
	
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	NumberFormat numFormat = NumberFormat.getNumberInstance();
	
	private final JTextField itemNameTF = new JTextField();
	private final JComboBox itemCategoryCB = new JComboBox();
	private final JTextField clerkTF = new JTextField();
	private final JLabel lblItemId = new JLabel("Item ID:");
	private final JLabel lblItemName = new JLabel("Item Name:");
	private final JLabel lblItemCategory = new JLabel("Item Category:");
	private final JLabel lblWholesalePrice = new JLabel("Wholesale Price:");
	private final JLabel lblRetailPrice = new JLabel("Retail Price:");
	private final JLabel lblMinimumQuantity = new JLabel("Minimum Quantity:");
	private final JLabel lblClerk = new JLabel("Clerk:");
	private final JLabel lblQuantityOnHand = new JLabel("Quantity on Hand:");
	private final JFormattedTextField wholesalePriceFTF = new JFormattedTextField(currencyFormat);
	private final JFormattedTextField retailPriceFTF = new JFormattedTextField(currencyFormat);
	private final JFormattedTextField itemIdFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField qohFTF = new JFormattedTextField(numFormat);
	private final JFormattedTextField minQuantFTF = new JFormattedTextField(numFormat);
	private final JButton btnAdd = new JButton("OK");
	
	
	private Item insData;
	
	
//	1a. Load the driver
	private String connString = "jdbc:ucanaccess://C:/Users/Public/InventoryLynch.accdb";
	private final JButton btnCancel = new JButton("Cancel");
	//private String connString = "jdbc:ucanaccess://C:/Users/blync/System Design/InventoryLynch.accdb";
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItemFrame frame = new AddItemFrame();
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
	public AddItemFrame() {
		clerkTF.setToolTipText("Enter Clerk");
		clerkTF.setBounds(224, 228, 86, 20);
		clerkTF.setColumns(10);
		itemNameTF.setToolTipText("Enter Item Name");
		itemNameTF.setBounds(224, 42, 86, 20);
		itemNameTF.setColumns(10);
		jbInit();
	}
	private void jbInit() {

		
		setTitle("Add Item");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(itemNameTF);
		itemCategoryCB.setToolTipText("Select Item Category");
		itemCategoryCB.setModel(new DefaultComboBoxModel(new String[] {"Dairy", "Meat", "Frozen", "Canned", "Produce", "Beverage", "Paper", "Snack", "Cereal", "Other"}));
		itemCategoryCB.setBounds(224, 73, 86, 20);
		
		contentPane.add(itemCategoryCB);
		
		contentPane.add(clerkTF);
		lblItemId.setBounds(98, 11, 116, 14);
		
		contentPane.add(lblItemId);
		lblItemName.setBounds(98, 42, 116, 14);
		
		contentPane.add(lblItemName);
		lblItemCategory.setBounds(98, 73, 116, 14);
		
		contentPane.add(lblItemCategory);
		lblWholesalePrice.setBounds(98, 104, 116, 14);
		
		contentPane.add(lblWholesalePrice);
		lblRetailPrice.setBounds(98, 135, 116, 14);
		
		contentPane.add(lblRetailPrice);
		lblMinimumQuantity.setBounds(98, 197, 116, 14);
		
		contentPane.add(lblMinimumQuantity);
		lblClerk.setBounds(98, 228, 116, 14);
		
		contentPane.add(lblClerk);
		lblQuantityOnHand.setBounds(98, 166, 116, 14);
		
		contentPane.add(lblQuantityOnHand);
		wholesalePriceFTF.setToolTipText("Enter Wholesale Price");
		wholesalePriceFTF.setBounds(224, 104, 86, 20);
		wholesalePriceFTF.setValue(0);
		contentPane.add(wholesalePriceFTF);
		retailPriceFTF.setToolTipText("Enter Retail Price");
		
		retailPriceFTF.setBounds(224, 135, 86, 20);
		
		contentPane.add(retailPriceFTF);
		itemIdFTF.setToolTipText("Enter Item ID");
		itemIdFTF.setBounds(224, 11, 86, 20);
		retailPriceFTF.setValue(0);
		contentPane.add(itemIdFTF);
		qohFTF.setToolTipText("Enter Quantity on Hand");
		
		qohFTF.setBounds(224, 169, 86, 20);
		
		contentPane.add(qohFTF);
		minQuantFTF.setToolTipText("Enter Minimum Quantity");
		minQuantFTF.setBounds(224, 200, 86, 20);
		
		contentPane.add(minQuantFTF);
		btnAdd.setToolTipText("Select To Add Item");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnAdd_actionPerformed(e);
			}
		});
		btnAdd.setBounds(98, 269, 89, 23);
		
		contentPane.add(btnAdd);
		
//		1b. Load the Driver
		try {
			
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnCancel.setToolTipText("Select to Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setBounds(221, 269, 89, 23);
		
		contentPane.add(btnCancel);
	}
	protected void do_btnAdd_actionPerformed(ActionEvent e) {
			try {
//				2. Establish connection
				Connection conn = DriverManager.getConnection(connString);
				
				//test for input errors
				if(itemIdFTF.getText().isEmpty() || itemNameTF.getText().isEmpty() || wholesalePriceFTF.getText().isEmpty() || retailPriceFTF.getText().isEmpty() || 
						qohFTF.getText().isEmpty() || minQuantFTF.getText().isEmpty() || clerkTF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Input Boxes Empty. \nPlease Fill in All Input Data.","Error",JOptionPane.ERROR_MESSAGE);	
				}
				else {
					//Test for duplicate ItemID and ItemName before adding to table
					Statement testIdStmt = conn.createStatement();
					String testIdQuery = "SELECT ItemID,ItemName,ItemCategory,WholesalePrice,RetailPrice,QOH,MinQuant,Clerk FROM Items WHERE (ItemID ="+ itemIdFTF.getText()+")";
					ResultSet testIdRs = testIdStmt.executeQuery(testIdQuery);
					if(testIdRs.next()){
					JOptionPane.showMessageDialog(this, "Duplicate Item ID. \nPlease Re-Enter Data.","Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
				
//						3. Create the statement
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
						String query = "INSERT INTO Items(ItemID,ItemName,ItemCategory,WholesalePrice,RetailPrice,QOH,MinQuant,Clerk) VALUES (";
						query += itemIdFTF.getValue() + ",";
						query += "'" + itemNameTF.getText().trim() + "',";
						query += "'" + itemCategoryCB.getSelectedItem().toString().trim() + "',";
						query += "'" + wholesalePriceFTF.getValue() + "',";
						query += "'" + retailPriceFTF.getValue() + "',";
						query += qohFTF.getText().trim() + ",";
						query += minQuantFTF.getText().trim() + ",";
						query += "'" + clerkTF.getText().trim() + "')";
				
						//System.out.println(query);
				
//						4. execute statement
//						5. process results				
						stmt.executeUpdate(query);
				
//						6. close stuff
						stmt.close();
						this.dispose();
					}
					testIdStmt.close();
					testIdRs.close();
				}
				conn.close();
			}
			//default catch statement from iLearn resources
			catch (SQLException ex){
				System.out.println("SQL Exception: " + ex.getMessage());
				System.out.println("SQL State: " + ex.getSQLState());
				System.out.println("Vendor Error: " + ex.getErrorCode());
				ex.printStackTrace();
			} 
		}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
