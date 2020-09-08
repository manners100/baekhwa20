package baekhwa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import baekhwa.domain.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//mapper-board.xml 의 <select id="findAll"> id와 메서드 이름이 일치...
	List<BoardDto> findAll();

	void save(BoardDto dto);

	BoardDto findById(int no);

	void edit(BoardDto dto);

	void delete(int no);

}
