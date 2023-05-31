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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.haerak.domain.user.LoginSessionDomain;
import kr.co.haerak.service.club.InsertClubService;
import kr.co.haerak.service.club.RegReviewService;
import kr.co.haerak.service.club.ShowClubService;
import kr.co.haerak.vo.club.ClubInsertVO;
import kr.co.haerak.vo.club.ClubUpdateVO;
import kr.co.haerak.vo.club.ReplyVO;
import kr.co.haerak.vo.club.ReviewVO;
import oracle.sql.DATE;

/**
 * �μ� ������ (���� ��, ���, ����, �ı�� ������)
 * @author user
 */
@SessionAttributes("lsDomain")
@Controller
public class ClubController {
	
	@Autowired(required = false)
	private ShowClubService scs;
	
	@Autowired(required = false)
	private InsertClubService ics;
	
	@Autowired(required = false)
	private RegReviewService rrs;
	
		/**
		 * ���ӻ�������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@RequestMapping(value = "/club_info.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String showClubInfoForm(int club_Num,Model model) {
			//LoginSessionDomain lsDomain = new LoginSessionDomain("����", "�ȳ��ϼ���", "../images/a.png", "abcd1", "", 1);
			LoginSessionDomain lsDomain=null;
			String userId=null;
			try {
				lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");				
				userId=lsDomain.getUserId();
			}catch (NullPointerException e) {
			}
			
			if(lsDomain!=null) {				
				model.addAttribute("lsDomain", lsDomain);
			}
			
			if(userId==null) {
				userId="Ʋ�����̵�";
			}
			
			model.addAttribute("clubNum",club_Num);
			model.addAttribute("reviewList",scs.inforeviewListSelectService(club_Num));
			model.addAttribute("clubInfo",scs.showClubService(club_Num));
			model.addAttribute("interFlag",scs.selectInterService(userId, club_Num));
			model.addAttribute("approvalFlag",scs.approvalCheck(userId, club_Num)); //�̹� ��û�� �������� Ȯ��
			
			return "club/club_info";
		}//ShowClubInfoForm
	
		

		/**
		 * ��Ʈ Ŭ�� �� ���ɸ�� �߰�/����(ajax)
		 * @param model
		 * @param clubNum
		 */
		@ResponseBody
		@GetMapping("updateInterestList.do")
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
		@GetMapping("/approvalrequest.do")
		public String insertapprovalList(Model model,int club_Num) {
			LoginSessionDomain lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");
			model.addAttribute("lsDomain", lsDomain);
			String userId="";
			userId=lsDomain.getUserId();
			if(userId==null) {
				userId="Ʋ�����̵�";
			}
			model.addAttribute("approvalFlag",scs.clubApprovalInsert(userId, club_Num));
				
			return "forward:club_info.do";
		}
		

		/**
		 * �ı� ������ ������
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@RequestMapping(value = "/reviewSeeMoreForm.do", method = {RequestMethod.GET,RequestMethod.POST})
		public String reviewSeeMoreForm(Model model,int clubNum,String selluserId) {
			//LoginSessionDomain lsDomain = new LoginSessionDomain("�ϴ�", "�ȳ��ϼ���", "../images/a.png", "abcd1", "", 1);
			LoginSessionDomain lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");
			model.addAttribute("lsDomain", lsDomain);
			
			
			model.addAttribute("selluserId",selluserId);
			model.addAttribute("clubNum",clubNum);
			
			return "club/review";
		}//reviewSeeMoreForm	
		
		@ResponseBody
		@GetMapping("/reviewReply.do")
		public String reviewReplyAjax(int clubNum,int currentPage) {
			String jsonObj=scs.showReviewService(clubNum,currentPage);
			
			return jsonObj;
		}
		
		
		/**
		 *  �ı� �ۼ��ϱ�(do_chain���� �������������������)
		 */
		@PostMapping("/reviewInsert.do")
		public String reviewInsert(ReviewVO rVO) {
			System.out.println(rVO);
			
			rrs.insertReviewService(rVO);
			
			return "forward:reviewSeeMoreForm.do";
		}//reviewInsert	
		
