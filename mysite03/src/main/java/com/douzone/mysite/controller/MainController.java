package com.douzone.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;
import com.douzone.mysite.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo siteVo = siteService.find();
		model.addAttribute("siteVo",siteVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping("/msg1")
	public String message1() {
		return "안녕~~";
	}
	@ResponseBody
	@RequestMapping("/msg2")
	public UserVo message2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setEmail("aaa@aaa.com");
		return vo;
	}
}
