package baekhwa.service;

import java.util.List;

import baekhwa.domain.dto.FileRequestDto;
import baekhwa.domain.dto.FilesResponseDto;

public interface FilesService {

	void save(FileRequestDto dto);

	List<FilesResponseDto> findAll();

}
