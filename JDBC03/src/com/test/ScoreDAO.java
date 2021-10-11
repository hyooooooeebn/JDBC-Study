package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	private Connection conn;
	
	public ScoreDAO() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
	}
	
	
	// 이름 국어 영어 수학 입력
	public int add(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES (SCORESEQ.NEXTVAL, '%s', %s,%s,%s)"
									, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	// 인원 수 count
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt  = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");			
		}
		
		rs.close();
		stmt.close();
		
		return result;
		
	}
	
	// select 전체 + 평균 총점
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, ROUND((KOR+ENG+MAT)/3,2) AS AVG" 
				  + " FROM TBL_SCORE" 
				  +	" ORDER BY SID";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getString("KOR"));
			dto.setEng(rs.getString("ENG"));
			dto.setMat(rs.getString("MAT"));
			dto.setTot(rs.getString("TOT"));
			dto.setAvg(rs.getString("AVG"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	public void close()
	{
		DBConn.close();
	}
}
