package kr.co.haerak.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * �μ� ������ (���� ��, ���, ����, �ı�� ������)
 * @author user
 */
@Controller
public class ClubController {
	
		/**
		 * ���ӻ�������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/club/club_info.do")
		public String showClubInfoForm(Model model) {
			
			
			return "club/club_info";
		}//ShowClubInfoForm
	
		

		/**
		 * ��Ʈ Ŭ�� �� ���ɸ�� �߰�/����(ajax)
		 * @param model
		 * @param clubNum
		 */
		@GetMapping("/club/updateInterestList.do")
		public void updateInterestList(Model model,int clubNum) {
			
		}//updateInterestList
		
		

		/**
		 * �ı� ������ ������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/club/reviewSeeMoreForm.do")
		public String reviewSeeMoreForm(Model model,int clubNum) {
			
			
			return "reviewMore";
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
			
			
			return "result";
		}//clubRegistrationCategoryForm
		
		
		
		/**
		 * ���ӵ�� �� ������
		 * @param model
		 * @return
		 */
		@GetMapping("/club/clubRegistrationForm.do")
		public String clubRegistrationForm(Model model, int categoryNum) {
			
			
			return "result";
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
