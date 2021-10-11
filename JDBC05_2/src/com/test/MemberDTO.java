package com.test;

public class MemberDTO
{
	// 주요 속성 구성
	int empId, basicpay, sudang, pay;
	String empName, ssn, cityName, tel, buseoName, jikwiName;
	
	String ibsaDate; // ※ 입사일은 오라클에서 날짜 형식으로 처리되지만 문자열로 구성

	// getter/setter 구성
	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public int getBasicpay()
	{
		return basicpay;
	}

	public void setBasicpay(int basicpay)
	{
		this.basicpay = basicpay;
	}

	public int getSudang()
	{
		return sudang;
	}

	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}

	public int getPay()
	{
		return pay;
	}

	public void setPay(int pay)
	{
		this.pay = pay;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public String getSsn()
	{
		return ssn;
	}

	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}

	public String getCityName()
	{
		return cityName;
	}

	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}

	public String getTel()
	{
		return tel;
	}

	public void setTel(String tel)
	{
		this.tel = tel;
	}

	public String getBuseoName()
	{
		return buseoName;
	}

	public void setBuseoName(String buseoName)
	{
		this.buseoName = buseoName;
	}

	public String getJikwiName()
	{
		return jikwiName;
	}

	public void setJikwiName(String jikwiName)
	{
		this.jikwiName = jikwiName;
	}

	public String getIbsaDate()
	{
		return ibsaDate;
	}

	public void setIbsaDate(String ibsaDate)
	{
		this.ibsaDate = ibsaDate;
	}
	
	
}
