package com.edu.service;

import java.util.List;

import com.edu.vo.MemberVO;

/**
 * 이 인터페이스는 회원관리에 관련된 Service의 명세가 포함된 파일입니다.
 * 인터페이스라서 {구현내용} 없음.
 * @author 장효원
 *
 */
public interface IF_MemberService {
	public List<MemberVO> selectMember() throws Exception;

}
