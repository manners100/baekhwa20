package baekhwa.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.BoardDto;

public interface BoardService {

	ModelAndView findAll(int page);

	void save(BoardDto dto);

	BoardDto findById(int no);

	void edit(BoardDto dto);

	void delete(int no);



}
