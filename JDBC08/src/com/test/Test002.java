/*====================================================
 * Test002.java
  * -CallableStatement를 활용한 sql 구문 전송 실습
 =====================================================*/

package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.util.DBConn;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class Test002
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공~!!!");
				
				try
				{
					// 쿼리문 준비
					String sql = "{call PRC_MEMBERSELECT(?)}";
					
					// CallableStatement 작업 객체 생성
					CallableStatement cstmt = conn.prepareCall(sql);
					
					
					// ★★ CHECK ★★
					// 프로시저 내부에서 sys_refcursor를 사용하고 있기 때문에
					// OracleTypes.CURSOR를 사용하기 위한 등록 과정이 필요힌 상황.
					// 1. Project Explorer 상에서 해당 프로젝트 마우스 우클릭
					//   > Build Path > Configure Build Path > Libraries 탭 선택
					//   > 『ojdbc6.jar』파일 추가 등록
					//		(외부 jar 파일 연결)
					// 2. 『import oracle.jdbc.OracleTypes;』 구문 추가 등록
					
					// ★★ CHECK ★★
					cstmt.registerOutParameter(1, OracleTypes.CURSOR);
					cstmt.execute();
					ResultSet rs = (ResultSet)cstmt.getObject(1);
					
					while (rs.next())
					{
						String sid = rs.getString("SID");
						String name = rs.getString("NAME");
						String tel = rs.getString("TEL");
						
						String str = String.format("%3s %7s %10s", sid, name, tel);
						
						System.out.println(str);
					}
					
					rs.close();
					cstmt.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
			DBConn.close();
			System.out.println("종료");
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}

/*
데이터베이스 연결 성공~!!!

1     김진희 010-1111-1111
2     이찬호 010-2222-2222
3     박혜진 010-3333-3333
4     윤유동 010-4444-4444
5     박혜진 010-5555-5555
6     정효진 010-6666-6666
7     손다정 010-7777-7777
8     박효빈 010-8888-8888
9     손범석 010-9999-9999
10     손범석 010-1100-1100
11     최수지 010-1100-1100
12     채지윤 010-1200-1200
13     서승균 010-3733-7202
14     장민지 010-1234-1234
15     장진하 010-4569-4569
16     정가연 010-7894-7894
종료
*/