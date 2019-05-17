<%@ page isErrorPage="true"%>
<html>
<body>
	<h2>Error</h2>
	<p>
		Status code :
		<%=request.getAttribute("javax.servlet.error.status_code")%></p>
	<p>
		Exception :
		<%=exception.getCause()%></p>
</body>
</html>