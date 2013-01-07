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
	List<OrderLog> list = (List<OrderLog>)request.getAttribute("orderLogList");
 %>
  </head>
  
<body>
 <form action="<%=basePath %>manageServlet/addOrder" name="inputForm" method="post" enctype="multipart/form-data">
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
					桌号
				</th>
    			<th>
					点菜人登陆账号
				</th>
    			<th>
					时间
				</th>
    			<th>
					当前状态
				</th>
    			<th>
					操作
				</th>
    		</tr>
    		<%
    			for(OrderLog o : list){
    		 %>
    		<tr height="30px;">
    			<td align="center">
					<%=o.getOrderName() %>
				</td>
				<td align="center">
					<%=o.getDeskId()%> 桌
				</td>
				<td align="center">
					<%=o.getLoginId() %>
				</td>
				<td align="center">
					<%=o.getCreate_at()%>
				</td>
				<td align="center">
					<%String res = "";if("0".equals(o.getOpType()))res="新点的菜";else if("1".equals(o.getOpType()))res="已送往厨房";else res="错误数据";%>
					<font color="red"><%=res %></font>
				</td>
				<td align="center">
					<a href="<%=basePath %>orderLogManage/updateLogList?log=<%=o.getId() %>&op=1">送往厨房</a>|
					<a href="<%=basePath %>orderLogManage/updateLogList?log=<%=o.getId() %>&op=2">完成交易</a>|
					<a href="<%=basePath %>orderLogManage/updateLogList?log=<%=o.getId() %>&op=3">删除</a>
				</td>
    		</tr>
    		<%} %>
    	</tbody>
    </table>
  </form>
</body>
  
</html>

