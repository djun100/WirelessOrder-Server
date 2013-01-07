<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.amaker.bean.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    
    <title>添加新菜</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<%
	Video video = (Video)request.getAttribute("video");
 %>
 <script type="text/javascript">
 function updateForm(){
	var frm = document.inputForm;
	frm.submit();
}

 </script>
  </head>
  
<body>
 <form action="<%=basePath %>manageServlet/updateVideo" name="inputForm" method="post" enctype="multipart/form-data">
 	<input type="hidden" name="videoId" value="<%=video.getId() %>">
 	<input type="hidden" name="videoPath" value="<%=video.getPath() %>">
	<table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="50px;">
    			<th width="70%" style="padding-top: 10px;" colspan="2">
    				<h2>修改</h2>
    			</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr height="30px;">
    			<td align="right">
					视频名称：
				</td>
    			<td>
					<input type="text" name="videoName" value="<%=video.getName() %>">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					视频描述：
				</td>
    			<td>
    				<textarea rows="4" cols="50" name="videoDesc"><%=video.getDescription() %></textarea>
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					目前视频：
				</td>
    			<td>
    				<object width="180" height="170" classid="clsid:02BF25D5-8C17-4B23-BC80-D3488ABDDC6B" codebase=" http://www.apple.com/qtactivex/qtplugin.cab ">
						<param name="src" value="<%=basePath + video.getPath() %>" />
						<param name="controller" value="true" />
						<param name="type" value="video/quicktime" />
						<param name="target" value="myself" />
						<param name="autostart" value="false"/>
						<param name="bgcolor" value="black" />
						<param name="pluginspage" value="http://www.apple.com/quicktime/download/index.html " />
					</object>
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					新视频：
				</td>
    			<td>
    				<input type="file" name="videoPath">
				</td>
    		</tr>    		
    		<tr>
    			<td colspan="2" align="center">
					<input type="button" value="修改" onclick="updateForm();"> &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置">
				</td>
    		</tr>
    	</tbody>
    </table>
  </form>
</body>
  
</html>

