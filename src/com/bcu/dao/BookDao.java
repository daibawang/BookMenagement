package com.bcu.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.bcu.bean.Book;
import com.bcu.utils.JDBCUtils;

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
			String sql = "select * from bookdetail ";
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
	public void update(Book book){
		Connection connection = null;
		
		try {
			connection=JDBCUtils.getConnection();
			String sql = "update book set bookName=?,bookAuthor=?,bookCode=?,bookType=?,press=?,"
					+ "bookWhere=?,inTime=?,number=?";
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
	/*@不定条件查询书籍*/
//	public ResultSet findbook(Connection con,Book book) throws SQLException{
////		Connection  = null;
////		connection=JDBCUtils.getConnection();
//		//便于拼串
//		StringBuffer sql = new StringBuffer("select * from book b where 1=1");
//		if(StringUtil.isNotEmpty(book.getBookName())){
//			sql.append(" and b.bookName like '%"+book.getBookName()+"%' " );
//		}
//		if(StringUtil.isNotEmpty(book.getBookAuthor())){
//			sql.append(" and b.bookAuthor like '%"+book.getBookAuthor()+"%' " );
//		}
//		if(StringUtil.isNotEmpty(book.getBookType())){
//			sql.append(" and b.bookType like '%"+book.getBookType()+"%' " );
//		}
//		PreparedStatement pstmt =(PreparedStatement) con.prepareStatement(sql.toString());
//		return pstmt.executeQuery();
//	}

}
