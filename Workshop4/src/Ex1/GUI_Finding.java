package Ex1;

import java.awt.EventQueue;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Finding extends JFrame {

	private JPanel contentPane;
	private JTable tbstudent;

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
			tbRow.add(s.getGender());
			tbRow.add(s.getMark());
			tbRow.add(s.rank());
			rows.add(tbRow);
		}
		
	Model.setDataVector(rows, columns);
 	tbstudent.setModel(Model);
	}
	
	public void getStudent_byClassId(String ClassId) {
		Model.setRowCount(0);
		ArrayList<Student> ls = ps.getStudent_byClass(ClassId);
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
		tbstudent.setModel(Model);
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
		setTitle("Tra cứu sinh viên");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lớp");
		lblNewLabel.setBounds(123, 70, 46, 14);
		contentPane.add(lblNewLabel);
		
		tbstudent = new JTable();
		tbstudent.setBounds(46, 179, 507, 171);
		contentPane.add(tbstudent);
		
		JLabel lblNewLabel_1 = new JLabel("Danh sách sinh viên");
		lblNewLabel_1.setBounds(62, 141, 104, 14);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"TH1", "TH4"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(179, 66, 135, 22);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Tìm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					getStudent_byClassId((String)comboBox.getSelectedItem());
			}
		});
		btnNewButton.setBounds(337, 66, 89, 23);
		contentPane.add(btnNewButton);
		
		getAllStudent();
	}
}
