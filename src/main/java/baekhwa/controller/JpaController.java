package baekhwa.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import baekhwa.domain.dto.JpaBoardRequestDto;
import baekhwa.domain.dto.JpaBoardRequestUpdateDto;
import baekhwa.domain.dto.JpaBoardResponseDto;
import baekhwa.service.JpaBoardService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class JpaController {
	
	@Autowired
	private JpaBoardService service;
	
	@PostMapping("/jpa/edit")
	public String edit(JpaBoardRequestUpdateDto update) {
		log.debug(update);
		service.update(update);
		return "redirect:/jpa/list";
	}
	
	@GetMapping("/jpa/delete/{no}")
	public String delete(@PathVariable long no ) {
		
		service.delete(no);
		
		return "redirect:/jpa/list";
	}
	
	@GetMapping("/jpa/{no}")
	public String detail(@PathVariable Long no, Model model) {
		//DB no의 게시글 정보를 갖고옵시다.
		JpaBoardResponseDto dto=service.findById(no);
		//MVC 
		model.addAttribute("dto", dto);
		
		return "/jpa/detail";//페이지이동
	}
	
	@PostMapping("/jpa/write")
	public String write(JpaBoardRequestDto dto, HttpServletRequest request) {
		dto.setUser_ip(request.getRemoteAddr() );//user_ip 셋
		log.debug(dto);
		
		service.save(dto);
		
		return "redirect:/jpa/list";
	}
	
	@GetMapping("/jpa/list")
	public String list(Model model) {
		//페이지에 갖고갈 데이터 갖고작업을
		//누구한테 시킬까요?
		List<JpaBoardResponseDto> list=service.findAll();
		model.addAttribute("jpaList", list);
		
		return "/jpa/list";//페이지 이동
	}
	
	@GetMapping("/jpa/write")
	public String write() {
		return "/jpa/write";
	}
}
