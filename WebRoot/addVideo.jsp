<%@ page language="java" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function uploadForm(){
	var frm = document.inputForm;
	frm.submit();
}
</script>
  </head>
  
<body>
 <form action="<%=basePath %>manageServlet/addVideo" name="inputForm" method="post" enctype="multipart/form-data">
	<table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="50px;">
    			<th width="70%" style="padding-top: 10px;" colspan="2">
    				<h2>添加</h2>
    			</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr height="30px;">
    			<td align="right">
					视频名称：
				</td>
    			<td>
					<input type="text" name="videoName">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					视频描述：
				</td>
    			<td>
    				<textarea rows="4" cols="50" name="videoDesc"></textarea>
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					视频文件：
				</td>
    			<td>
    				<input type="file" name="videoFile">
				</td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
					<input type="button" value="添加" onclick="uploadForm();"> &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重置">
				</td>
    		</tr>
    	</tbody>
    </table>
  </form>
</body>
  
</html>

