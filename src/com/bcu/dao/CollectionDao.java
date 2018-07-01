package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bcu.bean.Borrow;
import com.bcu.bean.Collection;
import com.bcu.utils.JDBCUtils;

public class CollectionDao {
	
	public List<Collection> getCollection(int studentid,int bookid){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "SELECT * from collection where studentid=? and bookid=?";
			Object[] params = {studentid,bookid};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Collection> list = (List<Collection>)queryRunner.
					query(connection,sql,new BeanListHandler(Collection.class),params);
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
	public List<Collection> getCollectionByUidlimit(int id,int start,int number){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from collection where studentid=? limit ?,?";
			Object[] params = {id,start,number};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Collection> list = (List<Collection>)queryRunner.
					query(connection,sql,new BeanListHandler(Collection.class),params);
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
	
	public List<Collection> getCollectionByUid(int id){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from collection where studentid=?";
			Object[] params = {id};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Collection> list = (List<Collection>)queryRunner.
					query(connection,sql,new BeanListHandler(Collection.class),params);
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
	public List<Collection> getBorrowByBid(int id){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from collection where bookid=?";
			Object[] params = {id};
			QueryRunner queryRunner = new QueryRunner();
			
			List<Collection> list = (List<Collection>)queryRunner.
					query(connection,sql,new BeanListHandler(Collection.class),params);
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
	
	public void add(Collection collection){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "insert into collection (bookid,studentid) values (?,?)";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {collection.getBookid(),collection.getStudentid()};
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
