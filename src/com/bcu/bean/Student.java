package com.bcu.bean;

import java.util.Date;

public class Student {
	private int id;
	private String studentNum;
	private String studentName;
	private String password;
	private String sex;
	private Date birthdate;
	private String studentClass;
	private String grade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getStudentClass() {
		return studentClass;
	}
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", studentNum=" + studentNum + ", studentName=" + studentName + ", password="
				+ password + ", sex=" + sex + ", birthdate=" + birthdate + ", studentClass=" + studentClass + ", grade="
				+ grade + "]";
	}
	

}
