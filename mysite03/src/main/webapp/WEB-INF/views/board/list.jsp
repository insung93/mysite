<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" name="searchform" action="${pageContext.servletContext.contextPath }/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="hidden" name="a" value="search"/>
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th style="text-align: left">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="contents" varStatus="status">
					<tr>
						<td>[${pageInfo.totalCount-status.index-(pageInfo.currentPage-1)*pageInfo.displayRow}]</td>
						<td style="text-align: left; padding-left:${contents.depth * 20 }px">
							<c:if test="${contents.depth != 0 }"><img src="${pageContext.servletContext.contextPath }/assets/images/reply.png"></c:if>
							<a href="${pageContext.servletContext.contextPath }/board?a=view&no=${contents.no }">${contents.title }</a>
						</td>
						<td>${contents.userName }</td>
						<td>${contents.hit }</td>
						<td>${contents.regDate }</td>
						<td>
							<c:choose>
								<c:when test="${authUser.no == contents.userNo }">
									<a href="${pageContext.servletContext.contextPath }/board?a=delete&no=${contents.no }&depth=${contents.depth }&groupNo=${contents.groupNo}" class="del" style='background-image:url("${pageContext.servletContext.contextPath }/assets/images/recycle.png")'>삭제</a>
								</c:when>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
				</table>
				
				
				
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><c:if test="${pageInfo.currentPage != 1 }"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${pageInfo.prevPageNo}">◀</a></c:if></li>
							<c:forEach var="it" begin="${pageInfo.leftPage }" end="${pageInfo.rightPage}" step="1">
								<li <c:if test="${it==pageInfo.currentPage}">class="selected"</c:if>><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${it}">${it}</a></li>
							</c:forEach>
						<li><c:if test="${pageInfo.currentPage != pageInfo.lastPageNo }"><a href="${pageContext.servletContext.contextPath }/board?a=list&page=${pageInfo.nextPageNo}">▶</a></c:if></li>
					</ul>
				</div>
				<!-- pager 추가 -->

				<div class="bottom">
					<c:choose>
						<c:when test="${not empty authUser }">
							<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>