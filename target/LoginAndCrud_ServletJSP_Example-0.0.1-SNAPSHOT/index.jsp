<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="fragments/header.jsp"/>
	<c:choose>
		<c:when  test="${not empty userlogin}">
			<% response.sendRedirect("http://localhost:8080/LoginCrudServletJSPExample/controller"); %>
		</c:when>
		<c:otherwise>
			<form action="login" method="POST">
			<table>
				<tr><th colspan="2">Giriş Paneli</th></tr>
				<tr>
					<td colspan="2">
						<div class="message">
							<c:if test="${not empty message}">${message}</c:if>
						</div>
				    </td>
				</tr>
				<tr>
					<td>Kullanıcı Adı</td>
					<td><input type="text" name="userName"/></td>
				</tr>
				<tr>
					<td>Şifre</td>
					<td><input type="password" name="userPass"/></td>
				</tr>
				<tr>
					<td colspan="2" align="right" ><input type="submit" name="signin" value="Giriş Yap" /></td>
				</tr>
			</table>
		</form>
		</c:otherwise>
	</c:choose>
<jsp:include page="fragments/footer.jsp"/>