/*==========================================
   #29. LoginController.java
   - 사용자 정의 컨트롤러 클래스
   - 로그인 액션 처리 전용 클래스
   - DAO 에 대한 의존성 주입(DI) 준비
     → 인터페이스 자료형을 가진 속성 구성
     → setter 메소드 준비
==========================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성한다.
public class LoginController implements Controller
{
	private IEmployeeDAO dao;
	
	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		// 데이터 수신(→ LoginForm.jsp 페이지로부터 넘어온 데이터)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String admin = request.getParameter("admin");
		
		String name = null;
		
		try
		{
			// 로그인 처리 → 대상에 따른 로그인 처리 방식 구분/분기
			if(admin==null)
			{
				name = dao.login(id, pw);		//-- 일반 사원 로그인
			}
			else
			{
				name = dao.loginAdmin(id, pw);	//-- 관리자 로그인
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		// 로그인 수행에 따른 성공 여부 확인 및 구분/분기
		if(name=="")
		{
			// 로그인 실패 → 로그인 폼 다시 요청할 수 있도록 안내
			mav.setViewName("redirect: loginform.action");
		}
		else
		{
			// 로그인 성공
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			
			// 관리자인지 여부 확인
			if(admin==null)		//-- 일반 사원으로 로그인 성공
			{
				mav.setViewName("redirect: emplist.action");
			}
			else				//-- 관리자로 로그인 성공
			{
				session.setAttribute("admin", "");
				mav.setViewName("redirect: employeelist.action");
			}
		}
		
		return mav;
	}
}
