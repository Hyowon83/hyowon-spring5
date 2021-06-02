package com.edu.test;


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
	
	@Test
	public void junitTest() {
		//로거의 장점: 조건에 따라서 출력을 조정할 수 있음.
		logger.debug("Junit테스트 시작입니다.");
	}
}
