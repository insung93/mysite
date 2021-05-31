package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session == null) {
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			MvcUtils.redirect(request.getContextPath(), request, response);
			return;
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		
		vo.setTitle(title);
		vo.setContents(content);
		vo.setUserNo(authUser.getNo());
		
		new BoardRepository().insert(vo);
		
		MvcUtils.redirect(request.getContextPath()+"/board", request, response);

	}

}
