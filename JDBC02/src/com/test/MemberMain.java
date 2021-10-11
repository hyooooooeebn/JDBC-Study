/*=====================
   MemberMain.java
=======================*/

/*
 * TBL_MEMBER 테이블을 활용하여
 * 이름과 전화번호를 여러 건 입력받고, 전체출력
 * 단, DB연동, MemberDAO, MemberDTO 클래스 활용
 * 
 * 실행 예)
 * 이름 전화번호 입력(1) : 이찬호 010-1111-1111
 * >> 회원 정보 입력 완료~!!!
 * 이름 전화번호 입력(2) : 박혜진 010-2222-2222
 * >> 회원 정보 입력 완료~!!!
 * 이름 전화번호 입력(3) : 윤유동 010-3333-3333
 * >> 회원 정보 입력 완료~!!!
 * 이름 전화번호 입력(4) : .
 * 
 * --------------------------------
 * 전체 회원 수 : 3명
 * --------------------------------
 * 번호       이름     전화번호
 * 1      이찬호  010-1111-1111
 * 2      박혜진  010-2222-2222
 * 3      윤유동  010-3333-3333
*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      
      try
      {
         MemberDAO dao = new MemberDAO();
         
         int count = dao.count();
         
         do
         {
            System.out.printf("이름 전화번호 입력(%d) : ", (++count));
            String name = sc.next();
            
            // 반복의 조건을 무너뜨리는 코드 구성 (. 입력)
            if(name.equals("."))
               break;
            
            String tel = sc.next(); 
            
            // DTO 객체 구성
            MemberDTO dto = new MemberDTO();
            dto.setName(name);
            dto.setTel(tel);
            
            // DB에 데이터 입력하는 메소드
            int result = dao.add(dto);
            if(result>0)
               System.out.println(">> 회원 정보 입력 완료~!!!");
            
         } while (true);
         
         System.out.println();
         System.out.println("-----------------------------------------------");
         System.out.printf("전체 회원 수 : %d명\n", dao.count());
         System.out.println("-----------------------------------------------");
         System.out.println("번호   이름   전화번호");
         // 리스트 출력
         for(MemberDTO obj : dao.lists())
         {
            System.out.printf("%3s %7s %12s\n"
                          , obj.getSid(), obj.getName(), obj.getTel());
         }
         System.out.println("-----------------------------------------------");
         
         
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      finally
      {
         try
         {
            DBConn.close();
            System.out.println("데이터베이스 연결 닫힘~!!!");
            System.out.println("프로그램 종료됨~!!!");
            
         } catch (Exception e)
         {
            System.out.println(e.toString());
         }
      }
      
   }
}