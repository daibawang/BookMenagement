package com.bcu.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Collection;
import com.bcu.bean.Return;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.CollectionDao;
import com.bcu.dao.ReturnDao;
import com.bcu.utils.Time;
import com.bcu.view.Login_student;

public class BookCollectionTableModel extends AbstractTableModel{
			//每页显示的数据个数
			private int number = 8;
			//	临时的list集合，存储更改的书籍信息
			private List<Book> bookList = new ArrayList<>();
			
			public List<Book> getBookList() {
				return bookList;
			}

			public void setBookList(List<Book> bookList) {
				this.bookList = bookList;
			}
			//取出收藏记录的前8条信息
			private List<Collection> list = new CollectionDao().getCollectionByUidlimit(Login_student.Userid, 0, number);
			
			private String[] titles = {"图书名称","图书作者","图书编号","图书类型","出版社","所在书架","在架数"};
			
			public List<Collection> getList() {
				return list;
			}

			public void setList(List<Collection> list) {
				this.list = list;
			}

			public String[] getTitles() {
				return titles;
			}

			public void setTitles(String[] titles) {
				this.titles = titles;
			}

			public int getNumber() {
				return number;
			}

			public void setNumber(int number) {
				this.number = number;
			}


			@Override
			public int getRowCount() {
				return list.size();
			}

			@Override
			public int getColumnCount() {
				return titles.length;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				Collection cbook = list.get(rowIndex);
				BookDao dao = new BookDao();
				Book book = dao.findbookbyid(cbook.getBookid());
				
				switch (columnIndex) {
				case 0:
					return book.getBookName();
				case 1:
					return book.getBookAuthor();
				case 2:
					return book.getBookCode();
				case 3:
					return book.getBookType();
				case 4:
					return book.getPress();
				case 5:
					return book.getBookWhere();
				case 6:
					return book.getNumber();
				}
				return null;
			}
			@Override
			public String getColumnName(int column) {
				return titles[column];
			}
			
			public boolean isNotNull(int index){
				Collection cbook = list.get(index);
				BookDao dao = new BookDao();
				Book book = dao.findbookbyid(cbook.getBookid());
				System.out.println("库存"+book);
				if(book.getNumber()==0){
					return false; //没有
				}else{
					return true;
				}
				
			}
			
			public void Borrowbook(int index){
				System.out.println("index"+index);
				System.out.println(list);
				Collection cbook = list.get(index);
				BookDao dao = new BookDao();
				Book book = dao.findbookbyid(cbook.getBookid());
				Borrow bbook = new Borrow();
				BorrowDao bdao = new BorrowDao();
				bbook.setBookid(book.getId());
				bbook.setStudentid(Login_student.Userid);
				//插入当前时间
				try {
					bbook.setBorrowTime(Time.getNowTime());
					System.out.println(Time.getNowTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bbook.setBookCode(book.getBookCode());
				bbook.setBookName(book.getBookName());
				bbook.setBookType(book.getBookType());
				bbook.setPress(book.getPress());
				bbook.setReturnTime(null);
				bdao.add(bbook);
				//数量减1
				dao.changeBookNum(book.getId(),book.getNumber()-1);
			
			}

}
