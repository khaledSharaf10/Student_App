/**
 * 
 */
package oop.studentConroller;

import java.util.List;

import oop.model.student;

/**
 * @author Sharaf
 *
 */
public interface student_interface {
	
	public void save(student students);
	public void update(student students);
	public void delete(student students);
	public student get(int id);
	public List<student> list();

}
