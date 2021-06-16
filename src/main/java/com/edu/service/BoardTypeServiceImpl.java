package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardTypeDAO;
import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 DAO클래스에 접근하기 위한 서비스 클래스입니다.
 * @author JARVIS
 *
 */
@Service //애노테이션을 붙여야만 스프링빈에 등록이 됩니다.
public class BoardTypeServiceImpl implements IF_BoardTypeService {
	@Inject
	private IF_BoardTypeDAO boardTypeDAO;
	
	@Override
	public void deleteBoardType(String board_type) throws Exception {
		// DAO클래스객체를 이용해서 메소드를 호출.
		boardTypeDAO.deleteBoardType(board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// DAO클래스객체를 이용해서 메소드를 호출.
		boardTypeDAO.updateBoardType(boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// DAO클래스객체를 이용해서 메소드를 호출. 실행결과반환readBoardType(아래실행에 필요한 변수=매개변수,파라미터,인자,아규먼트)
		return boardTypeDAO.readBoardType(board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// DAO클래스객체를 이용해서 메소드를 호출.
		boardTypeDAO.insertBoardType(boardTypeVO);
	}

	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		// DAO클래스객체를 이용해서 메소드를 호출.
		return boardTypeDAO.selectBoardType();
	}
	
}
