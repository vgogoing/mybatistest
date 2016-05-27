<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function setCurrentPage(num){
		var form = document.myform;
		form.currentPage.value = num;
		form.submit();
	}
</script>
</head>
<body>
	<form action="/em?cmd=select" method="post" name="myform">
		<input type="hidden" name="currentPage">
		<input type="text" name="name" value="${emQuery.name}" placeholder="名字">
		<input type="number" name="minSalary" value="${emQuery.minSalary}" placeholder="最低薪水">
		<input type="number" name="maxSalary" value="${emQuery.maxSalary}" placeholder="最高薪水">
		<input type="submit" value="查询">
	</form>
	<a href="/em?cmd=editpage">添加</a>
	<table border="1" cellpadding="0" cellspacing="0" >
		<tr>
			<td>id</td>
			<td>名字</td>
			<td>密码</td>
			<td>部门</td>
			<td>薪水</td>
			<td>生日</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${pageList.data}" var="em">
		<tr>
			<td>${em.id}</td>
			<td>${em.name}</td>
			<td>${em.password}</td>
			<td>${em.dept}</td>
			<td>${em.salary}</td>
			<td><fmt:formatDate value="${em.bornDate}" pattern="yyyy-MM-dd"/></td>
			<td>
				<a href="/em?cmd=editpage&id=${em.id}">修改</a>
				<a href="/em?cmd=delete&id=${em.id}">删除</a>
			</td>
		</tr>
		</c:forEach>
	</table>
		<a href="javascript:setCurrentPage(1)">首页|</a>
		<a href="javascript:setCurrentPage(${pageList.prevPage})">上页|</a>
		<a href="javascript:setCurrentPage(${pageList.nextPage})">下页|</a>
		<a href="javascript:setCurrentPage(${pageList.lastPage})">尾页|</a>
		当前${pageList.currentPage}/${pageList.totalPage}页 |共${pageList.totalCount}条
</body>
</html>