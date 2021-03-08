package com.cjy.kakaoLoginTest.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjy.kakaoLoginTest.service.KakaoService;

@Controller
public class KakaoController {

	@Autowired
	private KakaoService kakaoService;

	// 카카오 로그인 테스트
	@RequestMapping("/login")
	public String login(HttpSession session, @RequestParam(value = "code", required = false) String code)
			throws Exception {
		// 1.인증코드 받기
		System.out.println("-------결과1-------");
		System.out.println("### 인증코드 ### : " + code);

		// 2.인증된 코드로 사용자토큰 받기
		System.out.println("-------결과2-------");
		// responseCode: 200 => 성공
		String access_Token = kakaoService.getAccessToken(code);
		System.out.println("### 사용자 토큰 ###: " + access_Token);

		// 3.사용자토큰으로 로그인한 유저의 정보 받아오기
		System.out.println("-------결과3-------");
		// responseCode: 200 => 성공
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		System.out.println("### 사용자 토큰 ### : " + access_Token);
		System.out.println("### email ### : " + userInfo.get("email"));
		System.out.println("### 카카오nickname ### : " + userInfo.get("nickname"));
		System.out.println("### 카카오프로필 이미지(원본) ### : " + userInfo.get("profile_image"));
		System.out.println("### 카카오프로필 이미지(썸네일) ### : " + userInfo.get("thumbnail_image"));
		
		
		// 4.session 값 셋팅
		session.setAttribute("access_Token", access_Token);
		session.setAttribute("nickname", userInfo.get("nickname"));
		session.setAttribute("email", userInfo.get("email"));
		session.setAttribute("profile_image", userInfo.get("profile_image"));
		session.setAttribute("thumbnail_image", userInfo.get("thumbnail_image"));

		return "login";
	}

	// 앱에서 로그아웃(정보제공 여부 유지)
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		kakaoService.kakaoLogout((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("nickname");
		session.removeAttribute("email");
		session.removeAttribute("profile_image");
		session.removeAttribute("thumbnail_image");
		
		return "logout";
	}
	
	// 앱과 연결된 카카오계정 연결해제(정보제공 여부도 전부 초기화)
	@RequestMapping("/unlink")
	public String unlink(HttpSession session) {
		kakaoService.kakaoUnlink((String) session.getAttribute("access_Token"));
		session.removeAttribute("access_Token");
		session.removeAttribute("nickname");
		session.removeAttribute("email");
		session.removeAttribute("profile_image");
		session.removeAttribute("thumbnail_image");
		
		return "logout";
	}

}
