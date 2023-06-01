package kr.co.haerak.controller.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import kr.co.haerak.service.user.InquiryService;
import kr.co.haerak.vo.user.FindIdVO;
import kr.co.haerak.vo.user.FindPassVO;
@SessionAttributes("lsDomain")
@Controller
public class InquiryController {
	
	@Autowired
	private InquiryService is;
	
	@GetMapping("/find_id.do")
	public String findIdForm() {
		
		return "user/id_inquiry";
	}//findIdForm
	
	@GetMapping("/find_pass.do")
	public String findPassForm() {
		
		return "user/pass_inquiry";
	}//findPassForm
	
	@PostMapping("id_inquiry_process.do")
	public String findIdProcess(FindIdVO fiVO, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String uri="user/succ_id_inquiry";
		
		String userId=is.idInquiryService(fiVO);
		if("".equals(userId)||userId==null){// ����� ������
			model.addAttribute("flag", 1);
			uri="user/id_inquiry";
		}else {
			model.addAttribute("userId", userId);
		}//end else
		
		
		return uri;
	}//findIdProcess

	@PostMapping("pass_inquiry_process.do")
	public String findpassProcess(FindPassVO fpVO, Model model) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String uri="mypage/edit_pass";
		
		String userId=is.passInquiryService(fpVO);
		if("".equals(userId)||userId==null){// ����� ������
			model.addAttribute("flag", 1);
			uri="user/pass_inquiry";
		}else {//������ userId�� ��Ƽ� pass_inqury��
			model.addAttribute("userId", userId);
		}//end else
		
		
		return uri;
	}//findIdProcess
	
}//class
