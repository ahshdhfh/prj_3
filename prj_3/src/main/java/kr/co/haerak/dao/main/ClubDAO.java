package kr.co.haerak.dao.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.MyBatisHandler;
import kr.co.haerak.domain.main.ClubSalesDomain;
import kr.co.haerak.domain.main.UserDomain;
import kr.co.haerak.vo.main.SeeMoreVO;

/**
 * ���� DAO
 */
@Component
public class ClubDAO {
	

	/**
	 *�˻�
	 */
	public List<ClubSalesDomain>selectSearch(String search)throws PersistenceException{
		List<ClubSalesDomain>result = new ArrayList<ClubSalesDomain>();
		
		
		//1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
			
	   //2. �������� �� ��� ���
		result = ss.selectList("kr.co.haerak.dao.main_mapper.selectSearch",search);
			
	   //3. MyBatis Handler �ݱ�
		if( ss!=null) {ss.close();} //end if
		
		return result;
		
	}
	
	
	
	

	
	
	
	/**
	 * ���ο��� ���̴� ī�װ��� ����
	 */
	public List<ClubSalesDomain>selectRankClub(int categoryNum){
		List<ClubSalesDomain>clubRank = new ArrayList<ClubSalesDomain>();
		
		
		//1. MyBatis Handler ���
		SqlSession ss= MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. �������� �� ��� ���
		clubRank = ss.selectList("kr.co.haerak.dao.main_mapper.selectRankClub",categoryNum);
		
		for(ClubSalesDomain csd : clubRank) {
			
			List<UserDomain> ud = null;
			ud = ss.selectList("kr.co.haerak.dao.main_mapper.selectUserImg", csd.getClub_Num());
			
			
			csd.setUserInfo(ud);   
		}
		
		
		//3. MyBatis Handler �ݱ�
		if(ss != null) { ss.close();}
		
		return clubRank;
	}
	
	
	
	
	
	
	/**
	 * ī�װ��� ������
	 */
	/*
	public List<ClubSalesDomain>selectMoreClub(int categoryNum, int actiAreaNum){
		List<ClubSalesDomain>clubMore = new ArrayList<ClubSalesDomain>();
		
		//1.
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2.
		clubMore = ss.selectList("kr.co.haerak.dao.main_mapper.selectMoreClub",categoryNum);
		clubMore = ss.selectList("kr.co.haerak.dao.main_mapper.selectMoreClub",actiAreaNum);
		
		
		
		
		for(ClubSalesDomain csd : clubMore) {
			
			List<UserDomain> ud = null;
			ud=ss.selectList("kr.co.haerak.dao.main_mapper.selectUserImg", csd.getClub_Num());
			
			csd.setUserInfo(ud);
		}
		
		
		//3.
		if(ss != null) {ss.close();}
		
		
		return clubMore;
	}

*/	

	
	
	
	public List<ClubSalesDomain> selectMoreClub(SeeMoreVO smVO) {
	    List<ClubSalesDomain> clubMore = new ArrayList<ClubSalesDomain>();
	    System.out.println(smVO);
	    // 1.
	    SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
	    
	    // 2. 
	    clubMore = ss.selectList("kr.co.haerak.dao.main_mapper.selectMoreClub",smVO);
	    
	    // 3.
	    for (ClubSalesDomain csd : clubMore) {
	    	
	    	 List<UserDomain> ud = ss.selectList("kr.co.haerak.dao.main_mapper.selectUserImg", csd.getClub_Num());
		     csd.setUserInfo(ud);
	       }
	    
	    if(ss!=null) {ss.close();}
	    
		return clubMore;
	       
	    }
	    
	
	
	
		  public static void main(String[] args) {
			  
				
				 SeeMoreVO smVO = new SeeMoreVO(); smVO.setSearchText("���");
			
			  
			  
			  
		 System.out.println(new ClubDAO().selectMoreClub(smVO));
		  }
		 
	 
	 

}
