<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%
    	pageContext.setAttribute("newline", "\n");
		pageContext.setAttribute("left", "<");
    %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="guestbook">
				<form:form action="${pageContext.request.contextPath }/guestbook/add" method="post">
					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name"></td>
								
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="message" id="content"></textarea></td>

						</tr>
						<tr>
							<spring:hasBindErrors name="guestbookVo">
								<c:if test="${errors.hasFieldErrors('name') }">
								<spring:message code="${errors.getFieldError('name').codes[0] }" 
											text="${errors.getFieldError('name').defaultMessage }" />
								</c:if>
							</spring:hasBindErrors>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>

					</table>
				</form:form>
				<ul>
				
				<c:set var="count" value="${fn:length(list) }"/>
				<c:forEach items="${list }" var="content" varStatus="status">
					<li>
						<table>
							<tr>
								<td align="center" width=10>[${count-status.index }]</td>
								<td width=70>${fn:replace(content.name,left,'&lt;')}</td>
								<td>${content.regDate }</td>
								<td><a href="${pageContext.request.contextPath }/guestbook/delete/${content.no }">삭제</a></td>
								
							</tr>
							<tr>
								<td colspan=4>${fn:replace(fn:replace(content.message ,left,'&lt;') ,newline,'</br>')}<br></td>
								
							</tr>
						</table> <br>
					</li>
				</c:forEach>

				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>