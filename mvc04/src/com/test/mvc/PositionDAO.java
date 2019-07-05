/*=======================================
   #12. EmployeeDAO.java
   - 데이터베이스 액션 처리 클래스.
   - 직위 정보 입출력 / 수정 / 삭제 액션
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

public class PositionDAO implements IPositionDAO
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
	public ArrayList<Position> list() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY, DELCHECK FROM POSITIONVIEW ORDER BY POSITIONID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Position position = new Position();
			
			position.setPositionId(rs.getString("POSITIONID"));
			position.setPositionName(rs.getString("POSITIONNAME"));
			position.setMinBasicPay(rs.getInt("MINBASICPAY"));
			position.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(position);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public int add(Position position) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO POSITION(POSITIONID, POSITIONNAME, MINBASICPAY) VALUES(POSITIONSEQ.NEXTVAL, ?, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, position.getPositionName());
		pstmt.setInt(2, position.getMinBasicPay());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int remove(String positionId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM POSITION WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int modify(Position position) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE POSITION SET POSITIONNAME=?, MINBASICPAY=? WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, position.getPositionName());
		pstmt.setInt(2, position.getMinBasicPay());
		pstmt.setInt(3, Integer.parseInt(position.getPositionId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}
	
	@Override
	public Position searchId(String positionId) throws SQLException
	{
		Position result = new Position();
		
		String sql = "SELECT POSITIONID, POSITIONNAME, MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			result.setPositionId(rs.getString("POSITIONID"));
			result.setPositionName(rs.getString("POSITIONNAME"));
			result.setMinBasicPay(rs.getInt("MINBASICPAY"));
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
}
