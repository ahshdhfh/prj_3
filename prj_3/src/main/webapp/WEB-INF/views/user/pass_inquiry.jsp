<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<style type="text/css">
#container{  height: 700px;position: relative; }
/* container에 position:relative가 설정되었고  container의 자식 div인 
loginBox는 container의 시작점이 loginBox의 시작좌표가된다.
  */
.inputBox_email1{width:130px;height: 50px;border: 2px solid #333; border-radius: 10px;color:#5E5E5E;cursor: pointer;}
.inputBox_email2{width:125px;height: 50px;border: 2px solid #333; border-radius: 10px;color:#5E5E5E;cursor: pointer;}

#footer{  height: 300px;}

#joinMembership{width:300px; height: 56px; text-align: center; font-size: 37px; font-weight: bold; 
				position: absolute; left: 800px; top: 9px; border: 2px solid #333; border-radius: 10px;color:#5E5E5E}
#logo{width:202px; height: 54px;position: absolute; left: 128px; top: 13px;}
#checkbox1{position: absolute; left: 700px; top: 130px;}
#checkbox2{position: absolute; left: 700px; top: 200px;}
#checkbox3{position: absolute; left: 700px; top: 450px;}
#colorBtn{position: absolute; left: 950px; top: 15px; width:180px;height: 50px;font-size:20px;
		border: 1px solid #333;cursor: pointer;background-color: #F7A144;color:#FFF;font-weight: bold;border-radius: 10px;}
#whiteBtn{position: absolute; left: 770px; top: 15px; width:180px;border-radius: 10px;font-size:20px;
		height: 50px;border: 1px solid #333;cursor: pointer;background-color: #fff;color:#333;font-weight: bold}
#div1{position: absolute;width:500px;height:30px;left:710px;top: 130px;border-bottom: 3px solid #5E5E5E}
#div2{position: absolute;width:500px;height:30px;left:710px;top: 200px;}
#div3{position: absolute;width:500px;height:30px;left:710px;top: 280px;}
#div4{position: absolute;width:500px;height:30px;left:710px;top: 360px;}
#font1{font-size:20px;font-family: "맑은고딕"; font-weight:bold; color: #333333;  }
#font2{font-size:10px;font-family: "맑은고딕"; font-weight:bold; color: #E88935;  }
#font3{font-size:22px;font-family: "맑은고딕"; font-weight:bold; color: #333333;  }
#textarea{width:500px;height: 150px;}
#inputBox1{width:415px;height: 50px;border: 2px solid #333; border-radius: 10px;color:#5E5E5E;font-size: 15px;cursor: pointer;}
#inputBox2{width:200px;height: 50px;border: 2px solid #333; border-radius: 10px;color:#5E5E5E;font-size: 15px;cursor: pointer;}
#inputBox3{width:185px;height: 50px;border: 2px solid #333; border-radius: 10px;color:#5E5E5E;font-size: 15px;cursor: pointer;}
#nextBtn{width:500px;height: 50px;background-color: #333333; color: #FFF;font-size: 18px;font-weight:bold;
		border-radius: 10px;position: absolute; left:710px; top:450px;cursor: pointer;}
</style>
<!-- bootstrap 시작-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
<!-- bootstrap 끝-->
<link rel="stylesheet" type="text/css" href="http://localhost/prj_3/css/headerFooter.css">
<link rel="stylesheet" type="text/css" href="http://localhost/prj_3/css/main.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
function nullCheck() {
	var obj = document.frm;
	if(obj.name.value==""){
		alert("이름을 입력해주세요");
		obj.name.focus();
		return;
	}
	if(obj.id.value==""){
		alert("아이디를 입력해주세요");
		obj.id.focus();
		return;
	}
	if(obj.email.value==""){
		alert("이메일을 입력해주세요")
		obj.email.focus();
		return;
	}
	if(obj.email2.value=="선택없음"){
		alert("포털사이트를 선택해주세요")
		obj.email2.focus();
		return;
	}
	$("#frm").submit();
}//nullCheck

$(function(){
	if($("#flag").val()==1 ){
		alert("일치하는 정보가 없습니다. 다시 확인해주세요.")
	}//end if
	  $('#email_select').change(function(){
	   if($('#email_select').val() == "1"){//직접입력
	    $("#last_email").val(""); //값 초기화
	    $("#last_email").attr("readonly",false);
	   } else if($('#email_select').val() == ""){//선택없음
	    $("#last_email").val(""); //값 초기화
	    $("#last_email").attr("readonly",true); //textBox 비활성화
	   } else {
	    $("#last_email").val($('#email_select').val()); //도메인선택
	    $("#last_email").attr("readonly",true); //비활성화
	   }
	  });
	 });
</script>
</head>
<body>

<div class="wrap"> <!-- wrap ( w : 900 x h : 1200) -->
<div class="header">
<jsp:include page="/header.do"/>
</div>

<form id="frm" name="frm" action="pass_inquiry_process.do" method="post">
<div id="container">
	<input type="hidden" id="flag"value="${flag}"/>
	<input type="button" id="colorBtn" value="비밀번호찾기" >
	<a href="find_id.do">
	<input type="button" id="whiteBtn" value="아이디찾기" >
	</a>
	<div id="div1">
		<font id="font3">비밀번호 찾기</font>
	</div>
	<div id="div2">
		<label id="font1">이름 &nbsp&nbsp&nbsp</label>
		<input type="text" id="inputBox1" name="userName" placeholder="이름">
	</div>
	<div id="div3">
		<label id="font1">아이디&nbsp</label>
		<input type="text" id="inputBox1" name="userId" placeholder="아이디" value="<%=request.getParameter("userId")!=null?request.getParameter("userId"):"" %>">
	</div>
	<div id="div4">
		<label id="font1">이메일&nbsp</label>
		<input type="text" class="inputBox_email1" placeholder="이메일" name="email" id="email"><strong>@</strong>
		<input type="text" id="last_email" size="10" class="inputBox_email2" name="email2" >
		<select class="inputBox_email2" id="email_select" name="email_select">
		<option value="" selected>선택없음</option>
		<option value="gmail.com">gmail.com</option>
		<option value="naver.com">naver.com</option>
		<option value="nate.com">nate.com</option>
		<option value="daum.net">daum.net</option>
		<option value="hotmail.com">hotmail.com</option>
		<option value="1">직접 입력</option>
	</select>
	</div>
	<input type="button" id="nextBtn" value="다음" onclick="nullCheck()">
</div><!-- container -->
</form>

<div class="container5">
   <jsp:include page="/footer.do" />
</div><!--container5  -->
</div><!-- wrap -->
</body>
</html>