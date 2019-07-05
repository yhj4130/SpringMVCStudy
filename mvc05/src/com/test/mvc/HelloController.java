/*=========================================
   HelloController.java
   - 사용자 정의 컨트롤러 클래스
   - 『@Controller』 어노테이션 등록
   - 『@RequestMapping』 어노테이션 등록
=========================================*/

package com.test.mvc;

import org.springframework.stereotype.Controller;		//-- check !
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//import org.springframework.web.servlet.mvc.Controller;

// ※ Annotation 표기법으로 만든 컨트롤러 객체 지정
@Controller
public class HelloController // implements Controller
{
	/*
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
	*/
	
	// ※ 액션 처리를 위한 메소드는 사용자 지정.
	
	/*
	@RequestMapping("URL매핑주소")
	public String 메소드이름(매개변수)
	{
		// ※ 비즈니스 로직 처리 (모델 활용)
		// ...
		
		return "뷰 이름.jsp";
	}
	*/
	
	// 메소드의 매개변수에 필요로하는 객체를 지정하면
	// 이것을 스프링에 전달해 준다.
	@RequestMapping("/hello.action")
	public String hello(Model model)
	{
		// ※ 비즈니스 로직 처리 (모델 활용)
		// ...
		
		// 데이터 전달 명령
		model.addAttribute("hello", "Hello, SpringMVCAnnotation World!");
		
		return "/WEB-INF/views/Hello.jsp";
	}
	
}
