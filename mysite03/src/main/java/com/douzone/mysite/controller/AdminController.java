package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private FileUploadService fileuploadService;
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping("")
	public String main() {
		List<SiteVo> list = siteService.findAll();
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
