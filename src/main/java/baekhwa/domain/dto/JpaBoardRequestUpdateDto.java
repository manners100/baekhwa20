package baekhwa.domain.dto;

import lombok.Data;

@Data
public class JpaBoardRequestUpdateDto {
	
	private long no;
	private String subject;
	private String content;

}
