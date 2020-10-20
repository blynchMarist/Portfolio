import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PriceFilterFrame extends JFrame {
	
	
	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
	
	private JPanel contentPane;
	private final JLabel lblItemPriceIs = new JLabel("Retail Price is Less than:");
	private final JLabel lblItemPriceIs_1 = new JLabel("Retail Price is Greater than:");
	private final JFormattedTextField lessThanFTF = new JFormattedTextField(currencyFormat);
	private final JFormattedTextField greaterThanFTF = new JFormattedTextField(currencyFormat);
	private final JButton btnAddFilter = new JButton("Apply Filter");
	
	
	private Filters myFilters;
	private final JButton btnCancel = new JButton("Cancel");

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PriceFilterFrame frame = new PriceFilterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the frame.
	 */
	public PriceFilterFrame(Filters filterData) {
		myFilters = filterData;
		jbInit();
	}
	private void jbInit() {
		setTitle("Price Filter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblItemPriceIs.setBounds(81, 89, 157, 14);
		
		contentPane.add(lblItemPriceIs);
		lblItemPriceIs_1.setBounds(81, 61, 157, 14);
		
		contentPane.add(lblItemPriceIs_1);
		lessThanFTF.setText("Enter Retail Price Upper Range");
		lessThanFTF.setBounds(248, 86, 81, 17);
		lessThanFTF.setValue(0);
		contentPane.add(lessThanFTF);
		greaterThanFTF.setText("Enter Retail Price Lower Range");
		greaterThanFTF.setBounds(248, 61, 81, 17);
		greaterThanFTF.setValue(0);
		contentPane.add(greaterThanFTF);
		btnAddFilter.setToolTipText("Select To Apply Filter");
		btnAddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnAddFilter_actionPerformed(e);
			}
		});
		btnAddFilter.setBounds(81, 151, 111, 23);
		
		contentPane.add(btnAddFilter);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setToolTipText("Select To Cancel");
		btnCancel.setBounds(218, 151, 111, 23);
		
		contentPane.add(btnCancel);
	}
	protected void do_btnAddFilter_actionPerformed(ActionEvent e) {

		//the replace trim is not necessary for formatting while adding to database, but forces it to stay as consistent double until it reaches there.
		
		if(Double.parseDouble(lessThanFTF.getText().replace('$',' ').trim())>0.00) {
			myFilters.setLessThanPrice(lessThanFTF.getText().replace('$',' ').trim());
		}
		if(Double.parseDouble(greaterThanFTF.getText().replace('$',' ').trim())>0.00) {
			myFilters.setGreaterThanPrice(greaterThanFTF.getText().replace('$',' ').trim());
		}
		this.dispose();
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
