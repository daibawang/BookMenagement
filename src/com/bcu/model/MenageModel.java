package com.bcu.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.bcu.bean.Menage;
import com.bcu.dao.MenageDao;

public class MenageModel extends AbstractTableModel{
	MenageDao sdao = new MenageDao();
	private List<Menage> list =sdao.findAll();
	
	private String[] titles = {"管理员账号","管理员姓名","电话","密码"};

	public MenageDao getSdao() {
		return sdao;
	}

	public void setSdao(MenageDao sdao) {
		this.sdao = sdao;
	}

	public List<Menage> getList() {
		return list;
	}

	public void setList(List<Menage> list) {
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
		Menage m = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return m.getMenageNum();
		case 1:
			return m.getMenageName();
		case 2:
			return m.getPhone();
		case 3:
			return m.getMenageNum();
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		return titles[column];
	}
	
}
