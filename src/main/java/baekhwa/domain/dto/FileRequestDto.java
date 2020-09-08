package baekhwa.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import baekhwa.domain.entity.Files;
import lombok.Data;

@Data
public class FileRequestDto {
	
	private String t_text;
	private String d_text;
	
	private String fileName;
	//private MultipartFile fileInfo;
	
	//데이터를 저장하기위해서는 Files 로 넘겨서 저장해야한다.
	//*
	public Files toEntity() {
		//return new Files(fileName, t_text, d_text);
		return Files.builder().t_text(t_text).d_text(d_text).fileName(fileName).build();
	}
	//*/

}
