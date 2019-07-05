/*======================================
   #9. EmployeeDAO.java
   - 데이터베이스 액션 처리 클래스.
   - 직원 정보 입출력 액션, 수정 액션.
======================================*/

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO
{	
	private DataSource dataSource;
 
	public void setDataSource(DataSource dataSource)
	{ 
		this.dataSource = dataSource; 
	}
	
	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		String sql = "SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME, TELEPHONE"
				+ ", DEPARTMENTID, DEPARTMENTNAME, POSITIONID, POSITIONNAME, REGIONID, REGIONNAME, BASICPAY, EXTRAPAY, PAY, GRADE" + 
				" FROM EMPLOYEEVIEW ORDER BY EMPLOYEEID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Employee employee = new Employee();
			
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn1(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunar(rs.getInt("LUNAR"));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentId(rs.getString("DEPARTMENTID"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionId(rs.getString("POSITIONID"));
			employee.setPositionName(rs.getString("POSITIONNAME"));
			employee.setRegionId(rs.getString("REGIONID"));
			employee.setRegionName(rs.getString("REGIONNAME"));
			employee.setBasicPay(rs.getInt("BASICPAY"));
			employee.setExtraPay(rs.getInt("EXTRAPAY"));
			employee.setPay(rs.getInt("PAY"));
			employee.setGrade(rs.getInt("GRADE"));
			
			result.add(employee);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public ArrayList<Region> regionList() throws SQLException
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
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW ORDER BY DEPARTMENTID";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			Department department = new Department();
			
			department.setDepartmentId(rs.getString("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			department.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(department);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public ArrayList<Position> positionList() throws SQLException
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
	public int getMinBasicPay(String positionId) throws SQLException
	{
		int result = 0;
		
		String sql = "SELECT MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
			result = rs.getInt("MINBASICPAY");
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		int result = 0;
		
		String sql = "INSERT INTO EMPLOYEE(EMPLOYEEID, NAME, SSN1, SSN2, BIRTHDAY, LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID, BASICPAY, EXTRAPAY) " + 
				"VALUES(EMPLOYEESEQ.NEXTVAL, ?, ?, CRYPTPACK.ENCRYPT(?, ?), TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setInt(8, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(9, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(10, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		
		String sql = "DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(employeeId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		
		String sql = "UPDATE EMPLOYEE" + 
				" SET NAME=?" + 
				"  , BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD')" + 
				"  , LUNAR=?" + 
				"  , TELEPHONE=?" + 
				"  , DEPARTMENTID=?" + 
				"  , POSITIONID=?" + 
				"  , REGIONID=?" + 
				"  , BASICPAY=?" + 
				"  , EXTRAPAY=?" + 
				"  , SSN1=?" + 
				"  , SSN2=CRYPTPACK.ENCRYPT(?, ?)" + 
				" WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getBirthday());
		pstmt.setInt(3, employee.getLunar());
		pstmt.setString(4, employee.getTelephone());
		pstmt.setInt(5, Integer.parseInt(employee.getDepartmentId()));
		pstmt.setInt(6, Integer.parseInt(employee.getPositionId()));
		pstmt.setInt(7, Integer.parseInt(employee.getRegionId()));
		pstmt.setInt(8, employee.getBasicPay());
		pstmt.setInt(9, employee.getExtraPay());
		pstmt.setString(10, employee.getSsn1());
		pstmt.setString(11, employee.getSsn2());
		pstmt.setString(12, employee.getSsn2());
		pstmt.setInt(13, Integer.parseInt(employee.getEmployeeId()));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public Employee searchId(String employeeId) throws SQLException
	{
		Employee result = new Employee();
		
		String sql = "SELECT EMPLOYEEID, NAME, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') AS BIRTHDAY, LUNAR, TELEPHONE, DEPARTMENTID, POSITIONID, REGIONID, BASICPAY, EXTRAPAY, SSN1, SSN2" + 
				" FROM EMPLOYEE WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(employeeId));
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setSsn1(rs.getString("SSN1"));
			result.setSsn2(rs.getString("SSN2"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}

	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = "";
		
		String sql = "SELECT NAME FROM EMPLOYEE WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?, ?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
			result = rs.getString("NAME");
		
		return result;
	}

	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = "";
		
		String sql = "SELECT NAME FROM EMPLOYEE WHERE EMPLOYEEID=? AND SSN2 = CRYPTPACK.ENCRYPT(?, ?) AND GRADE=0";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
			result = rs.getString("NAME");
		
		return result;
	}

}
