<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- header.jsp  -->
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div class="container p-5">

	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.postNumber}</td>
					<td>${board.title}</td>
					<td>${board.content}</td>
					<td>${board.author}</td>
					<td>${board.createdAt}</td>
					<td>
						<div class="d-flex">
							<form action="/board/${board.id}/delete" method="post")>
								<button class="btn btn-danger">삭제</button>
							</form>
							<form action="/board/${board.id}/updateForm" method="get">
								<button class="btn btn-warning">수정</button>
							</form>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- Pagination -->
		<div class="d-flex justify-content-center">
			<ul class="pagination">
			
				<!-- Previous Page Link -->
				<li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>">
					<a class="page-link" href="?page=${currentPage - 1}&size=${size}">&lt;</a>
				</li>
				
				<!-- Page Numbers -->
				<c:forEach begin="1" end="${totalPages}" var="page">
					<li class="page-item <c:if test='${currentPage == page}'>active</c:if>">
						<a class="page-link" href="?page=${page}&size=${size}">${page}</a>
					</li>
				</c:forEach>
				
				<!-- Next Page Link -->
				<li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>">
					<a class="page-link" href="?page=${currentPage + 1}&size=${size}">&gt;</a>
				</li>
			</ul>
		</div>

</div>

<!-- footer.jsp -->
<%@ include file="/WEB-INF/view/layout/footer.jsp"%>