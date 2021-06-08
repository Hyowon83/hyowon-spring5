package com.edu.dao;

import java.util.List;

import com.edu.vo.MemberVO;

/**
 * 이 인터페이스는 회원관리에 관련된 CRUD 메소드 명세가 포함된 파일입니다.
 * 이 인터페이스는 메소드명만 있고 {구현내용}은 없는 목록파일입니다.
 * @author 장효원
 *
 */
public interface IF_MemberDAO {
	//List<제네릭타입>형: MemberVO의 1개 레코드를 List라는 클래스형태로 감싸줘서 여러개의 레코드를 저장 할 수 있게 함.
	public List<MemberVO> selectMember() throws Exception; //인터페이스라 Exception뒤에 {}가 없음.
}
