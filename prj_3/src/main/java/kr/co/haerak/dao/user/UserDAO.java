package kr.co.haerak.dao.user;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.MyBatisHandler;
import kr.co.haerak.domain.user.LoginSessionDomain;
import kr.co.haerak.domain.user.ModifyInfoDomain;
import kr.co.haerak.domain.user.ModifyProfileDomain;
import kr.co.haerak.vo.user.ChkIdPassVO;
import kr.co.haerak.vo.user.FindIdVO;
import kr.co.haerak.vo.user.FindPassVO;
import kr.co.haerak.vo.user.LoginVO;
import kr.co.haerak.vo.user.ModifyInfoVO;
import kr.co.haerak.vo.user.ModifyPassVO;
import kr.co.haerak.vo.user.ModifyProfileVO;
import kr.co.haerak.vo.user.UserDupVO;
import kr.co.haerak.vo.user.UserVO;

@Component
public class UserDAO {

	public int insertMember(UserVO uVO) {
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		int cnt = ss.insert("kr.co.haerak.user_mapper.insertMember", uVO);
		// 3. transaction�Ϸ�
		if (cnt == 1) {
			ss.commit();
		}
		// 4. �������
		if (ss != null) {
			ss.close();
		}
		
		return cnt;
	}// insertMember

	public boolean idDup(String userId) {
		boolean dupFlag = true;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		String result = ss.selectOne("kr.co.haerak.user_mapper.idDup", userId);
		// 3. transaction�Ϸ�
		if (result != null) {
			dupFlag = false;
		}
		// 4. �������
		if (ss != null) {
			ss.close();
		}

		return dupFlag;// false�� ���̵� ��� ����
	}// idDup

	public boolean nickDup(String nickName) {
		boolean dupFlag = true;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		String result = ss.selectOne("kr.co.haerak.user_mapper.nickDup", nickName);
		// 3. transaction�Ϸ�
		if (result != null) {//result�� ������ ��밡��
			dupFlag = false;
		}
		// 4. �������
		if (ss != null) {
			ss.close();
		}
		return dupFlag;// false�� ���̵� ��� ����
	}// nickDup

	public String userDup(UserDupVO udVO) {
		String userId = "";

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		userId = ss.selectOne("kr.co.haerak.user_mapper.userDup", udVO);
		// 3. transaction�Ϸ�
		// 4. �������
		if (ss != null) {
			ss.close();
		}

		return userId;
	}// userDup

	public LoginSessionDomain selectLogin(LoginVO lVO) {
		LoginSessionDomain lsDomain = null;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		lsDomain = ss.selectOne("kr.co.haerak.user_mapper.selectLogin", lVO);
		// 3. transaction�Ϸ�
		if (lsDomain != null) {// �α��� �ֽ�ȭ
			int cnt = ss.update("loginDateUpdate", lVO.getUserId());
			if (cnt == 1) {
				ss.commit();
			} // end if
		} // end if

		// 4. �������
		if (ss != null) {
			ss.close();
		}
		return lsDomain;
	}// selectLogin

	public String selectFindId(FindIdVO fiVO) {
		String userId = "";

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		userId = ss.selectOne("kr.co.haerak.user_mapper.selectFindId", fiVO);
		// 3. transaction�Ϸ�

		// 4. �������
		if (ss != null) {
			ss.close();
		}

		return userId;
	}// selectFindId

	public String selectFindPass(FindPassVO fpVO) {

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		String userId = ss.selectOne("kr.co.haerak.user_mapper.selectFindPass", fpVO);
		// 3. transaction�Ϸ�

		// 4. �������
		if (ss != null) {
			ss.close();
		}

		return userId;// true�� ���� false�� ����
	}// selectFindPass

	/**
	 * �α��ν� delete_date�� ���� �ִ��� Ȯ��
	 * @param 
	 * @return true�� Ż���� ȸ����
	 */
	public boolean selectChkUser(String userId) {
		boolean ckhFlag = false;

		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		String user_id = ss.selectOne("kr.co.haerak.user_mapper.selectChkUser", userId);
		// 3. transaction�Ϸ�
		
		if (user_id != null)//��ȸ����� ������ true
			ckhFlag = true;// Ż���Ͻÿ� ���� ������ �α��� ����
		// 4. �������
		if (ss != null) {
			ss.close();
		}

		return ckhFlag;// true�� �α��� ����!
	}// selectChkUserInfo

