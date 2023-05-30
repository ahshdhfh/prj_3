package kr.co.haerak.dao.club;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.MyBatisHandler;
import kr.co.haerak.domain.club.ClubInsertDomain;
import kr.co.haerak.vo.club.ClubInsertVO;

/**
 * ���Ӱ��� DAO
 * @author user
 */
@Component
public class InsertClubDAO {

	
	/**
	 * ���� ��� method
	 * @param cVO
	 */
	public void insertClubInfo(ClubInsertVO cVO) {
		SqlSession ss = MyBatisHandler.getInstance().getMyBatisHandler(false);
		
		ss.insert("insertClubInfo",cVO);
		List<String> list = cVO.getClubImg();
		
		for(String clubImg : list) { 
			ss.insert("insertClubImg","http://localhost/prj_3/club_images/"+clubImg);   
		}//end for
		
		ss.commit();
		
		if(ss!=null)ss.close();
		
	}//insertClubInfo
	
	
	/**
	 * ���� ���� method
	 * @param cVO
	 */
	public void updateClubInfo(ClubInsertVO cVO) {
		
		
		
	}//updateClubInfo
	
	
	/**
	 * ������ ������ ������ �������� ����
	 * @param clubNum
	 * @return
	 */
	public ClubInsertDomain setSelectClub(int clubNum) {
		
		ClubInsertDomain result=null;
		
		//1. Mybatis Handler ���
		SqlSession ss=MyBatisHandler.getInstance().getMyBatisHandler(false);
		//2. handler ��� <select id="subquery" resultMap="cdResult">
		//parameterType�� �����Ƿ� �Ű����� �ϳ��� method�� ����Ѵ�.
		result=ss.selectOne("setSelectClub",clubNum); //��ǰ���
		//3. MyBatis Handler �ݱ�
		
		System.out.println(result);
		if(ss !=null) {ss.close();} //end if
		
		
		return result;
	}//setSelectClub
	
	public static void main(String[] args) {
		new InsertClubDAO().setSelectClub(1);
	}//main
	
	
}//InsertClubDAO
