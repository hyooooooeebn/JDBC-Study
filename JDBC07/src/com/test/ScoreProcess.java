package com.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class ScoreProcess
{
	private ScoreDAO dao;

	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}

	public void insert()
	{
		try
		{
			dao.connection();

			int count = dao.count();

			Scanner sc = new Scanner(System.in);

			do
			{
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));

				String name = sc.next();
				if (name.equals("-1"))
				{
					return;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();

				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);

				int result = dao.add(dto);
				if (result > 0)
					System.out.println("성적이 입력되었습니다.");
			} while (true);

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}

		dao.close();

	}

	// 전체 조회
	public void selectAll() throws SQLException
	{
		dao.connection();

		int count = dao.count();

		System.out.println();
		System.out.printf("전체 인원 : %d명\n", count);
		System.out.println("\n번호  이름   국어  영어  수학  총점 평균   석차");

		for (ScoreDTO dto : dao.lists())
		{
			System.out.printf("%3d %4s %4d %5d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
					dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());

		}

		dao.close();
	}

	// 이름 검색 출력
	public void searchName() throws SQLException
	{
		dao.connection();

		Scanner sc = new Scanner(System.in);
		System.out.print("이름을 입력하세요. : ");
		String name = sc.next();

		ArrayList<ScoreDTO> arraylist = dao.search(name);

		if (arraylist.size() > 0)
		{
			System.out.println("번호  이름   국어  영어  수학  총점 평균   석차");

			for (ScoreDTO dto : arraylist)
			{
				System.out.printf("%3d %4s %4d %5d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());

			}
		} else
		{
			System.out.println("결과가 존재하지 않습니다.");
		}

		dao.close();
	}

	// 수정
	public void update() throws SQLException
	{
		dao.connection();

		Scanner sc = new Scanner(System.in);

		System.out.print("수정할 번호를 입력하세요. : ");
		int sid = sc.nextInt();

		ArrayList<ScoreDTO> arraylist = dao.lists(sid);

		System.out.println("번호  이름   국어  영어  수학  총점 평균   석차");

		for (ScoreDTO dto : arraylist)
		{
			System.out.printf("%3d %4s %4d %5d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
					dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
		}

		System.out.println();
		System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
		String name = sc.next();
		int kor = sc.nextInt();
		int eng = sc.nextInt();
		int mat = sc.nextInt();

		ScoreDTO dto = new ScoreDTO();
		dto.setName(name);
		dto.setKor(kor);
		dto.setEng(eng);
		dto.setMat(mat);
		dto.setSid(sid);

		int result = dao.modify(dto);
		if (result > 0)
		{
			System.out.println("수정이 완료되었습니다.");
		}


		dao.close();
	}

	// 삭제
	public void delete() throws SQLException
	{
		Scanner sc = new Scanner(System.in);

		dao.connection();

		System.out.print("삭제할 번호를 입력하세요. ");
		int sid = sc.nextInt();

		ArrayList<ScoreDTO> arrayList = dao.lists(sid);

		if (arrayList.size() > 0)
		{
			System.out.println("번호  이름   국어  영어  수학  총점 평균   석차");

			for (ScoreDTO dto : arrayList)
			{
				System.out.printf("%3s %4s %4d %5d %5d %5d %5.1f %5d\n", dto.getSid(), dto.getName(), dto.getKor(),
						dto.getEng(), dto.getMat(), dto.getTot(), dto.getAvg(), dto.getRank());
			}

			System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
			String reponse = sc.next();
			if (reponse.equals("Y") || reponse.equals("y"))
			{
				int result = dao.delete(sid);

				if (result > 0)
				{
					System.out.println("삭제가 완료되었습니다.");
				}
			} else
			{
				System.out.println("삭제가 취소되었습니다.");
			}

		} else
		{
			System.out.println("삭제할 대상이 존재하지 않습니다.");
		}
		
		dao.close();

	}
	
}
