package Ex2;

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
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI_Finding extends JFrame {

	private JPanel contentPane;
	private JTable table;

	DefaultTableModel Model = new DefaultTableModel();
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
		ArrayList<Student> ls = ps.getListStudent();
		for(int i =0 ;i<ls.size();i++) {
			Student s = (Student) ls.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(s.getID());
			tbRow.add(s.getName());
			tbRow.add(s.getClassID());
			if(s.getGender() == true) {
				tbRow.add("Nam");
			}
			if(s.getGender() == false) {
				tbRow.add("Nữ");
			}
			tbRow.add(s.getMark());
			tbRow.add(s.rank());
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
	 	table.setModel(Model);
	}
	
	public void get_Student_byClassID_byGender(String ClassID,boolean Gender) {
		Model.setRowCount(0);
		ArrayList<Student> ls = ps.getStudent_byClass_Gender(ClassID, Gender);
		for(int i=0;i<ls.size();i++) {
			Student s = (Student) ls.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(s.getID());
			tbRow.add(s.getName());
			tbRow.add(s.getClassID());
			if(s.getGender() == true) {
				tbRow.add("Nam");
			}
			if(s.getGender() == false) {
				tbRow.add("Nữ");
			}
			tbRow.add(s.getMark());
			tbRow.add(s.rank());
			
			rows.add(tbRow);
		}
		Model.setDataVector(rows, columns);
	 	table.setModel(Model);
}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Finding frame = new GUI_Finding();
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
	public GUI_Finding() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 379);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lớp");
		lblNewLabel.setBounds(124, 41, 46, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH1", "TH4"}));
		comboBox.setBounds(184, 37, 175, 22);
		contentPane.add(comboBox);
		
		table = new JTable();
		table.setBounds(61, 127, 541, 202);
		contentPane.add(table);
		
		JLabel lblNewLabel_1 = new JLabel("Giới tính");
		lblNewLabel_1.setBounds(124, 77, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(188, 73, 54, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(255, 73, 54, 23);
		contentPane.add(rbFemale);
		
		JLabel lblNewLabel_2 = new JLabel("Danh sách sinh viên");
		lblNewLabel_2.setBounds(61, 96, 131, 20);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Tìm kiếm sinh viên");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbMale.isSelected() == true) {
					  get_Student_byClassID_byGender((String)comboBox.getSelectedItem(),true);
				  }
				  if (rbFemale.isSelected() == true) {
					  get_Student_byClassID_byGender((String)comboBox.getSelectedItem(),false);
				  }
			}
		});
		btnNewButton.setBounds(393, 37, 167, 23);
		contentPane.add(btnNewButton);
		getAllStudent();
	}
}
