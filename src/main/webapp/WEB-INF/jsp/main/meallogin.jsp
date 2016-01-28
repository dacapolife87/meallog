<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <!-- Theme Made By www.w3schools.com - No Copyright -->
  <title>Meal Login Page</title>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
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
          <form role="form">
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> UserId</label>
              <input type="text" class="form-control" id="username" placeholder="Enter UserId">
            </div>
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
              <input type="password" class="form-control" id="psw" placeholder="Enter password">
            </div>
              <button type="submit" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          <p>Not a member? <a href="#">Sign Up</a></p>
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
        <form role="form">
          <div class="form-group">
            <label for="usrname"><span class="glyphicon glyphicon-user"></span>UserId</label>
            <!-- Check exsist ID -->
            <button type="button" class="btn btn-info btn-xs pull-right">Check Id</button>
            <input type="text" class="form-control" id="signUp_userID" placeholder="Enter UserId">
          </div>
          
          <div class="form-group">
            <label for="usrmail"><span class="glyphicon glyphicon-user"></span>UserMail</label>
            <input type="text" class="form-control" id="signUp_userMail" placeholder="Enter UserMail">
          </div>
          <div class="form-group">
            <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Password</label>
            <input type="password" class="form-control" id="signUp_psw" placeholder="Enter Password">
          </div>
          <div class="form-group">
            <label for="psw"><span class="glyphicon glyphicon-eye-open"></span>Password Check</label>
            <input type="password" class="form-control" id="signUp_pswCheck" placeholder="Enter Password Again">
          </div>
            <button type="submit" class="btn btn-success btn-block" id="signup"><span class="glyphicon glyphicon-ok-sign"></span>Sign Up</button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
        </div>
    </div>
  </div>


<script type="text/javascript">
  $(document).ready(function(){
    $("#logInBtn").click(function(){
      $("#loginModal").modal();
    });

    $("#signUpBtn").click(function(){
      $("#signupModal").modal();
    });
    
    $("#signup").click(function(){
        $("#loginModal").on("click", function(e){ //목록으로 버튼
            e.preventDefault();
        
            fn_userSignUp();
        });
    });
  });
  
  
  function fn_userSignUp(){
      var comSubmit = new ComSubmit("signupModal");
      comSubmit.setUrl("<c:url value='/joinMember.do' />");
      comSubmit.submit();
  }
</script>

</body>
</html>
