package com.edu.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

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
	private DataSource dataSource; //Inject로 객체를 만들면 메모리 관리를 스프링이 대신해줌.
	//Inject는 자바8부터 지원. 이전 자바7에서는 @Autowired로 객체를 만들었음.
	@Inject //MemberService서비스를 주입받아서 객체를 사용합니다.(아래)
	private IF_MemberService memberService;
	@Inject
	private IF_BoardService boardService;
	
	@Test
	public void insertBoard() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("인서트 후 반환값테스트");
		boardVO.setContent("JUnit 입력 테스트");
		boardVO.setWriter("admin");
		boardService.insertBoard(boardVO);
	}
	
	@Test
	public void updateMember() throws Exception {
		//이 메소드는 회원정보를 수정하는 기능.jsp에서 사용예정
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("admin@test.com");
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		memberVO.setPoint(100);
		memberVO.setUser_name("최고관리자");
		memberVO.setUser_pw(""); //입력하지 않으면 업데이트에서 제외됩니다.
		//메소드 내에서 적용된 객체변수 생성(아래)
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//스프링5 시큐리티 암호화를 적용하는 로직(아래)
		if((memberVO.getUser_pw()).length() > 0) {
			String userPwEncoder = passwordEncoder.encode(memberVO.getUser_pw());
			memberVO.setUser_pw(userPwEncoder); //암호화된 해시데이터가 memberVO 객체에 임시저장됨.
		}
		memberVO.setUser_id("admin"); //수정 조회조건에 사용.
		memberService.updateMember(memberVO);
		// =========== 여기까는 jsp에서 1명의 회원만 업데이트(수정)할때 사용하는 로직
		// =========== 이후 부터는 모든회원중의 시큐리티암호화가 되지않는 사용자만 암호만 업데이트하는 로직
		//아래수정 call호출을 회원수만큼 반복을 해야합니다.(아래)
		PageVO pageVO = new PageVO();
		pageVO.setPage(1); //기본값으로 1페이지를 줍니다.
		pageVO.setPerPageNum(10); //UI하단에서 사용되는 페이지 개수
		pageVO.setQueryPerPageNum(1000); //쿼리에서 사용되는 페이지당 개수
		//MemberVO타입을 가진 리스트형 객체 List<MemberVO>
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		//향상된 for반복문 (memberOne:listMember) {구현내용}
		for(MemberVO memberOne:listMember) { //listMember객체가 비워질때까지 반복
			//혹시 실수로 여러번 실행시켜서 이중암호화를 시킬수있으므로 데이터 크기가 일정이상이 넘어가면 실행되지 않게 제외조건을 추가 한다.(아래)
			String rawPassword = memberOne.getUser_pw();
			if(rawPassword.length() < 10) { //원시암호데이터 길이가 10보다 작을때만 암호화로직 진입
				//memberOne이라는 객체(1개 레코드)의 암호를 뽑아서 시큐리티로 암호화 시킨 후 onePwEncoder라는 변수를 입력.
				String onePwEncoder = passwordEncoder.encode(rawPassword);
				memberOne.setUser_pw(onePwEncoder);
				memberService.updateMember(memberOne); //1명(admin만) 수정해도 모든 회원 업데이트
			}
		}
		selectMember();
	}
	
	@Test
	public void readMember() throws Exception {
		//이 메소드는 회원 상세보기(1개의 레코드) jsp에 사용할 예정
		MemberVO memberVO = new MemberVO();
		//100명 중 1명을 보려면 고유키(기본키,주키,PK)가 필요. user_id
		memberVO.setUser_id("admin");
		memberVO = memberService.readMember(memberVO.getUser_id());
	}
	
	@Test
	public void deleteMember() throws Exception {
		memberService.deleteMember("user_del");
	}
	@Test
	public void insertMember() throws Exception {
		MemberVO memberVO = new MemberVO();
		//insert쿼리에 저장할 객체를 만듭니다.
		memberVO.setUser_id("user_del");
		memberVO.setUser_pw("1234"); //스프링 시큐리티 중 암호화로 처리예정
		memberVO.setEmail("user@test.com");
		memberVO.setPoint(10);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_USER");
		memberVO.setUser_name("삭제할 사용자");
		memberService.insertMember(memberVO);
		selectMember();
	}
	
	//스프링 코딩 시작 순서(리드미로 옮겨놓음)
	@Test
	public void selectMember() throws Exception {
		//이 메소드가 회원 관리 테이블에서 더미로 입력한 100개의 레코드를 출력하는 메소드를 테스트하면 -> 회원관리 목록이 출력됨.
		//현재 100명 검색기능, 페이징기능 여기서 구현, 1페이지에 10명씩.
		//현재 몇페이지, 검색어 임시저장 공간 -> DB에 페이징 조건문, 검색조건문
		//변수를 2-3이상은 바로 String 변수로 처리하지 않고, VO만들어서 사용.
		//PageVO.java 클래스를 만들어서 페이징처리변수와 검색어변수 선언, get/set 생성
		//PageVO 만들기 전에 SQL쿼리로 가상페이지를 구현해보면서 필요한 변수를 만들어야합니다.
		//PageVO 객체를 만들어서 가상으로 초기값을 입력합니다.
		PageVO pageVO = new PageVO();
		pageVO.setPage(1); //기본값으로 1페이지를 줍니다.
		pageVO.setPerPageNum(10); //UI하단에서 사용되는 페이지 개수
		pageVO.setQueryPerPageNum(1000); //쿼리에서 사용되는 페이지당 개수
		//pageVO.setTotalCount(memberService.countMember(pageVO)); //테스트를 위해 카운트를 입력해줌. 하드코딩. 다른 설정보다 상단에 위치할때 에러남.
		//startPage와 endPage의 계산에 위의 토탈카운트 변수값이 필수입니다.
		/*
		pageVO.setSearch_type("user_id"); //검색타입: all, user_id, user_name
		pageVO.setSearch_keyword("user_del");
		*/
		//매퍼쿼리_DAO클래스_Service클래스_JUnit(나중엔컨트롤러에서 작업)했는데 이제는 역순으로 작업 할 예정
		//더 진행하기 전에 현재 pageVO객체에는 어떤값이 들어있는지 확인하고 사용.(아래)
		logger.info("pageVO저장된 값 확인: "+pageVO.toString());
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		listMember.toString();
	}
	
	@Test
	public void oldQueryTest() throws Exception {
		//스프링빈을 사용하지 않을 때 예전 방식: 코딩테스트에서는 스프링 설정을 안쓰고, 직접 DB아이디/암호 입력		
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE","apmsetup");
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
