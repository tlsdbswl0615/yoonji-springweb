<!doctype html>
<!-- 
p.277 [리스트 11.9] 회원가입 화면
 -->
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글 수정</title>
</head>
<body>
	<h2>글 수정</h2>
	<form action="./app/article/revise?articleId=${article.articleId}" method="post">
		<p>
			제목:<br> <input type="text" name="title" value="${article.title }">
		</p>
		<p>
			내용:<br> <textarea name="content" style="width: 100%; height: 200px;" required>${article.content}</textarea>
		</p>
		<button type="submit">등록</button>
	</form>
</body>
</html>