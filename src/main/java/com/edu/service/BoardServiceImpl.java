package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardDAO;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 DAO메소드를 호출하는 기능을 합니다.
 * @author 장효원
 *
 */
@Service //애노테이션을 붙이면 스프링빈으로 등록 됨.
public class BoardServiceImpl implements IF_BoardService {
	
	@Inject
	private IF_BoardDAO boardDAO;
	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// TODO 첨부파일 List형으로 조회하는 DAO호출
		return boardDAO.readAttach(bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// TODO 페이징 처리시 PageVO의 totalCount변수에 사용될 값을 리턴값으로 받음.
		return boardDAO.countBoard(pageVO);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// TODO 게시물 삭제할때 3개의 메소드가 실행(댓글+첨부파일 삭제 -> 게시물이 삭제됨)
		//트랜젝션이 필요한 순간의 작업순서: 1. 첨부파일 삭제 완료, 2. 게시물 삭제에서 에러가 나서 삭제가 안됨.
		//위와 같은 상황을 방지하는 목적의 기능:  @Transantional
		//특이사항: 첨부파일DB만 삭제해서 해결 + 실제 업로드한 파일을 삭제가 필요(나중에 추가)
		boardDAO.deleteAttachAll(bno);
		//댓글 삭제는 나중에...
		boardDAO.deleteBoard(bno);
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// TODO 첨부파일이 있으면 updateAttach -> updateViewCount
		boardDAO.updateAttach(null);
		boardDAO.updateBoard(boardVO);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// TODO 게시물 상세보기시 실행순서 readBoard -> updateViewCount 2개의 메소드가 필요
		BoardVO boardVO = boardDAO.readBoard(bno);
		boardDAO.updateViewCount(bno);
		return boardVO;
	}

	@Override
	   public void insertBoard(BoardVO boardVO) throws Exception {
	      // TODO [모]게시물 insertBoard->[자]첨부파일 있으면 첨부파일 insertAttach
	      //게시물 등록 + 반환값으로 bno를 추가
	      int bno = boardDAO.insertBoard(boardVO);
	      //첨부파일 등록
	      String[] save_file_names=boardVO.getSave_file_names();//폴더에 저장 될 파일명들
	      String[] real_file_names=boardVO.getReal_file_names();//UI용 배열 파일명들
	      if(save_file_names == null) {return;}
	      //첨부파일이 null이 아닐때 아래 실행.
	      int index = 0;
	      String real_file_name = "";//UI용 1개의 파일명
	      AttachVO attachVO = new AttachVO();
	      for(String save_file_name:save_file_names) { //첨부파일 갯수만큼 반복진행
	    	  real_file_name = real_file_names[index];
	    	  attachVO.setBno(bno);
	    	  attachVO.setReal_file_name(real_file_name);
	    	  attachVO.setSave_file_name(save_file_name);
	    	  boardDAO.insertAttach(attachVO);
	      }
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// TODO DAO 1개만 호출하면 됨.
		return boardDAO.selectBoard(pageVO);
	}

}
