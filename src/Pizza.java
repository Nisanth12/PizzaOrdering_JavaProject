import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Pizza {

	private JFrame frame;
	private JTextField txtname;
	private JTextField txtmobnumber;
	private JTextField txtaddress;
	private JTextField txtvariety;
	private JTextField txtquantity;
	private JTable table;
	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pizza window = new Pizza();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pizza() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table1;
	private JTextField txtSid;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/Pizza_Order","root","Chiptronex@1");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from pizza");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Order Here", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setToolTipText("");
		panel.setBounds(33, 54, 412, 320);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("NAME:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(27, 44, 58, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PH-NUMBER:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(27, 84, 117, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ADDRESS:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_2.setBounds(27, 121, 92, 25);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("PIZZA VARIETY:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3.setBounds(27, 168, 142, 14);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("QUANTITY:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1.setBounds(27, 217, 142, 14);
		panel.add(lblNewLabel_1_3_1);
		
		txtname = new JTextField();
		txtname.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtname.setBounds(173, 43, 190, 20);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtmobnumber = new JTextField();
		txtmobnumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtmobnumber.setColumns(10);
		txtmobnumber.setBounds(173, 83, 190, 20);
		panel.add(txtmobnumber);
		
		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtaddress.setColumns(10);
		txtaddress.setBounds(173, 125, 190, 20);
		panel.add(txtaddress);
		
		txtvariety = new JTextField();
		txtvariety.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtvariety.setColumns(10);
		txtvariety.setBounds(173, 167, 190, 20);
		panel.add(txtvariety);
		
		txtquantity = new JTextField();
		txtquantity.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtquantity.setColumns(10);
		txtquantity.setBounds(173, 216, 190, 20);
		panel.add(txtquantity);
		
		JButton btnorder = new JButton("ORDER");
		btnorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
              String name,mobnumber,address,variety,quantity;
				
				name = txtname.getText();
				mobnumber = txtmobnumber.getText();
				address = txtaddress.getText();
				variety = txtvariety.getText();
				quantity = txtquantity.getText();
				int len = mobnumber.length();
               
                if (len != 10) {
                    Component btnNewButton = null;
					JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }

				try {
					Object pst = con.prepareStatement("insert into pizza(name,mobnumber,address,variety,quantity)values(?,?,?,?,?)");
					((PreparedStatement) pst).setString(1, name);
					((PreparedStatement) pst).setString(2, mobnumber);
					((PreparedStatement) pst).setString(3, address);
					((PreparedStatement) pst).setString(4, variety);
					((PreparedStatement) pst).setString(5, quantity);
					((PreparedStatement) pst).executeUpdate();
					JOptionPane.showMessageDialog(null, "Oreder Successful!");
					table_load();
					          
					txtname.setText("");
					txtmobnumber.setText("");
					txtaddress.setText("");
					txtvariety.setText("");
					txtquantity.setText("");
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				 
			}
			
		});
		btnorder.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnorder.setBounds(30, 263, 89, 31);
		panel.add(btnorder);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnExit.setBounds(173, 263, 89, 31);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtmobnumber.setText("");
				txtaddress.setText("");
				txtvariety.setText("");
				txtquantity.setText("");
				txtname.requestFocus();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnClear.setBounds(289, 263, 89, 31);
		panel.add(btnClear);
		
		JLabel lblNewLabel = new JLabel("Pizza Order Page");
		lblNewLabel.setFont(new Font("Segoe UI Variable", Font.BOLD | Font.ITALIC, 27));
		lblNewLabel.setBounds(294, 6, 224, 37);
		frame.getContentPane().add(lblNewLabel);
		
		table = new JTable();
		table.setBorder(null);
		table.setBounds(455, 68, 369, 198);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "EDIT ORDER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(455, 277, 369, 97);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtid = new JTextField();
		txtid.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtid.setColumns(10);
		txtid.setBounds(142, 11, 190, 20);
		panel_1.add(txtid);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("ORDER ID:");
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_3_1_1.setBounds(10, 14, 142, 14);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				id = txtid.getText();
				
				try {
					pst = con.prepareStatement("delete from pizza where id =?");
					
					pst.setString(1, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Cancled  Successful!");
					table_load();
					          
					txtname.setText("");
					txtmobnumber.setText("");
					txtaddress.setText("");
					txtvariety.setText("");
					txtquantity.setText("");
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnCancel.setBounds(129, 42, 102, 31);
		panel_1.add(btnCancel);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			private JLabel txtmobnumer;
			
			

			public void actionPerformed(ActionEvent e) {
String name,mobnumber,address,variety,quantity,id;
				
				name = txtname.getText();
				mobnumber = txtmobnumer.getText();
				address = txtaddress.getText();
				variety = txtvariety.getText();
				quantity = txtquantity.getText();
				id = txtid.getText();
				
				try {
					pst = con.prepareStatement("update pizza set name = ?,mobnumber = ?,address = ?,variety = ?,quantity = ? where id =?");
					pst.setString(1, name);
					pst.setString(2, mobnumber);
					pst.setString(3, address);
					pst.setString(4, variety);
					pst.setString(5, quantity);
					pst.setString(6, id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Updated Successful!");
					table_load();
					          
					txtname.setText("");
					txtmobnumber.setText("");
					txtaddress.setText("");
					txtvariety.setText("");
					txtquantity.setText("");
					txtname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
			}});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		btnUpdate.setBounds(257, 42, 89, 31);
		panel_1.add(btnUpdate);
	}

	public JTable getTable1() {
		return table1;
	}

	public void setTable1(JTable table1) {
		this.table1 = table1;
	}

	public JTextField getTxtSid() {
		return txtSid;
	}

	public void setTxtSid(JTextField txtSid) {
		this.txtSid = txtSid;
	}
}

