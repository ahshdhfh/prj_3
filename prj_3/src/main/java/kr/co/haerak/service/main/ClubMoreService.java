package kr.co.haerak.service.main;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.haerak.dao.main.ClubDAO;
import kr.co.haerak.domain.main.ClubSalesDomain;
import kr.co.haerak.domain.main.UserDomain;
import kr.co.haerak.vo.main.SeeMoreVO;

@Component
public class ClubMoreService   {
	
	
	@Autowired(required = false)
	private ClubDAO cDAO;
	
	
	
	
	//카테고리별 더보기, 검색 했을 때 리스트 조회
	public String selectMoreClub(SeeMoreVO smVO, int currentPage1){
		double limitdata=8.0; //한화면에 보여줄 최대 데이터 개수 
		
		int currentPage=currentPage1; //현재 페이지
		double pageCount=5; //최대 페이지 개수
		double pageGroup=Math.ceil(currentPage / pageCount); //페이지 그룹
		double lastNum=pageGroup*pageCount;
		double firstNum=lastNum-(pageCount - 1);
		
		
		
		
		
		List<ClubSalesDomain> list=null;
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("resultFlag", false);
		int i=0;
		try {
        list=cDAO.selectMoreClub(smVO);
        jsonObj.put("resultFlag", true);
        jsonObj.put("totaldata", list.size()); //총 데이터 개수
        jsonObj.put("pageCnt", Math.ceil(list.size()/limitdata)); //페이지개수
		
		jsonObj.put("currentPage",currentPage ); //현재페이지
		jsonObj.put("pageGroup", pageGroup); //페이지그룹
		jsonObj.put("lastNum", lastNum); //페이지개수
		jsonObj.put("firstNum",firstNum ); //페이지개수
        
        
        
        JSONArray jsonArr = new JSONArray();
        JSONArray jsonArr1 = new JSONArray();
        JSONObject jsonTemp = null;
        JSONObject jsonTemp1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("M.d.(E) a hh시 ");
        
        for(ClubSalesDomain csd : list) {
        	jsonTemp = new JSONObject();
        	jsonTemp.put("club_Img",csd.getClub_Img());
        	jsonTemp.put("club_name",csd.getClub_name());
        	jsonTemp.put("area_name",csd.getArea_name());
        	jsonTemp.put("club_Num",csd.getClub_Num());
        	jsonTemp.put("price",csd.getPrice());
        	jsonTemp.put("category_Num",csd.getCategory_Num());
        	jsonTemp.put("club_Date",sdf.format(csd.getClub_Date()));
        	jsonTemp.put("USER_COUNT", csd.getUSER_COUNT());
        	jsonTemp.put("number_People", csd.getNumber_People());
        	if(!csd.getUserInfo().isEmpty()) {
        		jsonArr1=new JSONArray();
        		for(UserDomain ud : csd.getUserInfo()) {
        			
        			jsonTemp1 = new JSONObject();
        			jsonTemp1.put("USER_IMG", ud.getUSER_IMG());
        			jsonTemp1.put("USER_ID", ud.getUSER_ID());
        			jsonArr1.add(jsonTemp1);
        		}
        		jsonTemp.put("userInfo",jsonArr1);
        	}
        		
        	
        	jsonArr.add(jsonTemp);
        }
        jsonObj.put("data", jsonArr);
        
        
		}finally {
					
		}
		
		
		return jsonObj.toJSONString();
	}




}
