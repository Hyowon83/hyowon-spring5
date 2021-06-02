package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 이클래스는 admin관리자단을 접근하는 클래스입니다.
 * 변수 Object를 만들어서 jsp로 전송 + jsp에서 폼값을 받아서 Object로 처리
 * @author 장효원
 *
 */
@Controller
public class AdminController {
	
	//URL요청 경로=리퀘스트맵핑 는 반드시 *절대경로*로 표시.
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin(Model model) throws Exception { //에러 발생시 Exception클래스의 정보를 스프링으로 보내게 됩니다.
		
		//아래 상대경로에서 /WEB-INF/views/폴더가 루트(최상위)입니다. prefix(접두어)처리 되어있어서 생략가능.
		//아래 상대경로 home.jsp에서 .jsp는 suffix(접미어)처리되어있어서 생략가능. 
		return "admin/home"; //return 경로=접근경로 는 반드시 *상대경로*로 표시.
	}
}
