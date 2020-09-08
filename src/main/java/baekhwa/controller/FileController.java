package baekhwa.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import baekhwa.domain.dto.FileRequestDto;
import baekhwa.domain.dto.FilesResponseDto;
import baekhwa.service.FilesService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class FileController {
	@Autowired
	private FilesService service;
	
	@GetMapping("/file/event")
	public String fileEvent(Model model) {
		//DB에서 listdata갖고오기
		List<FilesResponseDto> list=service.findAll();
		model.addAttribute("list", list);
		
		return "/files/list";
	}
	
	@GetMapping("/file/reg")
	public String fileReg() {
		return "/files/reg";
	}
	
	@PostMapping("/file/reg")
	public String fileReg(FileRequestDto dto, MultipartFile fileInfo) throws IllegalStateException, IOException {
		String fileName=fileInfo.getOriginalFilename();
		dto.setFileName(fileName);
		//file:/D:/spring/work/baekhwa/bin/main/static/upload
		ClassPathResource cr=new ClassPathResource("static/upload");
		//log.debug(cr.getURI());
		File dir=cr.getFile();
		//bin폴더기준입니다.
		
		//파일업로드
		//new File(디렉토리,파일이름)
		File dest=new File(dir,fileName);
		//log.debug(dest.getPath());
		fileInfo.transferTo(dest);
		
		log.debug(dto);
		//DB에 dto저장
		
		service.save(dto);
		
		return "redirect:/file/event";//request url mapping 
	}

}
