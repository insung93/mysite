package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	@Autowired
	GalleryRepository galleryRepository;
	public void upload(GalleryVo vo) {
		galleryRepository.upload(vo);
	}
	public List<GalleryVo> find() {
		return galleryRepository.find();
	}
	public void delete(int no) {
		galleryRepository.delete(no);
		
	}
}
