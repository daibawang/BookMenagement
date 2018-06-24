package com.bcu.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;


import com.bcu.model.BookTableModel;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class Try extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	
	private int start;
	private int number = 5;
	private BookTableModel tableModel;
	private JPanel panel_1;
	private JButton btnAdd;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Try frame = new Try();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Try() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
//		使用数组的方式给表格提供数据
		String[][] data = {
				{"张三","22","男"},
				{"张三","22","男"},
				{"张三","22","男"},
				{"张三","22","男"}
		};
		String[] titles = {"姓名","年龄","性别"};
		table = new JTable(data,titles);
//		如果直接将table加到contentPane上，不会显示表头，我们需要在中间加一层
//		JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		*/
		
		tableModel = new BookTableModel();
		table = new JTable(tableModel);
//		如果直接将table加到contentPane上，不会显示表头，我们需要在中间加一层
//		JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		
		
		
		
	}
}
