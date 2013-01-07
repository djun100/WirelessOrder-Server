<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    
    <title>点餐后台管理</title>
	<meta http-equiv="pragma" content="no-cache" >
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <table width="80%" height="80%" align="center" border="1" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="30px;">
    			<th width="70%" style="padding-top: 10px;">
    				<h3>管理</h3>
    				<!-- <h2>LIXA手机点餐系统后台管理</h2>  -->    				
    				<a href="<%=basePath %>servlet/OrderLogUpdateServlet" target="mainframe">点菜管理</a>|
    				<a href="<%=basePath %>servlet/ListOrderServlet" target="mainframe">菜单查询</a>|
    				<a href="<%=basePath %>" target="mainframe">视频查询</a>
    			</th>
    			<th width="30%">
					<a href="addOrder.jsp" target="mainframe">添加新菜</a>|
					<a href="addVideo.jsp" target="mainframe">添加视频</a>
				</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td colspan="2">&nbsp;
					<iframe height="100%" width="98%" name="mainframe"></iframe>
				</td>
    		</tr>
    	</tbody>
    </table>
  </body>
  
</html>

