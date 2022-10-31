package Bill;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Vector;
import java.util.Calendar;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class GUI_Statistic extends JFrame {

	private JPanel contentPane;
	private JTextField txtService;
	private JTextField txtRoom;
	private JTextField txtTotal;
	private JTable table;
	JSpinner sp1 = new JSpinner();
	JSpinner sp2 = new JSpinner();
	
	Bill_Process p = new Bill_Process();
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	
	public void loadData(ResultSet rs) throws SQLException {
		Model.setRowCount(0);
		Model.setColumnCount(0);
		columns.add("billID");
		columns.add("Client Name");
		columns.add("Phone");
		columns.add("Room ID");
		columns.add("Room Type");
		columns.add("Price");
		columns.add("Check-in day");
		columns.add("Check-out day");
		columns.add("Service charge");
		columns.add("Room Charge");
		columns.add("Total Charge");
		while (rs.next()) {
			Vector<Object> tbRow = new Vector<>();
			tbRow.add(rs.getString("billID"));
			tbRow.add(rs.getString("ClientName"));
			tbRow.add(rs.getString("phone"));
			tbRow.add(rs.getString("RoomID"));
			tbRow.add(rs.getString("roomtype"));
			tbRow.add(rs.getDouble("price"));
			tbRow.add(rs.getDate("checkinday"));
			tbRow.add(rs.getDate("checkoutday"));
			tbRow.add(rs.getDouble("servicecharge"));
			tbRow.add(rs.getDouble("roomcharge"));
			tbRow.add(rs.getDouble("total"));
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
					GUI_Statistic frame = new GUI_Statistic();
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
	public GUI_Statistic() {
		setTitle("Statistic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1199, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(25, 454, 544, 177);
		contentPane.add(panel);
		panel.setLayout(null);
		
		sp1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sp1.setModel(new SpinnerDateModel(new Date(1666112400000L), null, null, Calendar.DAY_OF_YEAR));
		sp1.setBounds(126, 42, 205, 39);
		panel.add(sp1);
		
		sp2.setModel(new SpinnerDateModel(new Date(1666112400000L), new Date(1603040400000L), null, Calendar.MILLISECOND));
		sp2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sp2.setBounds(126, 101, 205, 39);
		panel.add(sp2);
		
		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(24, 49, 104, 24);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("End Date");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(24, 108, 104, 24);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\junoc\\eclipse-workspace\\62TH4_Nhom11\\icon\\icons8-search-48 (1).png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double serviceCharge = 0;
				double roomCharge = 0;
				Date d1 = (Date) sp1.getValue();
				Date d2 = (Date) sp2.getValue();
				java.sql.Date d3 = new java.sql.Date(d1.getTime());
				java.sql.Date d4 = new java.sql.Date(d2.getTime());
				if(d2.before(d1)) {
					showMessageDialog(null,"Start date must before end date");
				}
				else {
					try {
						ResultSet rs = p.getBillByDate(d3, d4);
						loadData(rs);
						for (int i = 0; i< Model.getRowCount(); i++) {
							serviceCharge = serviceCharge + (double)Model.getValueAt(i, 8);
							roomCharge = roomCharge + (double)Model.getValueAt(i, 9);
						}
						txtService.setText(Double.toString(serviceCharge));
						txtRoom.setText(Double.toString(roomCharge));
						txtTotal.setText(Double.toString(roomCharge+serviceCharge));
					} catch (SQLException e1) {
//						 TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(353, 59, 181, 60);
		panel.add(btnNewButton);
		
		txtService = new JTextField();
		txtService.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtService.setEditable(false);
		txtService.setBounds(853, 468, 223, 39);
		contentPane.add(txtService);
		txtService.setColumns(10);
		
		txtRoom = new JTextField();
		txtRoom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtRoom.setEditable(false);
		txtRoom.setColumns(10);
		txtRoom.setBounds(853, 528, 223, 39);
		contentPane.add(txtRoom);
		
		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTotal.setEditable(false);
		txtTotal.setToolTipText("");
		txtTotal.setColumns(10);
		txtTotal.setBounds(853, 592, 223, 39);
		contentPane.add(txtTotal);
		
		JLabel lblService = new JLabel("Service");
		lblService.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblService.setBounds(714, 475, 104, 24);
		contentPane.add(lblService);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRoom.setBounds(714, 535, 104, 24);
		contentPane.add(lblRoom);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTotal.setBounds(714, 599, 104, 24);
		contentPane.add(lblTotal);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1165, 415);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			loadData(p.getBillData());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
