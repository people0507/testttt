package Ex3;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI_Finding extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	DefaultTableModel Model = new DefaultTableModel();
	Vector <String> columns = new Vector<String>(); 
	Vector <Vector<Object>> rows = new Vector<>();
	Process_Student ps = new Process_Student();
	
	public void  getAllStudent() {
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		columns.add("");
		
		ArrayList<Student> ls = ps.getListStudent();
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
		setBounds(100, 100, 657, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lớp");
		lblNewLabel.setBounds(129, 31, 46, 14);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH4", "TH1"}));
		comboBox.setBounds(219, 27, 135, 22);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Giới tính");
		lblNewLabel_1.setBounds(129, 69, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(219, 65, 46, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(284, 65, 53, 23);
		contentPane.add(rbFemale);
		
		table = new JTable();
		table.setBounds(43, 142, 547, 227);
		contentPane.add(table);
		
		JButton btnSearch = new JButton("New button");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbMale.isSelected() == true) {
					  get_Student_byClassID_byGender((String)comboBox.getSelectedItem(),true);
				  }
				  if (rbFemale.isSelected() == true) {
					  get_Student_byClassID_byGender((String)comboBox.getSelectedItem(),false);
				  }
			}
		});
		btnSearch.setBounds(379, 27, 89, 23);
		contentPane.add(btnSearch);
		getAllStudent();
	}
}
