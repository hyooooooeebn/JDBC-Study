package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;

	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	public void close()
	{
		DBConn.close();
	}
	
	// 성적 입력
	public int add(ScoreDTO dto) throws SQLException
	{
		String sql = "INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
				+ " VALUES (SCORESEQ.NEXTVAL, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result; 
	}
	
	// 성적 전체 출력
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR+ENG+MAT) AS TOT"
				+ ",  (KOR+ENG+MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE"
				+ " ORDER BY SID";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs  = pstmt.executeQuery();
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 이름 검색 출력
	public ArrayList<ScoreDTO> search(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
				+ ", (KOR+ENG+MAT) AS TOT"
				+ ",  (KOR+ENG+MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE"
				+ " WHERE NAME = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, name);
		
		ResultSet rs = pstmt.executeQuery();
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));

			result.add(dto);
		}
		
		rs.close();
		pstmt.close();
		
		return result;
		
	}
	
	// 성적 수정
	public int modify(ScoreDTO dto) throws SQLException
	{
		String sql = "UPDATE TBL_SCORE"
				+ " SET NAME = ?, KOR = ?, ENG = ?, MAT = ?"
				+ " WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, dto.getName());
		pstmt.setInt(2, dto.getKor());
		pstmt.setInt(3, dto.getEng());
		pstmt.setInt(4, dto.getMat());
		pstmt.setInt(5, dto.getSid());
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}
	
	// 성적 삭제
	public int delete(int sid) throws SQLException
	{
		String sql = "DELETE FROM TBL_SCORE WHERE SID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		int result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
		
	}
	
	// 인원수 찾기
	public int count() throws SQLException
	{
		int result =0;
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next())
			result = rs.getInt("COUNT");
		
		rs.close();
		pstmt.close();
		
		return result;
	}
	
	// 아이디에 따른 검색
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();

		String sql = "SELECT *" + " FROM( SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT) / 3 AS AVG, RANK() OVER(ORDER BY (KOR + ENG + MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE) WHERE SID = ?";
				
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, sid);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next())
		{
			ScoreDTO dto = new ScoreDTO();

			dto.setSid(rs.getInt("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));

			result.add(dto);
		}

		rs.close();
		pstmt.close();

		return result;
	}
	
	
}
