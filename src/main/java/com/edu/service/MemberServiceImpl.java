package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_MemberDAO;
import com.edu.vo.MemberVO;

/**
 * 이 클래스는 회원관리서비스 인터페이스의 구현내용을 포함한 파일입니다.
 * 상속 extends, 구현 implements 키워드를 사용.
 * 스프링빈으로 등록하려면, @Service 에너테이션을 명시.
 * @author 장효원
 *
 */
@Service
public class MemberServiceImpl implements IF_MemberService {
	@Inject //IF_MemberDAO를 주입해서 객체로 사용(아래)
	private IF_MemberDAO memberDAO;

	@Override
	public List<MemberVO> selectMember() throws Exception {
		// 인테페이스에서 상속받은 메소드를 구현(아래)
		return memberDAO.selectMember();
	}

}
