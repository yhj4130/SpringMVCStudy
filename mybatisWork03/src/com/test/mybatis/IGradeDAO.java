/*==================
   IMemberDAO.java
   - 인터페이스
==================*/

package com.test.mybatis;

import java.util.ArrayList;

public interface IGradeDAO
{
	public int add(GradeDTO dto);
	public int count();
	public ArrayList<GradeDTO> list();
	public int remove(GradeDTO dto);
	public int update(GradeDTO dto);
	public GradeDTO search(GradeDTO dto);
	public int id(GradeDTO dto);
}