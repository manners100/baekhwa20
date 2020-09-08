package baekhwa.service;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baekhwa.domain.dto.JpaBoardRequestDto;
import baekhwa.domain.dto.JpaBoardRequestUpdateDto;
import baekhwa.domain.dto.JpaBoardResponseDto;
import baekhwa.domain.entity.JpaBoard;
import baekhwa.domain.entity.JpaBoardRepository;

@Service
public class JpaBoardServiceImpl implements JpaBoardService{
	
	@Autowired
	private JpaBoardRepository repository;
	
	@Override
	public void save(JpaBoardRequestDto dto) {
		
		repository.save( dto.toEntity() );
		
	}

	@Override
	public List<JpaBoardResponseDto> findAll() {
		
		List<JpaBoard> result=repository.findAll();
		
		//create collection<JpaBoardResponseDto>
		List<JpaBoardResponseDto> list=new Vector<>();
		for(JpaBoard entity : result) {
			//result에저장된 entity정보를 JpaBoardResponseDto 변경후
			JpaBoardResponseDto dto=new JpaBoardResponseDto(entity);
			//List<JpaBoardResponseDto> 에 저장
			list.add(dto);
		}
		
		return list;
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
