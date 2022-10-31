package Bill;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Client.Client;
import Client.GUI_Client;
import Room.Process_Room;
import Service.Service;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;


public class GUI_Bill extends JFrame {

	private JPanel contentPane;
	private JTable detailTable;
	private JTable billTable;
	private JLabel lblNewLabel;
	private JLabel lblName;
	private JLabel lblPhone;
	private JTextField txtIC;
	private JButton btnNewButton;
	private JTextField txtName;
	private JTextField txtPhone;
	private JLabel lblRoomid;
	private JTextField txtCheckIn;
	private JTextField txtCheckOut;
	private JLabel lblRoomid_2;
	private JTextField txtRoomPrice;
	private JTextField txtRoomCharge;
	private JLabel lblRoomid_3;
	private JLabel lblRoomid_1_1_2;
	private JTextField txtTotalServiceCharge;
	private JLabel lblRoomid_4;
	private JTextField txtTotalCharge;
	private JPanel panel_2;
	private JLabel lblServiceName;
	private JLabel lblServiceId;
	private JLabel lblPrice;
	private JLabel lblQuantity;
	private JTextField txtServiceID;
	private JTextField txtPrice;
	private JLabel lblCost;
	private JTextField txtServiceCharge;
	private JButton btnAdd;
	JComboBox cbRoomType = new JComboBox();
	JComboBox cbRoomID = new JComboBox();
	
	
	
	
	Bill_Process p = new Bill_Process();
	Process_Room pr = new Process_Room();
	
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	DefaultTableModel  DetailModel =  new DefaultTableModel();
	Vector<String> columns2 = new Vector<String>();
	Vector<Vector<Object>> rows2 = new Vector<>();
	private JButton btnCheckOut;
	private JButton btnReload;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnCalculate;
	private JComboBox cb_ServiceName;
	private JScrollPane scrollPane_2;
	private JTextField txtRID;
	private JButton btnSearch;
	JSpinner spQuantity = new JSpinner();
	JButton btnUpdate = new JButton("     Update   ");
	JButton btnDelete = new JButton("     Delete    ");
	
