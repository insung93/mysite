package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.GalleryService;
import com.douzone.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	@Autowired
	GalleryService galleryService;
	@Autowired
	FileUploadService fileuploadService;

	@RequestMapping("")
	public String index(Model model) {
		List<GalleryVo> list = galleryService.find();
		System.out.println(list);
		model.addAttribute("list",list);
		return "gallery/index";
	}

	@RequestMapping("/upload")
	public String upload(GalleryVo vo, @RequestParam("file") MultipartFile file, Model model) {
		String url = fileuploadService.restore(file);
		model.addAttribute("url", url);
		vo.setUrl(url);
		galleryService.upload(vo);
		return "redirect:/gallery";
	}

	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable(value = ("no")) int no) {
		galleryService.delete(no);
		return "redirect:/gallery";
	}

}