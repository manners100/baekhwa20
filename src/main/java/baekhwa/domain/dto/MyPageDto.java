package baekhwa.domain.dto;

import lombok.Data;

@Data
public class MyPageDto{

	private int page;
	private int size;
	private int total;
	
	private int from;
	private int to;
	
	public MyPageDto(int page, int total) {
		this.page=page;
		size=10;
		this.total=total;
		
		int pageBlockNo= page/size; // 0 :1,2,3,4, 1:5
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
