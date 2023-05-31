package kr.co.haerak.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.co.haerak.dao.admin.AdminDAO;
import kr.co.haerak.domain.admin.AdminDomain;
import kr.co.haerak.vo.admin.AdminVO;

@Controller
public class AdminLoginService {
	
	//������ �α���
	@Autowired(required = false)
	private AdminDAO aDAO;
	
	//�α��� �޼ҵ� ����
	public AdminDomain loginCheckService(AdminVO aVO) {
		AdminDomain lcs=aDAO.loginCheck(aVO);
		 
		return lcs;
	}
	 
	 
}//class
