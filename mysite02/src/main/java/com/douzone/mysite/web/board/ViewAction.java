package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no") ;
		
		BoardVo viewBoard = new BoardRepository().findAny(no);

		// 2. request 범위에 데이터(객체) 저장
		request.setAttribute("list", viewBoard);
		
		MvcUtils.forward("board/view", request, response);
	}

}
