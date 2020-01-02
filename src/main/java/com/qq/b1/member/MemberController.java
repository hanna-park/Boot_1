package com.qq.b1.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member/**")
public class MemberController {

	@Autowired
	private MemberService memberservice;
	
	@GetMapping("memberLogin")
	public void memberLogin() throws Exception{
		
	}
	
	@PostMapping("memberLogin")
	public ModelAndView memberLogin(MemberVO memberVO,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberservice.memberLogin(memberVO);
		
		String msg = "로그인 실패";

		if(memberVO != null) {
			msg="로그인 성공";
			session.setAttribute("member", memberVO);
		}
		
		String path="../";
		
		mv.setViewName("common/result");
		mv.addObject("msg",msg);
		mv.addObject("path",path);
		
		return mv;
	}
	
	
	@GetMapping("memberJoin")
	public String memberJoin() throws Exception{
		return "member/memberJoin";
	}
	
	@PostMapping("memberJoin")
	public ModelAndView memberJoin(MemberVO memberVO,MultipartFile files) throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberservice.memberJoin(memberVO, files);
		
		String msg = "Join Fail";
		String path = "../";
		
		if(result>0) {
			msg= "Join Success";
		}
		
		mv.setViewName("common/result");
		mv.addObject("msg",msg);
		mv.addObject("path",path);
		
		return mv;
	}
	
}
