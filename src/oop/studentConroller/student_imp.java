package oop.studentConroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

//import javax.swing.JOptionPane;


import oop.model.student;
import oop.studentdb.studentdb;

public class student_imp implements student_interface {

	@Override
	public void save(student students) {
		try {
			Connection conn =  studentdb.getConnection();
			String sql ="insert into student_info (fname , course , fee) values (?,?,?)";
			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setString(1, students.getFname());
			pst.setString(2, students.getCourse());
			pst.setInt(3, students.getFee());
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Saved");
//			load();
//			txtbname.setText("");
//			txtedition.setText("");
//			txtprice.setText("");
//			txtbname.requestFocus();
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		} 
		
	}

	@Override
	public void update(student students) {
		// TODO Auto-generated method stub
	     try {
	          
	            Connection con =studentdb.getConnection();
	            String sql = "UPDATE student_info SET fname=?,course=?,fee=? WHERE id=?";
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setString(1, students.getFname());
	            ps.setString(2, students.getCourse());
	            ps.setInt(3, students.getFee());
	            ps.setInt(4, students.getId());
	            ps.executeUpdate();
	 
	        
	            JOptionPane.showMessageDialog(null, "Updated!");
	        } catch (Exception e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error");
	        }
		
	}

	@Override
	public void delete(student students) {
		// TODO Auto-generated method stub
		
		try {
	          
            Connection con = studentdb.getConnection();
            String sql = "delete from student_info WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);  
            ps.setInt(1, students.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleteddd!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error");
        }
		
	}

	@Override
	public student get(int id) {
		student st = new student();
		
		try {
			Connection conn =  studentdb.getConnection();
			String sql ="select * from student_info where id =?";
			PreparedStatement pst =conn.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rs =pst.executeQuery();
			if(rs.next()) {
				st.setId(rs.getInt("id"));
				st.setFname(rs.getString("fname"));
				st.setCourse(rs.getString("course"));
				st.setFee(rs.getInt(rs.getInt("fee")));
				
			}
//			JOptionPane.showMessageDialog(null, "Saved");
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
		} 
		return st;
	}

	@Override
	public List<student> list() {
		
		List<student> list = new ArrayList<student>();
		try {
			Connection conn = studentdb.getConnection();
			String sql ="select * from student_info";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			
			while(rs.next()) {
				student st = new student();
				st.setId(rs.getInt("id"));
				st.setFname(rs.getString("fname"));
				st.setCourse(rs.getString("course"));
				st.setFee(rs.getInt(rs.getInt("fee")));
				list.add(st);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erorr");
		}
		return list;
	}
	
	

}
