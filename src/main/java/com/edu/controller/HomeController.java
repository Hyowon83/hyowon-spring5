package com.edu.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * 이 클래스는 MVC웹 프로젝트(스프링)를 최초로 생성시 자동으로 생성되는 클래스입니다.
 * 웹브라우저의 요청사항을 view단(jsp)에 연결시켜주는 클래스가 @Controller
 * 스프링에서 관리하는 클래스를 스프링빈(콩) = 스프링빈을 명시 @Controller 애노테이션
 * Beans(콩들) 그래프로 이 프로젝트의 규모 확인 가능
 * 스프링이 관리하는 클래스는 파일아이콘에 S가 붙는다.
 */
                        
@Controller
public class HomeController {
	//스프링빈(클래스)에서는 logger로 디버그 합니다. ( = 로거 객체를 만든다)
	private Logger logger = Logger
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
