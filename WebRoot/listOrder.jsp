<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.amaker.bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    
    <title>菜单列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%
	List<Order> list = (List<Order>)request.getAttribute("list");
 %>
  </head>
  
<body>
 <form action="<%=basePath %>servlet/addOrderServlet" name="inputForm" method="post" enctype="multipart/form-data">
	<table width="100%" height="100%" align="center" border="1" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="30px;">
    			<th width="100%" style="padding-top: 10px;" colspan="8">
    				<h2>列表</h2>
    			</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr height="20px;">
    			<th>
					菜名
				</th>
				<th>
					价格
				</th>
    			<th>
					描述
				</th>
    			<th>
					类型
				</th>
    			<th>
					图片
				</th>
    			<th>
					最后修改时间
				</th>
    			<th>
					添加时间
				</th>
    			<th>
					操作
				</th>
    		</tr>
    		<%
    			for(Order o : list){
    		 %>
    		<tr height="30px;">
    			<td align="center">
					<%=o.getName() %>
				</td>
				<td align="center">
					<%=o.getPrice() %> 元
				</td>
				<td align="center" width="35%">
					<%=o.getDescription().length()>40?o.getDescription().substring(0, 38)+"。。":o.getDescription() %>
				</td>
				<td align="center">
					<%if("recai".equals(o.getType()))
						out.print("热菜");
					   else if("liangcai".equals(o.getType()))
					    out.print("凉菜");
					   else
					    out.print("面食");
					 %>
				</td>
				<td align="center">
				    <img width="50px" height="40px" src="<%=basePath + o.getImgage_path() %>" />
				</td>
				<td align="center">&nbsp;
					<%=o.getUpdate_at()==null?"":o.getUpdate_at() %>
				</td>
				<td align="center">
					<%=o.getCreate_at() %>
				</td>				
				<td align="center">
					<a href="<%=basePath %>servlet/DeleteOrderServlet?id=<%=o.getId() %>">删除</a>|
					<a href="<%=basePath %>servlet/QueryOrderServlet?orderId=<%=o.getId() %>">修改</a>
				</td>
    		</tr>
    		<%} %>
    	</tbody>
    </table>
  </form>
</body>
  
</html>

