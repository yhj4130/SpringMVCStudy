/*=======================================
   #11. EmployeeDAO.java
   - 데이터베이스 액션 처리 클래스.
   - 지역 정보 입출력 / 수정 / 삭제 액션
   - Connection 객체에 대한 의존성 주입
     → setter 메소드 정의
=======================================*/

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class RegionDAO implements IRegionDAO
{
	// 인터페이스 자료형을 속성으로 구성
	private DataSource dataSource;
	
	// setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	// 인터페이스 메소드 재정의 ------------------------------------------------------------------

	@Override
	public ArrayList<Region> list() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		String sql = "SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW ORDER BY REGIONID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Region region = new Region();
			
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public int add(Region region) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO REGION(REGIONID, REGIONNAME) VALUES(REGIONSEQ.NEXTVAL, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, region.getRegionName());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int remove(String regionId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM REGION WHERE REGIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(regionId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int modify(Region region) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, region.getRegionName());
		pstmt.setInt(2, Integer.parseInt(region.getRegionId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}
	
	@Override
	public Region searchId(String regionId) throws SQLException
	{
		Region result = new Region();
		
		String sql = "SELECT REGIONID, REGIONNAME FROM REGION WHERE REGIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(regionId));
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			result.setRegionId(rs.getString("REGIONID"));
			result.setRegionName(rs.getString("REGIONNAME"));
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
}
