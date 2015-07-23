<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
	
	<table border=1px>
	<tr class=tags><th>Sıra</th><th>Kullanıcı Adı</th><th>Yetki</th><th>Sil</th><th>Güncelle</th></tr>
	
	<c:forEach items="${users}" var="user" varStatus="i">
		<c:choose>
				<c:when test="${i.count % 2 == 0 }">
					<c:out value="<tr class=black>" escapeXml="false"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="<tr class=white>" escapeXml="false"></c:out>
				</c:otherwise>
			</c:choose>
				<td>${i.count}</td>
				<td><c:out value="${user.userName}"></c:out></td>
				<td>
					<c:choose>
						<c:when test="${user.userAuthority == 1 }">
							Yetkili
						</c:when>
						<c:otherwise>
							Kullanıcı
						</c:otherwise>
					</c:choose>
				</td>
				<td><img src="img/delete.png" onClick="deleteUser('${user.userId}','${user.userName }');" /></td>
				<td><img src="img/update.png" onClick="updateUser('${user.userId}','${user.userName}','${user.userAuthority}');" /></td>
			</tr>
	</c:forEach>
	
</table>


