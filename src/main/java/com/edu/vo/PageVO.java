package com.edu.vo;
/**
 * 이 클래스는 공통(회원관리, 게시물관리)으로 사용하는 페이징처리+검색처리 기능을 포함한 파일입니다.
 * @author 장효원
 * 
 * 참고로 이 클래스는 오라클, MySql(마리아DB) 등 어디서든 사용할수있는 Get/Set 로직입니다.
 * queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage
 * search_keyword, search_type
 *
 */
public class PageVO {
	private int queryStartNo; //쿼리전용 변수
	private int queryPerPageNum; //쿼리전용
	private Integer page; //jsp에서발생 자바전용. null값을 허용.
	private int perPageNum; //UI하단에 출력될 페이징 개수 계산에 필요
	private int totalCount; //계산식의 기초값으로 이 전체개수가 구해진 이후 페이징등의 계산식이 진행됩니다.
	private int startPage; //출력될 페이징의 시작번호
	private int endPage; //출력될 페이징의 끝번호
	private boolean prev; //UI하단의 이전페이지로 이동
	private boolean next; //UI하단의 다음페이지로 이동
	private String search_keyword; //jsp에서 받은 검색어 쿼리전용 변수
	private String search_type; //검색조건 쿼리전용변수
	
	public int getQueryStartNo() {
		//this.page-1의 이유: jsp에서 받아올때는 1,2,3,....으로 받지만 쿼리에서는 0,1,2,...로 받기 때문에 0으로 맞춰줌.
		queryStartNo = (this.page-1)*queryPerPageNum;
		return queryStartNo;
	}
	public void setQueryStartNo(int queryStartNo) {
		this.queryStartNo = queryStartNo;
	}
	public int getQueryPerPageNum() {
		return queryPerPageNum;
	}
	public void setQueryPerPageNum(int queryPerPageNum) {
		this.queryPerPageNum = queryPerPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		//전체개수 값을 지정한 이후 계산식 실행
		calcPage();
	}
	private void calcPage() {
		// 이 메소드는 totalCount 변수값을 기반으로 prev, next, startPage, endPage 등등을 구현하게 됩니다.
		//2 1.9 1.8 ... 1.2 1.1 => 2
		//ceil(11/10)*10 => 20페이지 == tempEnd 1-10페이지 에서 11페이지값이 존재하면, 끝페이지에 임시로 20이라는 숫자를 줍니다.
		int tempEnd = (int) Math.ceil(page/(double)this.perPageNum)*this.perPageNum; //ceil 천장 값을 지정해줌.
		//jsp에서 클릭한 페이지번호 예로 1 부터 10까지는 클릭하면, 임시 끝페이지가 10.
		//만약에 11페이지를 클릭하면, 임시 끝페이지가 20.확인 위 tempEnd변수값으로 아래내용에 이용 시작페이지를 계산하게 됩니다.
		this.startPage = (tempEnd - this.perPageNum) + 1;//UI페이지하단에 페이지번호가 출력되도록 하는 반복의 시작 변수.
		//예, 1-10까지는 page를 jsp에 클릭했을때, 시작페이지가 항상 1페이지, 하지만 11-20부터는 시작페이지가 위 계산식을 이용해서 항상 11페이지로 출력됩니다.
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
}
