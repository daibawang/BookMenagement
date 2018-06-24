package com.bcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Book;
import com.bcu.dao.BookDao;

public class BookTableModel extends AbstractTableModel{

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
	//最初先加载前7条数据
	private List<Book> list = new BookDao().getBooks(0,number);
	private String[] titles = {"图书名称","图书作者","图书编号","图书类型","出版社","所在书架","出版日期","在架数"};
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
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
		Book book = list.get(rowIndex);
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
			return book.getInTime();
		case 7:
			return book.getNumber();
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
	

}
