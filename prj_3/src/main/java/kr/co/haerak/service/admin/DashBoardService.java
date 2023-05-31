package kr.co.haerak.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.co.haerak.dao.admin.AdminDAO;
import kr.co.haerak.dao.admin.DashBoardDAO;
import kr.co.haerak.dao.admin.ManageClubDAO;
import kr.co.haerak.dao.admin.ManageMemberDAO;
import kr.co.haerak.dao.admin.ManageReviewDAO;
import kr.co.haerak.domain.admin.CategoryCntDomain;
import kr.co.haerak.domain.admin.ClubStatusCntDomain;
import kr.co.haerak.domain.admin.NewAndWithdrawallCntDomain;
import kr.co.haerak.domain.admin.TotalMemberCntDomain;
import kr.co.haerak.domain.admin.TradingStatusDomain;
import kr.co.haerak.vo.admin.MemberCntVO;
import kr.co.haerak.vo.admin.TotalMemberCntVO;

@Component
public class DashBoardService {

	//�뽬����
	@Autowired(required = false)
	private DashBoardDAO dbDAO;
	
	//���� �� �湮��
	public int totalVisitService() {
		int tvs=dbDAO.totalVisit();
	
		return tvs;
	}
	

	//��ȸ����
	public int memberCntService() {
		int mcs = dbDAO.memberCnt();
		
		return mcs;
	}

	//�ű԰��� ȸ�� ��
	public int newMemberCntService() {
		int mncs =  dbDAO.newMemberCnt();
		return mncs;
		
		
	}

	//���� Ż���� ȸ�� ��
	public int withdrawalMembersService() {
		
		int wms= dbDAO.withdrawalMembers();
		return wms;
	}

	//���� ���οϷ� ��
	public int tradingStatusCntService() {
		int tscs= dbDAO.tradingStatusCnt();
		
		return tscs;
	}

	//���οϷ� ��Ȳ ��
	public List<TradingStatusDomain> tradingStatusService(){
		List<TradingStatusDomain> tss= dbDAO.tradingStatus();
		
		return tss;
	}
	
	//ī�װ��� ���� ��(�Ҽȸ�/Ŭ��/ç����)
	public CategoryCntDomain categoryCntService(){
		
		CategoryCntDomain ccd = dbDAO.categoryCnt();
		
		return ccd;
	}

	//������ ������Ȳ
	public  List<ClubStatusCntDomain> regionCntService() {
		List<ClubStatusCntDomain> dcs = dbDAO.regionCnt();
		
		return dcs;
	}

	//�ű�&Ż��ȸ��
	public List<NewAndWithdrawallCntDomain> newAndwithdrawallCntService(){
		List<NewAndWithdrawallCntDomain> nacs = dbDAO.newAndWithdrawallCnt();
		
		return nacs;
	}

	//������ȸ����
	public List<TotalMemberCntVO> monthTotalCntService(){
		List<TotalMemberCntVO> mtcs = dbDAO.monthTotalCnt();
		
		return mtcs;
	}
	
	

}//class