		/**
		 *  �亯 �ۼ��ϱ�(do_chain���� �������������������)
		 */
		@PostMapping("/replyInsert.do")
		public String replyInsert(ReplyVO rpVO) {
			System.out.println(rpVO);
			
			rrs.insertReplyService(rpVO);
		
			return "forward:reviewSeeMoreForm.do";
		}//replyInsert	
		
		
		/**
		 * ���Ӽ��������� �� ����
		 * @param model
		 * @param clubNum
		 * @return
		 */
		@GetMapping("/clubModifyForm.do")
		public String clubModifyForm(Model model,int clubNum) {
			LoginSessionDomain lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");
			model.addAttribute("lsDomain", lsDomain);
			
			model.addAttribute("pageInfo","�������� ����");
			model.addAttribute("setClubInfo",ics.setSelectClub(clubNum));
			model.addAttribute("clubNum",clubNum);
			
			return "club/sell_page";
		}//clubModifyForm
		
		
		

		
		
		
		/**
		 * ���� ����ϱ������� ���� (ī�װ� ���� ������)
		 * @param model
		 * @return
		 */
		@GetMapping("/clubRegistrationCategoryForm.do")
		public String clubRegistrationCategoryForm(Model model) {
			LoginSessionDomain lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");
			model.addAttribute("lsDomain", lsDomain);
			
			return "club/select_category_page";
		}//clubRegistrationCategoryForm
		
		
		
		/**
		 * ���ӵ�� �� ������
		 * @param model
		 * @return
		 */
		@GetMapping("/clubRegistrationForm.do")
		public String clubRegistrationForm(Model model, int categoryNum) {
			
			LoginSessionDomain lsDomain = (LoginSessionDomain)model.getAttribute("lsDomain");
			model.addAttribute("lsDomain", lsDomain);
			
			model.addAttribute("categoryNum",categoryNum);
			model.addAttribute("pageInfo","���� ���");
			
			return "club/sell_page";
		}//clubRegistrationForm
		
		
		/**
		 * ���� ���� ���������� �ۼ��Ϸ� ��ư ���� �� 
		 * @param model
		 * @return
		 */
		@PostMapping("/clubModifyProcess.do")
		public String clubModifyProcess(Model model, HttpServletRequest request) throws IOException {
			System.out.println("��Ʈ�ѷ�");
			File file=new File("C:/Users/user/git/prj_3/prj_3/src/main/webapp/club_images");
			int max=1024*1024*5;
			MultipartRequest mr = new MultipartRequest(request, file.getAbsolutePath(), max, "UTF-8", new DefaultFileRenamePolicy());
			
			LoginSessionDomain lsDomain = (LoginSessionDomain) model.getAttribute("lsDomain");
			String userId = lsDomain.getUserId();
			//String userId = "abcd1";
			int price= Integer.parseInt(mr.getParameter("price"));
			int actiAreaNum= 1;
			int numberPeople= Integer.parseInt(mr.getParameter("numberPeople"));
			int clubNum=Integer.parseInt(mr.getParameter("clubNum"));
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
			} catch (NullPointerException e) {
				date=null;
			}
			if(date!=null) {
				Date clubDate = new java.sql.Date(date.getTime());				
				ClubUpdateVO cuVO=new ClubUpdateVO(price, clubNum, actiAreaNum, numberPeople, clubName, detailTxt, clubAddr, detailAddr, userId, latitude, longitude, clubTime, zipcode, clubImg,clubDate);
				System.out.println(cuVO);
				ics.updateClubInfo(cuVO);
			}else {				
				ClubUpdateVO cuVO=new ClubUpdateVO(price, clubNum, actiAreaNum, numberPeople, clubName, detailTxt, clubAddr, detailAddr, userId, latitude, longitude, clubTime, zipcode, clubImg);
				System.out.println(cuVO);
				ics.updateClubInfo(cuVO);
			}
			
			return "forward:club_info.do?club_Num="+clubNum;
		}//clubModifyProcess
		
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
			String userId = lsDomain.getUserId();
			//String userId = "abcd1";
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
			Date clubDate = new java.sql.Date(date.getTime());
			ClubInsertVO ciVO = new ClubInsertVO(price, categoryNum, actiAreaNum, numberPeople, clubName, detailTxt, clubAddr, detailAddr, userId, latitude, longitude, clubTime, zipcode, clubImg, clubDate);
			ics.insertClubInfo(ciVO);
			
			return "forward:main.do";
		}//clubRegistrationProcess
		
		
		
}//class
