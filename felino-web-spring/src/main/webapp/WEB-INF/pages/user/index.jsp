<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Panel</title>
</head>
<body>
User Panel
<sec:authorize ifAnyGranted="ROLE_USER">
	Welcome Back,  <c:out value="${pageContext.request.userPrincipal.name} "/>
<a href="<c:url value="/logout" />" class=	"btn btn-success" >Sign out</a>
</sec:authorize>
</body>
</html>