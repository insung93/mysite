package com.douzone.mysite.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Auth
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping("")
	public String index(Model model, @RequestParam(value = "page", required = true, defaultValue = "1") int page) {
		int displayRow = 10;
		List<BoardVo> list = boardService.findByPage(page,displayRow);
		HashMap<String, Integer> map = boardService.paging(page,displayRow);
		model.addAttribute("pageInfo",map);
		model.addAttribute("list", list);
		return "board/list";
	}
	@RequestMapping(value="/view",	method = RequestMethod.GET)
	public String view(@RequestParam("no") Long no , Model model) {
		BoardVo viewBoard = boardService.findAny(no);
		model.addAttribute("list", viewBoard);
		return "board/view";
	}
	@RequestMapping(value="/delete/{no}",method = RequestMethod.GET)
	public String delete(@PathVariable("no") Long no , Model model) {
		boardService.delete(no);
		return "redirect:/board";
	}
	@RequestMapping(value="/modify",method = RequestMethod.GET)
	public String modify(@RequestParam("no") Long no,Model model) {
		BoardVo boardvo = boardService.findAny(no);
		model.addAttribute("no",no);
		model.addAttribute("list", boardvo);
		return "board/modify";
	}
	@RequestMapping(value="/modify/{no}",method = RequestMethod.POST)
	public String modify(@PathVariable("no") Long no, BoardVo vo) {
		vo.setNo(no);
		boardService.modify(vo);
		return "redirect:/board";
	}
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo, 
						@RequestParam("no") Long no) {
		vo.setUserNo(authUser.getNo());
		boardService.insert(vo,no);
		return "redirect:/board";
	}
	@RequestMapping(value="/search")
	public String search(@RequestParam("combo") String combo,
						@RequestParam("kwd") String kwd, 
						@RequestParam(value = "page", required = true, defaultValue = "1") int page, Model model ) {
		int displayRow = 10;
		List<BoardVo> list = boardService.findByKwd(combo,kwd,page,displayRow);
		HashMap<String, Integer> map = boardService.pagingByKwd(combo,kwd,page,displayRow);
		model.addAttribute("pageInfo",map);
		model.addAttribute("list", list);
		model.addAttribute("kwd",kwd);
		model.addAttribute("combo",combo);
		return "board/list";
	}
}