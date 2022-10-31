package Ex1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Delete extends JFrame {

	private JPanel contentPane;
	private JTextField txtMsv;

	Process_Student ps = new Process_Student();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Delete frame = new GUI_Delete();
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
	public GUI_Delete() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã sinh viên");
		lblNewLabel.setBounds(50, 44, 76, 14);
		contentPane.add(lblNewLabel);
		
		txtMsv = new JTextField();
		txtMsv.setBounds(119, 41, 181, 20);
		contentPane.add(txtMsv);
		txtMsv.setColumns(10);
		
		JButton btnDel = new JButton("Xóa sinh viên");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(ps.delStudent(txtMsv.getText()) == true) {
						ps.delStudent(txtMsv.getText());
						JOptionPane.showMessageDialog(contentPane,
							    "Xóa thành công.",
							    "Xóa sinh viên",
							    JOptionPane.PLAIN_MESSAGE);
					}
					else if(txtMsv.getText().equals(null) ) {
						btnDel.setEnabled(false);
						JOptionPane.showMessageDialog(contentPane,
							    "Xóa không thành công.",
							    "Xóa sinh viên",
							    JOptionPane.PLAIN_MESSAGE);
					}
					
			}
		});
		btnDel.setBounds(310, 40, 114, 23);
		contentPane.add(btnDel);
	}
}
