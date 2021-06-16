package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 게시판생성관리 쿼리의 인터페이스를 구현 메소드가 포함된 클래스입니다.
 * @author 장효원
 *
 */
@Repository
public class BoardTypeDAOImpl implements IF_BoardTypeDAO {
	//sqlSession 템플릿 의존성을 주입합니다.
	@Inject //자바8부터 나온 기능.
	private SqlSession sqlSession;
	
	@Override
	public void deleteBoardType(String board_type) throws Exception {
		// sqlSession템플릿을 이용해서 매퍼쿼리를 실행.
		sqlSession.delete("boardTypeMapper.deleteBoardType", board_type);
		//서식 sqlSession.~템플릿 메서드("SQL쿼리위치", )
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// sqlSession템플릿을 이용해서 매퍼쿼리를 실행.
		sqlSession.update("boardTypeMapper.updateBoardType", boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		// sqlSession템플릿을 이용해서 매퍼쿼리를 실행.
		return sqlSession.selectOne("boardTypeMapper.readBoardType", board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		// sqlSession템플릿을 이용해서 매퍼쿼리를 실행sqlSession템플릿을 이용.
		sqlSession.insert("boardTypeMapper.insertBoardType", boardTypeVO);
		
	}

	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		//sqlSession템플릿을 이용해서 매퍼쿼리를 실행.
		return sqlSession.selectList("boardTypeMapper.selectBoardType");
	}
	
}
