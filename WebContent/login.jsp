<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login page</title>
  </head>
  
  <body>
    This is my index page. <br>
    
    <form method="post" id="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check">
    	<table>
    		<tr>
    			<td>用户名</td>
    			<td>
    			<input name="j_username" type="text" value="yemao" />
    			</td>
    		</tr>
    		<tr>
    			<td>密码</td>
    			<td>
    			<input name="j_password" type="text" value="123456" />
    			</td>
    		</tr>
    		<tr>
    			<td>
    			<input type="submit" value="登录" />
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
