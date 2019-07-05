/*==================
   IMemberDAO.java
   - 인터페이스
==================*/

package com.test.mybatis;

import java.util.ArrayList;

public interface IMemberDAO
{
	public int add(MemberDTO dto);
	public int count();
	public ArrayList<MemberDTO> list();
	public int remove(MemberDTO dto);
}
