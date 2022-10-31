package Room;

import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class GUI_Room extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtRoomID;
	private JTextField txtPrice;
	DefaultTableModel Model = new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Room pr = new Process_Room();
	
	public void getAllRoom()
	{
		ArrayList<Room> lr = pr.get_ListRoom();
		for(int i =0;i<lr.size();i++)
		{
			Room r = (Room) lr.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(r.getRoomID());
			tbRow.add(r.getRoomType());
			tbRow.add(r.getPrice());
			if(r.isStatus()==true)
			{
				tbRow.add("CÒN PHÒNG");
			}
			else
			{
				tbRow.add("KHÔNG CÒN PHÒNG");
			}
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
		table.setModel(Model);
	}
	public void getRoombyRoomType(String roomType)
	{
		Model.setRowCount(0);
		ArrayList<Room> lr = pr. get_ListRoombyRoomType(roomType);
		for(int i =0;i<lr.size();i++)
		{
			Room r = (Room) lr.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(r.getRoomID());
			tbRow.add(r.getRoomType());
			tbRow.add(r.getPrice());
			if(r.isStatus()==true)
			{
				tbRow.add("CÒN PHÒNG");
			}
			else
			{
				tbRow.add("KHÔNG CÒN PHÒNG");
			}
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
		table.setModel(Model);
	}
	public void addRoom(String roomID, String roomType,double price,boolean status)
	{
		if(pr.add_Room(roomID, roomType, price, status)==true)
		{
			Model.setRowCount(0);
			ArrayList<Room> lr = pr.get_ListRoom();
			for(int i =0;i<lr.size();i++)
			{
				Room r = (Room) lr.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(r.getRoomID());
				tbRow.add(r.getRoomType());
				tbRow.add(r.getPrice());
				if(r.isStatus()==true)
				{
					tbRow.add("CÒN PHÒNG");
				}
				else
				{
					tbRow.add("KHÔNG CÒN PHÒNG");
				}
				rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			table.setModel(Model);
		}
	}
	public void updateRoom(String roomID, String roomType,double price,boolean status)
	{
		if(pr.update_Room(roomID, roomType, price, status)==true)
		{
			Model.setRowCount(0);
			ArrayList<Room> lr = pr.get_ListRoom();
			for(int i =0;i<lr.size();i++)
			{
				Room r = (Room) lr.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(r.getRoomID());
				tbRow.add(r.getRoomType());
				tbRow.add(r.getPrice());
				if(r.isStatus()==true)
				{
					tbRow.add("CÒN PHÒNG");
				}
				else
				{
					tbRow.add("KHÔNG CÒN PHÒNG");
				}
				rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			table.setModel(Model);
		}
	}
	public void deleteRoom(String roomID)
	{
		if(pr.delete_Room(roomID)==true)
		{
			ArrayList<Room> lr = pr.get_ListRoom();
			for(int i =0;i<lr.size();i++)
			{
				Room r = (Room) lr.get(i);
				rows.clear();
			}
			Model.setDataVector(rows, columns);
			table.setModel(Model);
		}
	}
//	public void test() {
//		columns.add("STT");
//		columns.add("name");
//		Vector<Vector<Object>> tbRows = new Vector<>()
//	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Room frame = new GUI_Room();
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
	public GUI_Room() {
		setTitle("ROOM MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ROOM MANAGER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(476, 40, 199, 35);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Room infomation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 106, 444, 614);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Room ID");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(20, 42, 97, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Roomtype");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(20, 101, 97, 21);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(20, 166, 97, 21);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Status");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(20, 244, 97, 21);
		panel.add(lblNewLabel_5);
		
		txtRoomID = new JTextField();
		txtRoomID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRoomID.setBounds(150, 42, 221, 31);
		panel.add(txtRoomID);
		txtRoomID.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrice.setBounds(150, 161, 221, 31);
		panel.add(txtPrice);
		txtPrice.setColumns(10);
		
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(new String[] {"Normal", "VIP", "Pair room", "Family room"}));
		cbType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbType.setBounds(150, 96, 221, 31);
		panel.add(cbType);
		
		JRadioButton rbHave = new JRadioButton("Available");
		rbHave.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbHave.setBounds(150, 244, 103, 21);
		panel.add(rbHave);
		
		JRadioButton rbEmpty = new JRadioButton("Unavailable");
		rbEmpty.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbEmpty.setBounds(255, 244, 151, 21);
		panel.add(rbEmpty);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("icon\\add24.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtRoomID.getText().equals("")||cbType.getSelectedItem().equals("")||txtPrice.getText().equals(""))
				{
					if( rbEmpty.isSelected()==false && rbHave.isSelected()==false)
					showMessageDialog(null,"Please enter full information");
				}else {
					ArrayList<Room>  arr =pr.get_ListRoom();
					boolean checkSame = false;
					for(int i =0;i<arr.size();i++) {
						Room r = (Room) arr.get(i);
						if(txtRoomID.getText().equals(r.getRoomID())) {
							checkSame = true;
						}
					}
					if(checkSame) {
						showMessageDialog(null,"ID already exists");
					} else {
						if(rbHave.isSelected()==true)
						{
							addRoom(txtRoomID.getText(),(String)cbType.getSelectedItem(),Double.parseDouble(txtPrice.getText()),true);
							txtRoomID.setText(null);
							txtPrice.setText(null);
							rbEmpty.setSelected(false);
							rbHave.setSelected(false);
							cbType.setSelectedIndex(0);
							showMessageDialog(null,"Insert successs");
						}
						if(rbEmpty.isSelected()==true)
						{
							addRoom(txtRoomID.getText(),(String)cbType.getSelectedItem(),Double.parseDouble(txtPrice.getText()),false);
							txtRoomID.setText(null);
							txtPrice.setText(null);
							rbEmpty.setSelected(false);
							rbHave.setSelected(false);
							cbType.setSelectedIndex(0);
							showMessageDialog(null,"Insert successs");
						}
					}
				}
				
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(20, 361, 120, 31);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon("icon\\update24.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbHave.isSelected()==true)
				{
					updateRoom(txtRoomID.getText(),(String)cbType.getSelectedItem(),Double.parseDouble(txtPrice.getText()),true);
					showMessageDialog(null,"Update successs");
				}
				if(rbEmpty.isSelected()==true)
				{
					updateRoom(txtRoomID.getText(),(String)cbType.getSelectedItem(),Double.parseDouble(txtPrice.getText()),false);
					showMessageDialog(null,"Update successs");
				}
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(166, 361, 125, 31);
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon("icon\\Actions-edit-clear-locationbar-rtl-icon.png"));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRoomID.setText(null);
				txtPrice.setText(null);
				rbEmpty.setSelected(false);
				rbHave.setSelected(false);
				cbType.setSelectedIndex(0);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(303, 361, 112, 31);
		panel.add(btnClear);
		
		JButton btnDel = new JButton("Delete");
		btnDel.setIcon(new ImageIcon("icon\\Editing-Eraser-icon.png"));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to delete this room ?","Delete",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					deleteRoom(txtRoomID.getText());
					txtRoomID.setText(null);
					txtPrice.setText(null);
					cbType.setSelectedIndex(0);
					rbEmpty.setSelected(false);
					rbHave.setSelected(false);
					getAllRoom();
				}
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDel.setBounds(20, 440, 120, 31);
		panel.add(btnDel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("icon\\delete24.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(164, 440, 127, 31);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(476, 185, 700, 535);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				int index = table.getSelectedRow();
//				txtRoomID.setText((String)(Model.getValueAt(index,0)));
//				cbType.setSelectedItem((String)(Model.getValueAt(index,1)));
//				txtPrice.setText(String.valueOf(Model.getValueAt(index, 2)));
//				if(((String)Model.getValueAt(index, 3)).equals("CÒN PHÒNG"))
//				{
//				   rbHave.setSelected(true);
//				   rbEmpty.setSelected(false);
//				}
//				else
//				{
//					rbHave.setSelected(false);
//					rbEmpty.setSelected(true);
//				}
			}
		});
		scrollPane.setBounds(10, 24, 680, 501);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				txtRoomID.setText((String)(Model.getValueAt(index,0)));
				cbType.setSelectedItem((String)(Model.getValueAt(index,1)));
				txtPrice.setText(String.valueOf(Model.getValueAt(index, 2)));
				if(((String)Model.getValueAt(index, 3)).equals("CÒN PHÒNG"))
				{
				   rbHave.setSelected(true);
				   rbEmpty.setSelected(false);
				}
				else
				{
					rbHave.setSelected(false);
					rbEmpty.setSelected(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Roomtype");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(492, 121, 84, 26);
		contentPane.add(lblNewLabel_1);
		
		JComboBox cbFindType = new JComboBox();
		cbFindType.setModel(new DefaultComboBoxModel(new String[] {"Normal", "VIP", "Pair room", "Family room"}));
		cbFindType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbFindType.setBounds(623, 121, 138, 26);
		contentPane.add(cbFindType);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getRoombyRoomType((String)cbFindType.getSelectedItem());
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSearch.setBounds(811, 117, 123, 35);
		contentPane.add(btnSearch);
		
		JButton btnRefresh = new JButton("Reload");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Model.setRowCount(0);
				getAllRoom();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefresh.setBounds(976, 117, 138, 35);
		contentPane.add(btnRefresh);
		columns.add("Mã phòng");
		columns.add("Loại phòng");
		columns.add("Giá phòng");
		columns.add("Tình trạng");
		getAllRoom();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
