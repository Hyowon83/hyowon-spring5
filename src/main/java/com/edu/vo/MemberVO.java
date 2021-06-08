package com.edu.vo;

import java.util.Date;

/**
 * 이 클래스는 DB에서 Model클래스로 입출력 혹은 Model에서 Service클래스로 입출력,
 * Service에서 Controller클래스로 데이터를 입출력, Controller에서 jsp로 데이커를 주고받을 때
 * 사용하는 클래스입니다. 
 * @author 장효원
 *
 */
public class MemberVO {
	//ERD를 보고 멤버변수를 만듭니다.
	private String user_id;
	private String user_pw;
	private String user_name;
	private String email;
	private Integer point; //int자료형을 안쓰고 Integer라는 클래스(참조형)자료형을 쓰는 이유: 클래스(참조형)데이터는 null값이 들어갈 수 있기 때문.
	private Boolean enabled; //논리-불린형 boolean -> Boolean 사용.
	private String levels;
	private Date reg_date;
	private Date update_date;
	
	//책에서는 롬복lombok.jar 을 사용해서 아래 내용을 생략한다.(메이븐에 추가해서 사용함)
	//우리는 그냥 get/set을 만들었음.
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	
	
}
