package Ex1;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Student extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_4;
	
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Student ps = new Process_Student();
	
	
	public void getAllStudent1() {
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
			 tbRow.add(s.getGender());
			 tbRow.add(s.getMark());
			 tbRow.add(s.rank());
			 rows.add(tbRow);
		 }
		 Model.setDataVector(rows, columns);
		 table.setModel(Model);
	}
	
	public void addStudent(String ID,String Name,String ClassID,String Gender,double Mark) {
		if (Process_Student.insertStudent(ID, Name, ClassID,Gender, Mark) == true) {
		Model.setRowCount(0);
		ArrayList<Student> ls = ps.getListStudent();
		for (int i = 0; i < ls.size(); i++) {
			Student s = (Student) ls.get(i);
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(s.getID());
			tbRow.add(s.getName());
			tbRow.add(s.getClassID());
			tbRow.add(s.getGender());
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
		setBounds(100, 100, 740, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên");
		lblNewLabel.setBounds(158, 24, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setBounds(158, 54, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lớp");
		lblNewLabel_2.setBounds(158, 80, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính");
		lblNewLabel_3.setBounds(158, 105, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm");
		lblNewLabel_4.setBounds(158, 130, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(237, 21, 245, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(237, 51, 245, 20);
		contentPane.add(textField_1);
		
		table = new JTable();
		table.setBounds(41, 212, 647, 268);
		contentPane.add(table);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(237, 127, 245, 20);
		contentPane.add(textField_4);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(237, 101, 46, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(297, 101, 46, 23);
		contentPane.add(rbFemale);
		
		JComboBox cbBox = new JComboBox();
		cbBox.setModel(new DefaultComboBoxModel(new String[] {"TH1", "TH4"}));
		cbBox.setBounds(237, 76, 245, 22);
		contentPane.add(cbBox);
		
		JButton btnNewButton = new JButton("Thêm sinh viên");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbMale.isSelected() == true) {
					  addStudent(textField.getText(), textField_1.getText(),(String)cbBox.getSelectedItem(),"Nam", Double.parseDouble(textField_4.getText()));
				}
				if (rbFemale.isSelected() == true) {
					  addStudent(textField.getText(), textField_1.getText(),(String)cbBox.getSelectedItem(),"Nữ", Double.parseDouble(textField_4.getText()));
				}
			}
		});
		btnNewButton.setBounds(267, 171, 158, 23);
		contentPane.add(btnNewButton);
		getAllStudent1();
	}
}
