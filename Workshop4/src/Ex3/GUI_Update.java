package Ex3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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
		setBounds(100, 100, 764, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên");
		lblNewLabel.setBounds(142, 95, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setBounds(142, 143, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Lớp");
		lblNewLabel_2.setBounds(142, 174, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính");
		lblNewLabel_3.setBounds(142, 211, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm");
		lblNewLabel_4.setBounds(142, 236, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtMsv = new JTextField();
		txtMsv.setBounds(228, 92, 172, 20);
		contentPane.add(txtMsv);
		txtMsv.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(228, 140, 172, 20);
		contentPane.add(txtName);
		
		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(228, 233, 172, 20);
		contentPane.add(txtMark);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH1", "TH4"}));
		comboBox.setBounds(227, 170, 173, 22);
		contentPane.add(comboBox);
		
		JRadioButton rbMale = new JRadioButton("Nam");
		rbMale.setBounds(228, 204, 54, 23);
		contentPane.add(rbMale);
		
		JRadioButton rbFemale = new JRadioButton("Nữ");
		rbFemale.setBounds(305, 204, 54, 23);
		contentPane.add(rbFemale);
		
		JButton btnSearch = new JButton("Tìm kiếm");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Student st = ps.getStudent_byID(txtMsv.getText());
				
				txtName.setText(st.getName());
				for (int j = 0; j < comboBox.getItemCount(); j++) {
					if (((String) comboBox.getItemAt(j)).equals(st.getClassID())) {
						comboBox.setSelectedIndex(j);
					}
				}
				if(st.getGender()==true) {
					rbMale.setSelected(true);
				}
				if(st.getGender()==false) {
					rbFemale.setSelected(true);
				}
				txtMark.setText(String.valueOf(st.getMark()));
			}
		});
		btnSearch.setBounds(484, 91, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnUpdate = new JButton("Cập nhật");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rbMale.isSelected()==true) 
				{
					ps.updateStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),true,Double.parseDouble(txtMark.getText()));
				}
				if(rbFemale.isSelected()==true) 
				{
					ps.updateStudent(txtMsv.getText(),txtName.getText(),(String)comboBox.getSelectedItem(),false,Double.parseDouble(txtMark.getText()));
				}
				
			}
		});
		btnUpdate.setBounds(484, 232, 89, 23);
		contentPane.add(btnUpdate);
	}
}
