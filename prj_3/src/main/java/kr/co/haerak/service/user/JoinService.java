package kr.co.haerak.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.user.UserDAO;
import kr.co.haerak.vo.user.UserDupVO;
import kr.co.haerak.vo.user.UserVO;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class JoinService {

	@Autowired
	private UserDAO uDAO;
	
	public int joinService(UserVO uVO) throws UnsupportedEncodingException, GeneralSecurityException {
		uVO.setUserPassword(DataEncrypt.messageDigest("MD5", uVO.getUserPassword()));//��ȣȭ �� �н����带 �ٽ� �����ϱ�

		DataEncrypt de = new DataEncrypt("Tkddydgangnamkong");//��ȣȭ Ű
		
		uVO.setEmail(uVO.getEmail()+"@"+uVO.getEmail2());//�̸��� �ϳ��� ��ġ��
		
		uVO.setEmail( de.encryption( uVO.getEmail()) );//�̸��� �ּ� ��ȣȭ�ϱ�
		
		int cnt = uDAO.insertMember(uVO);
		return cnt;
	}//joinService
	
	public int idDupService(String userId) {
		boolean flag=uDAO.idDup(userId);//false�� ���̵� ��� ����
		int cnt = 0;
		if(!flag)cnt=1;
		
		
		return cnt;
	}//idDupService
	
	public int nickDupService(String nickname) {
		boolean flag=uDAO.nickDup(nickname);//false�� �г��� ��� ����
		int cnt = 0;
		if(!flag)cnt=1;
		
		
		return cnt;
	}//nickDupService
	
	public String userDupService(UserDupVO udVO) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataEncrypt de = new DataEncrypt("Tkddydgangnamkong");//��ȣȭ Ű
		
		udVO.setEmail(udVO.getEmail()+"@"+udVO.getEmail2());//�̸��� �ϳ��� ��ġ��
		
		udVO.setEmail( de.encryption( udVO.getEmail()) );//�̸��� �ּ� ��ȣȭ�ϱ�
		
		String userId = uDAO.userDup(udVO);
		
		return userId;
	}//userDupService
	
}//class
