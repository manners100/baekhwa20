package baekhwa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baekhwa.domain.dto.BoardDto;
import baekhwa.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public List<BoardDto> findAll() {
		return mapper.findAll();
	}

	@Override
	public void save(BoardDto dto) {
		dto.setUser_ip( request.getRemoteAddr() );
		
		
		mapper.save(dto);
	}

	@Override
	public BoardDto findById(int no) {
		
		return mapper.findById(no);
	}

	@Override
	public void edit(BoardDto dto) {
		
		mapper.edit(dto);
	}

	@Override
	public void delete(int no) {
		mapper.delete(no);
		
	}

}
