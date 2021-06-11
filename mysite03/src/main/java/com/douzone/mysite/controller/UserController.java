package com.douzone.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError error : list) {
//				System.out.println(error);
//			}
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		// userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String login(HttpSession session,
//			@RequestParam(value = "email", required = true, defaultValue = "") String email,
//			@RequestParam(value = "password", required = true, defaultValue = "") String password, Model model) {
//		System.out.println(email + ":" + password);
//		UserVo authUser = userService.getUser(email, password);
//		if (authUser == null) {
//			model.addAttribute("result", "fail");
//			model.addAttribute("email", email);
//			return "user/login";
//		}
//		session.setAttribute("authUser", authUser);
//		System.out.println(authUser);
//		return "redirect:/";
//	}
//	
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		// 접근제어
//		if (authUser == null) {
//			return "redirect:/";
//		}
//		// 로그아웃 처리
//		session.removeAttribute("authUser");
//		session.invalidate();
//		return "redirect:/";
//	}
//	
	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, UserVo userVo) {
		userService.updateUser(userVo);
		authUser.setName(userVo.getName());

		return "redirect:/user/update";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model) {
		Long no = authUser.getNo();
		UserVo userVo = userService.getUser(no);

		model.addAttribute("user", userVo);
		return "user/update";
	}

//	@Auth
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String update(HttpSession session, UserVo userVo) {
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		// 접근제어
//		if (authUser == null) {
//			return "redirect:/";
//		}
//		userVo.setNo(authUser.getNo());
//		userService.updateUser(userVo);
//		authUser.setName(userVo.getName());
//
//		return "redirect:/user/update";
//	}
//	
//	@Auth
//	@RequestMapping(value = "/update", method = RequestMethod.GET)
//	public String update(HttpSession session, Model model) {
//		// 접근제어
//		UserVo authUser = (UserVo) session.getAttribute("authUser");
//		if (authUser == null) {
//			return "redirect:/";
//		}
//
//		Long no = authUser.getNo();
//		UserVo userVo = userService.getUser(no);
//
//		model.addAttribute("user", userVo);
//		return "user/update";
//	}

}
