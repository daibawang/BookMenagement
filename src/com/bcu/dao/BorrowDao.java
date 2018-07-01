package com.bcu.dao;
/*
 * 
 * 此类负责借阅记录的
 * 查询 删除 添加 操作
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.bcu.bean.Borrow;
import com.bcu.utils.JDBCUtils;

public class BorrowDao {
	
	public List<Borrow> getBorrowNocount(int id,int start,int number){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where studentid=? and state = 0 limit ?,?";
			Object[] params = {id,start,number};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Borrow> list = (List<Borrow>)queryRunner.
					query(connection,sql,new BeanListHandler(Borrow.class),params);
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
	public List<Borrow> getBorrow(){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow ";
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Borrow> list = (List<Borrow>)queryRunner.
					query(connection,sql,new BeanListHandler(Borrow.class));
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
	public List<Borrow> getBorrowByState(int id,int start,int number){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where studentid=? order by borrowTime DESC limit ?,?";
			Object[] params = {id,start,number};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Borrow> list = (List<Borrow>)queryRunner.
					query(connection,sql,new BeanListHandler(Borrow.class),params);
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
	//借书记录的id
	public Borrow getBorrowById(int id){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where id=?";
			Object[] params = {id};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			Borrow borrow = (Borrow)queryRunner.
					query(connection,sql,new BeanHandler(Borrow.class),params);
			return borrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	//stuid bookid
	public Borrow getBorrowBytwoid(int stuid,int bookid){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where studentid=? and bookid=?";
			Object[] params = {stuid,bookid};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			Borrow borrow = (Borrow)queryRunner.
					query(connection,sql,new BeanHandler(Borrow.class),params);
			return borrow;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	public List<Borrow> getBorrowByUid(int userid){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where studentid=?";
			Object[] params = {userid};
			QueryRunner queryRunner = new QueryRunner();
			@SuppressWarnings("unchecked")
			List<Borrow> list = (List<Borrow>)queryRunner.
					query(connection,sql,new BeanListHandler(Borrow.class),params);
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
	public List<Borrow> getBorrowByBid(int bookid){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "select * from borrow where bookid=?";
			Object[] params = {bookid};
			QueryRunner queryRunner = new QueryRunner();
			
			List<Borrow> list = (List<Borrow>)queryRunner.
					query(connection,sql,new BeanListHandler(Borrow.class),params);
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
	
	public void add(Borrow borrow){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			String sql = "insert into borrow (bookid,studentid,borrowTime,bookName,bookCode,bookType,press,returnTime) values (?,?,?,?,?,?,?,?)";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {borrow.getBookid(),borrow.getStudentid(),borrow.getBorrowTime(),borrow.getBookName(),borrow.getBookCode(),borrow.getBookType(),borrow.getPress(),null};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	public void updatestate(int bookid,Date nowdaye){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		
		try {
			String sql = "update borrow set state=1,returnTime=? where bookid=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {nowdaye,bookid};
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
