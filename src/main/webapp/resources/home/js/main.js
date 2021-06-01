$(document).ready(function() {
    $(".to_top a").smoothScroll();

    // 모바일용 메뉴 보이기/숨기기 액션 처리
	$('.openMOgnb').click(function() {
		// alert('메뉴보이기 액션 클릭');
		// $('.header_cont').css('display','block');
		$('.header_cont').slideDown("slow");
		$('#header').addClass('on');
	});
	$('.closePop').click(function() {
		// $('.header_cont').css('display', 'none');
		$('.header_cont').slideUp("fast");
		$('#header').removeClass('on');
	});
});

// PC용 대메뉴 마우스 롤아웃 이벤트 만들기(아래)
// 첫번째 서브메뉴: .gnb_depth2_1 마우스 롤아웃상태를 처리
//기본은 마우스 off상태로 해둠
var isOver1 = false; // 1차메뉴 숨김처리 플래그
var isOverSub1 = false; // 2차메뉴 숨김처리 플래그
function goHide1() {
	if(!isOver1 && !isOverSub1) { // 두개의 변수 모두 false일 때 아래내용 실행
		// jQuery는 뒤에서 앞으로 해석(영어와 같음)
		$('.gnb_depth2_1').stop().fadeOut('fast');
		// 대메뉴중 1번의 2차메뉴 영역
	}	
}
// 두번째 서브메뉴: .gnb_depth2_2 마우스 롤아웃 상태를 처리  
var isOver2 = false; // 1차메뉴 숨김처리 플래그
var isOverSub2 = false; // 2차메뉴 숨김처리 플래그
function goHide2() {
	if(!isOver2 && !isOverSub2) {
		$('.gnb_depth2_2').stop().fadeOut('fast');
	}
}
//  PC용 마우스롤오버 이벤트 처리부분(아래)
$(document).ready(function() {
	// [대메뉴1번]의 마우스 오버이벤트 => .gnb_depth2_1 서브메뉴 보이기(아래)
	$('.openAll1').mouseover(function(){
		// alert($('header').css('width'));
		// parseInt 문자를 숫자로 변환하는 형변환 함수
		if(parseInt($('header').css('width')) >=1049) { // header태그의 가로크기가 1049 이상이라면 함수실행
			$('.gnb_depth2_1').fadeIn('fast');
		}
		isOver1 = true;
	});
	// [대메뉴1번]의 마우스 아웃이벤트 => .gnb_depth2_1 서브메뉴 숨기기(아래)
	$('.openAll1').mouseout(function(){
		isOver1 = false;
		setTimeout('goHide1()',200); // 0.2마다 함수를 실행해서 서브메뉴를 숨김처리
		// goHide()만 써도 되지만 mouseout 이벤트를 놓칠수도 있기 때문에 setTumeout() 사용.
	});
	// [서브메뉴]의 마우스 오버이벤트
	$('.gnb_depth2_1').mouseover(function(){
		isOverSub1 = true;
	});
	// [서브메뉴]의 마우스 아웃이벤트
	$('.gnb_depth2_1').mouseout(function(){
		isOverSub1 = false;
		setTimeout('goHide1()',200); // 0.2마다 함수를 실행해서 서브메뉴를 숨김처리
	});
// ========================================================
	// [대메뉴2번]의 마우스 오버이벤트 => .gnb_depth2_2 서브메뉴 보이기(아래)
	$('.openAll2').mouseover(function(){
		if(parseInt($('header').css('width')) >=1049) {
			$('.gnb_depth2_2').fadeIn('fast');
		}
		isOver2 = true;
	});
	// [대메뉴2번]의 마우스 아웃이벤트 => .gnb_depth2_2 서브메뉴 숨기기(아래)
	$('.openAll2').mouseout(function(){
		isOver2 = false;
		setTimeout('goHide2()',200);
	});
	// [서브메뉴]의 마우스 오버이벤트
	$('.gnb_depth2_2').mouseover(function(){
		isOverSub2 = true;
	});
	// [서브메뉴]의 마우스 아웃이벤트
	$('.gnb_depth2_2').mouseout(function(){
		isOverSub2 = false;
		setTimeout('goHide2()',200); // 0.2마다 함수를 실행해서 서브메뉴를 숨김처리
	});
});