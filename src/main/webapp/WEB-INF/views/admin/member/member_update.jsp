<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원정보 수정</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">관리자관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 컨텐츠 내용 -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">수정</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <form name="form_write" action="/admin/member/member_update" method="post" enctype="multipart/form-data">
            <!-- get방식은 검색, post방식은 글쓰기 로그인 등.(get방식하면 입력한 비밀번호가 주소에 뜸) -->
            <!-- enctype="multipart/form-data": 첨부파일을 전송할 때 필수로 들어가야 함. -->
            <div class="card-body">
              
              <div class="form-group">
                <label for="user_id">사용자ID</label>
                <input readonly value="${memberVO.user_id}" name="user_id" type="text" class="form-control" id="user_id" placeholder="회원ID를 입력해주세요." required>
              </div>
              <div class="form-group">
                <label for="user_pw">사용자암호</label>
                <!-- 암호는 기존값이 필요없음: 값이 있으면 업데이트 진행, 없으면 제외함. -->
                <input value="" name="user_pw" type="password" id="user_pw" class="form-control" placeholder="비밀번호를 입력해주세요.">
              </div>
              <div class="form-group">
                <label for="user_name">사용자이름</label>
                <input value="${memberVO.user_name}" name="user_name" type="text" id="user_name" class="form-control" placeholder="이름을 입력해주세요." required>
              </div>
              <div class="form-group">
                <label for="email">이메일</label>
                <input value="${memberVO.email}" name="email" type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요." required>
              </div>
              <div class="form-group">
                <label for="point">포인트</label>
                <input value="${memberVO.point}" name="point" type="number" class="form-control" id="point" placeholder="포인트를 입력해주세요." required>
              </div>
              <div class="form-group">
                <label for="enabled">로그인 승인여부</label>
                  <select name="enabled" id="enabled" class="form-control">
                    <option value="1" ${memberVO.enabled==true?'selected':''} >허용</option>
                    <option value="0" ${memberVO.enabled==false?'selected':''}>금지</option>
                  </select>
              </div>
              <div class="form-group">
                <label for="levels">권한부여</label>
                  <select name="levels" id="levels" class="form-control">
                    <option value="ROLE_USER" ${memberVO.levels=='ROLE_USER'?'selected':''}>사용자</option>
                    <option value="ROLE_ADMIN" ${memberVO.levels=='ROLE_ADMIN'?'selected':''}>관리자</option>
                  </select>
              </div>
            </div>
            <!-- /.card-body -->
            
            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-info" id="btn_prev">이전</button>
              <button type="button" class="btn btn-default" id="btn_list">목록</button>
            </div>
            <input name="page" type="hidden" value="${pageVO.page}">
            <input name="search_type" type="hidden" value="${pageVO.search_type}">
            <input name="search_keyword" type="hidden" value="${pageVO.search_keyword}"> 
          </form>
        </div>
        <!-- //컨텐츠 내용 -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp" %>
<!-- 관리자단은 jQuery코어가 하단 footer에 있기 때문에 푸터 아래에 위치한다. -->
<script>
$(document).ready(function() {
	var form_update = $("form[name='form_write']")
	$("#btn_prev").click(function() {
		form_update.attr("action","/admin/member/member_view");
		form_update.attr("method","get");
		form_update.submit();
	});
	$("#btn_list").click(function() {
		form_update.attr("action","/admin/member/member_list");
		form_update.attr("method","get");
		form_update.submit();
	});
});
</script>