package baekhwa.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor//default 생성자
@Data
public class FilesResponseDto {
	private long no;
	private String fileName;
	private String t_text;
	private String d_text;
	private String used;
	//FilesResponseDto(){}
	@Builder
	public FilesResponseDto(long no, String fileName, String t_text, String d_text, String used) {
		this.no = no;
		this.fileName = fileName;
		this.t_text = t_text;
		this.d_text = d_text;
		this.used = used;
	}
	
	
}
