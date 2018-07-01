package com.bcu.bean;

import java.util.Date;

public class Return {
	private int	id;
	private int bookid;
	private int studentid;
	private Date borrowTime;
	private Date returnTime;
	
	public Return() {
		super();
	}
	public Return(int id, int bookid, int studentid, Date borrowTime, Date returnTime) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.studentid = studentid;
		this.borrowTime = borrowTime;
		this.returnTime = returnTime;
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
	public Date getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Override
	public String toString() {
		return "Return [id=" + id + ", bookid=" + bookid + ", studentid=" + studentid + ", borrowTime=" + borrowTime
				+ ", returnTime=" + returnTime + "]";
	}
	
}
