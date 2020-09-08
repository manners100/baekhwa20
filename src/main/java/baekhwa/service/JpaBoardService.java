package baekhwa.service;

import java.util.List;

import baekhwa.domain.dto.JpaBoardRequestDto;
import baekhwa.domain.dto.JpaBoardRequestUpdateDto;
import baekhwa.domain.dto.JpaBoardResponseDto;

public interface JpaBoardService {

	void save(JpaBoardRequestDto dto);

	List<JpaBoardResponseDto> findAll();

	JpaBoardResponseDto findById(Long no);

	void delete(long no);

	void update(JpaBoardRequestUpdateDto update);

}
