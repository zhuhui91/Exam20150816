package com.hand.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hand.bean.blogin;

/**
 * Servlet implementation class HandleLogin
 */
//public class HandleLogin extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public HandleLogin() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//    public void init(ServletConfig config) throws ServletException{
//	      super.init(config);
//	      try{ 
//	    	  Class.forName("com.mysql.jdbc.Driver"); 
//	      }
//	      catch(Exception e){
//	    	  e.printStackTrace();
//	      } 
//	   }
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doPost(request,response);
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//		   public void doPost(HttpServletRequest request,HttpServletResponse response) 
//		                        throws ServletException,IOException{
//		      Connection con; 
//		      Statement sql; 
//		      blogin loginBean=null;
//		      String backNews="";
//		      HttpSession session=request.getSession(true);
//		      try{  loginBean=(blogin)session.getAttribute("login");
//		            if(loginBean==null){
//		               loginBean=new blogin();  
//		               session.setAttribute("login",loginBean);
//		            }
//		      }
//		      catch(Exception ee){
//		            loginBean=new blogin();  
//		            session.setAttribute("login",loginBean);
//		      }
//		      String logname=request.getParameter("logname").trim();
//		      boolean ok=loginBean.getSuccess();
//		      if(ok==true&&logname.equals(loginBean.getLogname())){
//		           backNews=logname+"已经登录了";
//		           loginBean.setBackNews(backNews);
//		      }
//		      else{
//		           String uri="jdbc:mysql://localhost:3306/sakila";
//		           boolean boo=(logname.length()>0);
//		           try{ 
//		                con=DriverManager.getConnection(uri,"root","");
//		                String condition="select * from customer where first_name = '"+logname;
//		                sql=con.createStatement();  
//		                if(boo){
//		                   ResultSet rs=sql.executeQuery(condition);
//		                   System.out.println("select:"+rs.toString());
//		                   boolean m=rs.next();
//		                   if(m==true){
//		                     backNews="登录成功";
//		                     loginBean.setBackNews(backNews);
//		                     loginBean.setSuccess(true);
//		                     loginBean.setLogname(logname);
//		                     RequestDispatcher dispatcher=
//		                   	      request.getRequestDispatcher("showfilm.jsp");
//		                   		      dispatcher.forward(request,response);
//		                   }
//		                   else{
//		                     backNews="您输入的用户名不存在";
//		                     loginBean.setBackNews(backNews); 
//		                     loginBean.setSuccess(false); 
//		                     loginBean.setLogname(logname);
//		                   }
//		                }
//		                else{
//		                  backNews="您输入的用户名不存在";
//		                  loginBean.setBackNews(backNews); 
//		                  loginBean.setSuccess(false); 
//		                  loginBean.setLogname(logname);
//		                }
//		                con.close();
//		           }
//		           catch(SQLException exp){
//		                backNews=""+exp;
//		                loginBean.setBackNews(backNews); 
//		                loginBean.setSuccess(false); 
//		           }
//		      }
////		      RequestDispatcher dispatcher=
////	      request.getRequestDispatcher("showfilm.jsp");
////		      dispatcher.forward(request,response);
//		   }
//
//}
public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public HandleLogin() {
        super();
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
		String uname=request.getParameter("logname");
		String returnUri=request.getParameter("return_uri");
		
		System.out.println("用户名===>"+uname);
		System.out.println("return_Uri===>"+returnUri);
		
		RequestDispatcher rd=null;
		if( uname==null ){
			request.setAttribute("msg", "用户名为空!");
			rd=request.getRequestDispatcher("login.jsp");
			rd.forward(request,response);
		}else{
			if( uname.equals("MARY") ){
				request.getSession().setAttribute("flag", "login_success");
				if(returnUri!=null){
					rd=request.getRequestDispatcher(returnUri);
					rd.forward(request, response);
				}else{
					rd=request.getRequestDispatcher("index.jsp");
					rd.forward(request,response);
				}
			}else{
				request.getSession().setAttribute("flag", "login_error");
				request.setAttribute("msg", "用户名错误!");
				rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
			}
		}
		
	}

}