	/**
	 * ȸ������ �����Ҷ� ��й�ȣ �ѹ��� �Է¹ް� Ȯ���ϴ� ����
	 * @param cipVO
	 * @return
	 */
	public boolean selectChkUserInfo(ChkIdPassVO cipVO) {
		boolean ckhFlag = false;
		
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. Handler���
		String userId = ss.selectOne("kr.co.haerak.user_mapper.selectChkUserInfo", cipVO);
		// 3. transaction�Ϸ�
		
		if (userId!= null)// ��ȸ�� ���̵� �ִٸ�?
			ckhFlag = true;// 
		// 4. �������
		if (ss != null) {
			ss.close();
		}
		
		return ckhFlag;// true ���!
	}// selectChkUserInfo
	
	
	public int updatePass(ModifyPassVO mpVO) {
		int cnt=0;
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);

		// 2. Handler���
		cnt = ss.update("kr.co.haerak.user_mapper.updatePass", mpVO);
		// 3. transaction�Ϸ�
		if(cnt==1)ss.commit();
		if (ss != null) {
			ss.close();
		}

		return cnt;
	}// updatePass
	
	public ModifyInfoDomain selectInfo(String userId) {
		ModifyInfoDomain miDomain=null;
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. Handler���
		miDomain = ss.selectOne("kr.co.haerak.user_mapper.selectInfo", userId);
		// 3. transaction�Ϸ�
		
		if (ss != null) {
			ss.close();
		}
		
		return miDomain;
	}// ModifyInfoDomain
	
	public int updateInfo(ModifyInfoVO miVO) {
		int cnt=0;
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. Handler���
		cnt = ss.update("kr.co.haerak.user_mapper.updateInfo", miVO);
		if(cnt==1)ss.commit();
		// 3. transaction�Ϸ�
		
		if (ss != null) {
			ss.close();
		}
		
		return cnt;
	}// ModifyInfoDomain
	
	
	public ModifyProfileDomain selectProfile(String userId) {
		ModifyProfileDomain mpDomain=null;
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. Handler���
		mpDomain = ss.selectOne("kr.co.haerak.user_mapper.selectProfile", userId);
		// 3. transaction�Ϸ�
		
		if (ss != null) {
			ss.close();
		}
		
		return mpDomain;
	}// selectProfile
	
	public int updateProfile(ModifyProfileVO mpVO) {
		int cnt=0;
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		// 2. Handler���
		cnt = ss.update("kr.co.haerak.user_mapper.updateProfile", mpVO);
		// 3. transaction�Ϸ�
		if(cnt==1)ss.commit();
		if (ss != null) {
			ss.close();
		}
		
		return cnt;
	}// updateProfile
	
	public int deleteUser(String userId) {
		int cnt = 0;
		
		// 1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		// 2. Handler���
		cnt = ss.insert("kr.co.haerak.user_mapper.deleteUser", userId);
		// 3. transaction�Ϸ�
		
		if (ss != null) {
			ss.close();
		}
		
		return cnt;
	}//deleteUser
	
//	public static void main(String[] args) {
////		System.out.println(new UserDAO().idDup("abcd10"));
////		System.out.println(new UserDAO().selectLogin(new LoginVO("abcd2","XZPOtw4r9dqoTsPQzSxzGg==")));
////		System.out.println(new UserDAO().selectFindId(new FindIdVO("�ϴܱ�","trC2y8L4uUhOIy4Y3KcwEa8tGfrmc3m+aaqks9piX2w=")));
////		System.out.println(new UserDAO().selectChkUserInfo(new ChkIdPassVO("�ϴܱ�","trC2y8L4uUhOIy4Y3KcwEa8tGfrmc3m+aaqks9piX2w=")));
////		System.out.println(new UserDAO().selectChkUserInfo(new ChkIdPassVO("�ϴܱ�","trC2y8L4uUhOIy4Y3KcwEa8tGfrmc3m+aaqks9piX2w=")));
////		System.out.println(new UserDAO().updatePass(new ModifyPassVO("abcd2","XZPOtw4r9dqoTsPQzSxzGg==")));
////		System.out.println(new UserDAO().selectInfo("abcd2"));
////		System.out.println(new UserDAO().selectProfile("abcd2"));
////		System.out.println(new UserDAO().updateInfo(new ModifyInfoVO("","","","","","abcd2",5)));
//		
//	}// main
}// class
