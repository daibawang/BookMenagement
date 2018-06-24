package com.bcu.bean;

import java.util.Date;

public class Menage {
	private int id;
	private String menageNum;
	private String menageName;
	private String menagePassword;
	private String phone;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenageNum() {
		return menageNum;
	}
	public void setMenageNum(String menageNum) {
		this.menageNum = menageNum;
	}
	public String getMenageName() {
		return menageName;
	}
	public void setMenageName(String menageName) {
		this.menageName = menageName;
	}
	public String getMenagePassword() {
		return menagePassword;
	}
	public void setMenagePassword(String menagePassword) {
		this.menagePassword = menagePassword;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Menage [id=" + id + ", menageNum=" + menageNum + ", menageName=" + menageName + ", menagePassword="
				+ menagePassword + ", phone=" + phone + "]";
	}
}
	
