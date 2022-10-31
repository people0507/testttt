package Staff;

import java.awt.EventQueue;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI_Staff extends JFrame {

	private JPanel contentPane;
	private JTextField txtStaffId;
	private JTextField txtName;
	private JTextField txtSalary;
	private JTextField txtBirthDate;
	private JTable JTableStaff;
	DefaultTableModel Model = new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Staff ps = new Process_Staff();
	
	public void getAllStaff(){
		ArrayList<Staff> lr = ps.getListStaff();
		
		for(int i =0;i<lr.size();i++){
			Staff s = (Staff) lr.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(s.getStaffId());
			tbRow.add(s.getName());
			tbRow.add(s.getSalary());
			tbRow.add(s.getBirthDate());
			if(s.isGender()==true)
				tbRow.add("male");
			else
				tbRow.add("female");
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
		JTableStaff.setModel(Model);
	}
	public void addStaff(String staffId, String name, double salary, String birthDate, boolean gender) {
		if(ps.insert_Staff(staffId, name, salary,birthDate, gender) ==true) {
		Model.setRowCount(0);
		ArrayList<Staff> ls = ps.getListStaff();
		for (int i = 0; i < ls.size(); i++) {
			Staff s = (Staff) ls.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(s.getStaffId());
			tbRow.add(s.getName());
			tbRow.add(s.getSalary());
			tbRow.add(s.getBirthDate());
			if(s.isGender()==true)
				tbRow.add("male");
			else
				tbRow.add("female");
			
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
		JTableStaff.setModel(Model);
		}
	}
	
	public void updateStaff(String staffId, String name, double salary, String birthDate, boolean gender){
		if(ps.update_Staff(staffId, name, salary, birthDate, gender)==true)	{
			Model.setRowCount(0);
			ArrayList<Staff> lr = ps.getListStaff();
			for(int i =0;i<lr.size();i++){
				Staff s = (Staff) lr.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(s.getStaffId());
				tbRow.add(s.getName());
				tbRow.add(s.getSalary());
				tbRow.add(s.getBirthDate());
				if(s.isGender()==true)
					tbRow.add("male");
				else
					tbRow.add("female");
				
				rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			JTableStaff.setModel(Model);
		}
	}
	public void deleteStaff(String staffId){
		if(ps.delete_Staff(staffId)==true){
			ArrayList<Staff> lr = ps.getListStaff();
				Model.setRowCount(0);
				for(int i =0;i<lr.size();i++){
					Staff s = (Staff) lr.get(i);
					Vector<Object> tbRow = new Vector<>();
					tbRow.add(s.getStaffId());
					tbRow.add(s.getName());
					tbRow.add(s.getSalary());
					tbRow.add(s.getBirthDate());
					if(s.isGender()==true)
						tbRow.add("male");
					else
						tbRow.add("female");
					
					rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			JTableStaff.setModel(Model);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Staff frame = new GUI_Staff();
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
	public GUI_Staff() {
		setTitle("STAFF MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Staff infomation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(29, 126, 437, 614);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Staff ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(30, 58, 96, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(30, 121, 114, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Salary");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(30, 182, 96, 33);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Birth-date");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(30, 247, 96, 27);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(30, 339, 94, 27);
		panel.add(lblNewLabel_5);
		
		JRadioButton rbmale = new JRadioButton("Male");
		rbmale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbmale.setBounds(183, 342, 103, 21);
		panel.add(rbmale);
		
		JRadioButton rbfemale = new JRadioButton("Female");
		rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rbfemale.setBounds(288, 342, 103, 21);
		panel.add(rbfemale);
		
		txtStaffId = new JTextField();
		txtStaffId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtStaffId.setBounds(168, 48, 206, 36);
		panel.add(txtStaffId);
		txtStaffId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtName.setBounds(168, 111, 206, 36);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtSalary = new JTextField();
		txtSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSalary.setBounds(168, 180, 206, 36);
		panel.add(txtSalary);
		txtSalary.setColumns(10);
		
		txtBirthDate = new JTextField();
		txtBirthDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBirthDate.setBounds(168, 242, 206, 36);
		panel.add(txtBirthDate);
		txtBirthDate.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("icon\\add24.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtStaffId.getText().equals("") || txtName.getText().equals("") || txtSalary.getText().equals("") || txtBirthDate.getText().equals("")) {
				     showMessageDialog(null, "Please enter enough information.");     
				    } else {
				     ArrayList<Staff> arr = ps.getListStaff();
				     boolean checkSame = false;
				     for (int i = 0; i < arr.size(); i++) {
				      Staff s = (Staff) arr.get(i);
				      if (txtStaffId.getText().equals(s.getStaffId())) {
				       checkSame = true;
				      }
				     }
				     if (checkSame) {
				      showMessageDialog(null,"ID already exists.");      
				     } 
				     else {
				    if(rbmale.isSelected()==true) {
				    	addStaff(txtStaffId.getText(),txtName.getText(),Double.parseDouble(txtSalary.getText()),txtBirthDate.getText(),true);
				    	txtStaffId.setText(null);
						txtName.setText(null);
						txtSalary.setText(null);
						txtBirthDate.setText(null);
						rbfemale.setSelected(false);
						rbmale.setSelected(false);
				     showMessageDialog(null,"Add success!!"); 
				    }
				    if(rbfemale.isSelected()==true) {
				    	addStaff(txtStaffId.getText(),txtName.getText(),Double.parseDouble(txtSalary.getText()),txtBirthDate.getText(),false);
				    	txtStaffId.setText(null);
						txtName.setText(null);
						txtSalary.setText(null);
						txtBirthDate.setText(null);
						rbfemale.setSelected(false);
						rbmale.setSelected(false);
				     showMessageDialog(null,"Add success!!"); 
				    }  
				     }
				    }
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(23, 433, 114, 33);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon("icon\\update24.png"));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbmale.isSelected()==true)
				{   				
					updateStaff(txtStaffId.getText(),txtName.getText(),Double.parseDouble(txtSalary.getText()),txtBirthDate.getText(),true);;
				}
				if(rbfemale.isSelected()==true)
				{
					updateStaff(txtStaffId.getText(),txtName.getText(),Double.parseDouble(txtSalary.getText()),txtBirthDate.getText(),false);
				}
				showMessageDialog(null,"Update success!");
				txtStaffId.setText(null);
				txtName.setText(null);
				txtSalary.setText(null);
				txtBirthDate.setText(null);
				rbfemale.setSelected(false);
				rbmale.setSelected(false);
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdate.setBounds(160, 433, 114, 33);
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setIcon(new ImageIcon("icon\\Actions-edit-clear-locationbar-rtl-icon.png"));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtStaffId.setText(null);
				txtName.setText(null);
				txtSalary.setText(null);
				txtBirthDate.setText(null);
				rbfemale.setSelected(false);
				rbmale.setSelected(false);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(297, 433, 114, 33);
		panel.add(btnClear);
		
		JButton btnDel = new JButton("Del");
		btnDel.setIcon(new ImageIcon("icon\\Editing-Eraser-icon.png"));
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to delete this staff?", "Delete",
		                  JOptionPane.YES_NO_OPTION,
		                  JOptionPane.QUESTION_MESSAGE);
		               if(result == JOptionPane.YES_OPTION){
		                  deleteStaff(txtStaffId.getText());
		       txtStaffId.setText(null);
		       txtName.setText(null);
		       txtSalary.setText(null);
		       txtBirthDate.setText(null);
		     }
			}
		});
		btnDel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDel.setBounds(23, 537, 114, 33);
		panel.add(btnDel);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon("icon\\delete24.png"));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExit.setBounds(160, 537, 114, 33);
		panel.add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "List", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(476, 126, 700, 614);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 680, 581);
		panel_1.add(scrollPane);
		
		JTableStaff = new JTable();
		JTableStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(JTableStaff);
		JTableStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = JTableStaff.getSelectedRow();
				txtStaffId.setText((String) (Model.getValueAt(index, 0)));
				txtName.setText((String) (Model.getValueAt(index, 1)));		
				txtSalary.setText(String.valueOf(Model.getValueAt(index, 2)));
				txtBirthDate.setText((String) (Model.getValueAt(index, 3)));
				if(((String)Model.getValueAt(index, 4)).equals("male")){
				   rbmale.setSelected(true);
				   rbfemale.setSelected(false);
				}
				else
				{
					rbmale.setSelected(false);
					rbfemale.setSelected(true);
				}
			}
			
		});
		
		JLabel lblNewLabel = new JLabel("STAFF MANAGER");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(423, 28, 264, 64);
		contentPane.add(lblNewLabel);
		columns.add("Staff ID");
		columns.add("Name");
		columns.add("Salary");
		columns.add("BirthDate");
		columns.add("Gender");
		getAllStaff();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
