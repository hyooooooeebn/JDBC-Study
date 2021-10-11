package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberProcess
{
	private MemberDAO dao;

	public MemberProcess()
	{
		dao = new MemberDAO();
	}

	// 직원 입력 기능
	public void memberInsert()
	{
		try
		{
			dao.connection();

			Scanner sc = new Scanner(System.in);
			ArrayList<String> arraylist = new ArrayList<String>();

			System.out.println("직원 정보 입력 ----------------------------------------------------");
			System.out.print(" 이름 : ");
			String name = sc.next();

			System.out.print(" 주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();

			System.out.print(" 입사일(yyyy-mm-dd) : ");
			String ibsadate = sc.next();

			//
			System.out.print(" 지역(");
			arraylist = dao.city_name();

			for (String str : arraylist)
			{
				System.out.print(str + "/");
			}
			System.out.print(") : ");
			String city = sc.next();

			System.out.print(" 전화번호 : ");
			String tel = sc.next();

			//
			System.out.print(" 부서(");

			arraylist = dao.buseo_name();

			for (String str : arraylist)
			{
				System.out.print(str + "/");
			}
			System.out.print(") : ");

			String buseo = sc.next();

			//
			System.out.print(" 직위(");

			arraylist = dao.jikwi_name();

			for (String str : arraylist)
			{
				System.out.print(str + "/");
			}
			System.out.print(") : ");
			String jikwi = sc.next();

			String basicpay;
			
			do
			{
				System.out.printf(" 기본급(최소 %d원 이상) : ", dao.jikwi_basicpay(jikwi));
				basicpay = sc.next();
				
			} while (Integer.parseInt(basicpay) < dao.jikwi_basicpay(jikwi));


			System.out.print(" 수당 : ");
			String sudang = sc.next();

			MemberDTO dto = new MemberDTO();
			dto.setName(name);
			dto.setSsn(ssn);
			dto.setIbsadate(ibsadate);
			dto.setCity(dao.city_id(city));
			dto.setTel(tel);
			dto.setBuseo(dao.buseo_id(buseo));
			dto.setJikwi(dao.jikwi_id(jikwi));
			dto.setBasicpay(basicpay);
			dto.setSudang(sudang);

			int result = dao.add(dto);

			if (result > 0)
			{
				System.out.println(" ");
				System.out.println(" 직원 정보 입력 완료~!!! ");
				System.out.println("----------------------------------------------------직원 정보 입력");
			}

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end memberInsert()

	// 직원 전체 출력
	public void memberSelectAll()
	{
		try
		{
			dao.connection();

			Scanner sc = new Scanner(System.in);

			System.out.println("  1. 사번 정렬");
			System.out.println("  2. 이름 정렬");
			System.out.println("  3. 부서 정렬");
			System.out.println("  4. 직위 정렬");
			System.out.println("  5. 급여 내림차순 정렬");
			System.out.print("  >> 선택(1-5, -1종료) : ");
			String num = sc.next();
			if (num.equals("-1"))
			{
				return;

			}
			
			ArrayList<MemberDTO> arraylist = new ArrayList<MemberDTO>();
			
			System.out.println();
			System.out.printf("[전체 인원 : %d명]\n\n", dao.count());
			System.out.println("  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");
			System.out.println("===============================================================================================================================");
			
			switch (num)
			{
			case "1":
				arraylist = dao.lists_eid();
				break;
			case "2":
				arraylist = dao.lists_name();
				break;
			case "3":
				arraylist = dao.lists_buseo();
				break;
			case "4":
				arraylist = dao.lists_jikwi();
				break;
			case "5" :
				arraylist = dao.lists_pay();
			}

			for (MemberDTO dto : arraylist)
			{
				System.out.printf("%5s %5s %18s %15s %5s %18s %5s %5s %10s %10s %10s\n "
						, dto.getEid(), dto.getName(),dto.getSsn(), dto.getIbsadate()
						, dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi()
						, dto.getBasicpay(), dto.getSudang(), dto.getPay());
			}

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		} // end memberSelectAll()
	}

	// 직원 검색 출력
	public void memberSearch()
	{
		try
		{
			dao.connection();

			Scanner sc = new Scanner(System.in);
			System.out.println("  1. 사번 검색");
			System.out.println("  2. 이름 검색");
			System.out.println("  3. 부서 검색");
			System.out.println("  4. 직위 검색");
			System.out.print("  >> 선택(1-4,-1 종료) : ");
			String num = sc.next();
			if (num.equals("-1"))
			{
				return;
			}

			ArrayList<MemberDTO> arrayList = new ArrayList<MemberDTO>();

			switch (num)
			{

			case "1":
				System.out.print("사번을 입력하세요. : ");
				String eid = sc.next();
				arrayList = dao.memberSearch_eid(eid);
				break;
			case "2":
				System.out.print("이름을 입력하세요. : ");
				String name = sc.next();
				arrayList = dao.memberSearch_name(name);
				break;
			case "3":
				System.out.print("부서를 입력하세요. : ");
				String buseo = sc.next();
				arrayList = dao.memberSearch_buseo(buseo);
				break;
			case "4":
				System.out.print("직위를 입력하세요. : ");
				String jikwi = sc.next();
				arrayList = dao.memberSearch_jikwi(jikwi);
				break;

			}

			if (arrayList.size() > 0)
			{
				System.out.println("  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");
				System.out.println("===============================================================================================================================");
				

				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%5s %5s %18s %15s %5s %18s %5s %5s %10s %10s %10s\n ", dto.getEid(), dto.getName(),
							dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(),
							dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}

			} else
			{
				// 수신된 내용이 없다는 상황 안내
				System.out.println("검색 결과가 존재하지 않습니다.");
			}

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end memberSearch()

	// 직원 정보 수정
	public void memberUpdate()
	{
		try
		{
			Scanner sc = new Scanner(System.in);

			dao.connection();

			ArrayList<MemberDTO> arrayList = dao.lists_eid();
			if (arrayList.size() > 0)
			{
				System.out.println("  사번 이름 주민번호 입사일 지역 전화번호 부서 직위 기본급 수당 급여");

				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%5s %5s %18s %15s %5s %18s %5s %5s %10s %10s %10s\n ", dto.getEid(), dto.getName(),
							dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(),
							dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}

				System.out.print("수정할 번호를 입력하세요 : ");
				String eid = sc.next();
				
				ArrayList<String> arraylist = new ArrayList<String>();
				int c = 1;
				int b = 1;
				int j = 1;
				int ba = 1;
				
				System.out.println();
				System.out.print(" * 지역(");
				arraylist = dao.city_name();

				for (String str : arraylist)
				{
					System.out.print(c + "."+str + "/");
					c++;
				}
				System.out.print(")");
				System.out.println();

				//
				System.out.print(" * 부서(");

				arraylist = dao.buseo_name();

				for (String str : arraylist)
				{
					System.out.print(b + "."+str + "/");
					b++;
				}
				System.out.print(")");
				System.out.println();

				//
				System.out.print(" * 직위(");

				arraylist = dao.jikwi_name();

				for (String str : arraylist)
				{
					System.out.print(j + "."+str + "/");
					j++;
				}
				System.out.print(")");
				System.out.println();
				
				System.out.print(" * 직위 별 기본급(");
				
				arraylist = dao.jikwibasicpay();

				for (String str : arraylist)
				{

					System.out.print(ba + ". " + str + "원 /");
					ba++;
				}
				System.out.print(")");
				System.out.println();
				
				

				System.out.println("수정 데이터 입력");
				System.out.print("(이름 주민번호 입사일 지역(번호) 전화번호 부서(번호) 직위(번호) 기본급 수당 )\n : ");
				String name = sc.next();
				String ssn = sc.next();
				String ibsadate = sc.next();
				String city = sc.next();
				String tel = sc.next();
				String buseo = sc.next();
				String jikwi = sc.next();
				String basicpay = sc.next();
				String sudang = sc.next();

				MemberDTO dto = new MemberDTO();
				dto.setEid(eid);
				dto.setName(name);
				dto.setSsn(ssn);
				dto.setIbsadate(ibsadate);
				dto.setCity(city);
				dto.setTel(tel);
				dto.setBuseo(buseo);
				dto.setJikwi(jikwi);
				dto.setBasicpay(basicpay);
				dto.setSudang(sudang);

				int result = dao.modify(dto);
				if (result > 0)
				{
					System.out.println("수정이 완료되었습니다.");
				}
			}

			else
			{
				System.out.println("수정 대상이 존재하지 않습니다.");
			}

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

	// 직원 정보 삭제
	public void memberDelete()
	{
		try
		{
			dao.connection();

			Scanner sc = new Scanner(System.in);

			ArrayList<MemberDTO> arrayList = dao.lists_eid();

			if (arrayList.size() > 0)
			{
				System.out.println();
				System.out.println("  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");
				System.out.println("=============================================================================================================================== ");
				

				for (MemberDTO dto : arrayList)
				{
					System.out.printf("%5s %5s %18s %15s %5s %18s %5s %5s %10s %10s %10s\n ", dto.getEid(), dto.getName(),
							dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(),
							dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getPay());
				}

				System.out.println();
				System.out.print("삭제할 번호를 입력하세요. : ");
				int eid = sc.nextInt();

				System.out.println();
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N) : ");
				String reponse = sc.next();

				if (reponse.equals("Y") || reponse.equals("y"))
				{
					int result = dao.remove(eid);
					if (result > 0)
					{
						System.out.println("삭제가 완료되었습니다.");
					}
				} else
				{
					System.out.println("취소되었습니다.");
				}

			} else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다.");
			}

			dao.close();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}// end memberDelete()

}
