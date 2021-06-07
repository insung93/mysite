package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@RequestMapping("")
	public String index() {
		return "board/list";
	}
	
	@RequestMapping(value="/write",method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
}
