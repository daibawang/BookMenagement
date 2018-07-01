package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bcu.bean.Book;
import com.bcu.bean.Menage;
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
	public List<Menage> findAll(){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from User_menage ";
			QueryRunner queryRunner = new QueryRunner();
			 List<Menage> menage=( List<Menage>)queryRunner.query(connection, sqlString,new BeanListHandler(Menage.class));
			return menage;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	public void add(Menage meng){
		Connection connection = null;
		try {
			connection=JDBCUtils.getConnection();
			String sql = "insert into user_menage (menageNum,menageName,menagePassword,phone) values (?,?,?,?)";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {meng.getMenageNum(),meng.getMenageName(),meng.getMenagePassword(),meng.getPhone()};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	public void update(Menage meng,int id){
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "update user_menage set menageNum=?,menageName=?,menagePassword=?,phone=? where id=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {meng.getMenageNum(),meng.getMenageName(),meng.getMenagePassword(),meng.getPhone(),id};
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
