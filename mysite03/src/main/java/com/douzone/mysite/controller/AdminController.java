package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role = "ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private FileUploadService fileuploadService;

	@Autowired
	private SiteService siteService;

	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.find();
		model.addAttribute("siteVo", siteVo);
		return "admin/main";
	}

	@RequestMapping(value = "/main/update", method = RequestMethod.POST)
	public String updateMain(SiteVo vo, @RequestParam("file1") MultipartFile file1, Model model) {
		String url = fileuploadService.restore(file1);
		model.addAttribute("url", url);
		vo.setProfile(url);
		siteService.update(vo);
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
