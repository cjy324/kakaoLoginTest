<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="part/head.jspf"%>

<!-- 로그인 상태가 아닌 경우 노출 -->
<c:if test="${nickname == null}">
	<div>
		<a href="https://kauth.kakao.com/oauth/authorize
				?client_id=ba1b53f1a33228aace8ccb4b6f07e12b
				&redirect_uri=http://localhost:8025/login
				&response_type=code">
			<img src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_large_wide.png" alt="" />
		</a>
	</div>
</c:if>

<!-- 로그인 상태인 경우 노출 -->
<c:if test="${nickname != null}">
	<div>카카오 닉네임 : ${nickname}</div>
	<div>카카오 이메일 : ${email}</div>
	<!-- 아래 링크로 이동해 동의하지 않은 항목에 대해 추가 동의를 할 수 있도록 했습니다. -->
	<!-- 기본적인 개념은 첫 로그인연결 시 제공항목을 선택하는 것과 같다고 생각하시면 됩니다. -->
	<!-- scope에 추가 동의가 필요한 항목들의 ID를 (,)구분해서 추가하실 수 있습니다.-->
	<!-- ex) &scope=account_email, gender, age_range-->
	<button>
		<a href="https://kauth.kakao.com/oauth/authorize
				?client_id=ba1b53f1a33228aace8ccb4b6f07e12b
				&redirect_uri=http://localhost:8025/login
				&response_type=code
				&scope=account_email">
			이메일 정보제공 동의하러 가기
		</a>
	</button>
	<div>카카오 프로필 사진(원본) :</div>
	<img src="${profile_image}" />
	<div>카카오 프로필 사진(썸네일) :</div>
	<img src="${thumbnail_image}" />
	<hr>
	<button>
		<a href="https://kauth.kakao.com/oauth/logout
				?client_id=ba1b53f1a33228aace8ccb4b6f07e12b
				&logout_redirect_uri=http://localhost:8025/logout">
			로그아웃
		</a>
	</button>
	<button>
		<a href="unlink">
			카카오 계정 연결 해제
		</a>
	</button>
</c:if>

<%@ include file="part/foot.jspf"%>