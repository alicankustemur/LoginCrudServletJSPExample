<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<c:choose>
		<c:when test="${submit == 'update' }">
			<form action="" method="POST" id="frm" name="frm">
		</c:when>
		<c:otherwise>
			<form action="controller" method="POST" id="frm" name="frm">
		</c:otherwise>
	</c:choose>
		<table aling="left">
			<tr><th colspan="2">Kullanıcı İşlemleri</th></tr>
			<tr>
					<td colspan="2">
						<div class="message">
							<c:if test="${not empty loginmessage}">${loginmessage}</c:if>
						</div>
				    </td>
				</tr>
			<tr>
				<td>Kullanıcı Adı</td>
				<c:choose>
					<c:when test="${not empty redirect && redirect == 'update' }">
						<td><input type="text" name="user_name"  /></td>
					</c:when>
					<c:otherwise>
						<td><input type="text" name="user_name" value="${username}" /></td>
					</c:otherwise>
				</c:choose>
				
			</tr>
			<tr>
				<td>Şifre</td>
				<td><input type="password" name="user_pass" /></td>
			</tr>
	
			
			<tr>
				<td colspan="2" align="center">
				<select name="user_authority">
				
					<c:choose>
					<c:when test="${userauthority == 1 }">
						<option value="1" selected>Yetkili</option>
					</c:when>
					<c:otherwise>
						<option value="1">Yetkili</option>
					</c:otherwise>
				</c:choose>
					<c:choose>
					<c:when test="${userauthority == 2 }">
						<option value="2" selected>Kullanıcı</option>
					</c:when>
					<c:otherwise>
						<option value="2">Kullanıcı</option>
					</c:otherwise>
				</c:choose>
				
				</select>
				</td>
			</tr>
			
			
			<tr>
			
			<c:choose>
				<c:when test="${submit == 'update' }">
					<td colspan="2" align="right"><input type="submit" name="user_update" value="Güncelle" />
				</c:when>
				<c:otherwise>
					<td colspan="2" align="right"><input type="submit" name="user_add" value="Ekle" />
				</c:otherwise>
			</c:choose>
		
				<input type="submit" name="cancel" value="İptal"/></td>
				</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" name="logout" value="Çıkış Yap"></td>
			</tr>
			<c:if test="${not empty otherMessage}">
			<tr>
				<td colspan="2">
					<div class="other_message" align="center">${otherMessage}</div>
				</td>
			</tr>
			</c:if>
			
		</table>
	</form>
	
