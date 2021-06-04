package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ViewRendererServlet;
import org.springframework.web.servlet.ViewResolver;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guesbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guesbookService.getMessageList();
		model.addAttribute("list",list);
		return "guestbook/index";
	}
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String add(GuestbookVo vo, Model model) {
		guesbookService.addMessage(vo);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value="/delete/{no}",method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no, Model model) {
		model.addAttribute("no",no);
		return "guestbook/delete";
	}
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable("no") Long no, @RequestParam(value="password", required=true, defaultValue="") String password) {
		guesbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
	}
//	@ExceptionHandler(Exception.class)
//	public String handlerException() {
//		// 1. logging
//		// 2. 사과 페이지 이동
//		return "error/exception";
//	}
}