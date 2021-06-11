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
	private int queryPerPageNum; //쿼리전용, 페이징 쿼리에서 1페이지당 출력할 개수를 정하는 변수
	private Integer page; //jsp에서발생, 선택한 페이지 번호 변수. 자바전용. null값을 허용.
	private int perPageNum; //UI하단에 출력될 페이징 개수 계산에 필요
	private int totalCount; //계산식의 기초값으로 이 전체개수가 구해진 이후 페이징등의 계산식이 진행됩니다.
	private int startPage; //출력될 페이징의 시작번호
	private int endPage; //출력될 페이징의 끝번호
	private boolean prev; //UI하단의 이전페이지로 이동
	private boolean next; //UI하단의 다음페이지로 이동
	private String search_keyword; //jsp에서 받은 검색어 쿼리전용 변수
	private String search_type; //검색조건 쿼리전용변수
	
	
	
	@Override
	public String toString() {
		return "PageVO [queryStartNo=" + queryStartNo + ", queryPerPageNum=" + queryPerPageNum + ", page=" + page
				+ ", perPageNum=" + perPageNum + ", totalCount=" + totalCount + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", search_keyword=" + search_keyword
				+ ", search_type=" + search_type + "]";
	}
	public int getQueryStartNo() {
		//this.page-1의 이유: jsp에서 받아올때는 1,2,3,....으로 받지만 쿼리에서는 0,1,2,...로 받기 때문에 0으로 맞춰줌.
		queryStartNo = this.page-1; //오라클은 가능하지만 mysql에서는 쿼리를 수정해야한다.
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
		//임의값으로 계산(아래)
		//UI하단의 페이지 번호:  <(비활성) | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | >(활성-링크값 endPage+1)
		//UI하단의 페이지 번호:  <(활성) | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | >(활성-링크값 endPage+1)
		//위 상상대로 진행하면 전체개수는 101개 이상이 됩니다.
		//ceil(11/10)*10 => 20페이지 == tempEnd 1-10페이지 에서 11페이지값이 존재하면, 끝페이지에 임시로 20이라는 숫자를 줍니다.
		int tempEnd = (int) Math.ceil(page/(double)this.perPageNum)*this.perPageNum; //ceil 천장 값을 지정해줌.
		//jsp에서 클릭한 페이지번호 예로 1 부터 10까지는 클릭하면, 임시 끝페이지가 10.
		//만약에 11페이지를 클릭하면, 임시 끝페이지가 20.확인 위 tempEnd변수값으로 아래내용에 이용 시작페이지를 계산하게 됩니다.
		this.startPage = (tempEnd - this.perPageNum) + 1;//UI페이지하단에 페이지번호가 출력되도록 하는 반복의 시작 변수.
		//예, 1-10까지는 page를 jsp에 클릭했을때, 시작페이지가 항상 1페이지, 하지만 11-20부터는 시작페이지가 위 계산식을 이용해서 항상 11페이지로 출력됩니다.
		//위 startPage변수는 jsp에서 반복문의 시작값으로 사용. 지금 토탈개수는 101개 이상
		if (tempEnd*this.queryPerPageNum > this.totalCount) {
			this.endPage = (int)Math.ceil(this.totalCount/(double)this.queryPerPageNum);
			//위 계산식을 예를들면, (101/10) = 10.1 -> Math.ceil 값으로 11이 됨. (엔드페이지)
		}else {
			this.endPage = tempEnd;
		}
		//--------여기까지가 startPage와 endPage를 구하는 계산식
		//여기부터는 prev, next를 구하는 계산식
		//UI하단의 페이지 번호:  <(비활성) | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | >(활성-링크값 11페이지endPage+1)
		this.prev = (this.startPage != 1); //boolean형인 prev는 참(1) 혹은 (거짓), startPage가 1페이지가 아닐때만 prev활성화.
		this.next = (this.endPage*this.queryPerPageNum) < this.totalCount;
		//끝번호*현재페이지개수가 전체개수보다 작다면 next활성화. (10*10)<101
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
