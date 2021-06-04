package com.douzone.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET) 
	public String join() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println(vo);
		return "redirecty:/user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			HttpSession session,
			@RequestParam(value = "email", required = true, defaultValue = "") String email,
			@RequestParam(value = "password", required = true, defaultValue = "") String password, Model model) {
		System.out.println(email + ":" + password);
		UserVo authUser = userService.getUser(email, password);
		if (authUser == null) {
			model.addAttribute("result", "fail");
			model.addAttribute("email", email);
			return "user/login";
		}
		session.setAttribute("authUser", authUser);
		System.out.println(authUser);
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		UserVo authUser = (UserVo)session.getAttribute("authuser");
		//접근제어
		if(authUser == null) {
			return "redirect:/";
		}
		//로그아웃 처리
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
}
