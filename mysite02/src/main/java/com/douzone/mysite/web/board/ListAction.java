package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		totalPage
//		firstPageNo
//		lastPageNo 
//		nextPageNo
//		prevPageNo
//		currentPage 
//		Map m = new Map();
//		map.put("firstPageNo",firstPageNo)
//		request.setAttribute("pageInfo", map);
		List<BoardVo> list = new BoardRepository().findAll();

		// 2. request 범위에 데이터(객체) 저장
		request.setAttribute("list", list);
		
		
		
		MvcUtils.forward("board/list", request, response);

	}

}
