package com.douzone.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("")
	public String main() {
		return "admin/main";
	}
	@RequestMapping(value="/main/update", method = RequestMethod.POST)
	public String updateMain(SiteVo vo) {
		return "redirect:/admin";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
}
