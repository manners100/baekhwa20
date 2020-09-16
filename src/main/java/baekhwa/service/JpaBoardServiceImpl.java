package baekhwa.service;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import baekhwa.domain.dto.JpaBoardRequestDto;
import baekhwa.domain.dto.JpaBoardRequestUpdateDto;
import baekhwa.domain.dto.JpaBoardResponseDto;
import baekhwa.domain.dto.MyPageDto;
import baekhwa.domain.dto.PageDto;
import baekhwa.domain.entity.JpaBoard;
import baekhwa.domain.entity.JpaBoardRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JpaBoardServiceImpl implements JpaBoardService{
	
	@Autowired
	private JpaBoardRepository repository;
	
	@Override
	public void save(JpaBoardRequestDto dto) {
		
		repository.save( dto.toEntity() );
		
	}

	@Override
	public ModelAndView findAll(int page) {
		long start=System.nanoTime();
		//int page=0;//첫페이지
		int size=10;//페이지에 게시될 게시글수
		Sort sort=Sort.by(Direction.DESC, "no");//정렬방법,정렬요소
		
		Pageable pageable=PageRequest.of(page-1, size, sort);
		Page<JpaBoard> resultPage=repository.findAll(pageable);
		
		//PageDto<JpaBoard> pageDto=new PageDto<>(resultPage);
		MyPageDto pageDto=new MyPageDto(page, resultPage.getTotalPages());
		//log.debug("size :"+resultPage.getSize());
		log.debug("page-tot :"+resultPage.getTotalPages());
		//log.debug("getNumber :"+resultPage.getNumber());
		//log.debug("getNumberOfElements :"+resultPage.getNumberOfElements());
		//log.debug(resultPage.isFirst());
		//log.debug(resultPage.isLast());
		//log.debug(resultPage.hasNext());
		//log.debug(resultPage.hasPrevious());
		
		
		//page에서 List<JpaBoard> 얻어오기
		List<JpaBoard> result=resultPage.getContent();
		//create collection<JpaBoardResponseDto>
		List<JpaBoardResponseDto> list=new Vector<>();
		for(JpaBoard entity : result) {
			//result에저장된 entity정보를 JpaBoardResponseDto 변경후
			JpaBoardResponseDto dto=new JpaBoardResponseDto(entity);
			//List<JpaBoardResponseDto> 에 저장
			list.add(dto);
		}
		ModelAndView mv =new ModelAndView();
		mv.addObject("jpaList", list);
		mv.addObject("pageInfo", pageDto);
		long end=System.nanoTime();
		System.out.println(end-start+ "namo초");
		return mv;
	}

	@Override
	public JpaBoardResponseDto findById(Long no) {
		
		JpaBoard result=repository.findById(no).orElse(null);
		//DB결과가 Entity객체이므로 Dto로 데이터 변경하여 리턴하자..
		JpaBoardResponseDto dto=new JpaBoardResponseDto(result);
		
		return dto;
	}

	@Override
	public void delete(long no) {

		repository.deleteById(no);
		
	}

	@Transactional
	@Override
	public void update(JpaBoardRequestUpdateDto update) {
		//JpaBoard result=repository.findById(update.getNo()).orElse(null);
		//result.setSubject(update.getSubject());
		//result.setContent(update.getContent());
		//repository.save(result);
		//setter가 없으므로  update()를 만들어서 처리한다.
		repository.findById(update.getNo())
					.map(result->result.update(update))
					.orElse(null);
		
	}

}
