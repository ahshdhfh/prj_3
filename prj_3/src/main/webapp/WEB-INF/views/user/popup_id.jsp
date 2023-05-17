<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"  href="http://localhost/prj_2/ldk/main.css"/>
<style type="text/css">
#wrap{ width: 510px;height: 310px; margin: 0px auto;}
#idSub{ background: #FFF url('http://localhost/spring_mvc/images/id_background.png');
	width: 502px;height: 303px; position: relative;}
#inputId{ position: absolute;width:320px; top:105px; left: 80px}	
#divResult{ position: absolute;top:200px;left:80px }
</style>
<script type="text/javascript">
window.onload=function(){
	document.getElementById("btn").addEventListener("click",useId);
}
function useId(){
	var obj=document.frm;
	var id = obj.id.value;

	if(validateId(id)){//유효성검증통과
		obj.submit();
	}else{
		alert("아이디는 영문 소문자와 숫자의 조합으로만 4~15글자 내로 설정할 수 있습니다.");
		obj.id.focus();
		return;
	}//end else
	

}//useId

function sendId(id){
	//자식창 에서 부모창으로 값 전달 
	// opener - 부모창 : opener.window.document.폼이름.control명.value=값 
	opener.window.document.frm.userId.value=id;
	//자식창 닫기
	self.close();
}

function validateId(id){
	var regex = /^[a-z0-9]{4,15}$/;
	return regex.test(id);
}
</script>
</head>
<body>
<div id="wrap">

<div id="idSub">
	<div id="inputId">
	<form name="frm" action="popup_id.jsp" method="post">
	<label style="font-size: 20px">아이디</label>
	<input type="text" id="id" name="id" class="inputBox" autofocus="autofocus"
	value="" placeholder="아이디">
	<input type="button" value="중복확인" id="btn" class="btnBorder">
	</form>
	</div>
	

	
</div>

</div>
</body>
