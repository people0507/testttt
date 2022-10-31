package Ex3;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;


public class GUI_Student extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsv;
	private JTextField txtName;
	private JTextField txtMark;
	private JTable table;
	
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Student ps = new Process_Student();
	
	public void getAllStudent() {
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		ArrayList<Student> ls =  ps.getListStudent();
		 for(int i=0;i<ls.size();i++) {
			 Student s = (Student) ls.get(i);
			 Vector<Object> tbRow = new Vector<>();
			 tbRow.add(s.getID());
			 tbRow.add(s.getName());
			 tbRow.add(s.getClassID());
			 if(s.getGender()==true) {
				 tbRow.add("Nam");
			 }
			 if(s.getGender()==false) {
				 tbRow.add("Nữ");
			 }
			 tbRow.add(s.getMark());
			 tbRow.add(s.rank());
			 rows.add(tbRow);
		 }
		 Model.setDataVector(rows, columns);
		 table.setModel(Model);
	}
	
	public  void addStudent(String ID,String Name,String ClassID,boolean Gender,double Mark) {
		if (ps.insertStudent(ID, Name, ClassID,Gender, Mark) == true) {
			Model.setRowCount(0);
			ArrayList<Student> ls = ps.getListStudent();
			for (int i = 0; i < ls.size(); i++) {
				Student s = (Student) ls.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(s.getID());
				tbRow.add(s.getName());
				tbRow.add(s.getClassID());
				if(s.getGender()==true) {
					 tbRow.add("Nam");
				 }
				 if(s.getGender()==false) {
					 tbRow.add("Nữ");
				 }
				tbRow.add(s.getMark());
				tbRow.add(s.rank());
				
				rows.add(tbRow);
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
					GUI_Student frame = new GUI_Student();
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
	public GUI_Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên");
		lblNewLabel.setBounds(136, 53, 72, 14);
		contentPane.add(lblNewLabel);
		
		txtMsv = new JTextField();
		txtMsv.setBounds(218, 50, 189, 20);
		contentPane.add(txtMsv);
		txtMsv.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setBounds(136, 101, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lớp");
		lblNewLabel_2.setBounds(136, 142, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính");
		lblNewLabel_3.setBounds(136, 184, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm");
		lblNewLabel_4.setBounds(136, 221, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(218, 98, 189, 20);
		contentPane.add(txtName);
		
		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(218, 218, 189, 20);
		contentPane.add(txtMark);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH1", "TH4"}));
		comboBox.setBounds(218, 138, 189, 22);
		contentPane.add(comboBox);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(218, 180, 46, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(279, 180, 53, 23);
		contentPane.add(rbFemale);
		
		table = new JTable();
		table.setBounds(45, 295, 662, 242);
		contentPane.add(table);
		
		JButton btnAdd = new JButton("Thêm sinh viên");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMale.isSelected()==true) {
					addStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),true,Double.parseDouble(txtMark.getText()));
					
				}
				if(rbFemale.isSelected()==true) {
					addStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),false,Double.parseDouble(txtMark.getText()));
					
				}
			}
		});
		btnAdd.setBounds(246, 263, 120, 23);
		contentPane.add(btnAdd);
		getAllStudent();
	}
}
