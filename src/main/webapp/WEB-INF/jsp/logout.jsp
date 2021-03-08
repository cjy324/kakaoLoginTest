<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="part/head.jspf"%>

<c:if test="${nickname == null}">
	<div>
		<h1> 로그아웃 성공 </h1>
	</div>
	<button>
		<a href="login">
			로그인페이지로 이동
		</a>
	</button>
</c:if>

<%@ include file="part/foot.jspf"%>