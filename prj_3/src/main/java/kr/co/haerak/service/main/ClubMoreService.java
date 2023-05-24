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

@Component
public class ClubMoreService   {
	
	
	@Autowired(required = false)
	private ClubDAO cDAO;
	
	
	
	
	//ī�װ��� ������ Ŭ������ �� ����Ʈ ��ȸ
	public String selectMoreClub(int categoryNum){
		
		List<ClubSalesDomain> list=null;
		JSONObject jsonObj=new JSONObject();
		jsonObj.put("resultFlag", false);
		int i=0;
		try {
        list=cDAO.selectMoreClub(categoryNum);
        jsonObj.put("resultFlag", true);
        jsonObj.put("dataSize", list.size());
		
        JSONArray jsonArr = new JSONArray();
        JSONArray jsonArr1 = new JSONArray();
        JSONObject jsonTemp = null;
        JSONObject jsonTemp1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for(ClubSalesDomain csd : list) {
        	jsonTemp = new JSONObject();
        	jsonTemp.put("club_Img",csd.getClub_Img());
        	jsonTemp.put("club_name",csd.getClub_name());
        	jsonTemp.put("area_name",csd.getArea_name());
        	jsonTemp.put("club_Num",csd.getClub_Num());
        	jsonTemp.put("price",csd.getPrice());
        	jsonTemp.put("category_Num",csd.getCategory_Num());
        	jsonTemp.put("club_Date",sdf.format(csd.getClub_Date()));
        	
        	
        	
//        	if(csd.getUserInfo().size()!=0) {
        		
        		for(UserDomain ud : csd.getUserInfo()) {
        			
        			jsonTemp1 = new JSONObject();
        			jsonTemp1.put("USER_IMG", ud.getUSER_IMG());
        			jsonTemp1.put("USER_ID", ud.getUSER_ID());
        			jsonTemp1.put("club_Num",csd.getClub_Num());
        			
        			jsonArr1.add(jsonTemp1);
        		}
        		jsonTemp.put("userInfo",jsonArr1);
        		/*} 
				 * else { jsonTemp1 = new JSONObject(); jsonTemp1.put("USER_IMG"," ");
				 * jsonTemp1.put("USER_ID", " ");
				 * 
				 * jsonArr1.add(jsonTemp1); }
				 */
        	
        	
        	jsonArr.add(jsonTemp);
        }
        
        jsonObj.put("data", jsonArr);
        
        
		}finally {
					
		}
		
		
		return jsonObj.toJSONString();
	}

}