	public void loadBillData() throws SQLException {
		Model.setRowCount(0);
		Model.setColumnCount(0);
		cbRoomID.removeAllItems();
		columns.add("billID");
		columns.add("Client Name");
		columns.add("Phone");
		columns.add("Room ID");
		columns.add("Room Type");
		columns.add("Price");
		columns.add("Check-in day");
		columns.add("Check-out day");
		columns.add("Service charge");
		columns.add("Room Charge");
		columns.add("Total Charge");
		ResultSet rs = p.getBillData();
		while (rs.next()) {
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(rs.getString("billID"));
			tbRow.add(rs.getString("ClientName"));
			tbRow.add(rs.getString("phone"));
			tbRow.add(rs.getString("RoomID"));
			tbRow.add(rs.getString("roomtype"));
			tbRow.add(rs.getDouble("price"));
			tbRow.add(rs.getDate("checkinday"));
			tbRow.add(rs.getDate("checkoutday"));
			tbRow.add(rs.getDouble("servicecharge"));
			tbRow.add(rs.getDouble("roomcharge"));
			tbRow.add(rs.getDouble("total"));
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
		billTable.setModel(Model);
		rs = p.getAvaiabledRoom();
		while(rs.next()) {
			cbRoomID.addItem(rs.getString("id"));
		}
		cbRoomID.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbRoomID.getSelectedIndex()!=-1) {
					String roomID = cbRoomID.getSelectedItem().toString();
					double a = 100;
					try {
						double price = pr.getRoomPriceByID(roomID);
						txtRoomPrice.setText(Double.toString(price));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
//					txtRoomPrice.setText(Double.toString(a));
				}
			}
		});
		cbRoomID.setSelectedIndex(-1);
		cbRoomType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (cbRoomType.getSelectedIndex()>-1) {
					String roomType = cbRoomType.getSelectedItem().toString();
					cbRoomID.removeAllItems();
					try {
						ResultSet rs = p.getListEmptyRoomByRoomType(roomType);
						while (rs.next()) {
							cbRoomID.addItem(rs.getString("id"));
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cbRoomType.setSelectedIndex(-1);
	}
	
	public void loadDetailBillData(String billID) throws SQLException {
		DetailModel.setRowCount(0);
		DetailModel.setColumnCount(0);
		cb_ServiceName.removeAllItems();
		spQuantity.setValue(0);
		txtServiceCharge.setText("");
		columns2.add("Service ID");
		columns2.add("Service Name");
		columns2.add("Quantity");
		columns2.add("Price");
		columns2.add("Cost");
		ResultSet rs = p.getDetailBillData(billID);
		while (rs.next()) {
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(rs.getString("serviceid"));
			tbRow.add(rs.getString("servicename"));
			tbRow.add(rs.getInt("quantity"));
			tbRow.add(rs.getDouble("price"));
			tbRow.add(rs.getDouble("cost"));
			rows2.add(tbRow);
		}
		DetailModel.setDataVector(rows2,columns2);
		detailTable.setModel(DetailModel);
		
		ResultSet rs2 = p.getListSerVice();
		while (rs2.next()) {
			cb_ServiceName.addItem(rs2.getString("name"));
		}
		cb_ServiceName.setSelectedIndex(-1);
		txtServiceID.setText("");
		txtPrice.setText("");
	}
	public void clear() {
		txtName.setText("");
		txtPhone.setText("");
		txtIC.setText("");
		cbRoomType.setSelectedIndex(-1);
		cbRoomID.setSelectedIndex(-1);
		txtRoomPrice.setText("");
		txtCheckIn.setText("");
		txtCheckOut.setText("");
		txtRoomCharge.setText("");
		txtTotalServiceCharge.setText("");
		txtTotalCharge.setText("");
		txtServiceID.setText("");
		cb_ServiceName.setSelectedIndex(-1);
		txtPrice.setText("");
		txtServiceCharge.setText("");
		DetailModel.setRowCount(0);
		txtRID.setText("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Bill frame = new GUI_Bill();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public GUI_Bill() throws SQLException {
		setTitle("General");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 825);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Detail", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(999, 10, 570, 743);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane_1.setBounds(10, 21, 550, 389);
		panel.add(scrollPane_1);
		
		detailTable = new JTable();
		detailTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (Model.getValueAt(billTable.getSelectedRow(), 7)==null) {
				btnUpdate.setEnabled(true);
				btnDelete.setEnabled(true);
				}
				int idx = detailTable.getSelectedRow();
				cb_ServiceName.setSelectedItem(DetailModel.getValueAt(idx, 1));
				spQuantity.setValue(DetailModel.getValueAt(idx, 2));
			}
		});
		scrollPane_1.setViewportView(detailTable);
		
		panel_2 = new JPanel();
		panel_2.setBounds(10, 430, 550, 303);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		lblServiceName = new JLabel("Service Name");
		lblServiceName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblServiceName.setBounds(10, 85, 111, 23);
		panel_2.add(lblServiceName);
		
		lblServiceId = new JLabel("Service ID");
		lblServiceId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblServiceId.setBounds(10, 31, 85, 23);
		panel_2.add(lblServiceId);
		
		lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrice.setBounds(10, 139, 85, 23);
		panel_2.add(lblPrice);
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblQuantity.setBounds(10, 193, 85, 23);
		panel_2.add(lblQuantity);
		
		txtServiceID = new JTextField();
		txtServiceID.setEditable(false);
		txtServiceID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtServiceID.setColumns(10);
		txtServiceID.setBounds(131, 23, 178, 32);
		panel_2.add(txtServiceID);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrice.setColumns(10);
		txtPrice.setBounds(132, 133, 178, 32);
		panel_2.add(txtPrice);
		
		lblCost = new JLabel("Charge");
		lblCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCost.setBounds(10, 247, 85, 23);
		panel_2.add(lblCost);
		
		txtServiceCharge = new JTextField();
		txtServiceCharge.setEditable(false);
		txtServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtServiceCharge.setColumns(10);
		txtServiceCharge.setBounds(131, 243, 178, 32);
		panel_2.add(txtServiceCharge);
		
		spQuantity.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		btnAdd = new JButton("     Add       ");
		btnAdd.setIcon(new ImageIcon("icon\\add32.png"));
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((int)spQuantity.getValue() == 0) {
					showMessageDialog(null,"Quantity invalid!");
				}
				else {
					int idx = billTable.getSelectedRow();
					String billID = (String) Model.getValueAt(idx, 0);
					if(p.addNewSerVice(billID,txtServiceID.getText() ,(int)spQuantity.getValue() )) {
						showMessageDialog(null,"Insert successs");
						try {
							loadDetailBillData(billID);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAdd.setBounds(337, 40, 203, 47);
		panel_2.add(btnAdd);
		
		spQuantity.setEnabled(false);
		spQuantity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				double total = Double.parseDouble(spQuantity.getValue().toString())  * Double.parseDouble(txtPrice.getText());
				txtServiceCharge.setText(Double.toString(total));
			}
		});
		spQuantity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spQuantity.setBounds(131, 188, 178, 32);
		panel_2.add(spQuantity);
		
		cb_ServiceName = new JComboBox();
		cb_ServiceName.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(cb_ServiceName.getSelectedIndex()>-1) {
					Service sv = new Service();
					try {
						sv = p.getServiceByName(cb_ServiceName.getSelectedItem().toString());
						txtServiceID.setText(sv.getServiceID());
						txtPrice.setText(Double.toString(sv.getPrice()));
						spQuantity.setEnabled(true);
						txtServiceCharge.setText(Double.toString(Double.parseDouble(txtPrice.getText())* (int) spQuantity.getValue()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		cb_ServiceName.setSelectedIndex(-1);
		cb_ServiceName.setBounds(131, 78, 178, 32);
		panel_2.add(cb_ServiceName);
		
		btnUpdate.setIcon(new ImageIcon("icon\\update32.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Are you sure all the information is correct?", "Update",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	String billID = (String) Model.getValueAt(billTable.getSelectedRow(), 0);
			            	String svID = txtServiceID.getText();
			            	int quantity = (int)spQuantity.getValue();
			            	if(p.updateServiceFromBill(billID, svID, quantity)) {
			            		showMessageDialog(null, "Update success!");
			            		try {
			            			loadDetailBillData(billID);
			            		} catch (SQLException e1) {
			            			// TODO Auto-generated catch block
			            			e1.printStackTrace();
			            		}
			            	}
			            	else {
			            		showMessageDialog(null, "Cannot Update!");
			            	}
			            	
			  }
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnUpdate.setEnabled(false);
		btnUpdate.setBounds(337, 127, 203, 47);
		panel_2.add(btnUpdate);
		
		btnDelete.setIcon(new ImageIcon("icon\\delete32.png"));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"This action will not able to undo!", "Delete",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	
			            	String billID = (String) Model.getValueAt(billTable.getSelectedRow(), 0);
			            	String svID = txtServiceID.getText();
			            	if(p.deleteServiceFromBill(billID, svID)) {
			            		showMessageDialog(null, "delete success");
			            		try {
			            			loadDetailBillData(billID);
			            		} catch (SQLException e1) {
			            			// TODO Auto-generated catch block
			            			e1.printStackTrace();
			            		}
			            	}
			            	else {
			            		showMessageDialog(null, "cannot delete");
			            	}
			  }
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDelete.setEnabled(false);
		btnDelete.setBounds(337, 214, 203, 47);
		panel_2.add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Bill", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 10, 950, 743);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 70, 920, 386);
		panel_1.add(scrollPane_2);
		
		scrollPane = new JScrollPane();
		scrollPane_2.setViewportView(scrollPane);
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		billTable = new JTable();
		billTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clear();
				int rowIdx = billTable.getSelectedRow();
				try {
					loadDetailBillData((String) Model.getValueAt(rowIdx, 0));
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(Model.getValueAt(rowIdx, 7)==null) {
					btnAdd.setEnabled(true);
					spQuantity.setEnabled(true);
					btnCalculate.setEnabled(true);
					cbRoomType.setSelectedItem(Model.getValueAt(rowIdx, 4));
					cbRoomID.addItem(Model.getValueAt(rowIdx,3));
					cbRoomID.setSelectedItem(Model.getValueAt(rowIdx,3));
					Date checkInDate = (Date) Model.getValueAt(rowIdx, 6);
					txtCheckIn.setText(checkInDate.toString());

				}
				else {
					btnCalculate.setEnabled(false);
					btnAdd.setEnabled(false);
					btnUpdate.setEnabled(false);
					btnDelete.setEnabled(false);
					btnCheckOut.setEnabled(false);
					cbRoomType.setSelectedItem(Model.getValueAt(rowIdx, 4));
					cbRoomID.addItem(Model.getValueAt(rowIdx,3));
					cbRoomID.setSelectedItem(Model.getValueAt(rowIdx,3));
					txtTotalServiceCharge.setText(Double.toString((double) Model.getValueAt(rowIdx, 8)) );
					txtRoomCharge.setText(Double.toString((double) Model.getValueAt(rowIdx, 9)));
					txtTotalCharge.setText(Double.toString((double) Model.getValueAt(rowIdx, 10)));
					Date checkInDate = (Date) Model.getValueAt(rowIdx, 6);
					txtCheckIn.setText(checkInDate.toString());
					Date checkOutDate =(Date) Model.getValueAt(rowIdx, 7);
					txtCheckOut.setText(checkOutDate .toString());
				}
				String billID = (String) Model.getValueAt(rowIdx, 0);
				try {
					loadDetailBillData(billID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(billTable);
		
		lblNewLabel = new JLabel("Citizen ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 471, 71, 23);
		panel_1.add(lblNewLabel);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(20, 516, 71, 23);
		panel_1.add(lblName);
		
		lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setBounds(20, 560, 71, 23);
		panel_1.add(lblPhone);
		
		txtIC = new JTextField();
		txtIC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIC.setBounds(101, 466, 132, 32);
		panel_1.add(txtIC);
		txtIC.setColumns(10);
		
		btnNewButton = new JButton("Check");
		btnNewButton.setIcon(new ImageIcon("icon\\tick-icon.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ic = txtIC.getText();
				if(p.isExitedClient(txtIC.getText())) {
					clear();
					txtIC.setText(ic);
					Client cl = new Client();
					try {
					cl = p.getClientbyIC(txtIC.getText());
					txtName.setText(cl.getName());
					txtPhone.setText(cl.getNum());
					}
					catch (SQLException e1) {
						
					}
				}
				else {
					int result = JOptionPane.showConfirmDialog(contentPane,"Client IC is not exited! Add now?","",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						GUI_Client gc = new GUI_Client();
						gc.setVisible(true);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(236, 466, 103, 32);
		panel_1.add(btnNewButton);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setColumns(10);
		txtName.setBounds(101, 511, 238, 32);
		panel_1.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPhone.setColumns(10);
		txtPhone.setBounds(101, 555, 238, 32);
		panel_1.add(txtPhone);
		
		lblRoomid = new JLabel("RoomType");
		lblRoomid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid.setBounds(371, 471, 85, 23);
		panel_1.add(lblRoomid);
		
		cbRoomType.setModel(new DefaultComboBoxModel(new String[] {"Normal", "VIP", "Pair room", "Family room"}));
		cbRoomType.setBounds(477, 466, 151, 32);
		panel_1.add(cbRoomType);
		
		JLabel lblRoomid_1 = new JLabel("RoomID");
		lblRoomid_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_1.setBounds(371, 516, 71, 23);
		panel_1.add(lblRoomid_1);
		
		cbRoomID.setBounds(477, 511, 151, 32);
		panel_1.add(cbRoomID);
		
		JLabel lblRoomid_1_1 = new JLabel("Check-in date");
		lblRoomid_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_1_1.setBounds(638, 471, 105, 23);
		panel_1.add(lblRoomid_1_1);
		
		txtCheckIn = new JTextField();
		txtCheckIn.setEditable(false);
		txtCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCheckIn.setColumns(10);
		txtCheckIn.setBounds(752, 466, 178, 32);
		panel_1.add(txtCheckIn);
		
		JLabel lblRoomid_1_1_1 = new JLabel("Check-out date");
		lblRoomid_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_1_1_1.setBounds(638, 516, 116, 23);
		panel_1.add(lblRoomid_1_1_1);
		
		txtCheckOut = new JTextField();
		txtCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtCheckOut.setEditable(false);
		txtCheckOut.setBounds(752, 512, 178, 31);
		panel_1.add(txtCheckOut);
		txtCheckOut.setColumns(10);
		
		lblRoomid_2 = new JLabel("Price");
		lblRoomid_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_2.setBounds(371, 560, 71, 23);
		panel_1.add(lblRoomid_2);
		
		txtRoomPrice = new JTextField();
		txtRoomPrice.setEditable(false);
		txtRoomPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRoomPrice.setColumns(10);
		txtRoomPrice.setBounds(477, 555, 151, 32);
		panel_1.add(txtRoomPrice);
		
		txtRoomCharge = new JTextField();
		txtRoomCharge.setEditable(false);
		txtRoomCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRoomCharge.setColumns(10);
		txtRoomCharge.setBounds(752, 555, 178, 32);
		panel_1.add(txtRoomCharge);
		
		lblRoomid_3 = new JLabel("Room charge");
		lblRoomid_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_3.setBounds(638, 560, 96, 23);
		panel_1.add(lblRoomid_3);
		
		lblRoomid_1_1_2 = new JLabel("Service charge");
		lblRoomid_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_1_1_2.setBounds(638, 602, 105, 23);
		panel_1.add(lblRoomid_1_1_2);
		
		txtTotalServiceCharge = new JTextField();
		txtTotalServiceCharge.setEditable(false);
		txtTotalServiceCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotalServiceCharge.setColumns(10);
		txtTotalServiceCharge.setBounds(752, 597, 178, 32);
		panel_1.add(txtTotalServiceCharge);
		
		lblRoomid_4 = new JLabel("Total");
		lblRoomid_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_4.setBounds(638, 644, 71, 23);
		panel_1.add(lblRoomid_4);
		
		txtTotalCharge = new JTextField();
		txtTotalCharge.setEditable(false);
		txtTotalCharge.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotalCharge.setColumns(10);
		txtTotalCharge.setBounds(752, 639, 178, 32);
		panel_1.add(txtTotalCharge);
		
		JButton btnCheckIn = new JButton("Check-in");
		btnCheckIn.setIcon(new ImageIcon("icon\\checkicon.png"));
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"All the data is correct?","Check-In",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
				Client cl = new Client();
				try {
					cl = p.getClientbyIC(txtIC.getText());
					if(p.createNewBill(cbRoomID.getSelectedItem().toString(), cl.getClientID())) {
						showMessageDialog(null,"Insert successs");
						p.changeRoomStatus(cbRoomID.getSelectedItem().toString());
						clear();
						loadBillData();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnCheckIn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheckIn.setBounds(20, 638, 184, 47);
		panel_1.add(btnCheckIn);
		
		btnCheckOut = new JButton("Check-out");
		btnCheckOut.setIcon(new ImageIcon("icon\\pngegg.png"));
		btnCheckOut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Are you sure to check-out? This action will not be able to undo","Check-out",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
				String billID = Model.getValueAt(billTable.getSelectedRow(), 0).toString();
				double roomCharge = Double.parseDouble(txtRoomCharge.getText());
				double serviceCharge = Double.parseDouble(txtTotalServiceCharge.getText());
				if(p.checkOut(billID,serviceCharge, roomCharge)) {
					showMessageDialog(null,"Check-out successs");
					try {
						p.changeRoomStatus(Model.getValueAt(billTable.getSelectedRow(), 3).toString());
						clear();
						loadBillData();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			}
		});
		btnCheckOut.setEnabled(false);
		btnCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCheckOut.setBounds(236, 638, 178, 47);
		panel_1.add(btnCheckOut);
		
		btnReload = new JButton("Reload");
		btnReload.setIcon(new ImageIcon("icon\\recycle-2-icon.png"));
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loadBillData();
					clear();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnReload.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnReload.setBounds(442, 638, 151, 47);
		panel_1.add(btnReload);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setIcon(new ImageIcon("icon\\calculator-icon.png"));
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date checkInDate = (Date) Model.getValueAt(billTable.getSelectedRow(), 6);
				
				long millis=System.currentTimeMillis();
				Date today=new java.sql.Date(millis);
				txtCheckOut.setText(today.toString());
				long getDiff =  today.getTime() - checkInDate.getTime();
				long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
				if (getDaysDiff ==0) getDaysDiff =1;
				double roomCharge = getDaysDiff*Double.parseDouble(txtRoomPrice.getText());
				txtRoomCharge.setText(Double.toString(roomCharge));
				btnCheckOut.setEnabled(true);
				try {
					double totalServiceCharge = p.totalServiceCharge((String) Model.getValueAt(billTable.getSelectedRow(), 0));
					txtTotalServiceCharge.setText(Double.toString(totalServiceCharge));
					double totalCharge = roomCharge + totalServiceCharge;
					txtTotalCharge.setText(Double.toString(totalCharge));
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnCalculate.setEnabled(false);
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCalculate.setBounds(727, 690, 146, 32);
		panel_1.add(btnCalculate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(243, 13, 466, 47);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		txtRID = new JTextField();
		txtRID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRID.setColumns(10);
		txtRID.setBounds(81, 5, 244, 32);
		panel_3.add(txtRID);
		
		JLabel lblRoomid_1_2 = new JLabel("RoomID");
		lblRoomid_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRoomid_1_2.setBounds(10, 10, 71, 23);
		panel_3.add(lblRoomid_1_2);
		
		btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon("icon\\icons8-search-24.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = p.searchRoomForCheckOut(txtRID.getText());
//					if() {
						while (rs.next()){
							Model.setRowCount(0);
							if (rs.getDate("checkoutday")==null) {
								Vector<Object> tbRow = new Vector<>();
								tbRow.add(rs.getString("billID"));
								tbRow.add(rs.getString("ClientName"));
								tbRow.add(rs.getString("phone"));
								tbRow.add(rs.getString("RoomID"));
								tbRow.add(rs.getString("roomtype"));
								tbRow.add(rs.getDouble("price"));
								tbRow.add(rs.getDate("checkinday"));
								tbRow.add(rs.getDate("checkoutday"));
								tbRow.add(rs.getDouble("servicecharge"));
								tbRow.add(rs.getDouble("roomcharge"));
								tbRow.add(rs.getDouble("total"));
								rows.add(tbRow);
								Model.setDataVector(rows, columns);
								billTable.setModel(Model);
								return;
							}
							
						}
						showMessageDialog(null,"Invalid!");
//					}
//					else {
//						showMessageDialog(null,"Insert successs");
//					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(348, 5, 118, 32);
		panel_3.add(btnSearch);
		loadBillData();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
