<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/My97Date/My97DatePicker/WdatePicker.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="/em?cmd=save&id=${employee.id}" method="POST">
		<input type="hidden" name="id" value="${employee.id}" ><br>
		<input type="text" name="name" value="${employee.name}" placeholder="名字"><br>
		<input type="text" name="password" value="${employee.password}" placeholder="密码"><br>
		<select name="dept">
			<option value="技术部"  <c:if test='${employee.dept=="技术部"}'>selected</c:if>
			>
				技术部
			</option>
			<option value="市场部" <c:if test='${employee.dept=="市场部"}'>selected</c:if>
			>
				市场部
			</option>
		</select><br>
		<input type="number" name="salary" value="${employee.salary}" placeholder="薪水"><br>
		<input class="Wdate" type="text" onClick="WdatePicker()" name="bornDate" placeholder="年/月/日" value='<fmt:formatDate value="${employee.bornDate}" pattern="yyyy-MM-dd"/>'> 
		<font color=red>&lt;- 点我弹出日期控件</font>
		<br><input type="submit" value="保存或者修改">
	</form>
</body>
</html>