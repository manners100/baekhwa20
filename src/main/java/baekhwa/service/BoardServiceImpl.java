package baekhwa.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.BoardDto;
import baekhwa.domain.dto.MyPageDto;
import baekhwa.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public ModelAndView findAll(int page) {
		//return mapper.findAll();
		int limit =5;
		int offset=(page-1)*limit;
		//RowBounds 를 이용한 페이지 처리
		RowBounds rowbounds=new RowBounds(offset, limit);
		//totalPage필요
		int rowTotal=mapper.getCountOfRows();//전체 게시글수
		int pageTotal=rowTotal/limit; //13/5
		if(rowTotal%limit !=0) {
			pageTotal++;
		}
		MyPageDto myPageDto=new MyPageDto(page, pageTotal);
		
		List<BoardDto> list=mapper.findAllDesc(rowbounds);
		
		ModelAndView mv =new ModelAndView();
		mv.addObject("pageInfo", myPageDto);
		mv.addObject("boardList", list);
		
		return mv;
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
