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
	Order order = (Order)request.getAttribute("order");
 %>
 <script type="text/javascript">
 function updateForm(){
	var frm = document.inputForm;
	frm.submit();
}

 function init(){
 	var v = '<%=order.getType()%>';
 	var type = document.getElementsByName("orderType");
 	for(var i = 0; i < type.length; i = i + 1){
 		if(type[i].value == v){
 			type[i].checked = "checked";
 			return ;
 		}
 	}
 }
 </script>
  </head>
  
<body onload="init();">
 <form action="<%=basePath %>/servlet/UpdateServlet" name="inputForm" method="post" enctype="multipart/form-data">
 	<input type="hidden" name="orderId" value="<%=order.getId() %>">
 	<input type="hidden" name="orderImage" value="<%=order.getImgage_path() %>">
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
					菜名：
				</td>
    			<td>
					<input type="text" name="orderName" value="<%=order.getName() %>">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					价格：
				</td>
    			<td>
					<input type="text" name="orderPrice" value="<%=order.getPrice() %>">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					描述：
				</td>
    			<td>
    				<textarea rows="4" cols="50" name="orderDesc"><%=order.getDescription() %></textarea>
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					目前图片：
				</td>
    			<td>
    				<img src="<%=basePath + order.getImgage_path() %>" width="50px" height="40px">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					新图片图片：
				</td>
    			<td>
    				<input type="file" name="orderImage">
				</td>
    		</tr>
    		<tr height="30px;">
    			<td align="right">
					类型：
				</td>
    			<td>
    				<input type="radio" name="orderType" value="recai">热菜
    				<input type="radio" name="orderType" value="liangcai">凉菜菜
    				<input type="radio" name="orderType" value="mianshi">面食
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

