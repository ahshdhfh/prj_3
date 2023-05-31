package kr.co.haerak.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.haerak.service.admin.DashBoardService;

@Controller
public class DashBoardController {

	@Autowired(required = false)
	private DashBoardService dbs;
	
	
	@RequestMapping(value="/admin/dashboard.do", method= {RequestMethod.GET, RequestMethod.POST})
	
	public String dashboard(Model model) {
		//��� ��ġǥ�� 
		model.addAttribute("totalVisit",dbs.totalVisitService());
		model.addAttribute("memberCnt",dbs.memberCntService());
		model.addAttribute("newMemberCnt",dbs.newMemberCntService());
		model.addAttribute("withdrawalMembers",dbs.withdrawalMembersService());
		model.addAttribute("tradingStatusCnt",dbs.tradingStatusCntService());
		
		//��ǰ �ŷ���Ȳ
		model.addAttribute("tradingStatus",dbs.tradingStatusService());
		
		//ī�װ� ������Ʈ
		model.addAttribute("categoryCnt",dbs.categoryCntService());
		
		//������ �ŷ���Ȳ
		model.addAttribute("regionCnt",dbs.regionCntService());
		model.addAttribute("newAndwithdrawallCnt",dbs.newAndwithdrawallCntService());
		
		model.addAttribute("monthTotalCnt",dbs.monthTotalCntService());
		
		return "admin/dashboard";
	}
	
	  
	  

}

