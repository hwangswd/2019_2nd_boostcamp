package com.nts.reservation.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 로그인 여부에 따라 요청 리다이렉트 페이지를 이동
 * @author 황성욱
 * @since 2019-08-18
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		saveDestinationURI(request, response);// 자동 페이지 이동을 위한 URI 저장 
		//쿠키 로그인 검사 
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("loginedEmail") && cookie.getValue() != null) {
				return true;
			}
		}
		response.sendRedirect("/bookinglogin");
		return false;
	}

	/*
	 * Destination URI 저장 메소드 
	 */
	private void saveDestinationURI(HttpServletRequest request, HttpServletResponse response) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();

		if (query == null || query.equals("")) {
			query = "";
		} else {
			query = "?" + query;
		}
		// 자동 페이지 이동 처리를 위해 cookie 값에 저장 
		Cookie cookie = new Cookie("destination", uri + query);
		cookie.setMaxAge(30);//s store
		cookie.setPath("/bookinglogin");
		response.addCookie(cookie);
	}

}

//@Override
//public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//	throws Exception {
//	// email 쿠키가 있으면 통과
//	if (findCookie(request, "email")) {
//		return true;
//	}
//	// 없을 경우 원래 경로를 parameter로 붙여 bookinglogin로 보냄
//	String url = getLoginUrl("bookinglogin", request);
//	response.sendRedirect(url);
//	return false;
//}
//
///**
// * bookinglogin 경로에 원래 경로를 parameter로 붙이는 함수
// */
//private String getLoginUrl(String path, HttpServletRequest request) {
//	String url = path;
//	String servletPath = request.getServletPath();
//	String queryString = request.getQueryString();
//
//	url += "?url=" + servletPath;
//	if (queryString != null && queryString != "") {
//		url += "?" + queryString;
//	}
//	return url;
//}
//
///**
// * 해당하는 쿠키가 있는지 확인하는 함수
// */
//private boolean findCookie(HttpServletRequest request, String cookieName) {
//	Cookie[] cookies = request.getCookies();
//	if (cookies == null) {
//		return false;
//	}
//	for (Cookie cookie : cookies) {
//		if ("email".equals(cookie.getName())) {
//			return true;
//		}
//	}
//	return false;
//}

