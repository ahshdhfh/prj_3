package kr.co.haerak.service.club;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
	public String showReviewService(int clubNum,int currentPage1) {
		double limitdata=6.0; //��ȭ�鿡 ������ �ִ� ������ ���� 
		int currentPage=currentPage1; //���� ������
		double pageCount=5; //�ִ� ������ ����
		double pageGroup=Math.ceil(currentPage / pageCount); //������ �׷�
		double lastNum=pageGroup*pageCount;
		double firstNum=lastNum-(pageCount - 1);

		
		List<SetReviewDomain> result=new ArrayList<SetReviewDomain>();
		
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("resultFlag", false);
		result=scDAO.showReviewService(clubNum);
		
		
		jsonObj.put("resultFlag", true);
		jsonObj.put("totaldata", result.size()); //�� ������ ����
		jsonObj.put("pageCnt", Math.ceil(result.size()/limitdata)); //����������
		jsonObj.put("currentPage",currentPage ); //����������
		jsonObj.put("pageGroup", pageGroup); //�������׷�
		jsonObj.put("lastNum", lastNum); //����������
		jsonObj.put("firstNum",firstNum ); //����������
		JSONArray jsonArr=new JSONArray();
		JSONObject jsonTemp=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy��-mm��-dd�� a hh��mm��");
		for(SetReviewDomain srd : result) {
			jsonTemp=new JSONObject();
			jsonTemp.put("userId", srd.getUserId());
			jsonTemp.put("clubNum", srd.getClubNum());
			jsonTemp.put("clubReview", srd.getClubReview());
			jsonTemp.put("nickName", srd.getNickName());
			jsonTemp.put("replyNum", srd.getReplyNum());
			jsonTemp.put("reviewNum", srd.getReviewNum());
			jsonTemp.put("reviewReply", srd.getReviewReply());
			jsonTemp.put("userImg", srd.getUserImg());
			jsonTemp.put("writeDate", sdf.format(srd.getWriteDate()));
			jsonTemp.put("replyCheck",srd.getReplyCheck());
			jsonArr.add(jsonTemp);
		}
		
		jsonObj.put("data", jsonArr);
		
		return jsonObj.toJSONString();
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
	
	public boolean approvalCheck(String userId,int clubNum) {
		ClubSearchVO csVO=new ClubSearchVO(userId, clubNum);
		boolean approvalFlag=scDAO.approvalCheck(csVO);
		
		return approvalFlag;
	}
	
	
}//ShowClubService
