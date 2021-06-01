package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.HashMap;
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
		
		int count = new BoardRepository().countAll();
		int displayRow,displayPage,currentPage,totalPage,lastPageNo,firstPageNo, leftPage,rightPage;
		if(request.getParameter("page")==null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		displayRow = 5;
		displayPage = 10;
		leftPage = currentPage-2;
		rightPage = currentPage+2;
		totalPage = (int) Math.ceil((double)count/displayRow);
		if(leftPage<2) {
			leftPage = 1;
			rightPage = 5;
		}
		if(rightPage>totalPage) {
			rightPage = totalPage;
			leftPage = totalPage-4;
		}
		
		
		if(totalPage == 1 ) {
			lastPageNo = 1;
		} else {
			lastPageNo= totalPage;
		}
		firstPageNo=1;
		int nextPageNo=currentPage+1;
		int prevPageNo=currentPage-1;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage",currentPage);
		map.put("lastPageNo", lastPageNo);
		map.put("nextPageNo", nextPageNo);
		map.put("prevPageNo", prevPageNo);
		map.put("totalPage",totalPage);
		map.put("firstPageNo", firstPageNo);
		map.put("totalCount", count);
		map.put("leftPage", leftPage);
		map.put("rightPage", rightPage);
		
		request.setAttribute("pageInfo", map);
		System.out.println(map);
		//List<BoardVo> list = new BoardRepository().findAll();
		List<BoardVo> list = new BoardRepository().findByPage(currentPage,displayRow);
		
		// 2. request 범위에 데이터(객체) 저장
		request.setAttribute("list", list);
		
		
		
		MvcUtils.forward("board/list", request, response);

	}

}
