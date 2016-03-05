<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<%   
response.setHeader("cache-control","no-store"); // http 1.1   
response.setHeader("Pragma","no-cache"); // http 1.0   
response.setDateHeader("Expires",0); // proxy server 에 cache방지.   
%>  

<%@ include file="/WEB-INF/include/include-header.jspf" %>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Meal Login Page</title>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <style>
  .jumbotron {
      background-color: #f4511e;
      color: #fff;
  }
  </style>

</head>
<body>

<div class="jumbotron text-center">
  <h1>Temp Meal Project</h1> 
  <p>Test Page</p>

  <br/><br/><br/><br/><br/><br/> 
  <form class="form-inline">
    <button type="button" class="btn btn-primary" id="logInBtn">Login</button>
    <button type="button" class="btn btn-primary" id="signUpBtn">Sign Up</button>
    <br><br>
    <a id="kakao-login-btn"></a>
  	<script>
      // 사용할 앱의 JavaScript 키를 설정해 주세요.
      Kakao.init('52051eff6e1983acbaa448f833bf0493');

      // 카카오 로그인 버튼을 생성합니다.
      Kakao.Auth.createLoginButton({
        container: '#kakao-login-btn',
        success: function(authObj) {
        	var comSubmit = new ComSubmit();
        	comSubmit.addParam("authObj.access_token",authObj.access_token)
            comSubmit.setUrl("<c:url value='/kakaologin.do' />");
            comSubmit.submit();

        },
        fail: function(err) {
          alert(JSON.stringify(err));
          alert("kakao btn - fail");
        }
      });
    </script>
  	
  </form>
</div>

  <div class="modal fade" id="loginModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Login Modal Content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
        </div>
        <div class="modal-body" style="padding:40px 50px;">
          <form role="form" id="logInfrm">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> UserId</label>
              <input type="text" class="form-control" id="username" name="userID" placeholder="Enter UserId">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="password" class="form-control" id="psw" name="userPW" placeholder="Enter password">
            </div>
              <button type="submit" class="btn btn-success btn-block" id="login"><span class="glyphicon glyphicon-off"></span> Login</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          <p>Not a member? <a href="#" id="loginWin_signUp">Sign Up</a></p>
          <p>Forgot <a href="#">Password?</a></p>
        </div>
      </div>
      
    </div>
  </div>


  <div class="modal fade" id="signupModal" role="dialog">
    <div class="modal-dialog">

    <!-- SignUp Modal Content-->
    <div class="modal-content">
      <div class="modal-header" style="padding:35px 50px;">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4><span class="glyphicon glyphicon-user"></span>Sing Up</h4>
      </div>
      <div class="modal-body" style="padding:40px 50px;">
        <form role="form" id="signUpfrm">
          <div class="form-group">
            <label for="usrname"><span class="glyphicon glyphicon-user"></span>UserId</label>
            <!-- Check exsist ID -->
            <button type="button" class="btn btn-info btn-xs pull-right" id="idcheck">Check Id</button>
            <input type="text" class="form-control" onkeyup="idCheck()" id="signUp_userID" name="signUp_userID" placeholder="Enter UserId">
    
          </div>
          
          <div class="form-group">
            <label for="usrmail"><span class="glyphicon glyphicon-user"></span>UserMail</label>
            <input type="text" class="form-control" id="signUp_userMail" name="signUp_userMail" placeholder="Enter UserMail">
          </div>
          <div class="form-group">
            <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Password</label>
            <button type="button" class="btn btn-info btn-xs pull-right" id="pwcheck">Check PW</button>
            <input type="password" class="form-control" onkeyup="pwCheck()" id="signUp_psw" name="signUp_psw" placeholder="Enter Password">
          </div>
          <div class="form-group">
            <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Password Check</label>
            <input type="password" class="form-control" onkeyup="pwCheck()" id="signUp_pswCheck" placeholder="Enter Password Again">
          </div>
            <button type="submit" class="btn btn-success btn-block" id="signup"><span class="glyphicon glyphicon-ok-sign"></span>Sign Up</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
        </div>
    </div>
  </div>
	
<%@ include file="/WEB-INF/include/include-body.jspf" %>


