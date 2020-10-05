package baekhwa.service;

import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.UserRequestDto;

public interface UserService {

	ModelAndView save(UserRequestDto dto);

	ModelAndView login(UserRequestDto dto);

	void logout();

}
