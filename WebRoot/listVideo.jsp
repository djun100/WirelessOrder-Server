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
    
    <title>视频列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%
	List<Video> list = (List<Video>)request.getAttribute("list");
 %>
  </head>
  
<body>
 <form action="<%=basePath %>manageServlet/addOrder" name="inputForm" method="post" enctype="multipart/form-data">
	<table width="100%" height="100%" align="center" border="1" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="30px;">
    			<th width="100%" style="padding-top: 10px;" colspan="7">
    				<h2>列表</h2>
    			</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr height="20px;">
    			<th>
					视频名称
				</th>
    			<th>
					视频描述
				</th>
    			<th>
					视频预览
				</th>
    			<th>
					操作
				</th>
    		</tr>
    		<%
    			for(Video o : list){
    		 %>
    		<tr height="30px;">
    			<td align="center">
					<%=o.getName() %>
				</td>
				<td align="center">
					<%=o.getDescription().length()>40?o.getDescription().substring(0, 38)+"。。":o.getDescription() %>
				</td>				
				<td align="center">
					<object width="180" height="170" classid="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" codebase=" http://www.apple.com/qtactivex/qtplugin.cab ">
						<param name="src" value="<%=basePath + o.getPath() %>" />
						<param name="controller" value="true" />
						<param name="type" value="video/quicktime" />
						<param name="target" value="myself" />
						<param name="autostart" value="false"/>
						<param name="bgcolor" value="black" />
						<param name="pluginspage" value="http://www.apple.com/quicktime/download/index.html " />
					</object>
				</td>
				<td>
					<a href="<%=basePath %>manageServlet/overdueVideo?id=<%=o.getId() %>">设置过期</a>|
					<a href="<%=basePath %>/manageServlet/queryVideo?videoId=<%=o.getId() %>">修改</a>
				</td>
    		</tr>
    		<%} %>
    	</tbody>
    </table>
  </form>
</body>
  
</html>

