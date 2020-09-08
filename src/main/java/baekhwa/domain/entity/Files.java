package baekhwa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicInsert
@NoArgsConstructor
@Getter
@SequenceGenerator(name = "seq_files_gen", sequenceName = "seq_files", initialValue = 1, allocationSize = 1)
@Table(name = "file_img")
@Entity
public class Files {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)//mysql auto_increment
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_files_gen")
	long no;
	
	@Column(nullable = false)
	String fileName;
	@Column(nullable = false)
	String t_text;
	@Column(nullable = false)
	String d_text;
	
	@Column(columnDefinition = "varchar(255) default 'on'")
	String used;
	
	@Builder
	public Files(String fileName, String t_text, String d_text) {
		this.fileName = fileName;
		this.t_text = t_text;
		this.d_text = d_text;
	}
	
	
	/*
	public static FilesBuilder builder() {
		return new FilesBuilder();
	}
	public static class FilesBuilder{
		private String filename;
		private String t_text;
		private String d_text;
		public FilesBuilder filename(String filename) {
			this.filename = filename;
			return this;
		}
		public FilesBuilder t_text(String t_text) {
			this.t_text = t_text;
			return this;
		}
		public FilesBuilder d_text(String d_text) {
			this.d_text = d_text;
			return this;
		}
		public Files build() {
			return new Files(filename,t_text,d_text);
		}
		
	}
	//*/
	
	
	
}
