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
	
	@RequestMapping("/usr/home/getCount")
	@ResponseBody
	public int getCount() {
		count ++;
		return count;
	}
	
	@RequestMapping("/usr/home/setCount")
	@ResponseBody
	public String setCount(int count) {
		this.count = count;
		return "count 값이 " + this.count + "으로 초기화 되었습니다.";
	}
}
