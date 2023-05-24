package kr.co.haerak.controller.club;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.Loader.Simple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.haerak.domain.user.LoginSessionDomain;
import kr.co.haerak.service.club.InsertClubService;
import kr.co.haerak.service.club.ShowClubService;
import kr.co.haerak.vo.club.ClubInsertVO;

/**
 * �μ� ������ (���� ��, ���, ����, �ı�� ������)
 * @author user
 */
@Controller
public class ClubController {
	
	@Autowired(required = false)
	private ShowClubService scs;
	
	@Autowired
	private InsertClubService ics;
	
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
		 * @throws IOException 
		 */
		@PostMapping("/clubRegistrationProcess.do")
		public String clubRegistrationProcess(Model model, HttpServletRequest request) throws IOException {
			System.out.println("��Ʈ�ѷ�");
			File file=new File("C:/Users/user/git/prj_3/prj_3/src/main/webapp/club_images");
			int max=1024*1024*5;
			MultipartRequest mr = new MultipartRequest(request, file.getAbsolutePath(), max, "UTF-8", new DefaultFileRenamePolicy());
			
			LoginSessionDomain lsDomain = (LoginSessionDomain) model.getAttribute("lsDomain");
			//String userId = lsDomain.getUserId();
			String userId = "abcd1";
			int price= Integer.parseInt(mr.getParameter("price"));
			int categoryNum= Integer.parseInt(mr.getParameter("categoryNum"));
			int actiAreaNum= 1;
			int numberPeople= Integer.parseInt(mr.getParameter("numberPeople"));
			String clubName = mr.getParameter("clubName");
			String detailTxt = mr.getParameter("detailTxt");
			String clubAddr = mr.getParameter("clubAddr");
			String detailAddr = mr.getParameter("detailAddr");
			String latitude = mr.getParameter("latitude");
			String longitude = mr.getParameter("longitude");
			String clubTime = mr.getParameter("clubTime");
			String zipcode = mr.getParameter("zipcode");
			SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
			List<String> clubImg =new ArrayList<String>();
			//String[] clubImgs = mr.getParameterValues("clubImg");
			//System.out.println(clubImgs.toString());
			String imageNames = mr.getParameter("imageNames");
			System.out.println("��Ʈ�ѷ� �̹����� �Ķ���� ���� : "+imageNames);
			String[] clubImgs= imageNames.split(",");
			
			for(int i=0; i<clubImgs.length;i++ ) {
				clubImg.add(clubImgs[i]);
			}
			
			java.util.Date date=null;
			try {
				date = sdf.parse(mr.getParameter("clubDate"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date clubDate = new java.sql.Date(date.getTime());;
			ClubInsertVO ciVO = new ClubInsertVO(price, categoryNum, actiAreaNum, numberPeople, clubName, detailTxt, clubAddr, detailAddr, userId, latitude, longitude, clubTime, zipcode, clubImg, clubDate);
			ics.insertClubInfo(ciVO);
			
			return "main/main";
		}//clubRegistrationProcess
		
		
		
}//class
