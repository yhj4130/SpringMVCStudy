/*===============================
   SampleController.java
   - 사용자 정의 컨트롤러 클래스
===============================*/

package com.test.mvc;


import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MemberRecordController
{
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
	
	// 데이터베이스의 데이터를 읽어오는 과정
	@RequestMapping("/memberlist.action")
	public String memberList(Model model)
	{
		String result = "";
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		MemberDAO dao = new MemberDAO();
		
		try
		{
			dao.connect();
			list = dao.lists();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally {
			try
			{
				dao.close();
			} catch (Exception e2)
			{
				System.out.println(e2.toString());
			}
		}
		
		model.addAttribute("list", list);
		
		result = "/WEB-INF/views/MemberList.jsp";
		
		return result;
	}
	
	// 데이터 입력(회원 등록) 폼 요청 액션 메소드 정의
	@RequestMapping("/memberinsertform.action")
	public String memberInsertForm()
	{
		String result = "";
		
		result = "/WEB-INF/views/MemberInsertForm.jsp";
		
		return result;
	}
	
	// 데이터 입력(회원 등록) 액션 메소드 정의
	@RequestMapping("/memberinsert.action")
	public String memberInsert(MemberDTO dto) throws SQLException, ClassNotFoundException
	{
		String result = "";
		
		MemberDAO dao = new MemberDAO();
		
		dao.connect();
		dao.insertQuery(dto);
		dao.close();
		
		result = "/WEB-INF/views/MemberInsert.jsp";
		
		return result;
	}
}
