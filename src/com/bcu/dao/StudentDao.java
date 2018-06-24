package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bcu.bean.Student;
import com.bcu.utils.JDBCUtils;

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
}
