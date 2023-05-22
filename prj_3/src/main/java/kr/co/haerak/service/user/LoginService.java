package kr.co.haerak.service.user;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.user.UserDAO;
import kr.co.haerak.domain.user.LoginSessionDomain;
import kr.co.haerak.vo.user.LoginVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class LoginService {
	@Autowired(required = false)
	private UserDAO uDAO;
	
	
	public LoginSessionDomain loginService(LoginVO lVO) throws NoSuchAlgorithmException {
		LoginSessionDomain lsDomain=null;
		//��ȣȭ �ϱ�
		
		if(!uDAO.selectChkUser(lVO.getUserId())) {// Ż���� ȸ������ Ȯ���ϱ�
			lVO.setUserPassword(DataEncrypt.messageDigest("MD5", lVO.getUserPassword()));
			lsDomain=uDAO.selectLogin(lVO);
		}//end if
		
		return lsDomain;
	}//loginService
}//class
