package baekhwa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.UserRequestDto;
import baekhwa.service.UserService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/user/logout")
	public String logout() {
		service.logout();
		return "redirect:/";
	}
	
	@PostMapping("/user/login")
	public ModelAndView login(UserRequestDto dto) {
		//ModelAndView mv=service.login(dto);
		return service.login(dto);
	}
	
	
	@GetMapping("/user/login")
	public String login() {
		return "/sign/login";
	}
	
	@GetMapping("/user/signup")
	public String join() {
		return "/sign/join";
	}
	
	@PostMapping("/user/signup")
	public ModelAndView join(UserRequestDto dto) {
		//log.debug(dto);
		ModelAndView mv=service.save(dto);
		mv.setViewName("/sign/login");
		return mv;
	}
}
