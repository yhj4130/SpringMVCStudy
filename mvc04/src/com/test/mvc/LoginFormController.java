/*======================================================
   #28. LoginFormController.java
   - 사용자 정의 컨트롤러 클래스
   - 사용자의 최초 요청 페이지
   - 단순히 로그인 폼이 구성된 페이지를 뷰(View)로 제시
======================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성한다.
public class LoginFormController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/WEB-INF/views/LoginForm.jsp");
		
		return mav;
	}
}
