package baekhwa.service;

import java.util.List;

import baekhwa.domain.dto.EventListDto;
import baekhwa.domain.dto.EventRequestDto;
import baekhwa.domain.dto.EventResponseDto;

public interface EventService {

	void save(EventRequestDto dto);

	List<EventListDto> listAll();

	List<EventResponseDto> listAllByUsed();

	void used(Long no);

	void delete(Long no);

	void edit(EventListDto dto);

}
