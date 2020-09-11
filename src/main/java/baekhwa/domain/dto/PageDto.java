package baekhwa.domain.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import baekhwa.domain.entity.JpaBoard;
import lombok.Data;

@Data
public class PageDto<T> {
	
	private long page;
	private long size;
	private long total;
	
	private long from;
	private long to;
	
	
	public PageDto(Page<T> resultPage) {
		page=resultPage.getNumber()+1;
		size=10;
		total=resultPage.getTotalPages();
		
		long pageBlockNo= page/size; // 0 :1,2,3,4, 1:5
		if( 0 != page%size) {
			pageBlockNo++;
		}
		
		to=pageBlockNo*size;
		from=to-size+1;
		
		if(to>total) {
			to=total;
		}
	}
	

}
