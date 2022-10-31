package Ex1;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.w3c.dom.ls.LSException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class GUI_Update extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsv;
	private JTextField txtName;
	private JTextField txtMark;

	
	DefaultTableModel Model = new DefaultTableModel();
	Vector<String> columns = new  Vector<String>();
	Vector<Vector<Object>> rows = new Vector<Vector<Object>>();
	Process_Student ps = new Process_Student();
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Update frame = new GUI_Update();
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
	public GUI_Update() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên");
		lblNewLabel.setBounds(195, 30, 73, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setBounds(196, 85, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lớp");
		lblNewLabel_2.setBounds(196, 121, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính");
		lblNewLabel_3.setBounds(196, 161, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm");
		lblNewLabel_4.setBounds(196, 201, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtMsv = new JTextField();
		txtMsv.setBounds(278, 27, 185, 20);
		contentPane.add(txtMsv);
		txtMsv.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(278, 82, 185, 20);
		contentPane.add(txtName);
		
		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(278, 198, 185, 20);
		contentPane.add(txtMark);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH4", "TH1"}));
		comboBox.setBounds(278, 117, 185, 22);
		contentPane.add(comboBox);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(280, 157, 46, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(347, 157, 46, 23);
		contentPane.add(rbFemale);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Student> ls = ps.getStudent_byID(txtMsv.getText());
				for (int i = 0; i < ls.size(); i++) {
					Student st = (Student) ls.get(i);
					if (st.getID().equals(txtMsv.getText())) {
						txtName.setText(st.getName());
						System.out.print(st.getGender());
						for (int j = 0; j < comboBox.getItemCount(); j++) {
							if (((String) comboBox.getItemAt(j)).equals(st.getClassID())) {
								comboBox.setSelectedIndex(j);
							}
						}
						if(st.getGender().equals("Nam")){
							rbMale.setSelected(true);
						}
						if(st.getGender().equals("Nữ")){
							rbFemale.setSelected(true);
						}
						txtMark.setText(String.valueOf(st.getMark()));
					}
				}
			}
		});
		btnSearch.setBounds(532, 26, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rbMale.isSelected() == true) {
					  Process_Student.updateStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),"Nam", Double.parseDouble(txtMark.getText()));
				}
				if (rbFemale.isSelected() == true) {
					Process_Student.updateStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),"Nữ", Double.parseDouble(txtMark.getText()));
				}
			}
		});
		btnNewButton.setBounds(532, 212, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
