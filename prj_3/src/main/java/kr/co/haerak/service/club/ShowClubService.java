package kr.co.haerak.service.club;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.club.ShowClubDAO;
import kr.co.haerak.domain.club.ClubDetailDomain;
import kr.co.haerak.domain.club.SetReviewDomain;
import kr.co.haerak.vo.club.ClubSearchVO;

/**
 * ���� �������� service
 * @author user
 */
@Component
public class ShowClubService {

	@Autowired(required = false)
	private ShowClubDAO scDAO;
	
	
	/**
	 * ������ ������ ���� service
	 * @param clubNum
	 * @return
	 */
	public ClubDetailDomain showClubService(int clubNum) {
		
		ClubDetailDomain result=scDAO.showClubService(clubNum);
		
		return result;
	}//showClubService
	
	
	/**
	 *  ���ӻ� ���������� ���並 �������� service
	 * @param clubNum
	 * @return
	 */
	public List<SetReviewDomain> showReviewService(int clubNum) {
		
		List<SetReviewDomain> result=new ArrayList<SetReviewDomain>();
		
		
		return result;
	}//showReviewService
	
	/**
	 * ���ɻ������� Ȯ���ϴ� service
	 * @param userId
	 * @param clubNum
	 * @return
	 */
	public boolean selectInterService(String userId,int clubNum) {
		boolean interFlag=false;
		ClubSearchVO csVO=new ClubSearchVO(userId, clubNum);
		String diff="";
		diff=scDAO.selectInterService(csVO);
		
		if(userId.equals(diff)) {
			interFlag=true; //��ġ�ϸ� true
		}//end if
		
		
		return interFlag;
	}//selectInterService
	
	
	/**
	 * ��Ʈ Ŭ�� �� ���ɸ�� �߰�/����
	 * @param userId
	 * @param clubNum
	 */
	public void updateInterList(String userId,int clubNum,int flag) {
		ClubSearchVO csVO=new ClubSearchVO(userId, clubNum);
		scDAO.updateInterList(csVO, flag);
	}//updateInterList
	
	
	/**
	 * ���ӽ�û�ϱ�
	 * @param userId
	 * @param clubNum
	 */
	public boolean clubApprovalInsert(String userId,int clubNum) {
		ClubSearchVO csVO=new ClubSearchVO(userId, clubNum);
		boolean approvalFlag=scDAO.clubApprovalInsert(csVO);
		
		return approvalFlag;
	}//clubapprovalInsert
	
	
	
}//ShowClubService
