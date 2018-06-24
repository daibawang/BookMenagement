package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bcu.bean.Menage;
import com.bcu.bean.Student;
import com.bcu.utils.JDBCUtils;

/*
 * @功能：此类负责管理员的相关操作
 * 查询用户，
 * 修改用户信息，
 * 删除用户，
 * 添加用户
 * private String menageNum;
	private String menageName;
	private String menagePassword;
	private String phone;
 * */
public class MenageDao {
	public Menage findMenage(String menageNum,String menagePassword){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from User_menage where menageNum=? and menagePassword=? ";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {menageNum,menagePassword};
			Menage menage;
			menage=(Menage)queryRunner.query(connection, sqlString,new BeanHandler(Menage.class),params);
			return menage;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
}
