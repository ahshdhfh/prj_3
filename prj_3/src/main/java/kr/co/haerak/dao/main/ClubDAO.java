package kr.co.haerak.dao.main;

import java.util.ArrayList;
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
	public List<ClubSalesDomain>selectSearch(ClubSalesDomain csDo)throws PersistenceException{
		List<ClubSalesDomain>search = new ArrayList<ClubSalesDomain>();
		
		
		//1. MyBatis Handler ���
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
			
	   //2. �������� �� ��� ���
		search = ss.selectList("kr.co.haerak.dao.main_mapper.selectSearch",csDo);
			
	   //3. MyBatis Handler �ݱ�
		if( ss!=null) {ss.close();} //end if
		
		return search;
		
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
		
		System.out.println(clubRank);
		
		//3. MyBatis Handler �ݱ�
		if(ss != null) { ss.close();}
		
		return clubRank;
	}
	
	
	
	
	
	
	/**
	 * ī�װ��� ������
	 */
	public List<ClubSalesDomain>selectMoreClub(SeeMoreVO smVO){
		List<ClubSalesDomain>clubMore = new ArrayList<ClubSalesDomain>();
		
		//1. 
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		//2. 
		clubMore = ss.selectList("kr.co.haerak.dao.main_mapper.clubMore",smVO);
		
		//3.
		if(ss != null) {ss.close();}
		
		return clubMore;
	}




	 public static void main(String[] args) { 
	System.out.println(new ClubDAO().selectRankClub(1));
	
	 }
	 

}
