/*===============================
   RegionInsertFormController.java
   - 사용자 정의 컨트롤러 클래스
===============================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성한다.
public class PositionInsertFormController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("name")==null)			//-- 로그인이 되어있지 않은 상황
		{
			mav.setViewName("redirect: loginform.action");
			return mav;
		}
		else if(session.getAttribute("admin")==null)	//-- 관리자가 아닌 일반사원
		{
			mav.setViewName("redirect: logout.action");
			return mav;
		}
		
		mav.setViewName("/WEB-INF/views/PositionInsertForm.jsp");
	
		return mav;
	}
}
