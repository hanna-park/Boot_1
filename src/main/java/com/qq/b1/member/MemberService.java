package com.qq.b1.member;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qq.b1.util.FilePathGenerator;
import com.qq.b1.util.FileSaver;

@Service
public class MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberFilesMapper memberFilesMapper;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private FilePathGenerator filePathGenerator;
	@Autowired
	private FileSaver fileSaver;
	
	
	public MemberVO memberLogin(MemberVO memberVO) throws Exception{
		return memberMapper.memberLogin(memberVO);
	}
	
	
	public int memberJoin(MemberVO memberVO, MultipartFile files) throws Exception{
		File file= filePathGenerator.getUseClassPathResource("upload");
		String fileName = fileSaver.save(file, files);
		System.out.println(fileName);
		int result= memberMapper.memberJoin(memberVO);
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setFname(fileName);
		memberFilesVO.setId(memberVO.getId());
		memberFilesVO.setOname(fileName);
		result = memberFilesMapper.memberFilesInsert(memberFilesVO);
		
		return result; 

	}
	
	
}
