package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bcu.bean.Borrow;
import com.bcu.bean.Return;
import com.bcu.utils.JDBCUtils;

public class ReturnDao {
	public List<Return> getReturnByUid(int id){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from returnbook where studentid=?";
			Object[] params = {id};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Return> list = (List<Return>)queryRunner.
					query(connection,sql,new BeanListHandler(Return.class),params);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<Return> getReturnByBid(int id){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from returnbook where bookid=?";
			Object[] params = {id};
			QueryRunner queryRunner = new QueryRunner();
			
			List<Return> list = (List<Return>)queryRunner.
					query(connection,sql,new BeanListHandler(Return.class),params);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	public void add(Return returnbook){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "insert into returnbook (bookid,studentid,borrowTime,returnTime) values (?,?,?,?)";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {returnbook.getBookid(),returnbook.getStudentid(),returnbook.getBorrowTime(),returnbook.getReturnTime()};
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
