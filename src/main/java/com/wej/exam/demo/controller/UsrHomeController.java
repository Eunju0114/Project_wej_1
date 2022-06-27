package com.wej.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {

	private int count;
	
	public UsrHomeController() {
		count = -1;
	}
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4() {
		count ++;
		return count;
	}
	
	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public String showMain5() {
		count = -1;
		return "count 값이 초기화 되었습니다.";
	}
}
