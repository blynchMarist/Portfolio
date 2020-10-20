import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class InventoryLynchFrame extends JFrame {

	private JPanel contentPane;
	private final JTable table = new JTable();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnTools = new JMenu("Tools");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnSetSort = new JMenu("Set Sort");
	private final JMenu mnSetFilter = new JMenu("Set Filter");
	private final JMenuItem mntmAddItem = new JMenuItem("Add Item");
	private final JRadioButtonMenuItem mbtnByName = new JRadioButtonMenuItem("By Name");
	private final JRadioButtonMenuItem mbtnByRetailPrice = new JRadioButtonMenuItem("By Retail Price");
	private final JRadioButtonMenuItem mbtnByCategory = new JRadioButtonMenuItem("By Category");
	private final ButtonGroup sortButtonGroup = new ButtonGroup();
	private final JMenuItem mntmByRetailPrice = new JMenuItem("By Retail Price");
	private final JMenuItem mntmByCategory = new JMenuItem("By Category");

	Item itemData = new Item();
	Filters filterData = new Filters();
	
//	1a. Load the driver
	private String connString = "jdbc:ucanaccess://C:/Users/Public/InventoryLynch.accdb";
	private final JRadioButtonMenuItem mbtnByItemId = new JRadioButtonMenuItem("By Item ID");
	private final JLabel lblSort = new JLabel("Sort:");
	private final JLabel lblPriceFilter = new JLabel("Price Filter:");
	private final JLabel lblCategoryFilter = new JLabel("Category Filter:");
	private final JButton btnCancelSort = new JButton("Cancel");
	private final JButton btnCancelPriceFilter = new JButton("Cancel");
	private final JButton btnCancelCategoryFilter = new JButton("Cancel");
	private final JMenuItem mntmHelp = new JMenuItem("Help");
	
	/**"C:\Users\Public\InventoryLynch.accdb"
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryLynchFrame frame = new InventoryLynchFrame();
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
	public InventoryLynchFrame() {
		jbInit();
	}
	private void jbInit() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				do_this_windowGainedFocus(e);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});

		setTitle("Inventory Lynch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 520);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmExit_actionPerformed(e);
			}
		});
		
		mnFile.add(mntmExit);
		
		menuBar.add(mnTools);
		mntmAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmAddItem_actionPerformed(e);
			}
		});
		
		mnTools.add(mntmAddItem);
		
		mnTools.add(mnSetSort);
		sortButtonGroup.add(mbtnByItemId);
		mbtnByItemId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mbtnByItemId_actionPerformed(e);
			}
		});
		mbtnByItemId.setSelected(true);
		
		mnSetSort.add(mbtnByItemId);
		sortButtonGroup.add(mbtnByName);
		mbtnByName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mbtnByName_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mbtnByName);
		sortButtonGroup.add(mbtnByRetailPrice);
		mbtnByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mbtnByRetailPrice_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mbtnByRetailPrice);
		sortButtonGroup.add(mbtnByCategory);
		mbtnByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mbtnByCategory_actionPerformed(e);
			}
		});
		
		mnSetSort.add(mbtnByCategory);
		
		mnTools.add(mnSetFilter);
		mntmByRetailPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByRetailPrice_actionPerformed(e);
			}
		});
		
		mnSetFilter.add(mntmByRetailPrice);
		mntmByCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmByCategory_actionPerformed(e);
			}
		});
		
		mnSetFilter.add(mntmByCategory);
		
		menuBar.add(mnHelp);
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_mntmHelp_actionPerformed(e);
			}
		});
		
		mnHelp.add(mntmHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 784, 355);
		
		contentPane.add(scrollPane);
		table.setFont(new Font("Courier New", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ItemID", "ItemName", "ItemCategory", "WholesalePrice", "RetailPrice", "QOH", "MinQuant", "Clerk"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		

		lblSort.setBounds(105, 377, 689, 14);
		
		
		contentPane.add(lblSort);
		lblPriceFilter.setBounds(105, 399, 689, 14);
		
		contentPane.add(lblPriceFilter);
		lblCategoryFilter.setBounds(105, 424, 689, 14);
		
		contentPane.add(lblCategoryFilter);
		btnCancelSort.setToolTipText("Cancel Sort Filter");
		btnCancelSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancelSort_actionPerformed(e);
			}
		});
		btnCancelSort.setBounds(10, 377, 85, 14);
		
		contentPane.add(btnCancelSort);
		btnCancelPriceFilter.setToolTipText("Cancel Price Filter");
		btnCancelPriceFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancelPriceFilter_actionPerformed(e);
			}
		});
		btnCancelPriceFilter.setBounds(10, 399, 85, 14);
		
		contentPane.add(btnCancelPriceFilter);
		btnCancelCategoryFilter.setToolTipText("Cancel Category Filter");
		btnCancelCategoryFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancelCategoryFilter_actionPerformed(e);
			}
		});
		btnCancelCategoryFilter.setBounds(10, 424, 85, 14);
		
		contentPane.add(btnCancelCategoryFilter);
//		1b. Load the Driver
		
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void do_mntmExit_actionPerformed(ActionEvent e) {
		this.dispose();
	}
	protected void do_mntmAddItem_actionPerformed(ActionEvent e) {
		AddItemFrame addItem = new AddItemFrame();
		addItem.setVisible(true);
	}
	//runs query when window gains focus
	protected void do_this_windowGainedFocus(WindowEvent e) {
		runQuery();
	}
	//runs query when sort items are selected
	protected void do_mbtnByItemId_actionPerformed(ActionEvent e) {
		runQuery();
	}
	protected void do_mbtnByName_actionPerformed(ActionEvent e) {
		runQuery();
	}
	protected void do_mbtnByRetailPrice_actionPerformed(ActionEvent e) {
		runQuery();
	}
	protected void do_mbtnByCategory_actionPerformed(ActionEvent e) {
		runQuery();
	}
	protected void do_mntmByRetailPrice_actionPerformed(ActionEvent e) {
		PriceFilterFrame priceFrame = new PriceFilterFrame(filterData);
		priceFrame.setVisible(true);
	}
	protected void do_mntmByCategory_actionPerformed(ActionEvent e) {
		CategoryFilterFrame categoryFrame = new CategoryFilterFrame(filterData);
		categoryFrame.setVisible(true);
	}
	protected void do_btnCancelSort_actionPerformed(ActionEvent e) {
		mbtnByItemId.setSelected(true);
		runQuery();
	}
	protected void do_btnCancelPriceFilter_actionPerformed(ActionEvent e) {
		filterData.setGreaterThanPrice("");
		filterData.setLessThanPrice("");
		runQuery();
	}
	protected void do_btnCancelCategoryFilter_actionPerformed(ActionEvent e) {
		filterData.setCategories(new ArrayList<String>());
		runQuery();
	}
	protected void do_mntmHelp_actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "This is a represention of your Inventory Database."
				+ "\nTo add an item to the database, please navigate to the Tools menu and select Add Item. Please fill out all requested information for the item. Items must have unique Item IDs."
				+ "\nTo apply a sort, please navigate to the Tools menu and select one of the options listed."
				+ "\nTo apply a price or category filter, please navigate to the Tools menu, and then Select Filter."
				+ "\nFor Retail Price filters, enter a price range for the items you want listed."
				+ "\nFor the Category Filter, please select all item categories that you would like listed. If no item is selected, it will withdraw the filter"
				+ "\nTo withdraw any existing filters, please select the Cancel button next to each respective filter.","Help",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void runQuery() {
		ResultSet rs = null;
		Statement stmt = null;
		try {
//			2. establish connection
			Connection conn = DriverManager.getConnection(connString);
//			3. create the statement
			stmt = conn.createStatement();
			String query = "SELECT ItemID,ItemName,ItemCategory,WholesalePrice,RetailPrice,QOH,MinQuant,Clerk FROM Items WHERE (1=1) ";
			
			if(!filterData.getGreaterThanPrice().isEmpty()) {
				query += "AND RetailPrice >" + filterData.getGreaterThanPrice() + " ";
			}
			if(!filterData.getLessThanPrice().isEmpty()) {
				query += "AND RetailPrice <" + filterData.getLessThanPrice() + " ";
			}
			if(!filterData.getCategories().isEmpty()) {
				for(int i = 0; i<filterData.getCategories().size(); i++) {
					if(i == 0) {
						query += "AND (";
					}
					else {
						query += "OR ";
					}
					query += "ItemCategory='" + filterData.getCategories().get(i) + "' ";
				}
				query += ")";
			}
			
			if(mbtnByItemId.isSelected()) {
				query += "ORDER BY ItemId";
			}
			else if(mbtnByName.isSelected()) {
				query += "ORDER BY ItemName";
			}
			else if(mbtnByRetailPrice.isSelected()) {
				query += "ORDER BY RetailPrice";
			}
			else if(mbtnByCategory.isSelected()) {
				query += "ORDER BY ItemCategory";
			}
//			4. execute the statement
			rs = stmt.executeQuery(query);
//			5. Process the results
			while(table.getRowCount()>0) {
				((DefaultTableModel)table.getModel()).removeRow(0);
			}
			int numColumns = rs.getMetaData().getColumnCount();
			
			while(rs.next()) {
				//create an Object array to hold a single record. 
				Object[] row = new Object[numColumns];
				
				//get each field in the record
				for(int i=0;i<numColumns;i++) {
					//formats wholesale and retail rows correctly
					if(i == 3 || i == 4) {
						row[i] = "$"+String.format("%.2f", rs.getObject(i+1));
					}
					else
						row[i] = rs.getObject(i+1);
				}
				//insert record into the table
				((DefaultTableModel)table.getModel()).insertRow(rs.getRow()-1, row);
			}
//			6. close stuff
			stmt.close();
			rs.close();
			conn.close();	
		}
		//default catch statement from iLearn resources
		catch (SQLException ex)
		{
			System.out.println("SQL Exception: " + ex.getMessage());
			System.out.println("SQL State: " + ex.getSQLState());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			ex.printStackTrace();
		} 
		//update labels placed here to update on every change. 
		updateLabels();
	}
	public void updateLabels() {
		//Make sure	labels are accurate
		String sortSelection;
		if(mbtnByName.isSelected()) {
			sortSelection = "Name";
		}
		else if(mbtnByRetailPrice.isSelected()) {
			sortSelection = "Retail Price";
		}
		else if(mbtnByCategory.isSelected()) {
			sortSelection = "Category";
		}
		else {
			sortSelection = "Item ID";
		}
		lblSort.setText("Sort: " + sortSelection);
		
		String testNone = "";
		String greaterThan = "";
		String lessThan = "";
		if(filterData.getGreaterThanPrice().isEmpty() && filterData.getLessThanPrice().isEmpty()) {
			testNone = "None";
		}
		if(!filterData.getGreaterThanPrice().isEmpty()) {
			greaterThan += "Greater than: $"+filterData.getGreaterThanPrice() + ". ";
		}
		if(!filterData.getLessThanPrice().isEmpty()) {
			lessThan += "Less Than: $" + filterData.getLessThanPrice() + ".";
		}
		lblPriceFilter.setText("Price Filter: " + testNone + greaterThan + lessThan);
		String categorySelection = "";
		if(filterData.getCategories().isEmpty()) {
			categorySelection = "None";
		}
		else {
			for(int i=0;i<filterData.getCategories().size();i++) {
				categorySelection += filterData.getCategories().get(i);
				if(i<filterData.getCategories().size()-1) {
					categorySelection += ",";
				}
				categorySelection += " ";
			}
		}
		lblCategoryFilter.setText("Category Filter: " + categorySelection);
	}
	
}