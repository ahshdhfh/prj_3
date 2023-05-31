package kr.co.haerak.dao.club;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.MyBatisHandler;
import kr.co.haerak.domain.club.ClubDetailDomain;
import kr.co.haerak.domain.club.SetReviewDomain;
import kr.co.haerak.vo.club.ClubSearchVO;

/**
 * ���ӻ������� DAO
 * @author user
 */
@Component
public class ShowClubDAO {

	
	/**
	 * ������ ������ ���� service
	 * @param clubNum
	 * @return
	 */
	public ClubDetailDomain showClubService(int clubNum) {
		
		ClubDetailDomain result=null;
		List<String> clubImg=new ArrayList<String>();
		
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� <select id="subquery" resultMap="cdResult">
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		result=ss.selectOne("selectClubInfoDetail",clubNum); //��ǰ���
		clubImg=ss.selectList("selectClubImg",clubNum); //��ǰ�̹���
		result.setClubImg(clubImg);
		ss.update("riviewCntAdd",clubNum);
		ss.commit();
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		return result;
	}//showClubService
	
	/**
	 * ���� ������������ ��� 4�� ��������
	 * @param clubNum
	 * @return
	 */
	public List<SetReviewDomain> inforeviewListSelect(int clubNum) {
		List<SetReviewDomain> result=null;
		
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� <select id="subquery" resultMap="cdResult">
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		result=ss.selectList("InforeviewListSelect",clubNum); //��ǰ���
		System.out.println(result);
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		return result;
	}//inforeviewListSelect
	
	/**
	 *  ���Ӵ����� ���������� ���並 �������� service
	 * @param clubNum
	 * @return
	 */
	public List<SetReviewDomain> showReviewService(int clubNum) {
		
		List<SetReviewDomain> result=new ArrayList<SetReviewDomain>();
		
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� 
		result=ss.selectList("clubInfoSelectReview",clubNum);
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		
		return result;
	}//showReviewService
	
	/**
	 * ���ɻ������� Ȯ���ϴ� service
	 * @param userId
	 * @param clubNum
	 * @return
	 */
	public String selectInterService(ClubSearchVO csVO) {
		String result="";
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� 
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		result=(String)ss.selectOne("interCheck",csVO); //����üũ
		System.out.println(result);
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		return result;
	}//selectInterService
	
	
	/**
	 * ��Ʈ Ŭ�� �� ���ɸ�� �߰�/����
	 * @param userId
	 * @param clubNum
	 */
	public boolean updateInterList(ClubSearchVO csVO,int flag) { //flag   1: �߰� 2: ����
		
		boolean result=false;
		int sqlcheck=0;
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� 
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		if(flag==1) {
			sqlcheck=ss.insert("interAdd",csVO); //�߰�
			
		}else if(flag==2) {
			sqlcheck=ss.delete("interDelete",csVO); //����
		}
		
		if(sqlcheck==1) {
			ss.commit();
			result=true;
		}//end if
		
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		System.out.println(sqlcheck);
		return result;
		
	}//updateInterList
	
	
	/**
	 * ���ӽ�û�ϱ�
	 */
	public boolean clubApprovalInsert(ClubSearchVO csVO) {
		int cnt=0;
		boolean approvalFlag=false;
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� 
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		
		if(approvalCheck(csVO)) {
		cnt=ss.insert("clubApprovalInsert",csVO); //���� �߾�����
		if(cnt==1) {
			ss.commit();
			approvalFlag=true; //����
		}else {
			ss.rollback();
		}//end else
		}
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if

		return approvalFlag;
	}//clubapprovalInsert
	
	public boolean approvalCheck(ClubSearchVO csVO) {
		
		boolean approvalFlag=false;
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� 
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		String joinCheck=ss.selectOne("joinListCheck",csVO);
		String approvalCheck=ss.selectOne("approvallistCheck",csVO);
		if(joinCheck==null) {
			if(approvalCheck==null) {
				approvalFlag=true;
			}//end if
		}//end if
		
		
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		
		return approvalFlag;
	}
	
	public static void main(String[] args) {
		//System.out.println(new ShowClubDAO().showClubService(1));
		//ClubSearchVO sVO=new ClubSearchVO();
		//sVO.setClub_Num(1);
		//sVO.setUser_id("abcd12");
		//System.out.println(sVO.getUser_id());
		//System.out.println(new ShowClubDAO().updateInterList(sVO,2));
		//new ShowClubDAO().clubApprovalInsert(sVO);
		//new ShowClubDAO().showReviewService(1);
		new ShowClubDAO().inforeviewListSelect(1);
	}//main
	
	
}//ShowClubDAO
