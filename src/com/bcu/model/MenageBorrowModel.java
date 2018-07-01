package com.bcu.model;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Book;
import com.bcu.bean.Borrow;
import com.bcu.bean.Return;
import com.bcu.bean.Student;
import com.bcu.dao.BookDao;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.ReturnDao;
import com.bcu.dao.StudentDao;
import com.bcu.utils.Time;
import com.bcu.view.Login_student;

public class MenageBorrowModel extends AbstractTableModel{
	
	private List<Borrow> list = new BorrowDao().getBorrow();
	
	private String[] titles = {"借阅编号","学生学号","学生姓名","图书名称","借阅日期","还书日期","借阅状态"};
	
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
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titles.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Borrow borrowbook = list.get(rowIndex);
		StudentDao sdao = new StudentDao();
		Student student = sdao.findStudentbyId(borrowbook.getStudentid());
		String states;
		if(borrowbook.getState()==0){
			states="未归还";
		}else{
			states="已归还";
		}
		switch (columnIndex) {
		case 0:
			return borrowbook.getId();
		case 1:
			return student.getStudentNum();
		case 2:
			return student.getStudentName();
		case 3:
			return borrowbook.getBookName();
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
	public boolean isreturn(int index,String bname){
		BookDao bodao = new BookDao();
		Book book = new Book();
		book=bodao.findBookByname(bname); //要还的书
		
		BorrowDao dao = new BorrowDao();
		Borrow rbook = dao.getBorrowBytwoid(index,book.getId());
		if(rbook==null){
			return false; //没借书
		}else{
			return true;
		}
		
	}
	public void returnbook(int index,String bname){
		try {
			Date nowDate = Time.getNowTime();
			BookDao bodao = new BookDao();
			Book book = new Book();
			book=bodao.findBookByname(bname); //要还的书
			Return rbook = new Return();
			rbook.setBookid(book.getId());
			rbook.setBorrowTime(rbook.getBorrowTime());
			rbook.setReturnTime(nowDate);
			rbook.setStudentid(index);
			ReturnDao rDao = new ReturnDao();
			rDao.add(rbook);
			BorrowDao dao = new BorrowDao();
			dao.updatestate(book.getId(),nowDate); //标记为已还书
			bodao.changeBookNum(book.getId(),book.getNumber()+1);//库存+1
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
			//借书
			public void Borrowbook(Borrow rbook){
				BorrowDao bdao = new BorrowDao();
				BookDao bodao = new BookDao();
				Borrow bbook = new Borrow();
				Book book = new Book();
				book=bodao.findBookByname(rbook.getBookName());
				
				bbook.setBookCode(book.getBookCode());
				bbook.setBookType(book.getBookType());
				bbook.setPress(book.getPress());
				bbook.setReturnTime(null);
				bdao.add(bbook);	
				//数量减1
				BookDao dao = new BookDao();
				dao.changeBookNum(book.getId(),book.getNumber()-1);
			}
			
			
			
			public List<Borrow> findbynum(int stunum){
				BorrowDao bDao = new BorrowDao();
				Student student = new Student();
				StudentDao sDao = new StudentDao();
				student = sDao.findStudentbyNum(stunum);
				return bDao.getBorrowByUid(student.getId());
				
			}
			
	
}
