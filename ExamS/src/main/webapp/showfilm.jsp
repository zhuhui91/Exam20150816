<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.hand.bean.showFilm" %>
<jsp:useBean id="show" type="com.hand.bean.showFilm" scope="application" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示电影列表</title>
</head>
<body>
<div align="center">
显示电影列表
<BR>当前显示的内容是：
  <table border=5 bordercolorlight=#3399ff>
  <tr>
    <th width=50 height=50>film_id</th>
    <th width=50 height=50>title</th>
    <th width=200 height=50>description</th>
    <th width=200 height=50>language</th>
  </tr>
  <jsp:getProperty name="show" property="presentPageResult"/>
  </table>
 <BR>每页最多显示<jsp:getProperty name="show" property="pageSize"/>条信息
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty name="show" property="showPage"/>
   </Font>页,共有
   <Font color=blue><jsp:getProperty name="show" property="pageAllCount"/>
   </Font>页。
<BR>单击“上一页”或“下一页”按纽查看信息

</div>

</body>
</html>