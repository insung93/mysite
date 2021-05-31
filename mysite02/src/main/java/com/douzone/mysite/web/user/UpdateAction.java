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
		HttpSession session = request.getSession();
		if(session == null) {
			return;
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		
		UserVo userVo = new UserVo();
		
		userVo.setNo(authUser.getNo());
		userVo.setName(name);
		userVo.setPassword(password);
		userVo.setGender(gender);
		userVo.setEmail(email);
		
		
		Boolean result = new UserRepository().update(userVo);
		if(result) {
			authUser.setName(name);
		}

		MvcUtils.redirect(request.getContextPath(), request, response);
	}

}
