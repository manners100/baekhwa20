package baekhwa.service;

import java.util.List;

import baekhwa.domain.dto.BoardDto;

public interface BoardService {

	List<BoardDto> findAll();

	void save(BoardDto dto);

	BoardDto findById(int no);

	void edit(BoardDto dto);

	void delete(int no);



}
