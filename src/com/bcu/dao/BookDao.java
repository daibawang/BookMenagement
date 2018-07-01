package com.bcu.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bcu.bean.Book;
import com.bcu.bean.Student;
import com.bcu.utils.JDBCUtils;
import com.bcu.utils.StringUtil;

/*
 * 
 * 此类负责书本相关的操作
 * 	查询 删除 添加 修改
 */
public class BookDao {
	
	public List<Book> getBooks() {
		Connection connection = null;
		
		try {
			connection =JDBCUtils.getConnection();
			String sql = "select * from book ";
			QueryRunner queryRunner = new QueryRunner();
			List<Book> list = (List<Book>) queryRunner.query(connection, sql,new BeanListHandler(Book.class));
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	//限制条数查询
	public List<Book> getBooks(int start,int number){
			Connection connection = null;
		try {
			connection =JDBCUtils.getConnection();
			String sql = " select  *  from  book  limit ?,?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] pramas = {start,number};
			List<Book> list = (List<Book>) queryRunner.query(connection, sql,new BeanListHandler(Book.class),pramas);
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	//	有多少条数据
	public int getCount() {
		Connection connection = null;
		long count;
		try {
			connection=JDBCUtils.getConnection();
			String sql = "select count(id) from book";
			QueryRunner queryRunner = new QueryRunner();
			count = (long) queryRunner.query(connection, sql,new ScalarHandler());
			return (int) count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return 0;
	}
	public void addbook(Book book){
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "insert into book (bookName,bookAuthor,bookCode,bookType,press,bookWhere,inTime,number) values (?,?,?,?,?,?,?,?)";
			QueryRunner queryRunner = new QueryRunner();
			
			Object[] params = {book.getBookName(),book.getBookAuthor(),book.getBookCode(),
					book.getBookType(),book.getPress(),book.getBookWhere(),book.getInTime(),book.getNumber()};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	public void update(Book book,int id){
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "update book set bookName=?,bookAuthor=?,bookCode=?,bookType=?,press=?,"
					+ "bookWhere=?,inTime=?,number=? where id=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {book.getBookName(),book.getBookAuthor(),book.getBookCode(),
					book.getBookType(),book.getPress(),book.getBookWhere(),book.getInTime(),book.getNumber(),id};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	public void changeBookNum(int id , int num ){
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "update book set number=? where id=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {num,id};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	public void delete(String  bookCode) {
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "delete from book where bookCode = ?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {bookCode};
			queryRunner.update(connection, sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
	}
	//不定条件查询书记
	@SuppressWarnings("unchecked")
	public List<Book> findbook(Book fbook){
		Connection connection = null;
		connection=JDBCUtils.getConnection();
		try {
			StringBuilder  sql = new StringBuilder("select * from book b where 1=1");
			List<Object> parms = new ArrayList<Object>();
			
			if(StringUtil.isNotEmpty(fbook.getBookName())){
				sql.append(" and b.bookName like '%"+fbook.getBookName()+"%' " );
			}
			if(StringUtil.isNotEmpty(fbook.getBookAuthor())){
				sql.append(" and b.bookAuthor like '%"+fbook.getBookAuthor()+"%' " );
			}
			if(StringUtil.isNotEmpty(fbook.getBookType())){
				sql.append(" and b.bookType like '%"+fbook.getBookType()+"%' " );
			}
			QueryRunner queryRunner = new QueryRunner();
			return (List<Book>) queryRunner.query(connection,sql.toString(), new BeanListHandler(Book.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	//按照id/bookid查询
	public Book findbookbyid(int id){
		Connection connection=null;
		connection=JDBCUtils.getConnection();

		try {
			String sql = "select * from book where id = ? ";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {id};
			Book findbook = null;
			findbook=(Book)queryRunner.query(connection, sql,new BeanHandler(Book.class),params);
			return findbook;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			//释放资源的操作，一定要放在finally中
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	//按照编号来查找
	public Book findBookBycode(int number ){
		Connection connection =null;
		try {
			connection =JDBCUtils.getConnection();
			String sqlString = "select * from book where bookCode=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = {number};
			Book book;
			book=(Book)queryRunner.query(connection, sqlString,new BeanHandler(Book.class),params);
			return book;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCUtils.release(null, null, connection);
		}
		return null;
	}
	
	//按照书名来查找
		public Book findBookByname(String name ){
			Connection connection =null;
			try {
				connection =JDBCUtils.getConnection();
				String sqlString = "select * from book where bookName=?";
				QueryRunner queryRunner = new QueryRunner();
				Object[] params = {name};
				Book book;
				book=(Book)queryRunner.query(connection, sqlString,new BeanHandler(Book.class),params);
				return book;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(null, null, connection);
			}
			return null;
		}


}
