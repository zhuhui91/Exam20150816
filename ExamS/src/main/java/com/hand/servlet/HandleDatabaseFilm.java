package com.hand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hand.bean.*;

import com.sun.rowset.*;

import java.sql.*;
import java.io.*;

/**
 * Servlet implementation class HandleDatabaseFilm
 */
public class HandleDatabaseFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleDatabaseFilm() {
        super();
        // TODO Auto-generated constructor stub
    }
    CachedRowSetImpl rowSet=null;
    public void init(ServletConfig config) throws ServletException{
       super.init(config);
       try {  Class.forName("com.mysql.jdbc.Driver");
       }
       catch(Exception e){
    	   e.printStackTrace();
       } 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
HttpSession session=request.getSession(true); 
blogin login=(blogin)session.getAttribute("login"); 
boolean ok=true; 
if(login==null){
  ok=false; 
  response.sendRedirect("login.jsp");    
}
if(ok==true){
  continueDoPost(request,response);
}
}
		
	 public void continueDoPost(HttpServletRequest request,HttpServletResponse response)
             throws ServletException,IOException { 
HttpSession session=request.getSession(true); 
Connection con=null; 
StringBuffer presentPageResult=new StringBuffer();
showFilm showBean=null;
try{ 
showBean=(showFilm)session.getAttribute("show");
if(showBean==null){
showBean=new showFilm();  
session.setAttribute("show",showBean);
}
}
catch(Exception exp){
showBean=new showFilm();  
session.setAttribute("show",showBean);
} 
showBean.setPageSize(3);  //每页显示3条记录
int showPage=Integer.parseInt(request.getParameter("showPage"));
if(showPage>showBean.getPageAllCount())
showPage=1;
if(showPage<=0)
showPage=showBean.getPageAllCount();
showBean.setShowPage(showPage);  
int pageSize=showBean.getPageSize();
String uri="jdbc:mysql://localhost:3306/sakila";
try{ 
con=DriverManager.getConnection(uri,"sakila","");
Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_READ_ONLY);
ResultSet rs=sql.executeQuery("SELECT * FROM film");
rowSet=new CachedRowSetImpl();  //创建行集对象
rowSet.populate(rs);
con.close();                     //关闭连接
showBean.setRowSet(rowSet);  //数据存储在showBean中
rowSet.last();
int m=rowSet.getRow();           //总行数
int n=pageSize;
int pageAllCount=((m%n)==0)?(m/n):(m/n+1);
showBean.setPageAllCount(pageAllCount);//数据存储在showBean中 
presentPageResult=show(showPage,pageSize,rowSet);
showBean.setPresentPageResult(presentPageResult); 
}
catch(SQLException exp){}
RequestDispatcher dispatcher=
request.getRequestDispatcher("showfilm.jsp");
dispatcher.forward(request, response); 
} 
	
	 public StringBuffer show(int page,int pageSize,CachedRowSetImpl rowSet){
	      StringBuffer str=new StringBuffer();
	      try{ 
	           rowSet.absolute((page-1)*pageSize+1);
	           for(int i=1;i<=pageSize;i++){
	              str.append("<tr height=200>");
	              str.append("<td width=50>"+rowSet.getString(1)+"</td>"); 
	              str.append("<td width=50>"+rowSet.getString(3)+"</td>");
	              str.append("<td width=50>"+rowSet.getString(4)+"</td>");
	              str.append("<td width=200>"+rowSet.getString(5)+"</td>");
	              str.append("</tr>");
	              rowSet.next();  
	           }
	      }
	      catch(SQLException exp){}
	      return str;
	   }
		
}

