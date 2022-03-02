package oop.studentview;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import oop.model.student;
import oop.studentConroller.student_imp;
import oop.studentdb.studentdb;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class StudentForm {

	private JFrame frmStudentInformation;
	private JTextField txtName;
	private JTextField txtCourse;
	private JTextField txtFee;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm window = new StudentForm();
					window.frmStudentInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public StudentForm() {
		initialize();
		load();
	}
	
	
	
	int search;
	public void load() {
		
//		student_imp stn = new student_imp();
//			List<student> list = stn.list();
//			DefaultTableModel DFT = (DefaultTableModel) table.getModel();
//			DFT.setRowCount(0);
//			for(student st:list) {
//				int sid = st.getId();
//				String name = st.getFname();
//				String course = st.getCourse();
//				int fee = st.getFee();
//				DFT.addRow(new Object[] {sid,name,course,fee});
//			
//			}
			
			try {
				Connection conn =  studentdb.getConnection();
				String sql ="select * from student_info";
				PreparedStatement pst =conn.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmStudentInformation = new JFrame();
		frmStudentInformation.setTitle("Student Information");
		frmStudentInformation.setBounds(100, 100, 1103, 576);
		frmStudentInformation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmStudentInformation.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setForeground(Color.BLUE);
		panel.setBounds(10, 11, 1052, 128);
		frmStudentInformation.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(139, 48, 311, 50);
		lblNewLabel.setForeground(Color.WHITE);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 134, 1052, 87);
		frmStudentInformation.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				student stn = new student();
				String sname = txtName.getText();
				String course = txtCourse.getText();
				int fee = Integer.parseInt(txtFee.getText());
				
				stn.setFname(sname);
				stn.setCourse(course);
				stn.setFee(fee);
				
				student_imp info = new student_imp();
				
				info.save(stn);
				load();
				
				txtName.setText("");
				txtCourse.setText("");
				txtFee.setText("");
				txtName.requestFocus();
				
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSave.setBounds(23, 25, 89, 39);
		panel_1.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        student st = new student();

		        String fname = txtName.getText();
		        String course = txtCourse.getText();
		        int fee = Integer.parseInt(txtFee.getText());
		        
		        st.setFname(fname);
		        st.setCourse(course);
		        st.setFee(fee);
		        st.setId(search);
		        student_imp dao = new student_imp();
		        dao.update(st);
		        load();
		        txtName.setText("");
		        txtCourse.setText("");
		        txtFee.setText(""); 
		        txtName.requestFocus();
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnUpdate.setBounds(111, 25, 107, 39);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        student st = new student();
		        st.setId(search);
		        student_imp dao = new student_imp();
		        dao.delete(st);
		        load();
		        txtName.setText("");
		        txtCourse.setText("");
		        txtFee.setText(""); 
		        txtName.requestFocus();
		
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDelete.setBounds(216, 25, 107, 39);
		panel_1.add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			
			
//			int search;
			public void actionPerformed(ActionEvent e) {
				
				search = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID"));
				
				student_imp info = new student_imp();
				
				student stn = info.get(search);
				
				txtName.setText(stn.getFname());
				txtCourse.setText(stn.getCourse());
				txtFee.setText(String.valueOf(stn.getFee()));
				
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSearch.setBounds(312, 25, 107, 39);
		panel_1.add(btnSearch);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(10, 232, 476, 270);
		frmStudentInformation.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(24, 46, 151, 35);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(24, 104, 93, 35);
		panel_2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fee");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(24, 163, 73, 35);
		panel_2.add(lblNewLabel_1_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(246, 46, 151, 35);
		panel_2.add(txtName);
		txtName.setColumns(10);
		
		txtCourse = new JTextField();
		txtCourse.setColumns(10);
		txtCourse.setBounds(246, 108, 151, 35);
		panel_2.add(txtCourse);
		
		txtFee = new JTextField();
		txtFee.setColumns(10);
		txtFee.setBounds(246, 167, 151, 35);
		panel_2.add(txtFee);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(496, 232, 533, 270);
		frmStudentInformation.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 513, 248);
		panel_3.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
	}
}
