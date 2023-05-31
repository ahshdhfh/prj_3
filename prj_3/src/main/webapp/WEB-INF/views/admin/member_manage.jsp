<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 부트스트랩 시작  -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<!-- 부트스트랩 끝  -->
<link rel="stylesheet" type="text/css" href="http://localhost/prj_3/css/admin.css">
<style type="text/css">


/* 화면구성  */
#wrap { width: 1200px; height: 800px; margin: 0px auto;  }
#header { width: 200px; height: 800px; float: left; background-color: #F7A144;    }
#container { width: 990px; height: 800px;  float: right;    }
#con_header { height: 60px;    }
#con_main {height: 740px; position: relative; }

/* 테이블 */
#search_commdb { position: absolute; top:50px; left: 30px;  }
.table_comm{ border: 1px solid #333;}
.btn_search{ background: #F7A144; color: white; width: 70px; height: 35px;  }
.text_search { border: 1px solid #333; height: 30px; }

td{border: 1px solid #333; text-align: center }
tr{ height: 30px; }
#table_div{ position: absolute; top:100px; left: 30px; }
.table_header{ background: #F7A144; color: white; font-weight: bold; font-size: 15px; }

/* 메뉴 */
.col_tit {font-size: 20px; color: #000000; padding-bottom: 10px;}
.menu { }
.menu li {position: relative;}
.menu li a {
    font-size: 16px; text-transform: uppercase;
    color: black;
    font-weight:bold;
    border-bottom: 1px solid #dbdbdb;
    padding: 10px; display: block;
    transition: box-shadow 0.34s ease, background 0.34s ease;
}
.menu li a i {
    position: absolute; right: 10px; top: 15px;
}
.menu li a:hover {
    box-shadow: inset 200px 0 0 0 rgba(255, 102, 51,0.6);
    color: #fff;
    background: #F7A144;
}


</style>
<!-- jQuery CDN 시작 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!-- jQuery CDN 끝 -->
<script type="text/javascript">

$(function(){

	jquery();
	
});//ready

function search() {
	var obj=document.frm;
	var search=obj.id.value;
	jquery(search);	
}


function jquery(search) {
	var searchInput="";
	
	if(!search==""){		
	searchInput=search;
	}
	
	var jsonParam={userid : searchInput};
	$.ajax({
		url : "memberlistajax.do",
		dataType: "JSON",
		data : jsonParam,
		error : function( xhr ){
			console.log( xhr.status );
		},
		success : function ( jsonObj ){
			var tbody="";	
			let cnt=0;
			http://localhost/prj_3/hostlist.do
		    if($("#table tr").length >1){
		    	$("#table > tbody").empty(); //tbody 모두 삭제
		    }//end if
			
			$.each( jsonObj.data,function(idx, ele){
				cnt++;
				
			tbody+="<tr><td>"+
						(idx + 1)+"</td><td>"+	
						"<a href='../othersMypageHost.do?userId="+ele.user_id+"'>"+ele.user_id+"</td><td></a>"+
						ele.nickname+"</td><td>"+
						ele.addr+"</td><td>"+
						ele.create_date+"</td></tr>";																
			} );//each
								
			if( cnt ==0 ){
				tbody="<tr><td colspan='5'>해당하는 회원이 없습니다.</td><tr>"
			}//end if
			
			$("#table:last").append( tbody );
			$("#id").val()="";
		}//success
	});//ajax
}//jquery


</script>
</head>
<body>
<div id="wrap" > <!-- wrap( w: 800 x h:950 ) -->
		<div id="header" ><!-- header(w: 200 x h : 1400 ) --> 
		<div>
		<a href="http://localhost/prj_3/admin/dashboard.do"><img src="http://localhost/prj_3/images/logo.png" width="150"></a>
		</div>
		<br>
		 <h4 class="col_tit"><i class="fa fa-bars" aria-hidden="true"></i>Menu</h4>
		       <div class="menu">
               		<ul>
                              <li><a href="http://localhost/prj_3/admin/dashboard.do"><i class="fa fa-th-large" aria-hidden="true" style="position: absolute; left: 1px; top: 10px;" ></i>&ensp;Dashboard <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                              <li><a href="http://localhost/prj_3/admin/memberlist.do"><i class="fa fa-user" aria-hidden="true" style="position: absolute; left: 1px; top: 10px;" ></i>&ensp;회원관리 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                              <li><a href="http://localhost/prj_3/admin/clublist.do"><i class="fa fa-cart-plus" aria-hidden="true" style="position: absolute; left: 1px; top: 10px;" ></i>&ensp;모임관리 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                              <li><a href="http://localhost/prj_3/admin/reviewlist.do"><i class="fa fa-commenting-o" aria-hidden="true" style="position: absolute; left: 1px; top: 10px;" ></i>&ensp;후기관리 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                              <li><a href="http://localhost/prj_3/admin/adminlogin.do"><i class="fa fa-sign-out" aria-hidden="true" style="position: absolute; left: 1px; top: 10px;" ></i>&ensp;로그아웃 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a></li>
                  	</ul>
                </div>
		</div>
		<div id="container"><!-- container(w: 800 x h : 600 ) -->
			<div id="con_header" >
			<div class="dash">
			<br>
			<h1>회원관리</h1>
			<hr>
			</div>
			</div>
			<div id="con_main">
			
			<div id="search_commdb" >
			<form action="memberlist.do" name="frm" id="frm">
			<label  >아이디조회</label>
			<i class="fa fa-search fa-lg" aria-hidden="true" ></i>
			<input type="text" class="text_search" name="userid" id="id">
			<input type="button" value="확인" class="btn_search" style="float: right;border-radius:5px" onclick="search()">
			</form>
			</div>
			
				<div id=table_div style="overflow: auto; height: 500px">
				<table class="table_comm" cellspacing="0" id="table">
				<thead>
				<tr class="table_header">
				<td width="80">회원번호</td>
				<td width="80">아이디</td>
				<td width="80">닉네임</td>
				<td width="400">주소</td>
				<td width="120">가입일</td>
				</tr>
				</thead>
	
				</table>
				</div>
			</div>
		</div>
</div>
</body>
</html>