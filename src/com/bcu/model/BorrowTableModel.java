package com.bcu.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Return;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.ReturnDao;
import com.bcu.utils.Time;
import com.bcu.view.Login_student;

public class BorrowTableModel extends AbstractTableModel{
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
		//取出借书记录的前8条信息
		private List<Borrow> list = new BorrowDao().getBorrowByState(Login_student.Userid, 0, number);
		private String[] titles = {"图书名称","图书编号","图书类型","出版社","借阅日期","还书日期","借阅状态"};
		
		public int getNumber() {
			return number;
		}

		public void setNumber(int number) {
			this.number = number;
		}

		public List<Borrow> getList() {
			return list;
		}

		public void setList(List<Borrow> list) {
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
			Borrow borrowbook = list.get(rowIndex);
			String states;
			if(borrowbook.getState()==0){
				states="未归还";
			}else{
				states="已归还";
			}
			switch (columnIndex) {
			case 0:
				return borrowbook.getBookName();
			case 1:
				return borrowbook.getBookCode();
			case 2:
				return borrowbook.getBookType();
			case 3:
				return borrowbook.getPress();
			case 4:
				return borrowbook.getBorrowTime();
			case 5:
				return borrowbook.getReturnTime();
			case 6:
				return states;
			}
			return null;
		}
		@Override
		public String getColumnName(int column) {
			return titles[column];
		}
		public boolean isreturn(int index){
			Borrow rbookindex = list.get(index); //要还的书
			BorrowDao dao = new BorrowDao();
			Borrow rbook = dao.getBorrowById(rbookindex.getId());
			if(rbook.getState()==0){
				return false; //未还
			}else{
				return true;
			}
			
		}
		
		public void returnbook(int index){
			System.out.println("index"+index);
			System.out.println(list);
			try {
				Date nowDate = Time.getNowTime();
				Borrow rbookindex = list.get(index); //要还的书
				System.out.println(rbookindex);
				BorrowDao dao = new BorrowDao();
				dao.updatestate(rbookindex.getBookid(),nowDate); //标记为已还书
				ReturnDao rdao = new ReturnDao();
				Return rbook=new Return();
				rbook.setBookid(rbookindex.getBookid());
				rbook.setBorrowTime(rbookindex.getBorrowTime());
				rbook.setStudentid(Login_student.Userid);
				//插入当前时间
				rbook.setReturnTime(nowDate);
				System.out.println(Time.getNowTime());
				rdao.add(rbook);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void addBoonNume(int index){
			Borrow rbookindex = list.get(index); //要还的书
			BookDao bdao = new BookDao();
			Book rbook = bdao.findbookbyid(rbookindex.getBookid());
			bdao.changeBookNum(rbookindex.getBookid(),rbook.getNumber()+1);
		}
		
		

}
