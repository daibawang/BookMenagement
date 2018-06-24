package com.bcu.bean;

import java.util.Date;

public class Book {
	
	private int id;
	private String bookName;
	private String bookAuthor;
	private String bookCode;
	private String bookType;
	private String bookWhere;
	private String press;
	private Date inTime;
	private int bookNow;
	private int number;
	
	public Book() {
		super();
	}
	public Book(int id, String bookName, String bookAuthor, String bookCode, String bookType, String bookWhere,
			String press, Date inTime, int bookNow, int number) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookCode = bookCode;
		this.bookType = bookType;
		this.bookWhere = bookWhere;
		this.press = press;
		this.inTime = inTime;
		this.bookNow = bookNow;
		this.number = number;
	}
	public Book(String bookName, String bookAuthor, String bookType) {
		super();
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookType = bookType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getBookWhere() {
		return bookWhere;
	}
	public void setBookWhere(String bookWhere) {
		this.bookWhere = bookWhere;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public int getBookNow() {
		return bookNow;
	}
	public void setBookNow(int bookNow) {
		this.bookNow = bookNow;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getBookCode() {
		return bookCode;
	}
	public void setBookCode(String bookCode) {
		this.bookCode = bookCode;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor + ", bookCode=" + bookCode
				+ ", bookType=" + bookType + ", bookWhere=" + bookWhere + ", press=" + press + ", inTime=" + inTime
				+ ", bookNow=" + bookNow + ", number=" + number + "]";
	}

	
}
