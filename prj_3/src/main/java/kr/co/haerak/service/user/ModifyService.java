package kr.co.haerak.service.user;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.user.UserDAO;
import kr.co.haerak.domain.user.ModifyInfoDomain;
import kr.co.haerak.domain.user.ModifyProfileDomain;
import kr.co.haerak.vo.user.ChkIdPassVO;
import kr.co.haerak.vo.user.ModifyInfoVO;
import kr.co.haerak.vo.user.ModifyPassVO;
import kr.co.haerak.vo.user.ModifyProfileVO;
import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.util.cipher.DataEncrypt;

@Component
public class ModifyService {
	
	@Autowired(required = false)
	private UserDAO uDAO;
	
	public boolean passChkService(ChkIdPassVO cipVO) throws NoSuchAlgorithmException {
		boolean flag = false;
		//��ȣȭ�� ������� ����
		cipVO.setUserPassword(DataEncrypt.messageDigest("MD5", cipVO.getUserPassword()));
		
		flag=uDAO.selectChkUserInfo(cipVO);
		
		return flag;// true�� ��� 
	}//passChkService
	
	public ModifyProfileDomain setProfileService(String userId) {
		ModifyProfileDomain mpDomain= uDAO.selectProfile(userId);
		
		return mpDomain;
	}//ModifyProfileDomain
	
	
	/**
	 * ������ ����
	 */
	public int modifyProfileService(ModifyProfileVO mpVO) {
		int cnt=uDAO.updateProfile(mpVO);
		
		return cnt;
	}//modifyProfileService
	
	public ModifyInfoDomain setInfoService(String userId) throws NoSuchAlgorithmException, UnsupportedEncodingException, GeneralSecurityException {
		ModifyInfoDomain mpDomain= uDAO.selectInfo(userId);
		
		//�̸��� ��ȣȭ �ϱ�
		DataDecrypt dd = new DataDecrypt("Tkddydgangnamkong");
		mpDomain.setEmail(dd.decryption(mpDomain.getEmail()));
		
		//�̸��� �ڸ���
		String email = mpDomain.getEmail().substring(0, mpDomain.getEmail().indexOf("@"));
		String email2 = mpDomain.getEmail().substring(mpDomain.getEmail().indexOf("@")+1);
		
		//set�ϱ�
		mpDomain.setEmail(email);
		mpDomain.setEmail2(email2);
		
		return mpDomain;
	}//ModifyInfoDomain
	
	public int modifyInfoService(ModifyInfoVO mpVO) {
		int cnt=uDAO.updateInfo(mpVO);
		
		return cnt;
	}//modifyInfoService
	
	
	public int modifyPassService(ModifyPassVO mpVO) throws NoSuchAlgorithmException {
		String dPass = DataEncrypt.messageDigest("MD5", mpVO.getNewPass());
		mpVO.setNewPass(dPass);
		int cnt = uDAO.updatePass(mpVO);
		
		return cnt;
	}//modifyPassService
	
	public int deleteUserService(String userId) {
		int cnt= uDAO.deleteUser(userId);
		return cnt;
	}//deleteUserService
	
}//class
