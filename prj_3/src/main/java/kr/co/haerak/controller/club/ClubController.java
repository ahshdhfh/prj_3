package kr.co.haerak.controller.club;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.haerak.service.club.ShowClubService;

/**
 * �μ� ������ (���� ��, ���, ����, �ı�� ������)
 * @author user
 */
@Controller
public class ClubController {
	
	@Autowired(required = false)
	private ShowClubService scs;
	
		/**
		 * ���ӻ�������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/club/club_info.do")
		public String showClubInfoForm(int club_Num,Model model) {
			String userId="";
			userId=(String)model.getAttribute("userId");

			if(userId==null) {
				userId="Ʋ�����̵�";
			}
			
			model.addAttribute("clubInfo",scs.showClubService(club_Num));
			model.addAttribute("interFlag",scs.selectInterService(userId, club_Num));
			
			return "club/club_info";
		}//ShowClubInfoForm
	
		

		/**
		 * ��Ʈ Ŭ�� �� ���ɸ�� �߰�/����(ajax)
		 * @param model
		 * @param clubNum
		 */
		@ResponseBody
		@GetMapping("/club/updateInterestList.do")
		public String updateInterestList(int club_Num,String userId,int flag) {
			//System.out.println("�� �Ķ���ͷ� �Ѿ�� �̸� : "+);
			//Service ����
			
			scs.updateInterList(userId, club_Num, flag);
			String result="true";
			return result;
		}//updateInterestList
		
		
		/**
		 * ���ӽ�û�ϱ� Ŭ�� 
		 * @param model
		 * @param club_Num
		 * @return
		 */
		@GetMapping("/club/approvalrequest.do")
		public String insertapprovalList(Model model,int club_Num) {
			
			String userId="";
			userId=(String)model.getAttribute("userId");

			if(userId==null) {
				userId="Ʋ�����̵�";
			}
			
			model.addAttribute("approvalFlag",scs.clubApprovalInsert("abcd4", club_Num));
			model.addAttribute("clubInfo",scs.showClubService(club_Num));
			model.addAttribute("interFlag",scs.selectInterService(userId, club_Num));
			
			return "club/club_info";
		}
		

		/**
		 * �ı� ������ ������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/club/reviewSeeMoreForm.do")
		public String reviewSeeMoreForm(Model model,int clubNum) {
			
			
			return "club/review";
		}//reviewSeeMoreForm	
		
		
		/**
		 *  �ı� �ۼ��ϱ�(ajax)
		 */
		@GetMapping("/club/reviewInsert.do")
		public void reviewInsert() {
			
		}//reviewInsert	
		
		/**
		 *  �亯 �ۼ��ϱ�(ajax)
		 */
		@GetMapping("/club/replyInsert.do")
		public void replyInsert() {
			
		}//replyInsert	
		
		
		/**
		 * ���Ӽ��������� �� ����
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/club/clubModifyForm.do")
		public String clubModifyForm(Model model,int clubNum) {
			
			
			return "result";
		}//clubModifyForm
		
		
		
		/**
		 * ���� ���� ���������� �ۼ��Ϸ� ��ư ���� �� 
		 * @param model
		 * @return
		 */
		@GetMapping("/club/clubModifyProcess.do")
		public String clubModifyProcess(Model model) {
			
			
			return "result";
		}//clubModifyProcess
		
		
		
		/**
		 * ���� ����ϱ������� ���� (ī�װ� ���� ������)
		 * @param model
		 * @return
		 */
		@GetMapping("/club/clubRegistrationCategoryForm.do")
		public String clubRegistrationCategoryForm(Model model) {
			
			
			return "club/select_category_page";
		}//clubRegistrationCategoryForm
		
		
		
		/**
		 * ���ӵ�� �� ������
		 * @param model
		 * @return
		 */
		@GetMapping("/club/clubRegistrationForm.do")
		public String clubRegistrationForm(Model model, int categoryNum) {
			
			model.addAttribute("categoryNum",categoryNum);
			model.addAttribute("pageInfo","���� ���");
			
			return "club/sell_page";
		}//clubRegistrationForm
		
		
		/**
		 * ���� ������������� �ۼ��Ϸ� ��ư Ŭ��
		 * @param model
		 * @param categoryNum
		 * @return
		 */
		@GetMapping("/club/clubRegistrationProcess.do")
		public String clubRegistrationProcess(Model model, HttpServletRequest request) {
			
			
			
			return "result";
		}//clubRegistrationProcess
		
		
		
}//class
