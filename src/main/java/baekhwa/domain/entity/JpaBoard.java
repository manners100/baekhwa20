package baekhwa.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import baekhwa.domain.dto.JpaBoardRequestUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Setter
@DynamicUpdate//원본하고 비교해서 변경된 데이터만 update합니다.
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)//날짜 자동으로 입력처리하기위해..
//@SequenceGenerator(name = "seq_jboard_gernerator", sequenceName = "SEQ_JBOARD", initialValue = 1, allocationSize = 1)
@Table(name = "jpa_board")
@Entity
public class JpaBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_jboard_gernerator")
	private Long no;
	
	@Column(nullable = false)//not null
	private String subject;
	@Column(nullable = false)
	private String writer;
	@Column(nullable = false)
	private String user_ip;
	
	@Column(columnDefinition ="text" ,nullable = false)
	//@Column(columnDefinition ="CLOB" ,nullable = false)
	private String content;
	
	private int count;
	
	@CreatedDate //데이터가 처음 만들어질때 자동생성
	private LocalDateTime reg_date;
	
	@Builder
	public JpaBoard(String subject, String writer, String user_ip, String content) {
		this.subject = subject;
		this.writer = writer;
		this.user_ip = user_ip;
		this.content = content;
	}
	
	
	public JpaBoard update(JpaBoardRequestUpdateDto update) {
		
		this.subject = update.getSubject();
		this.content = update.getContent();
		
		return this;
	}
	
	
	
	//@LastModifiedDate //데이터가 수정이 되면 자동으로 수정일 자동생성
	//private LocalDateTime edit_date;
	
	
}
