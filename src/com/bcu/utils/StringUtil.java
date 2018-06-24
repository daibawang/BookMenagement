package com.bcu.utils;
/*@功能：放一些常用的操作*/
public class StringUtil {
	
	/* 判断是否为空*/
	public static boolean isEmpty(String str){
		if(str==null||str.trim().length()==0){
			return true;
		}else{
			return false;
		}
	}
	
	/* 判断不为空*/
	public static boolean isNotEmpty(String str){
		if(str!=null&&str.trim().length()!=0){
			return true;
		}else{
			return false;
		}
	}

}
