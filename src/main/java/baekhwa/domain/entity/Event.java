package baekhwa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import baekhwa.domain.dto.EventListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@NoArgsConstructor
@Getter
@Table(name = "event_list")
@Entity
public class Event {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	private Long no;
	
	@Column(nullable = false)//not null
	private String l_text;
	@Column(nullable = false)
	private String t_text;
	@Column(nullable = false)
	private String b_text;
	
	@Column(nullable = false)
	private String l_url;
	@Column(nullable = false)
	private String t_url;
	@Column(nullable = false)
	private String b_url;
	
	@Column(columnDefinition = "varchar(255) default 'on'")
	private String used;
	//null일때 쿼리에 포함하지 않으면 default값인  on 저장된다.: @DynamicInsert
	
	@Builder
	public Event(String l_text, String t_text, String b_text, String l_url, String t_url, String b_url) {
		this.l_text = l_text;
		this.t_text = t_text;
		this.b_text = b_text;
		this.l_url = l_url;
		this.t_url = t_url;
		this.b_url = b_url;
	}
	
	public Event usedToggle() {
		if(used.equals("on")) {
			used="off";
		}else if(used.equals("off")) {
			used="on";
		}
		return this;
	}

	public void setNo(Long no) {
		this.no = no;
	}
	public Event update(EventListDto dto) {
		this.l_text=dto.getL_text();
		this.t_text=dto.getT_text();
		this.b_text=dto.getB_text();
		
		if(dto.getL_url()!=null)
			this.l_url = dto.getL_url();
		
		if(dto.getT_url()!=null)
			this.t_url = dto.getT_url();
		
		if(dto.getB_url()!=null)
			this.b_url = dto.getB_url();
		
		return this;
	}
	
	public Event updateText(String l_text, String t_text, String b_text) {
		this.l_text=l_text;
		this.t_text=t_text;
		this.b_text=b_text;
		
		return this;
	}
	
	
	

}
