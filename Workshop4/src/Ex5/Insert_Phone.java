package Ex5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Insert_Phone extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtAmount;

	Process_Phone ps =new Process_Phone();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Insert_Phone frame = new Insert_Phone();
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
	public Insert_Phone() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ID = new JLabel("ID");
		ID.setBounds(75, 74, 46, 14);
		contentPane.add(ID);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(75, 149, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(75, 216, 46, 14);
		contentPane.add(lblYear);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(75, 302, 46, 14);
		contentPane.add(lblCompany);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(75, 391, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(75, 461, 54, 14);
		contentPane.add(lblAmount);
		
		txtID = new JTextField();
		txtID.setBounds(148, 71, 150, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(148, 146, 150, 20);
		contentPane.add(txtName);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(148, 388, 150, 20);
		contentPane.add(txtPrice);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(148, 458, 150, 20);
		contentPane.add(txtAmount);
		
		JComboBox cbYear = new JComboBox();
		cbYear.setModel(new DefaultComboBoxModel(new String[] {"2010", "2011", "2012"}));
		cbYear.setBounds(148, 212, 150, 22);
		contentPane.add(cbYear);
		
		JComboBox cbCom = new JComboBox();
		cbCom.setModel(new DefaultComboBoxModel(new String[] {"samsung", "sonny"}));
		cbCom.setBounds(148, 298, 150, 22);
		contentPane.add(cbCom);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ps.insertPhone(txtID.getText(),txtName.getText(),Double.parseDouble((String)cbYear.getSelectedItem()),(String) cbCom.getSelectedItem(),Double.parseDouble(txtPrice.getText()),Double.parseDouble(txtAmount.getText()));
			System.out.println("111");
			}
		});
		btnAdd.setBounds(161, 517, 89, 23);
		contentPane.add(btnAdd);
	}
}
