package com.edu.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트해보는 클래스입니다.
 * 상사가 Junit CRUD까지 만들어서 일반사원에세 공개 + 회원관리
 * @author 장효원
 *
 */
//RunWith 인터페이스 현재클래스가 Junit실행클래스라고 명시.
@RunWith(SpringJUnit4ClassRunner.class)
//경로에서 **는 모든 폴더를 명시. *는 모든 파일명을 명시.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourceTest {
	//디버그용 로그 객체변수생성
	private Logger logger = Logger.getLogger(DataSourceTest.class);
	//dataSource객체는 데이터베이스 객체를 pool로 저장해서 사용할때 DataSource클래스를 사용(아래)
	@Inject //인젝트는 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 만들었음.
	DataSource dataSource; //Inject로 객체를 만들면 메모리 관리를 스프링이 대신해줌.
	//Inject는 자바8부터 지원. 이전 자바7에서는 @Autowired로 객체를 만들었음.
	
	
	//스프링 코딩 시작 순서
	//M - V - C 사이에 데이터를 입출력하는 임시저장공간(VO클래스-멤버변수+Get/Set메소드)생성.
	//보통 ValueObject클래스는 DB테이블과 1:1로 매칭 됨.
	//그래서 1. MemberVO.java라는 VO클래스를 생성한다.(필수)
	//2. VO클래스를 사용해서 DB(마이바티스)쿼리를 만든다.
	@Test
	public void selectMember() throws Exception {
		//이 메소드가 회원 관리 테이블에서 더미로 입력한 100개의 레코드를 출력하는 메소드를 테스트하면 -> 회원관리 목록이 출력됨.
		
	}
	
	@Test
	public void oldQueryTest() throws Exception {
		//스프링빈을 사용하지 않을 때 예전 방식: 코딩테스트에서는 스프링 설정을 안쓰고, 직접 DB아이디/암호 입력		
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE2","apmsetup");
		logger.debug("데이터베이스 직접 접속이 성공 하였습니다.DB종류는 : " + connection.getMetaData().getDatabaseProductName());
		//직접 쿼리를 날립니다. 날리기전 쿼리문장 객체생성 statment
		Statement stmt = connection.createStatement();
		//위 쿼리문장객체를 만드는 이유? 보안(SQL인젝션공격)
		//stmt 객체가 없으면, 개발자가 SQL인젝션 방지코딩을 넣어야합니다.
		//Insert쿼리문장 만듦(아래)
		//예전 방식으로 더미데이터(샘플데이터)를 100개를 입력합니다.
		/*
		 * for(int cnt=0;cnt<100;cnt++) {
		 * stmt.executeQuery("insert into dept02 values("+cnt+",'디자인부','경기도')"); }
		 */
		//인서트, 업데이트, 삭제시 sql디벨로퍼에서는 커밋이 될 수 있지만 
		//테이블에 입력되어있는 레코드셋을 select 쿼리 stmt문장으로 가져옴(아래)
		ResultSet rs = stmt.executeQuery("select * from dept order by deptno");
		//위에서 저장된 rs객체를 반복문으로 출력(아래)
		while(rs.next()) { 
			//rs객체의 레코드가 없을때까지 반복
			logger.debug(rs.getString("deptno")+" "+rs.getString("dname")+
					" "+rs.getString("loc"));
		}
		stmt = null;
		rs = null;
		connection = null; //메모리 초기화
	}
	
	@Test
	public void dbConnectionTest() {
		//데이터베이스 커넥션 테스트 : 설정은 root-context의 빈(스프링클래스)를 이용
		try {
			Connection connection = dataSource.getConnection();
			logger.debug("데이터베이스 접속이 성공 하였습니다.DB종류는 : " + connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			logger.debug("데이터베이스 접속이 실패 하였습니다.");
			//e.printStackTrace();
		}
		
	}
	
	@Test
	public void junitTest() {
		//로거의 장점: 조건에 따라서 출력을 조정할 수 있음.
		logger.debug("Junit테스트 시작입니다.");
	}
}
