package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String depth = request.getParameter("depth");
		String groupNo = request.getParameter("groupNo");
		new BoardRepository().deleteBoard(no,depth,groupNo);
		
		MvcUtils.redirect(request.getContextPath()+"/board", request, response);
	}

}
