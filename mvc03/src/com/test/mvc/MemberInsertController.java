/*===============================
   MemberInsertController.java
   - 사용자 정의 컨트롤러 클래스
   - 회원 정보 추가 액션
   - DAO 객체에 대한 의존성 주입
     → setter 메소드 추가
===============================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// implements Controller 혹은 extends AbstractController
//-- 서블릿에서 HttpServlet 을 상속받은 서블릿 객체 역할
public class MemberInsertController implements Controller
{
	// 인터페이스 자료형을 취하는 속성 구성
	private IMemberDAO dao;
	
	// setter 메소드 구성
	public void setDao(IMemberDAO dao)
	{
		this.dao = dao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// 액션 코드
		
		ModelAndView mav = new ModelAndView();
		
		request.setCharacterEncoding("UTF-8");
		
		// 이전 페이지로부터 넘어온 데이터 수신
		String name = request.getParameter("name");
		String tel = request.getParameter("telephone");
		
		try
		{
			MemberDTO member = new MemberDTO();
			member.setName(name);
			member.setTelephone(tel);
			
			dao.add(member);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		mav.setViewName("redirect:memberlist.action");
		
		return mav;
	}
}
