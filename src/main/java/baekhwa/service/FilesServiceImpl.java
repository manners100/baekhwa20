package baekhwa.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import baekhwa.domain.dto.FileRequestDto;
import baekhwa.domain.dto.FilesResponseDto;
import baekhwa.domain.entity.Files;
import baekhwa.domain.entity.FilesRepository;

@Service
public class FilesServiceImpl implements FilesService{
	@Autowired
	private FilesRepository repository;
	
	@Override
	public void save(FileRequestDto dto) {
		
		repository.save(dto.toEntity());
		
	}

	@Override
	public List<FilesResponseDto> findAll() {
		Sort sort=Sort.by(Direction.ASC, "no");
		
		List<FilesResponseDto> list=new Vector<>();
		List<Files> result=repository.findAll(sort);
		
		for(Files file : result) {
			FilesResponseDto dto=
					//new FilesResponseDto(file.getNo(), file.getFileName(), file.getT_text(), file.getD_text(), file.getUsed() );
					FilesResponseDto.builder()
					.fileName(file.getFileName()).no(file.getNo())
					.t_text(file.getT_text()).d_text(file.getD_text()).used(file.getUsed())
					.build();
			list.add(dto);
			//FilesResponseDto dto=new FilesResponseDto();
			//dto.setFileName(file.getFileName());
			//dto.setT_text(file.getT_text());
			//dto.setD_text(file.getD_text());
			//dto.setUsed(file.getUsed());
		}
		
		return list;
	}

}
