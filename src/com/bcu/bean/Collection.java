package com.bcu.bean;

import java.util.Date;

public class Collection  {
	private int	id;
	private int bookid;
	private int studentid;
	
	public Collection() {
		super();
	}
	public Collection(int id, int bookid, int studentid) {
		super();
		this.id = id;
		this.bookid = bookid;
		this.studentid = studentid;
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
	@Override
	public String toString() {
		return "Collection [id=" + id + ", bookid=" + bookid + ", studentid=" + studentid + "]";
	}
	
	

}
