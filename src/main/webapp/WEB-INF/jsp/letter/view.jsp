<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시판</title>
<script type="text/javascript">
	function confirmDelete() {
		if (confirm("삭제하시겠습니까?"))
			return true;
		else
			return false;
	}
</script>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>편지 보기</h2>
	<p>
<<<<<<< HEAD
		<c:choose>
			<c:when test="${param.mode == 'SENT' }">
				<a href="./app/letter/listSent">목록</a>
			</c:when>
			<c:otherwise>
				<a href="./app/letter/listReceived">목록</a>
			</c:otherwise>
		</c:choose>
		<a href="./app/letter/delete?letterId=${letter.letterId }&mode=${param.mode}"
			onclick="return confirmDelete();">삭제</a>
=======
		<a href="./app/letter/list">글목록</a>
		<c:if test="${letter.receiverId == sessionScope.MEMBER.memberId || letter.senderId == sessionScope.MEMBER.memberId }">
			<a href="./app/letter/updateForm?letterId=${letter.letterId }">글수정</a>
			<a href="./app/letter/delete?letterId=${letter.letterId }"
				onclick="return confirmDelete();">글삭제</a>
		</c:if>
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	</p>
	<hr />
	<p>
		<span style="font-weight: bold;">${letter.title }</span>
	</p>
	<p>
<<<<<<< HEAD
		보낸이 : <span>${letter.senderId }</span>:<span>${letter.senderName }</span><br />
		받는이 : <span>${letter.receiverId }</span>:<span>${letter.receiverName }</span><br />
		보낸시간 : <span>${letter.cdate }</span>
=======
		<span>${letter.cdate }</span> | <span>${letter.receiverName }</span> | <span>${letter.senderName }</span>
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
	</p>
	<hr />
	<p>${letter.contentHtml }</p>
	<hr />
<<<<<<< HEAD
=======
	<a href="./app/letter/delete?letterId=${letter.letterId}">삭제하기</a>
>>>>>>> b8a8eb8ebbb2fe9f2a569125829d9c8570b98834
</body>
</html>