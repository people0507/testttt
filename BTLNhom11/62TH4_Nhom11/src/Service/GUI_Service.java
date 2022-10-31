package Service;
import static javax.swing.JOptionPane.showMessageDialog;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class GUI_Service extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtService;
	private JTextField txtPrice;
	private JTable table;
	
	DefaultTableModel  Model =  new DefaultTableModel();
	Vector<String> columns = new Vector<String>();
	Vector<Vector<Object>> rows = new Vector<>();
	Process_Service ps = new Process_Service();

	
	public void get_AllService() {
	
		Model.setDataVector(rows, columns);
		table.setModel(Model);
		 ArrayList<Service> ls =  ps.get_ListService();
		 for(int i=0;i<ls.size();i++) {
			 Service s = (Service) ls.get(i);
			 Vector<Object> tbRow = new Vector<>();
			 tbRow.add(s.getServiceID());
			 tbRow.add(s.getName());
			 tbRow.add(s.getPrice());
			 rows.add(tbRow);
		 }
		 Model.setDataVector(rows, columns);
		 table.setModel(Model);
		 
	}
	// add
	public  void addService(String serviceID,String Name,double Price) {
		if (ps.add_Service(serviceID, Name, Price) == true) {
			Model.setRowCount(0);
			ArrayList<Service> ls = ps.get_ListService();
			for (int i = 0; i < ls.size(); i++) {
				Service s = (Service) ls.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(s.getServiceID());
				tbRow.add(s.getName());
				tbRow.add(s.getPrice());	
				rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			table.setModel(Model);
		}
		
	}
	
	public  void updateService(String serviceID,String Name,double Price) {
		if (ps.update_Service(serviceID, Name, Price) == true) {
			Model.setRowCount(0);
			ArrayList<Service> ls = ps.get_ListService();
			for (int i = 0; i < ls.size(); i++) {
				Service s = (Service) ls.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(s.getServiceID());
				tbRow.add(s.getName());
				tbRow.add(s.getPrice());	
				rows.add(tbRow);
			}
			Model.setDataVector(rows, columns);
			table.setModel(Model);
		}
	}
	
	public  void deleteService(String serviceID) {
		if (ps.delete_Service(serviceID) == true) {
			Model.setRowCount(0);
			ArrayList<Service> ls = ps.get_ListService();
			for (int i = 0; i < ls.size(); i++) {
				Service s = (Service) ls.get(i);
				Vector<Object> tbRow = new Vector<>();
				tbRow.add(s.getServiceID());	
				tbRow.add(s.getName());
				tbRow.add(s.getPrice());
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
					GUI_Service frame = new GUI_Service();
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
	public GUI_Service() {
		setTitle("SERVICE MANAGER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Service management", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 66, 530, 675);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Service ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(44, 80, 99, 34);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Service name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(44, 177, 99, 34);
		panel.add(lblNewLabel_1);
		
		JLabel lbpr = new JLabel("Price");
		lbpr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbpr.setBounds(44, 281, 99, 34);
		panel.add(lbpr);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon("icon\\add32.png"));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtID.getText().equals("") || txtService.getText().equals("") || txtPrice.getText().equals("")) {
					showMessageDialog(null, "Please enter enough information.");
				}else{
	                    ArrayList<Service> arr = ps.get_ListService();
	                    boolean checkSame = false;
	                    for (int i = 0; i < arr.size(); i++) {
	                    	Service s = (Service) arr.get(i);
	                        if (txtID.getText().equals(s.getServiceID())) {
	                            checkSame = true;
	                        }
	                    }
	                    if (checkSame) showMessageDialog(null,"ID already exists.");        
	                    else {
	                    	int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to insert this service?", "Insert",
	     			               JOptionPane.YES_NO_OPTION,
	     			               JOptionPane.QUESTION_MESSAGE);
	     			            if(result == JOptionPane.YES_OPTION){
	     			            	addService(txtID.getText(),txtService.getText(),Double.parseDouble(txtPrice.getText()));
	     			            	txtID.setText(null);
	    							txtService.setText(null);
	    							txtPrice.setText(null);
	     			            }
	                    }
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(58, 432, 160, 47);
		panel.add(btnAdd);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setIcon(new ImageIcon("icon\\update32.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to update this service?", "Update",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	updateService(txtID.getText(),txtService.getText(),Double.parseDouble(txtPrice.getText()));
			            	txtID.setText(null);
							txtService.setText(null);
							txtPrice.setText(null);
			            }
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(310, 432, 153, 47);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setIcon(new ImageIcon("icon\\Editing-Eraser-icon.png"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(contentPane,"Do you want to delete this service?", "Delete",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			               deleteService(txtID.getText());
							txtID.setText(null);
							txtService.setText(null);
							txtPrice.setText(null);
			            }
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(58, 568, 160, 47);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.setIcon(new ImageIcon("icon\\delete32.png"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(310, 568, 153, 47);
		panel.add(btnNewButton_3);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtID.setBounds(168, 80, 326, 34);
		panel.add(txtID);
		txtID.setColumns(10);
		
		txtService = new JTextField();
		txtService.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtService.setColumns(10);
		txtService.setBounds(168, 177, 326, 34);
		panel.add(txtService);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPrice.setColumns(10);
		txtPrice.setBounds(168, 281, 326, 34);
		panel.add(txtPrice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Service List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(567, 66, 609, 675);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 31, 589, 634);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				txtID.setText((String)(Model.getValueAt(index,0)));
				txtService.setText((String)(Model.getValueAt(index,1)));
				txtPrice.setText(String.valueOf(Model.getValueAt(index,2)));
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblServiceManager = new JLabel("SERVICE MANAGER");
		lblServiceManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblServiceManager.setBounds(459, 20, 212, 36);
		contentPane.add(lblServiceManager);
		columns.add("Mã dịch vụ");
		columns.add("Tên dịch vụ");
		columns.add("Giá dịch vụ");
		
		get_AllService();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
