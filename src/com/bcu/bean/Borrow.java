package com.bcu.bean;

import java.util.Date;

public class Borrow {
	private int	id;
	private int bookid;
	private int studentid;
	private Date borrowTime;
	private Date returnTime;
	private int state;
	private String bookName;
	private String bookCode;
	private String bookType;
	private String press;

	
	public Borrow() {
		super();
	}
	public Borrow(int id, int bookid, int studentid, Date borrowTime, Date returnTime, int state, String bookName,
			String bookCode, String bookType, String press) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.studentid = studentid;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
		this.state = state;
		this.bookName = bookName;
		this.bookCode = bookCode;
		this.bookType = bookType;
		this.press = press;
	}
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public Date getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(Date borrowTime) {
		this.borrowTime = borrowTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Borrow [id=" + id + ", bookid=" + bookid + ", studentid=" + studentid + ", borrowTime=" + borrowTime
				+ ", returnTime=" + returnTime + ", state=" + state + ", bookName=" + bookName + ", bookCode="
				+ bookCode + ", bookType=" + bookType + ", press=" + press + "]";
	}
	



	

}
