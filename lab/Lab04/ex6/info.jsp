<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<title>Title</title>
</head>
<style>
	table {
		border-collapse: collapse;
		text-align: center;
		width: 50%
	}

	td {
		font-weight: bold;
	}

	.purple {
		color: rgb(72, 59, 138);
	}

	.green {
		color: rgb(4, 116, 23);
	}

	.red {
		color: rgb(218, 47, 41);
	}
</style>
<body>
<div style="margin: 50px auto 0; width: 50%">
	<table border="1" style="width: 70%; margin: auto">
		<tr>
			<td class="purple">Họ tên</td>
			<td class="green">${param.name}</td>
		</tr>
		<tr>
			<td class="purple">Email</td>
			<td class="green">${param.email}</td>
		</tr>
		<tr>
			<td class="purple">Ngày sinh</td>
			<td class="green">${param.birthday}</td>
		</tr>
		<tr>
			<td class="purple">Giờ sinh</td>
			<td class="green">${param.birthtime}</td>
		</tr>
		<tr>
			<td class="purple">Giới tính</td>
			<td class="green">${param.gender}</td>
		</tr>
		<tr>
			<td class="purple">Quốc gia</td>
			<td class="green">${param.country}</td>
		</tr>
		<tr>
			<td class="purple" rowspan="${fn:length(requestScope.favorite_ide) + 1}">
				IDE Yêu thích
				<div class="red">Hãy đảm bảo rằng các IDE bạn chọn đã hiển thị đủ</div>
			</td>
		</tr>
		<c:forEach items="${requestScope.favorite_ide}" var="ide">
			<tr>
				<td style="border: none" class="green">
						${ide}
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td class="purple">Điểm TOEIC</td>
			<td class="green">${param.toeic}</td>
		</tr>
		<tr>
			<td class="purple">Giới thiệu bản thân</td>
			<td class="green">${param.message}</td>
		</tr>
	</table>
</div>
</body>
</html>
