package baekhwa.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import baekhwa.domain.dto.BoardDto;
import baekhwa.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board/delete/{no}")
	public String delete(@PathVariable int no) {
		service.delete(no);
		return "redirect:/board/list";
	}
	
	@PostMapping("/board/edit")
	public String edit(BoardDto dto) {
		
		//db
		service.edit(dto);
		
		return "redirect:/board/"+dto.getNo();
	}
	
	@GetMapping("/board/{no}")
	public String detail(@PathVariable int no, Model model) {
		//DB에서서 no의 데이터갖고오기
		
		BoardDto dto=service.findById(no);
		model.addAttribute("dto", dto);
		
		//페이지 이동
		return "/board/detail";
	}
	
	@PostMapping("/board/write")
	public String write(BoardDto dto) {
		service.save(dto);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "/board/write";
	}
	
	@GetMapping("/board/list")
	public String board(Model model) {
		//DB에서 데이터 갖고오기 (select ~~~~ 실행후 결과리턴)
		List<BoardDto> list=service.findAll();
		
		//ModelAndView mv=new ModelAndView("/board/list");
		//mv.addObject("boardList", list);
		//list.html페이지에서 데이터를 읽을수있는 (이름,value)
		model.addAttribute("boardList", list);
		//날짜 오늘인지 체크
		model.addAttribute("today", LocalDate.now());
		
		//페이지 이동
		return "/board/list";
	}

}
