package com.hand.bean;

public class blogin{
	   String  logname,backNews="";  
	   boolean success=false; 
	   public void setLogname(String name){
	      logname=name;
	   }
	   public String getLogname(){
	      return logname;
	   }
	   public String getBackNews(){
	      return backNews;
	   }
	   public void setBackNews(String s){
	      backNews=s;
	   } 
	   public void setSuccess(boolean b){
	      success=b;
	   }
	   public boolean getSuccess(){
	      return success;
	   }
	}