<script type="text/javascript">
$(document).ready(function(){
	/// kakao
	/*
	Kakao.init("52051eff6e1983acbaa448f833bf0493"); 
		function getKakaotalkUserProfile(){ 
			Kakao.API.request({ 
				url: '/v1/user/me', 
				success: function(res) { 
					$("#kakao-profile").append(res.properties.nickname); 
					$("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"})); 
				}, 
				fail: function(error) { 
					console.log(error); 
				} 
			}); 
		} 
		function createKakaotalkLogin(){ 
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove(); 
			var loginBtn = $("<a/>",{"class":"kakao-login-btn","text":"로그인"}); 
			loginBtn.click(function(){ 
				Kakao.Auth.login({ 
					persistAccessToken: true, 
					persistRefreshToken: true, 
					success: function(authObj) { 
						getKakaotalkUserProfile(); 
						createKakaotalkLogout(); 
					}, 
					fail: function(err) { 
						console.log(err); 
					} 
				}); 
			}); 
			$("#kakao-logged-group").prepend(loginBtn) 
		} 
		function createKakaotalkLogout(){ 
			$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove(); 
			var logoutBtn = $("<a/>",{"class":"kakao-logout-btn","text":"로그아웃"}); 
			logoutBtn.click(function(){ 
				Kakao.Auth.logout(); 
				createKakaotalkLogin(); 
				$("#kakao-profile").text(""); 
			}); 
			$("#kakao-logged-group").prepend(logoutBtn); 
		} 
		if(Kakao.Auth.getRefreshToken()!=undefined&&Kakao.Auth.getRefreshToken().replace(/ /gi,"")!=""){ 
			createKakaotalkLogout(); 
			getKakaotalkUserProfile(); 
		}else{ 
			createKakaotalkLogin(); 
		}
		*/
		///// kakao
			$("#logInBtn").click(function(){
				$("#loginModal").modal();
			});
			$("#signUpBtn").click(function(){
				$("#signupModal").modal();
			});
			$("#loginWin_signUp").click(function(){
				$("#signupModal").modal();
			});
			$("#signUpBtn_kakao").on("click", function(e){ //작성하기 버튼
				fn_kakaoLogin();
			});
			$("#login").on("click", function(e){ //작성하기 버튼
				e.preventDefault();
				fn_userLogin();
			});
			$("#signup").on("click", function(e){ //작성하기 버튼
				if(bidCheck==false){
					alert("사용 중인 ID 입니다.");
					$("#signupModal").modal();
				}else if(bpwCheck==false){
					alert("비밀번호가 잘못 되었습니다.");
					$("#signupModal").modal();
				}else{
					alert("회원가입 성공!");
					e.preventDefault();
					fn_userSignUp();
				}
			});
		}
);
function fn_kakaoLogin(){
	
}
function createKakaotalkLogin(){ 
	$("#kakao-logged-group .kakao-logout-btn,#kakao-logged-group .kakao-login-btn").remove(); 
	var loginBtn = $("<a/>",{"class":"kakao-login-btn","text":"로그인"}); 
	loginBtn.click(function(){ 
		Kakao.Auth.login({ 
			persistAccessToken: true, 
			persistRefreshToken: true, 
			success: function(authObj) { 
				getKakaotalkUserProfile(); 
			}, 
			fail: function(err) { 
			console.log(err); 
			} 
		}); 
	}); 
	$("#kakao-logged-group").prepend(loginBtn) 
} 

function fn_userLogin(){
	var mymeal = new myMealPage();
    var comSubmit = new ComSubmit("logInfrm");
    comSubmit.setUrl("<c:url value='/meallogin.do' />");
    comSubmit.submit();
}

function fn_userSignUp(){
    var comSubmit = new ComSubmit("signUpfrm");
    comSubmit.setUrl("<c:url value='/joinMember.do' />");
    comSubmit.submit();
}



var bidCheck = false;
var bpwCheck = false;
var elem;
var icon;

function idCheck(){
	var user_id = $('#signUp_userID').val();
    $.ajax({
        type: "GET",
        url: "/meallog/idCheck.do"+"?user_id="+user_id,
        //data: "user_id="+user_id,                    
        datatype: "json",
        success: function(data)
        {
        	getResult(data)
        }
    });
}

function getResult(isUse){
	elem =  document.getElementById("signup");
	icon = document.getElementById("idcheck");
	if(isUse==true){
		// 아이디 사용중일 경우
		icon.innerHTML="사용불가";
		elem.disabled = true;
		icon.className="btn btn-danger btn-xs pull-right";
		bidCheck = false;
	}else{
		document.getElementById("idcheck").innerHTML="사용가능";
		if(bpwCheck == true){
			elem.disabled = false;
		}
		icon.className="btn btn-info btn-xs pull-right";
		bidCheck = true;
	}
}
function pwCheck(){
	var pw1=document.getElementById("signUp_psw").value;
	var pw2=document.getElementById("signUp_pswCheck").value;
	elem =  document.getElementById("signup");
	icon = document.getElementById("pwcheck");
	if(pw1==pw2){
		document.getElementById("pwcheck").innerHTML="비밀번호일치";
		icon.className="btn btn-info btn-xs pull-right";
		if(bidCheck == true){
			elem.disabled = false;
		}
		bpwCheck = true;
	}else{
		document.getElementById("pwcheck").innerHTML="비밀번호불일치";
		icon.className="btn btn-danger btn-xs pull-right";
		elem.disabled = true;
		bpwCheck = false;
	}
}



	
  
  
</script>

</body>
</html>
