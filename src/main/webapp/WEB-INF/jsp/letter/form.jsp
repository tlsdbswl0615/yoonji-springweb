<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>편지 쓰기</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>편지 쓰기</h2>
<<<<<<< HEAD:src/main/webapp/WEB-INF/jsp/letter/form.jsp
	<form action="./app/letter/add" method="post">
		<p>
			받는이 : <input type="text" name="receiverId" value="${param.receiverId }"
				readonly /> | <input type="text" name="receiverName"
				value="${param.receiverName }" readonly />
		</p>
=======
	<p>
		<a href="./app/member/memberInfo">회원 정보</a>
	</p>
	<form action="./app/letter/add?receiverId=${param.receiverId}" method="post">
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834:src/main/webapp/WEB-INF/jsp/letter/addForm.jsp
		<p>제목 :</p>
		<p>
			<input type="text" name="title" maxlength="100" style="width: 100%;" required>
		</p>
		<p>내용 :</p>
		<p>
			<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
		</p>
		<p>
			<button type="submit">등록</button>
		</p>
	</form>
</body>
</html>