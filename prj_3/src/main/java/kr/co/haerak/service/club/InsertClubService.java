package kr.co.haerak.service.club;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.club.InsertClubDAO;
import kr.co.haerak.domain.club.ClubInsertDomain;
import kr.co.haerak.vo.club.ClubInsertVO;

/**
 * ���� ��� ���� Service
 * @author user
 */
@Component
public class InsertClubService {
	
	@Autowired(required = false)
	private InsertClubDAO icDAO;
	/**
	 * ���� ��� method
	 * @param cVO
	 */
	public void insertClubInfo(ClubInsertVO ciVO) {
		int i = Integer.parseInt(ciVO.getZipcode().substring(0, 2));
		int actiAreaNum=1;
		if(i<10)actiAreaNum=1;//����
		if(i>=10 && i <=20)actiAreaNum=2;//���
		if(i>=21 && i <=23)actiAreaNum=3;//��õ
		if(i>=24 && i <=26)actiAreaNum=4;//����
		if(i>=27 && i <=29)actiAreaNum=5;//���
		if(i==30)actiAreaNum=6;//����
		if(i>=31 && i <=33)actiAreaNum=7;//�泲
		if(i>=34 && i <=35)actiAreaNum=8;//����
		if(i>=36 && i <=40)actiAreaNum=9;//���
		if(i>=41 && i <=43)actiAreaNum=10;//�뱸
		if(i>=44 && i <=45)actiAreaNum=11;//���
		if(i>=46 && i <=49)actiAreaNum=12;//�λ�
		if(i>=50 && i <=53)actiAreaNum=13;//�泲
		if(i>=54 && i <=56)actiAreaNum=14;//����
		if(i>=57 && i <=60)actiAreaNum=15;//����
		if(i>=61 && i <=62)actiAreaNum=16;//����
		if(i==63)actiAreaNum=17;//����
		
		ciVO.setActiAreaNum(actiAreaNum);
		
		icDAO.insertClubInfo(ciVO);
		
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
		
		ClubInsertDomain result=new ClubInsertDomain();
		
		return result;
	}//setSelectClub
	
	
	
	
	
	
}//InsertClubService
