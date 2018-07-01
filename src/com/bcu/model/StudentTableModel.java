package com.bcu.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Borrow;
import com.bcu.bean.Return;
import com.bcu.bean.Student;
import com.bcu.dao.BorrowDao;
import com.bcu.dao.ReturnDao;
import com.bcu.dao.StudentDao;
import com.bcu.utils.StringUtil;

public class StudentTableModel  extends AbstractTableModel{
	
//	临时的list集合，存储更改的学生信息
	private List<Student> studentList = new ArrayList<>();
	//获取数据
	StudentDao sdao = new StudentDao();
	private List<Student> list =sdao.findStudentAll();
	private String[] titles = {"学生编号","学生学号","学生姓名","学生性别","年级","班级","借阅书籍书","未归还书籍数"};
	
	
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}


	public List<Student> getList() {
		return list;
	}

	public void setList(List<Student> list) {
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
		Student student = list.get(rowIndex);
		BorrowDao bdao= new BorrowDao();
		ReturnDao rdao = new ReturnDao();
		List<Borrow> bbooklist = bdao.getBorrowByUid(student.getId());
		List<Return> rbooklist = rdao.getReturnByUid(student.getId());
		switch (columnIndex) {
		case 0:
			return student.getId();
		case 1:
			return student.getStudentNum();
		case 2:
			return student.getStudentName();
		case 3:
			return student.getSex();
		case 4:
			return student.getGrade();
		case 5:
			return student.getStudentClass();
		case 6:
			return bbooklist.size();
		case 7:
			return bbooklist.size()-rbooklist.size();
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
	//修改信息
	public void updateStudent(int index,Student stu){
		
		Student  student= list.get(index);//要修改的
//		if(StringUtil.isEmpty(stu.getStudentNum())){
//			stu.setStudentNum(student.getStudentNum());
//		}else if(StringUtil.isEmpty(stu.getGrade())){
//			stu.setGrade(student.getGrade());
//		}else if(StringUtil.isEmpty(stu.getPassword())){
//			stu.setPassword(student.getGrade());
//		}else if(StringUtil.isEmpty(stu.getStudentName())){
//			stu.setStudentName(student.getStudentName());
//		}else if(StringUtil.isEmpty(stu.getSex())){
//			stu.setSex(student.getSex());
//		}else if(StringUtil.isEmpty(student.getStudentClass())){
//			stu.setStudentClass(student.getStudentClass());
//		}
		StudentDao sdao = new StudentDao();
		sdao.updateStudent(stu, student.getId());
		
	}
	//是否可以修改
//	public boolean iscanupdate(int index,Student stu){
//		Student student = list.get(index);
//		StudentDao sdao = new StudentDao();
//		Student stue = sdao.findStudentbyId(student.getId());
//		
//		int num=Integer.parseInt(stu.getStudentNum());
//		Student stus=sdao.findStudentbyNum(num);
//		if(stus==null){ //不重复
//			return true;
//		}else{
//			if(stue.getStudentNum()==stu.getStudentNum()){  //和修改的那条消息学号相同
//				return true;
//			}
//			return false;
//		}
//	}
}
