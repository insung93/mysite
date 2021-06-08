package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;

	public int getCount() {
		return boardRepository.countAll();
	}

	public List<BoardVo> findByPage(int page, int displayRow) {
		return boardRepository.findByPage(page, displayRow);
	}

	public BoardVo findAny(Long no) {
		boardRepository.hitUpdate(no);
		return boardRepository.findAny(no);
	}

	public void delete(Long no) {
		boardRepository.delete(no);
	}

	public void modify(BoardVo vo) {
		boardRepository.modify(vo);
	}

	public HashMap<String, Integer> paging(int page, int displayRow) {
		int count = getCount();

		int currentPage, totalPage, lastPageNo, firstPageNo, leftPage, rightPage;
		currentPage = page;
		int nextPageNo = currentPage + 1;
		int prevPageNo = currentPage - 1;

		leftPage = currentPage - 2;
		rightPage = currentPage + 2;
		totalPage = (int) Math.ceil((double) count / displayRow);
		if (leftPage < 2) {
			leftPage = 1;
			rightPage = 5;
		}
		if (rightPage > totalPage) {
			rightPage = totalPage;
			leftPage = totalPage - 4;
		}
		if (totalPage == 1) {
			lastPageNo = 1;
		} else {
			lastPageNo = totalPage;
		}
		if (totalPage < 5) {
			leftPage = 1;
			rightPage = totalPage;
		}
		firstPageNo = 1;

		if (totalPage == 0) {
			leftPage = 1;
			rightPage = 1;
			lastPageNo = 1;
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage", currentPage);
		map.put("lastPageNo", lastPageNo);
		map.put("nextPageNo", nextPageNo);
		map.put("prevPageNo", prevPageNo);
		map.put("totalPage", totalPage);
		map.put("firstPageNo", firstPageNo);
		map.put("totalCount", count);
		map.put("leftPage", leftPage);
		map.put("rightPage", rightPage);
		map.put("displayRow", displayRow);

		return map;
	}

	public void insert(BoardVo vo, Long no) {
		if (no == null) {
			boardRepository.insertNew(vo);
		} else {
			BoardVo parentsvo = boardRepository.findAny(no);
			boardRepository.updateOrderNo(parentsvo.getGroupNo(), parentsvo.getOrderNo());
			vo.setGroupNo(parentsvo.getGroupNo());
			vo.setOrderNo(parentsvo.getOrderNo() + 1);
			vo.setDepth(parentsvo.getDepth() + 1);
			boardRepository.insertComment(vo);
		}

	}

	public List<BoardVo> findByKwd(String combo, String kwd, int page, int displayRow) {
		return boardRepository.findByKwd(combo, kwd, page, displayRow);
	}

	public HashMap<String, Integer> pagingByKwd(String combo, String kwd, int page, int displayRow) {
		int count = getKwdCount(combo, kwd);
		int currentPage, totalPage, lastPageNo, firstPageNo, leftPage, rightPage;
		currentPage = page;
		int nextPageNo = currentPage + 1;
		int prevPageNo = currentPage - 1;

		leftPage = currentPage - 2;
		rightPage = currentPage + 2;
		totalPage = (int) Math.ceil((double) count / displayRow);
		if (leftPage < 2) {
			leftPage = 1;
			rightPage = 5;
		}
		if (rightPage > totalPage) {
			rightPage = totalPage;
			leftPage = totalPage - 4;
		}
		if (totalPage == 1) {
			lastPageNo = 1;
		} else {
			lastPageNo = totalPage;
		}
		if (totalPage < 5) {
			leftPage = 1;
			rightPage = totalPage;
		}
		firstPageNo = 1;

		if (totalPage == 0) {
			leftPage = 1;
			rightPage = 1;
			lastPageNo = 1;
		}

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage", currentPage);
		map.put("lastPageNo", lastPageNo);
		map.put("nextPageNo", nextPageNo);
		map.put("prevPageNo", prevPageNo);
		map.put("totalPage", totalPage);
		map.put("firstPageNo", firstPageNo);
		map.put("totalCount", count);
		map.put("leftPage", leftPage);
		map.put("rightPage", rightPage);
		map.put("displayRow", displayRow);

		return map;
	}

	private int getKwdCount(String combo, String kwd) {
		return boardRepository.getKwdCount(combo, kwd);
	}
}
