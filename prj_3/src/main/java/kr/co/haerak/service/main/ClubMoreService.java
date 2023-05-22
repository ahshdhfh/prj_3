package kr.co.haerak.service.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.haerak.dao.main.ClubDAO;
import kr.co.haerak.domain.main.ClubSalesDomain;
import kr.co.haerak.vo.main.SeeMoreVO;

public class ClubMoreService {
	
	
	@Autowired(required = false)
	private ClubDAO cDAO;
	
	
	
	
	//ī�װ��� ������ Ŭ������ �� ����Ʈ ��ȸ
	public List<ClubSalesDomain> selectMoreClub(SeeMoreVO smVO){
		
	List<ClubSalesDomain> clubMore = cDAO.selectMoreClub(smVO);
		
	return selectMoreClub(smVO);
	}

}
