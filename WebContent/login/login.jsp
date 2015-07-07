<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<c:if test="${not empty redirect && redirect == 'delete'}">
		<% response.sendRedirect("http://localhost:8080/LoginCrudServletJSPExample/controller");%>
</c:if>

<jsp:include page="/fragments/header.jsp"/>
	<c:if test="${not empty userlogin}">
		<jsp:include page="panel.jsp"/>
    	<jsp:include page="userlist.jsp"/>
    	</c:if>
<jsp:include page="/fragments/footer.jsp"/>

