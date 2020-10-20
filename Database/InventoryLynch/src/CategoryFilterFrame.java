import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CategoryFilterFrame extends JFrame {

	private JPanel contentPane;
	
	
	
	
	
	private Filters myFilters;
	private final JCheckBox chbxDairy = new JCheckBox("Dairy");
	private final JCheckBox chbxMeat = new JCheckBox("Meat");
	private final JCheckBox chbxFrozen = new JCheckBox("Frozen");
	private final JCheckBox chbxCanned = new JCheckBox("Canned");
	private final JCheckBox chbxProduce = new JCheckBox("Produce");
	private final JCheckBox chbxBeverage = new JCheckBox("Beverage");
	private final JCheckBox chbxPaper = new JCheckBox("Paper");
	private final JCheckBox chbxSnack = new JCheckBox("Snack");
	private final JCheckBox chbxOther = new JCheckBox("Other");
	private final JButton btnApplyFilters = new JButton("Apply Filter");
	private final JButton btnCancel = new JButton("Cancel");
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CategoryFilterFrame frame = new CategoryFilterFrame();
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
	public CategoryFilterFrame(Filters filterData) {
		myFilters = filterData;
		jbInit();
	}
	private void jbInit() {
		setTitle("Category Filters");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		chbxDairy.setToolTipText("Select Options");
		chbxDairy.setBounds(103, 19, 97, 23);
		
		contentPane.add(chbxDairy);
		chbxMeat.setToolTipText("Select Options");
		chbxMeat.setBounds(103, 45, 97, 23);
		
		contentPane.add(chbxMeat);
		chbxFrozen.setToolTipText("Select Options");
		chbxFrozen.setBounds(103, 71, 97, 23);
		
		contentPane.add(chbxFrozen);
		chbxCanned.setToolTipText("Select Options");
		chbxCanned.setBounds(103, 99, 97, 23);
		
		contentPane.add(chbxCanned);
		chbxProduce.setToolTipText("Select Options");
		chbxProduce.setBounds(103, 125, 97, 23);
		
		contentPane.add(chbxProduce);
		chbxBeverage.setToolTipText("Select Options");
		chbxBeverage.setBounds(202, 19, 97, 23);
		
		contentPane.add(chbxBeverage);
		chbxPaper.setToolTipText("Select Options");
		chbxPaper.setBounds(202, 45, 97, 23);
		
		contentPane.add(chbxPaper);
		chbxSnack.setToolTipText("Select Options");
		chbxSnack.setBounds(202, 71, 97, 23);
		
		contentPane.add(chbxSnack);
		chbxOther.setToolTipText("Select Options");
		chbxOther.setBounds(202, 97, 97, 23);
		
		contentPane.add(chbxOther);
		btnApplyFilters.setToolTipText("Select to Apply Filter");
		btnApplyFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnApplyFilters_actionPerformed(e);
			}
		});
		btnApplyFilters.setBounds(67, 173, 128, 23);
		
		contentPane.add(btnApplyFilters);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCancel_actionPerformed(e);
			}
		});
		btnCancel.setToolTipText("Select to Cancel");
		btnCancel.setBounds(217, 173, 128, 23);
		
		contentPane.add(btnCancel);
	}
	protected void do_btnApplyFilters_actionPerformed(ActionEvent e) {
		ArrayList<String> categoryList = new ArrayList<String>();
		if(chbxDairy.isSelected()) {
			categoryList.add(chbxDairy.getText());
		}
		if(chbxMeat.isSelected()) {
			categoryList.add(chbxMeat.getText());
		}
		if(chbxFrozen.isSelected()) {
			categoryList.add(chbxFrozen.getText());
		}
		if(chbxCanned.isSelected()) {
			categoryList.add(chbxCanned.getText());
		}
		if(chbxProduce.isSelected()) {
			categoryList.add(chbxProduce.getText());
		}
		if(chbxBeverage.isSelected()) {
			categoryList.add(chbxBeverage.getText());
		}
		if(chbxPaper.isSelected()) {
			categoryList.add(chbxPaper.getText());
		}
		if(chbxSnack.isSelected()) {
			categoryList.add(chbxSnack.getText());
		}
		if(chbxOther.isSelected()) {
			categoryList.add(chbxOther.getText());
		}
		
		myFilters.setCategories(categoryList);
		this.dispose();
	}
	protected void do_btnCancel_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
