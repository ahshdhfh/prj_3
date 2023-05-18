package kr.co.haerak.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.user.UserDAO;
import kr.co.haerak.vo.user.FindIdVO;
import kr.co.haerak.vo.user.FindPassVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class InquiryService {
	@Autowired
	private UserDAO uDAO;
	
	
	public String idInquiryService(FindIdVO fiVO) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String userId="";
		
		//�̸��� ��ȣȭ�Ͽ� �ֱ�
		DataEncrypt de = new DataEncrypt("Tkddydgangnamkong");//��ȣȭ Ű
		
		fiVO.setEmail(fiVO.getEmail()+"@"+fiVO.getEmail2());//�̸��� �ϳ��� ��ġ��
		fiVO.setEmail( de.encryption( fiVO.getEmail()) );//�̸��� �ּ� ��ȣȭ�ϱ�
		
		uDAO.selectFindId(fiVO);
		
		return userId;
	}//idInquiryService
	
	public String passInquiryService(FindPassVO fpVO) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		String userId="";
		
		//�̸��� ��ȣȭ�Ͽ� �ֱ�
		DataEncrypt de = new DataEncrypt("Tkddydgangnamkong");//��ȣȭ Ű
		
		fpVO.setEmail(fpVO.getEmail()+"@"+fpVO.getEmail2());//�̸��� �ϳ��� ��ġ��
		fpVO.setEmail( de.encryption( fpVO.getEmail()) );//�̸��� �ּ� ��ȣȭ�ϱ�
		
		uDAO.selectFindPass(fpVO);
		
		return userId;
	}//idInquiryService
	
}
