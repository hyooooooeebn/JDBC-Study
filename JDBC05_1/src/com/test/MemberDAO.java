package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	// DB 연결
	private Connection conn;

	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}

	// 1번======================
	// 직원 정보 입력
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID"
						+ ", TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
						+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s', %s" + ", '%s', %s, %s, %s , %s)",
				dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(),
				dto.getJikwi(), dto.getBasicpay(), dto.getSudang());
		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// 직원 인원 수 확인
	public int count() throws SQLException
	{
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}

		rs.close();
		stmt.close();

		return result;

	}

	// 2번=====================
	// 직원 전체 출력

	// 사번 정렬
	public ArrayList<MemberDTO> lists_eid() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN"
				+ ", TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE, C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO"
				+ ", J.JIKWI_NAME AS JIKWI, E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG"
				+ ", (E.BASICPAY*12)+E.SUDANG AS PAY" + " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J" + " ON J.JIKWI_ID = E.JIKWI_ID"
				+ " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID" + " ORDER BY E.EMP_ID";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);

		}

		rs.close();
		stmt.close();

		return result;
	}

	// 이름 정렬
	public ArrayList<MemberDTO> lists_name() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN"
				+ ", TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE, C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO"
				+ ", J.JIKWI_NAME AS JIKWI, E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG"
				+ ", (E.BASICPAY*12)+E.SUDANG AS PAY" + " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J" + " ON J.JIKWI_ID = E.JIKWI_ID"
				+ " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID" + " ORDER BY E.EMP_NAME";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);

		}

		rs.close();
		stmt.close();

		return result;
	}

	// 부서 정렬
	public ArrayList<MemberDTO> lists_buseo() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN"
				+ ", TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE, C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO"
				+ ", J.JIKWI_NAME AS JIKWI, E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG"
				+ ", (E.BASICPAY*12)+E.SUDANG AS PAY" + " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J" + " ON J.JIKWI_ID = E.JIKWI_ID"
				+ " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID" + " ORDER BY B.BUSEO_NAME";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);

		}

		rs.close();
		stmt.close();

		return result;
	}

	// 직위 정렬
	public ArrayList<MemberDTO> lists_jikwi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN"
				+ ", TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE, C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO"
				+ ", J.JIKWI_NAME AS JIKWI, E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG"
				+ ", (E.BASICPAY*12)+E.SUDANG AS PAY" + " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J" + " ON J.JIKWI_ID = E.JIKWI_ID"
				+ " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID" + " ORDER BY J.JIKWI_NAME";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);

		}

		rs.close();
		stmt.close();

		return result;
	}

	// 급여(내림) 정렬
	public ArrayList<MemberDTO> lists_pay() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN"
				+ ", TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE, C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO"
				+ ", J.JIKWI_NAME AS JIKWI, E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG"
				+ ", (E.BASICPAY*12)+E.SUDANG AS PAY" + " FROM TBL_EMP E JOIN TBL_BUSEO B"
				+ " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J" + " ON J.JIKWI_ID = E.JIKWI_ID"
				+ " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID" + " ORDER BY PAY DESC";

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);

		}

		rs.close();
		stmt.close();

		return result;
	}

	// 3번================
	// 직원 검색

	// 사번 검색
	public ArrayList<MemberDTO> memberSearch_eid(String eid) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN, TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE"
						+ ", C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO , J.JIKWI_NAME AS JIKWI"
						+ ", E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG, (E.BASICPAY*12)+E.SUDANG AS PAY"
						+ " FROM TBL_EMP E JOIN TBL_BUSEO B" + " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J"
						+ " ON J.JIKWI_ID = E.JIKWI_ID" + " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID"
						+ " WHERE E.EMP_ID = %s",
				eid);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// 이름 검색
	public ArrayList<MemberDTO> memberSearch_name(String name) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN, TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE"
						+ ", C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO , J.JIKWI_NAME AS JIKWI"
						+ ", E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG, (E.BASICPAY*12)+E.SUDANG AS PAY"
						+ " FROM TBL_EMP E JOIN TBL_BUSEO B" + " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J"
						+ " ON J.JIKWI_ID = E.JIKWI_ID" + " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID"
						+ " WHERE E.EMP_NAME = '%s'",
				name);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// 부서 검색
	public ArrayList<MemberDTO> memberSearch_buseo(String buseo) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN, TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE"
						+ ", C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO , J.JIKWI_NAME AS JIKWI"
						+ ", E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG, (E.BASICPAY*12)+E.SUDANG AS PAY"
						+ " FROM TBL_EMP E JOIN TBL_BUSEO B" + " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J"
						+ " ON J.JIKWI_ID = E.JIKWI_ID" + " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID"
						+ " WHERE B.BUSEO_NAME = '%s'",
				buseo);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// 직위 검색
	public ArrayList<MemberDTO> memberSearch_jikwi(String jikwi) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"SELECT E.EMP_ID AS EID, E.EMP_NAME AS NAME, E.SSN AS SSN, TO_CHAR(E.IBSADATE,'YYYY-MM-DD') AS IBSADATE"
						+ ", C.CITY_NAME AS CITY, E.TEL AS TEL, B.BUSEO_NAME AS BUSEO , J.JIKWI_NAME AS JIKWI"
						+ ", E.BASICPAY AS BASICPAY, E.SUDANG AS SUDANG, (E.BASICPAY*12)+E.SUDANG AS PAY"
						+ " FROM TBL_EMP E JOIN TBL_BUSEO B" + " ON E.BUSEO_ID = B.BUSEO_ID" + " JOIN TBL_JIKWI J"
						+ " ON J.JIKWI_ID = E.JIKWI_ID" + " JOIN TBL_CITY C" + " ON C.CITY_ID = E.CITY_ID"
						+ " WHERE J.JIKWI_NAME = '%s'",
				jikwi);

		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();

			dto.setEid(rs.getString(1));
			dto.setName(rs.getString(2));
			dto.setSsn(rs.getString(3));
			dto.setIbsadate(rs.getString(4));
			dto.setCity(rs.getString(5));
			dto.setTel(rs.getString(6));
			dto.setBuseo(rs.getString(7));
			dto.setJikwi(rs.getString(8));
			dto.setBasicpay(rs.getString(9));
			dto.setSudang(rs.getString(10));
			dto.setPay(rs.getString(11));

			result.add(dto);
		}

		rs.close();
		stmt.close();

		return result;
	}

	// 4번 ============
	// 직원 정보 수정
	public int modify(MemberDTO dto) throws SQLException
	{
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format(
				"UPDATE TBL_EMP" + " SET EMP_NAME = '%S', SSN = '%S', IBSADATE ='%S', CITY_ID = %S, TEL = '%S'"
						+ ", BUSEO_ID = %S , JIKWI_ID =%S , BASICPAY = %S, SUDANG = %S WHERE EMP_ID = %S",
				dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(),
				dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getEid());

		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// 5번 =============
	// 직원 정보 삭제
	public int remove(int eid) throws SQLException
	{
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", eid);
		result = stmt.executeUpdate(sql);

		stmt.close();

		return result;
	}

	// 부서 이름 찾기
	public ArrayList<String> buseo_name() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO ORDER BY BUSEO_ID";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			String str = null;

			str = rs.getString("BUSEO_NAME");

			result.add(str);
		}

		rs.close();

		stmt.close();

		return result;

	}

	// 직위 이름 찾기
	public ArrayList<String> jikwi_name() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI ORDER BY JIKWI_ID";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			String str = null;

			str = rs.getString("JIKWI_NAME");

			result.add(str);
		}
		rs.close();

		stmt.close();

		return result;

	}

	// 도시 이름 찾기
	public ArrayList<String> city_name() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT CITY_NAME FROM TBL_CITY ORDER BY CITY_ID";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			String str = null;

			str = rs.getString("CITY_NAME");

			result.add(str);
		}

		rs.close();

		stmt.close();

		return result;

	}

	// 직위 별 해당 기본급 찾기
	public int jikwi_basicpay(String jname) throws SQLException
	{
		int result = 0;

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME ='%s' ORDER BY JIKWI_ID",
				jname);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("MIN_BASICPAY");
		}
		rs.close();

		stmt.close();
		return result;
	}

	// 직위 별 기본급 전체 출력
	public ArrayList<String> jikwibasicpay() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();

		Statement stmt = conn.createStatement();
		String sql = "SELECT MIN_BASICPAY FROM TBL_JIKWI ORDER BY JIKWI_ID";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			String str = null;

			str = rs.getString("MIN_BASICPAY");

			result.add(str);
		}

		return result;

	}

	// 부서이름으로 부서번호 찾기
	public String buseo_id(String bname) throws SQLException
	{
		String result = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME = '%s'", bname);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getString("BUSEO_ID");
		}
		stmt.close();
		return result;
	}

	// 직위이름으로 직위번호 찾기
	public String jikwi_id(String jname) throws SQLException
	{
		String result = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME = '%s'", jname);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getString("JIKWI_ID");
		}
		stmt.close();
		return result;
	}

	// 도시이름으로 도시번호 찾기

	// 도시이름으로 도시번호 찾기
	public String city_id(String cname) throws SQLException
	{
		String result = null;

		Statement stmt = conn.createStatement();
		String sql = String.format("SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME = '%s'", cname);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getString("CITY_ID");
		}
		stmt.close();
		return result;
	}

	// DB 종료
	public void close()
	{
		DBConn.close();
	}

}
