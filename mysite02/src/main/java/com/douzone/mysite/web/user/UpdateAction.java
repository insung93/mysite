package com.douzone.mysite.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		UserVo userVo = new UserVo();
		
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);
		userVo.setEmail(email);
		
		
		UserVo updateUserVo = new UserRepository().update(userVo);
		
		userVo = new UserRepository().findByEmailAndPassword(updateUserVo.getEmail(), updateUserVo.getPassword());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);

		MvcUtils.redirect(request.getContextPath(), request, response);
	}

}
