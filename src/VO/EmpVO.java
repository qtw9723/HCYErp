package VO;

import java.util.Date;

public class EmpVO {
	private int empNo;
	private String team;
	private String job;
	private String dept;
	private String deptLoc;
	private String level;
	private int sal;
	private Date inputDate;
	private String ename;
	private String email;
	private String addr;
	private String ssn;
	private String tel;
	private String pass;
	private String hiredate;
	private int totalDayOff;
	private String jobTel;

	public EmpVO() {

	}// constructor

	public EmpVO(int empNo, String team, String job, String dept, String deptLoc, String level, int sal, Date inputDate,
			String ename, String email, String addr, String ssn, String tel, String pass, String hiredate,
			int totalDayOff,String jobTel) {
		this.empNo = empNo;
		this.team = team;
		this.job = job;
		this.dept = dept;
		this.deptLoc = deptLoc;
		this.level = level;
		this.sal = sal;
		this.inputDate = inputDate;
		this.ename = ename;
		this.email = email;
		this.addr = addr;
		this.ssn = ssn;
		this.tel = tel;
		this.pass = pass;
		this.hiredate = hiredate;
		this.totalDayOff = totalDayOff;
		this.jobTel=jobTel;
	}// constructor

	// getter
	public int getEmpNo() {
		return empNo;
	}

	public String getJobTel() {
		return jobTel;
	}

	public String getTeam() {
		return team;
	}

	public String getJob() {
		return job;
	}

	public String getDept() {
		return dept;
	}

	public String getDeptLoc() {
		return deptLoc;
	}

	public String getLevel() {
		return level;
	}

	public int getSal() {
		return sal;
	}

	public Date getInputDate() {
		return inputDate;
	}

	public String getEname() {
		return ename;
	}

	public String getEmail() {
		return email;
	}

	public String getAddr() {
		return addr;
	}

	public String getSsn() {
		return ssn;
	}

	public String getTel() {
		return tel;
	}

	public String getPass() {
		return pass;
	}

	public String getHiredate() {
		return hiredate;
	}

	public int getTotalDayOff() {
		return totalDayOff;
	}

	
	// setter
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public void setDeptLoc(String deptLoc) {
		this.deptLoc = deptLoc;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public void setTotalDayOff(int totalDayOff) {
		this.totalDayOff = totalDayOff;
	}

	public void setJobTel(String jobTel) {
		this.jobTel = jobTel;
	}
	
}// class
