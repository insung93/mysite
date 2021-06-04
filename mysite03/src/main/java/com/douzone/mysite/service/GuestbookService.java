package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.View;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	GuestbookRepository guestbookRepository;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}
	public void deleteMessage(Long no, String password) {
		guestbookRepository.delete(no,password);
	}
	public void addMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
