package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bcu.bean.Book;
import com.bcu.bean.Student;
import com.bcu.utils.JDBCUtils;
import com.bcu.utils.StringUtil;

/*
 * @功能：此类负责学生的相关操作
 * 查询用户，
 * 修改用户信息，
 * 删除用户，
 * 添加用户
 * */
public class StudentDao {
	public Student findStudent(String studentNum,String studentName,String password ){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from User_student where studentNum=? and studentName=? and password=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {studentNum,studentName,password};
			Student student;
			student=(Student)queryRunner.query(connection, sqlString,new BeanHandler(Student.class),params);
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	public Student findStudentbyId(int id ){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from User_student where id=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {id};
			Student student;
			student=(Student)queryRunner.query(connection, sqlString,new BeanHandler(Student.class),params);
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Student> findStudentAll(){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from User_student ";
			QueryRunner queryRunner = new QueryRunner();
			List<Student> studentlist;
			studentlist=(List<Student>)queryRunner.query(connection, sqlString,new BeanListHandler(Student.class));
			return studentlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	//不定条件查询学生
		@SuppressWarnings("unchecked")
		public List<Student> find(Student student){
			Connection connection = null;
			connection=JDBCUtils.getConnection();
			try {
				StringBuilder  sql = new StringBuilder("select * from User_student s where 1=1");
				List<Object> parms = new ArrayList<Object>();
				
				if(StringUtil.isNotEmpty(student.getStudentNum())){
					sql.append(" and s.studentNum like '%"+student.getStudentNum()+"%' " );
				}
				if(StringUtil.isNotEmpty(student.getStudentName())){
					sql.append(" and s.studentName like '%"+student.getStudentName()+"%' " );
				}
				if(StringUtil.isNotEmpty(student.getStudentClass())){
					sql.append(" and s.studentClass like '%"+student.getStudentClass()+"%' " );
				}
				QueryRunner queryRunner = new QueryRunner();
				return (List<Student>) queryRunner.query(connection,sql.toString(), new BeanListHandler(Student.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//释放资源的操作，一定要放在finally中
				JDBCUtils.release(null, null, connection);
			}
			return null;
		}
		
		public void addStudent(Student student){
			Connection connection = null;
			try {
				connection=JDBCUtils.getConnection();
				String sql = "insert into User_student (studentNum,studentName,password,sex,studentClass,grade) values (?,?,?,?,?,?)";
				QueryRunner queryRunner = new QueryRunner();
				
				Object[] params = {student.getStudentNum(),student.getStudentName(),student.getPassword(),student.getSex(),student.getStudentClass(),student.getGrade()};
				queryRunner.update(connection, sql,params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//释放资源的操作，一定要放在finally中
				JDBCUtils.release(null, null, connection);
			}
		}
		
		public Student findStudentbyNum(int number ){
			Connection connection =null;
			try {
				connection =JDBCUtils.getConnection();
				String sqlString = "select * from User_student where studentNum=?";
				QueryRunner queryRunner = new QueryRunner();
				Object[] params = {number};
				Student student;
				student=(Student)queryRunner.query(connection, sqlString,new BeanHandler(Student.class),params);
				return student;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(null, null, connection);
			}
			return null;
		}
		
		public void updateStudent(Student student,int id){
			Connection connection = null;
			try {
				connection=JDBCUtils.getConnection();
				String sql = "update  User_student set studentNum=?,studentName=?,password=?,sex=?,studentClass=?,grade=? where id=?";
				QueryRunner queryRunner = new QueryRunner();
				Object[] params = {student.getStudentNum(),student.getStudentName(),student.getPassword(),student.getSex(),student.getStudentClass(),student.getGrade(),id};
				queryRunner.update(connection, sql,params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				//释放资源的操作，一定要放在finally中
				JDBCUtils.release(null, null, connection);
			}
		}
}
