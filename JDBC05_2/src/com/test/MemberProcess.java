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

	// 직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);

		try
		{
			dao.connection();

			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder cityStr = new StringBuilder();
			for (String city : citys)
				cityStr.append(city + "/");

			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
			{
				buseoStr.append(buseo + "/");
			}

			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchJikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}

			// 사용자에게 보여지는 화면 처리
			System.out.println("직원 정보 입력 ----------------------------------------------------");
			System.out.print(" 이름 : ");
			String empName = sc.next();

			System.out.print(" 주민등록번호(yymmdd-nnnnnnn) : ");
			String ssn = sc.next();

			System.out.print(" 입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();

			//
			System.out.printf(" 지역(%s) : ", cityStr.toString());
			String cityName = sc.next();

			System.out.print(" 전화번호 : ");
			String tel = sc.next();

			//
			System.out.printf(" 부서(%s) :", buseoStr.toString());
			String buseoName = sc.next();

			//
			System.out.printf(" 직위(%s) : ", jikwiStr.toString());
			String jikwiName = sc.next();

			String basicpay;

			System.out.printf(" 기본급(최소 %d원 이상) : ", dao.searchBasicpay(jikwiName));
			int basicPay = sc.nextInt();

			System.out.print(" 수당 : ");
			int sudang = sc.nextInt();

			MemberDTO dto = new MemberDTO();
			dto.setEmpName(empName);
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsaDate);
			dto.setCityName(cityName);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicpay(basicPay);
			dto.setSudang(sudang);

			int result = dao.add(dto);

			if (result > 0)
				System.out.println(" 직원 정보 입력 완료~!!! ");

			System.out.println("----------------------------------------------------직원 정보 입력");

		} catch (Exception e)
		{
			System.out.println(e.toString());
		} finally
		{
			try
			{
				dao.close();

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}// end memberInsert()

	// 직원 전체 출력 메소드 정의
	public void memberLists()
	{
		Scanner sc = new Scanner(System.in);

		// 서브 메뉴 출력(안내)
		System.out.println();
		System.out.println("1. 사번 정렬"); // EMP_ID
		System.out.println("2. 이름 정렬"); // EMP_NAME
		System.out.println("3. 부서 정렬"); // BUSEO_NAME
		System.out.println("4. 직위 정렬"); // JIKWI_NAME
		System.out.println("5. 급여 내림차순 정렬"); // PAY DESC
		System.out.print("  >> 선택(1-5, -1종료) : ");
		String menuStr = sc.next();

		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
				return;

			String key = "";
			switch (menu)
			{
			case 1:
				key = "EMP_ID";
				break;
			case 2:
				key = "EMP_NAME";
				break;
			case 3:
				key = "BUSEO_NAME";
				break;
			case 4:
				key = "JIKWI_NAME";
				break;
			case 5:
				key = "PAY DESC";
				break;

			}

			dao.connection();

			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount());
			System.out.println(
					"  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");
			ArrayList<MemberDTO> memList = dao.lists(key);

			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%5s %5s %18s %15s %5s %18s %5s %5s %10s %10s %10s\n ", memberDTO.getEmpId(),
						memberDTO.getEmpName(), memberDTO.getSsn(), memberDTO.getIbsaDate(), memberDTO.getCityName(),
						memberDTO.getTel(), memberDTO.getBuseoName(), memberDTO.getJikwiName(), memberDTO.getBasicpay(),
						memberDTO.getSudang(), memberDTO.getPay());

			}

		} catch (Exception e)
		{
			System.out.println(e.toString());
		} finally
		{
			try
			{
				dao.close();

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}

	}// end memberList();

	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		Scanner sc = new Scanner(System.in);

		// 서브 메뉴 구성
		System.out.println("  1. 사번 검색");
		System.out.println("  2. 이름 검색");
		System.out.println("  3. 부서 검색");
		System.out.println("  4. 직위 검색");
		System.out.print("  >> 선택(1-4,-1 종료) : ");
		String menuStr = sc.next();

		try
		{
			int menu = Integer.parseInt(menuStr);
			if (menu == -1)
				return;

			String key = "", value = "";

			switch (menu)
			{
			case 1:
				System.out.print("검색할 사원번호 입력 : ");
				value = sc.next();
				key = "EMP_ID";
				break;
			case 2:
				System.out.print("검색할 사원이름 입력 : ");
				value = sc.next();
				key = "EMP_NAME";
				break;
			case 3:
				System.out.print("검색할 부서명 입력 : ");
				value = sc.next();
				key = "BUSEO_NAME";
				break;
			case 4:
				System.out.print("검색할 직위명 입력 : ");
				value = sc.next();
				key = "JIKWI_NAME";
				break;
			}

			dao.connection();

			System.out.println();
			System.out.printf("전체 인원 : %d명\n", dao.memberCount(key, value));
			System.out.println("  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");

			ArrayList<MemberDTO> memList = dao.searchLists(key, value);

			for (MemberDTO memberDTO : memList)
			{
				System.out.printf("%5d %5s %18s %15s %5s %18s %5s %5s %10d %10d %10d\n ", memberDTO.getEmpId(),
						memberDTO.getEmpName(), memberDTO.getSsn(), memberDTO.getIbsaDate(), memberDTO.getCityName(),
						memberDTO.getTel(), memberDTO.getBuseoName(), memberDTO.getJikwiName(), memberDTO.getBasicpay(),
						memberDTO.getSudang(), memberDTO.getPay());

			}

		} catch (Exception e)
		{
			System.out.println(e.toString());
		} finally
		{
			try
			{
				dao.close();

			} catch (Exception e)
			{
				System.out.println(e.toString());
			}

		}

	}// end memberSearch()

	// 직원 정보 수정 메소드 정의
	public void memberUpdate()
	{
		Scanner sc = new Scanner(System.in);

		try
		{
			System.out.print("수정할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			dao.connection();
			
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			
			if (members.size() > 0)
			{
				// 지역 리스트 구성
				ArrayList<String> citys = dao.searchCity();
				StringBuilder cityStr = new StringBuilder();
				for (String city : citys)
					cityStr.append(city + "/");

				// 부서 리스트 구성
				ArrayList<String> buseos = dao.searchBuseo();
				StringBuilder buseoStr = new StringBuilder();
				for (String buseo : buseos)
					buseoStr.append(buseo + "/");

				// 직위 리스트 구성
				ArrayList<String> jikwis = dao.searchJikwi();
				StringBuilder jikwiStr = new StringBuilder();
				for (String jikwi : jikwis)
					jikwiStr.append(jikwi + "/");
				
				
				// 사용자에게 보여지는 화면 처리
				
				MemberDTO mMember = members.get(0);
				int mEmpId = mMember.getEmpId();
				String mEmpName = mMember.getEmpName();
				String mSsn = mMember.getSsn();
				String mIbsaDate = mMember.getIbsaDate();
				String mCityName = mMember.getCityName();
				String mTel = mMember.getTel();
				String mBuseoName = mMember.getBuseoName();
				String mJikwiName = mMember.getJikwiName();
				int mBasicPay = mMember.getBasicpay();
				int mSundang = mMember.getSudang();

				System.out.println();
				System.out.println("직원 정보 수정 ----------------------------------------------------");
				System.out.printf("기존 이름 : %s\n", mEmpName);
				System.out.print("수정 이름 : ");
				String empName = sc.next();
				if (empName.equals("-"))
					empName = mEmpName;
				
				System.out.printf("기존 주민등록번호 : %s\n", mSsn);
				System.out.print("수정 주민등록번호(yymmdd-nnnnnnn) : ");
				String ssn = sc.next();
				if (ssn.equals("-"))
					ssn = mSsn;

				System.out.printf("기존 입사일 : %s\n" , mIbsaDate);
				System.out.print("수정 입사일(yyyy-mm-dd) : ");
				String ibsaDate = sc.next();
				if (ibsaDate.equals("-"))
					ibsaDate = mIbsaDate;
				
				System.out.printf("기존 지역 : %s\n", mCityName);
				System.out.printf("수정 지역(%s) : ", cityStr);
				String cityName = sc.next();
				if (cityName.equals("-"))
					cityName = mCityName;

				System.out.printf("기존 전화번호 : %s\n", mTel);
				System.out.print("수정 전화번호 : ");
				String tel = sc.next();
				if (tel.equals("-"))
					tel = mTel;

				System.out.printf("기존 부서 : %s\n", mBuseoName);
				System.out.printf("수정 부서(%s) :", buseoStr);
				String buseoName = sc.next();
				if (buseoName.equals("-"))
					buseoName = mBuseoName;

				System.out.printf("기존 직위 : %s\n", mJikwiName);
				System.out.printf("수정 직위(%s) : ", jikwiStr);
				String jikwiName = sc.next();
				if (jikwiName.equals("-"))
					jikwiName = mJikwiName;

				System.out.printf("기존 기본급 : %s\n",mBasicPay );
				System.out.printf("수정 기본급(최소 %d원 이상) : ", dao.searchBasicpay(jikwiName));
				String basicPayStr = sc.next();
				int basicPay = 0;
				if(basicPayStr.equals("-"))
					basicPay = mBasicPay;
				else
					basicPay = Integer.parseInt(basicPayStr);
				

				System.out.printf("기존 수당 : %s\n",mSundang);
				System.out.print("수정 수당 : ");
				String sudangStr = sc.next();
				int sudang = 0;
				if(sudangStr.equals("-"))
					sudang = mSundang;
				else 
					sudang = Integer.parseInt(sudangStr);
				
				
				// 새로 입력받은 내용을 통해 DTO 구성
				MemberDTO member = new MemberDTO();
				member.setEmpId(mEmpId);
				member.setEmpName(empName);
				member.setSsn(ssn);
				member.setIbsaDate(ibsaDate);
				member.setCityName(cityName);
				member.setTel(tel);
				member.setBuseoName(buseoName);
				member.setJikwiName(jikwiName);
				member.setBasicpay(basicPay);
				member.setSudang(sudang);

				int result = dao.modify(member);

				if (result > 0)
					System.out.println(" 직원 정보 수정 완료~!!! ");

				System.out.println("----------------------------------------------------직원 정보 수정");
 
			}
			else
			{
				System.out.println("수정 대상을 검색하지 못했습니다.");
			}
			
		} catch (Exception e)
		{

			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}//end memeberUpdate()

	
	// 직원 정보 삭제 메소드 정의
	public void memberDelete()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.print("삭제할 직원의 사원번호 입력 : ");
			String value = sc.next();
			
			// 직원 정보 확인 후 삭제여부 결정
			dao.connection();
			ArrayList<MemberDTO> members = dao.searchLists("EMP_ID", value);
			
			if (members.size() >0)
			{
				System.out.println();
				System.out.println("  사번    이름         주민번호            입사일      지역        전화번호       부서     직위    기본급     수당      급여");
				for (MemberDTO memberDTO : members)
				{
					System.out.printf("%5d %5s %18s %15s %5s %18s %5s %5s %10d %10d %10d\n ", memberDTO.getEmpId(),
							memberDTO.getEmpName(), memberDTO.getSsn(), memberDTO.getIbsaDate(), memberDTO.getCityName(),
							memberDTO.getTel(), memberDTO.getBuseoName(), memberDTO.getJikwiName(), memberDTO.getBasicpay(),
							memberDTO.getSudang(), memberDTO.getPay());

				}
				
				System.out.print("\n정말 삭제하시겠습니까?(Y/N) : ");
				String reponse = sc.next();
				if (reponse.equals("Y") || reponse.equals("y"))
				{
					int result = dao.remove(Integer.parseInt(value));
					if (result > 0)
						System.out.println("직원 정보가 정상적으로 삭제되었습니다.");
				} 
				
			}
			else
			{
				System.out.println("삭제 대상을 검색하지 못하였습니다.");
			}
			
			
			
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}//end memberDelete()
}
