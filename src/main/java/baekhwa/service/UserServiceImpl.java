package baekhwa.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.LogInfo;
import baekhwa.domain.dto.UserRequestDto;
import baekhwa.domain.entity.User;
import baekhwa.domain.entity.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Override
	public ModelAndView save(UserRequestDto dto) {
		//존재하는 email이 존재하는지 체크
		//존재하지 않으면이면 .orElse(객체) 객체를 생성시킨다.
		Optional<User> op=repository.findByEmail(dto.getEmail());
		ModelAndView mv=new ModelAndView();
		if(op.isPresent()) {//이미 존재한다.
			mv.addObject("log_msg", "이미 존재하는 id입니다.!");
		}else {
			repository.save(dto.toEntity());
			mv.addObject("welcome_msg", "회원가입을 환영합니다!</br>"+dto.getEmail()+"계정으로 가입되었습니다.");
		}
		return mv;
		
	}
	
	@Autowired
	HttpSession session;
	
	@Override
	public ModelAndView login(UserRequestDto dto) {
		Optional<User> op=repository.findByEmailAndPw(dto.getEmail(), dto.getPw());
		System.out.println(session);
		ModelAndView mv=new ModelAndView();
		if(op.isPresent()) {
			// 존재
			//User user=op.get();
			// 세션에 로그인정보 등록
			session.setAttribute("user", new LogInfo(op.get()));
			// 페이지이동 index페이지로..
			mv.setViewName("redirect:/");
		}else {
			// 비회원
			// 메세지를 날려주자
			mv.addObject("log_msg", "회원이 아니거나 비밀번호가 다릅니다!");
			// 페이지이동 로그인페이지로.. 다시 입력할수있도록 
			mv.setViewName("/sign/login");
		}
		/*
		User user=repository.findByEmailAndPw(dto.getEmail(), dto.getPw()).orElse(null);
		if(user!=null) {
			//회원존재
		}else {
			//비회원
		}
		*/
		return mv;
	}

	@Override
	public void logout() {
		session.removeAttribute("user");
	}

}
