package mybatis_study.dto;

import java.util.Date;

public class Student {
	private int studId;
	private String name;
	private String email;
	private PhoneNumber phone;
	private Date dob;
	public int getStudId() {
		return studId;
	}
	public void setStudId(int studId) {
		this.studId = studId;
	}
	public String getNmae() {
		return name;
	}
	public void setNmae(String nmae) {
		this.name = nmae;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PhoneNumber getPhone() {
		return phone;
	}
	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s, %s]", studId, name, email, phone, dob);
	}
	
	
}
