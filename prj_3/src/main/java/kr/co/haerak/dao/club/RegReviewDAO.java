package kr.co.haerak.dao.club;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.MyBatisHandler;
import kr.co.haerak.domain.club.ClubDetailDomain;
import kr.co.haerak.vo.club.ClubSearchVO;
import kr.co.haerak.vo.club.ReplyVO;
import kr.co.haerak.vo.club.ReviewVO;

/**
 * ��۰��� DAO
 * @author user
 */
@Component
public class RegReviewDAO {

	/**
	 * �ı�� �ۼ� �޼ҵ�
	 * @param rVO
	 */
	public void insertReviewService(ReviewVO rVO) {
		
		int cnt=0;
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� <select id="subquery" resultMap="cdResult">
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		cnt=ss.insert("insertReview",rVO); //��ǰ���
		
		if(cnt==1) {
			ss.commit();
			System.out.println("commit");
		}else {
			ss.rollback();
			System.out.println("rollback");
		}//end else
		
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
		
	}//insertReviewService
	
	
	/**
	 * �亯 �ۼ� �޼ҵ�
	 * @param rpVO
	 */
	public void insertReplyService(ReplyVO rpVO) {
		int cnt=0;
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� <select id="subquery" resultMap="cdResult">
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		cnt=ss.insert("insertReply",rpVO); //��ǰ���
		
		if(cnt==1) {
			ss.commit();
			ss.update("replyCheckUpdate",rpVO.getReviewNum());
			ss.commit();			
		}else {
			ss.rollback();
		}//end else
		
		//3. MyBatis Handler �ݱ�
		if(ss !=null) {ss.close();} //end if
		
	}//insertReplyService
	
	public static void main(String[] args) {
//		ReplyVO rpVO=new ReplyVO();
//		rpVO.setReviewReply("�亯�Դϴ�");
//		rpVO.setUserId("abcd1");
//		rpVO.setReviewNum(6);
//		
//		new RegReviewDAO().insertReplyService(rpVO);
		
	}//main
	
	
}//RegReviewDAO
