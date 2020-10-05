package baekhwa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import baekhwa.domain.dto.ReplyDto;
import baekhwa.domain.dto.ReplyResponseDto;
import baekhwa.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@ResponseBody
	@PostMapping("/reply/update")
	public void update(long rno, String content) {
		service.update(rno, content);
	}
	
	@ResponseBody
	@GetMapping("/reply/delete/{rno}")
	public void delete(@PathVariable long rno) {
		service.delete(rno);
	}
	
	
	@ResponseBody
	@PostMapping("/reply/reg")
	public String reg(ReplyDto dto) {
		service.reg(dto);
		return "등록완료";
	}
	
	
	@PostMapping("/reply/list")
	public String list(long bno, Model model) {
		System.out.println(bno);
		List<ReplyResponseDto> list=service.findAllByB_no(bno);
		System.out.println(list.size());
		model.addAttribute("replylist", list);
		return "/jpa/replyList";
	}
}
