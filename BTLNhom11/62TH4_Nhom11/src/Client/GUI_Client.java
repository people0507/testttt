package Client;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Bill.GUI_Bill;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class GUI_Client extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtIC;
	private JTextField txtNum;
	private JTextField txtAge;
	
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Client pc = new Process_Client();

	public void getallClient() {
		Model.setColumnCount(0);
		columns.add("ID");
		columns.add("Name");
		columns.add("Citizen Identification");
		columns.add("Phone Number");
		columns.add("Gender");
		columns.add("Age");
		Model.setRowCount(0);
		ArrayList<Client> arr = pc.get_listClient();
		for(int i=0;i<arr.size();i++) {
			Client cl = (Client) arr.get(i);
			Vector<Object> tbRows = new Vector<>();
			tbRows.add(cl.getClientID());
			tbRows.add(cl.getName());
			tbRows.add(cl.getIdIC());
			tbRows.add(cl.getNum());
			if(cl.isGender()==true) {
				 tbRows.add("Male");
			 }
			 if(cl.isGender()==false) {
				 tbRows.add("Female");
			 }
			 tbRows.add(cl.getAge());
			 rows.add(tbRows);
			 
		}
		Model.setDataVector(rows, columns);
		 table.setModel(Model);
	}
	
	public void addClient(String clientID,String name,String idIC,String num,boolean gender,int age) {
		if (pc.add_Client(clientID, name,idIC,num,gender,age) == true) {
		Model.setRowCount(0);
		ArrayList<Client> arr = pc.get_listClient();
		for (int i = 0; i < arr.size(); i++) {
			Client cl = (Client) arr.get(i);
			Vector<Object> tbRows = new Vector<>();
			tbRows.add(cl.getClientID());
			tbRows.add(cl.getName());
			tbRows.add(cl.getIdIC());
			tbRows.add(cl.getNum());
			if(cl.isGender()==true) {
				 tbRows.add("Male");
			 }
			 if(cl.isGender()==false) {
				 tbRows.add("Female");
			 }
			 tbRows.add(cl.getAge());
			rows.add(tbRows);
		}
		Model.setDataVector(rows, columns);
		table.setModel(Model);
	}
}
	public void getClientbyID(String ID) {
		Model.setRowCount(0);
		ArrayList<Client> ls = pc.getClient_byID(ID);
		for(int i=0;i<ls.size();i++) {
			Client cl = (Client) ls.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(cl.getClientID());
			tbRow.add(cl.getName());
			tbRow.add(cl.getIdIC());
			tbRow.add(cl.getNum());
			if(cl.isGender() == true) {
				tbRow.add("Nam");
			}
			if(cl.isGender() == false) {
				tbRow.add("Nữ");
			}
			tbRow.add(cl.getAge());
			
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
	 	table.setModel(Model);
}		
		
	
	public void updateClient(String clientID,String name,String idIC,String num,boolean gender,int age) {
		if (pc.update_Client(clientID, name,idIC,num,gender,age) == true) {
		Model.setRowCount(0);
		ArrayList<Client> arr = pc.get_listClient();
		for (int i = 0; i < arr.size(); i++) {
			Client cl = (Client) arr.get(i);
			Vector<Object> tbRows = new Vector<>();
			tbRows.add(cl.getClientID());
			tbRows.add(cl.getName());
			tbRows.add(cl.getIdIC());
			tbRows.add(cl.getNum());
			if(cl.isGender()==true) {
				 tbRows.add("Male");
			 }
			 if(cl.isGender()==false) {
				 tbRows.add("Female");
			 }
			 tbRows.add(cl.getAge());
			rows.add(tbRows);
		}
		Model.setDataVector(rows, columns);
		table.setModel(Model);
	}
}
	public void delete_Client(String clientID) {
		if (pc.delete_Client(clientID) == true) {
		Model.setRowCount(0);
		ArrayList<Client> arr = pc.get_listClient();
		for (int i = 0; i < arr.size(); i++) {
			Client cl = (Client) arr.get(i);
			Vector<Object> tbRows = new Vector<>();
			tbRows.add(cl.getClientID());
			tbRows.add(cl.getName());
			tbRows.add(cl.getIdIC());
			tbRows.add(cl.getNum());
			if(cl.isGender()==true) {
				 tbRows.add("Male");
			 }
			 if(cl.isGender()==false) {
				 tbRows.add("Female");
			 }
			 tbRows.add(cl.getAge());
			rows.add(tbRows);
		}
		Model.setDataVector(rows, columns);
		table.setModel(Model);
	}
}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Client frame = new GUI_Client();
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
	public GUI_Client() {
		setTitle("CLIENT MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Client List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 66, 1166, 396);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 1146, 360);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 487, 1166, 266);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(32, 29, 122, 25);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(32, 84, 122, 25);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Citizen ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(32, 139, 122, 25);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(589, 29, 122, 25);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Age");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(589, 84, 122, 25);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(589, 139, 122, 25);
		panel_1.add(lblNewLabel_5);
		
		txtID = new JTextField();
		txtID.setBounds(183, 28, 317, 27);
		panel_1.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(183, 83, 317, 27);
		panel_1.add(txtName);
		
		txtIC = new JTextField();
		txtIC.setColumns(10);
		txtIC.setBounds(183, 138, 317, 27);
		panel_1.add(txtIC);
		
		txtNum = new JTextField();
		txtNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9'||e.isActionKey()) {
	            	txtNum.setEditable(true);
	            } else {
	            	txtNum.setEditable(false);
	            }
			}
		});
		txtNum.setColumns(10);
		txtNum.setBounds(731, 28, 317, 27);
		panel_1.add(txtNum);
		
		txtAge = new JTextField();
		txtAge.setColumns(10);
		txtAge.setBounds(731, 83, 317, 27);
		panel_1.add(txtAge);
		
		JRadioButton rbMale = new JRadioButton("Male");
		rbMale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbMale.setBounds(744, 141, 103, 21);
		panel_1.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Female");
		rbFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbFemale.setBounds(930, 141, 103, 21);
		panel_1.add(rbFemale);
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setIcon(new ImageIcon("icon\\add32.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtID.getText().equals("") || txtName.getText().equals("") || txtIC.getText().equals("") || txtNum.getText().equals("")  || txtAge.getText().equals("")) {
					showMessageDialog(null, "Please enter enough information.");					
				} else {
					ArrayList<Client> arr = pc.get_listClient();
					boolean checkSame = false;
					for (int i = 0; i < arr.size(); i++) {
						Client cl = (Client) arr.get(i);
						if (txtID.getText().equals(cl.getClientID())) {
							checkSame = true;
						}
					}
					if (checkSame) {
						showMessageDialog(null,"ID already exists.");						
					} 
					else {
				if(rbMale.isSelected()==true) {
					addClient(txtID.getText(),txtName.getText(),txtIC.getText(),txtNum.getText(),true,Integer.parseInt(txtAge.getText()));
					txtID.setText(null);
					txtName.setText(null);
					txtIC.setText(null);
					txtNum.setText(null);
					rbMale.setSelected(false);
					rbFemale.setSelected(false);
					txtAge.setText(null);
					showMessageDialog(null,"Thêm thành công");	
				}
				if(rbFemale.isSelected()==true) {
					addClient(txtID.getText(),txtName.getText(),txtIC.getText(),txtNum.getText(),false,Integer.parseInt(txtAge.getText()));
					txtID.setText(null);
					txtName.setText(null);
					txtIC.setText(null);
					txtNum.setText(null);
					rbMale.setSelected(false);
					rbFemale.setSelected(false);
					txtAge.setText(null);
					showMessageDialog(null,"Thêm thành công");	
				}		
					}
				}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int index=table.getSelectedRow();
				txtID.setText((String)(Model.getValueAt(index,0)));  
				txtName.setText((String)(Model.getValueAt(index,1)));  
				txtIC.setText((String)(Model.getValueAt(index,2)));  
				txtNum.setText((String)(Model.getValueAt(index,3)));
				if (((String) Model.getValueAt(index, 4)).equals("Male")) {
					rbMale.setSelected(true);
					rbFemale.setSelected(false);
				}
				if (((String) Model.getValueAt(index, 4)).equals("Female")) {
					rbMale.setSelected(false);
					rbFemale.setSelected(true);
				}
				txtAge.setText(String.valueOf(Model.getValueAt(index,5)));
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(38, 196, 122, 42);
		panel_1.add(btnNewButton);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon("icon\\icons8-search-24.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getClientbyID(txtID.getText());
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(199, 196, 122, 42);
		panel_1.add(btnSearch);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon("icon\\update32.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtID.getText().equals("") || txtName.getText().equals("") || txtIC.getText().equals("") || txtNum.getText().equals("")  || txtAge.getText().equals("")) {
					showMessageDialog(null, "Please choose client to update.");
				}
					else {
				if(rbMale.isSelected()==true) {
					updateClient(txtID.getText(),txtName.getText(),txtIC.getText(),txtNum.getText(),true,Integer.parseInt(txtAge.getText()));
					showMessageDialog(null, "Cập nhật thành công");
					
				}
				if(rbFemale.isSelected()==true) {
					updateClient(txtID.getText(),txtName.getText(),txtIC.getText(),txtNum.getText(),false,Integer.parseInt(txtAge.getText()));
					showMessageDialog(null, "Cập nhật thành công");
				}
					}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(360, 196, 122, 42);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon("icon\\Editing-Eraser-icon.png"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to delete this Client?", "Delete",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	delete_Client(txtID.getText());
							txtID.setText(null);
							txtName.setText(null);
							txtIC.setText(null);
							txtNum.setText(null);
							rbMale.setSelected(false);
							rbFemale.setSelected(false);
							txtAge.setText(null);
							showMessageDialog(null, "Xóa thành công");    
			  }	
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDelete.setBounds(521, 196, 122, 42);
		panel_1.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon("icon\\Actions-edit-clear-locationbar-rtl-icon (32).png"));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID.setText(null);
				txtName.setText(null);
				txtIC.setText(null);
				txtNum.setText(null);
				rbMale.setSelected(false);
				rbFemale.setSelected(false);
				txtAge.setText(null);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(682, 196, 122, 42);
		panel_1.add(btnClear);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setIcon(new ImageIcon("icon\\recycle-2-icon.png"));
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLoad.setBounds(843, 196, 122, 42);
		panel_1.add(btnLoad);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("icon\\delete32.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					dispose();
//					if(btnExit.getModel().isPressed()==false) {
						
//						try {
//							dispose();
//							GUI_Client gc = new GUI_Client();
//							gc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//							GUI_Bill gb = new GUI_Bill();
//							gb.setVisible(true);
//							gc.setVisible(false);
////							System.exit(0); 
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}
//					GUI_Bill gb;
//					try {
////						gb = new GUI_Bill();
////						gb.setVisible(true);
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(1004, 196, 122, 42);
		panel_1.add(btnExit);
		
		JLabel lblNewLabel_6 = new JLabel("CLIENT MANAGER");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_6.setBounds(459, 10, 201, 61);
		contentPane.add(lblNewLabel_6);
		getallClient();
//		GUI_Client gc = new GUI_Client();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
