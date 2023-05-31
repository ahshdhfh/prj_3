package kr.co.haerak.service.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.club.RegReviewDAO;
import kr.co.haerak.vo.club.ReplyVO;
import kr.co.haerak.vo.club.ReviewVO;

/**
 * ��� ���� service
 * @author user
 */
@Component
public class RegReviewService {
	
	@Autowired(required = false)
	private RegReviewDAO rrDAO;
	
	/**
	 * �ı�� �ۼ� �޼ҵ�
	 * @param rVO
	 */
	public void insertReviewService(ReviewVO rVO) {
		
		rrDAO.insertReviewService(rVO);
		
	}//insertReviewService
	
	
	/**
	 * �亯 �ۼ� �޼ҵ�
	 * @param rpVO
	 */
	public void insertReplyService(ReplyVO rpVO) {
		
		rrDAO.insertReplyService(rpVO);
		
	}//insertReplyService
	
	
	
}//RegReviewService
