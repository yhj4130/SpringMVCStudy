/*================================================================
   #21. EmployeeInsertController.java
   - 사용자 정의 컨트롤러 클래스
   - 직원 데이터 수정 액션 수행 → employeelist.action 재요청 안내
   - DAO 객체에 대한 의존성 주입(DI)을 위한 준비
     → 인터페이스 형태의 자료형을 속성으로 구성
     → setter 메소드 준비
================================================================*/

package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// ※ Spring 의 『Controller』 인터페이스를 구현하는 방법을 통해
//    사용자 정의 컨트롤러 클래스를 구성한다.
public class EmployeeUpdateController implements Controller
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
		
		request.setCharacterEncoding("UTF-8");
		
		try
		{
			Employee employee = new Employee();
			
			employee.setEmployeeId(request.getParameter("employeeId"));
			employee.setName(request.getParameter("name"));
			employee.setSsn1(request.getParameter("ssn1"));
			employee.setSsn2(request.getParameter("ssn2"));
			employee.setBirthday(request.getParameter("birthday"));
			employee.setLunar(Integer.parseInt(request.getParameter("lunar")));
			employee.setTelephone(request.getParameter("telephone"));
			employee.setRegionId(request.getParameter("regionId"));
			employee.setDepartmentId(request.getParameter("departmentId"));
			employee.setPositionId(request.getParameter("positionId"));
			employee.setBasicPay(Integer.parseInt(request.getParameter("basicPay")));
			employee.setExtraPay(Integer.parseInt(request.getParameter("extraPay")));
			
			dao.modify(employee);
			
			mav.setViewName("redirect:employeelist.action");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		return mav;
	}
}