<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="meallog.user.vo.Member"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%   
response.setHeader("cache-control","no-store"); // http 1.1   
response.setHeader("Pragma","no-cache"); // http 1.0   
response.setDateHeader("Expires",0); // proxy server 에 cache방지.   
%>  


<%@ include file="/WEB-INF/include/include-header.jspf" %> 
<title>Meal First Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script>
 	$(document).ready(function(){
 		// This is the simple bit of jquery to duplicate the hidden field to subfile
		
 		myMealPage();

 		$('#pdffile').change(function(){
			$('#subfile').val($(this).val());
		});

		// This bit of jquery will show the actual file input box
		$('#showHidden').click(function(){
			$('#pdffile').css('visibilty','visible');
		});

		$("#pdffile").on('chang', function(){
			readURL(this);
		});
		
		function readURL(input){
			if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                    $('#preview_img').attr('src', e.target.result);
                }

              reader.readAsDataURL(input.files[0]);
            }
		}
 	});
 	</script>

</head>
  <style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
	<body>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Home</a>
				</div>
				<ul class="nav navbar-nav"   style="float:right;">
					<li  class="navbar-brand">
						<div style="color:white">
							<c:if test="${!empty sessionScope.member and !empty sessionScope.member.email}">${sessionScope.member.nick} 님 환영합니다.
						</div>
						</c:if>
					</li>
					<li class="active">
						<a href="#" id="logout">Logout</a>
					</li>
				</ul>
			</div>
		</nav>
		<div class="container-fluid">
			<div class="row content">
				<div class="col-sm-2 sidenav">
					<h4>Category</h4>
					<ul class="nav nav-pills nav-stacked nav-tabs">
						<li class="active" onClick="myMealPage()">
							<a data-toggle="tab" href="#section1">My_Meal</a>
						</li>
						<li onClick="otherMealPage()">
							<a data-toggle="tab" href="#section2">Other_Meal</a>
						</li>
						<li>
							<a data-toggle="tab" href="#section3">Average_Meal</a>
						</li>
						<li>
							<a data-toggle="tab" href="#section4">Upload</a>
						</li>
						<li>
							<a data-toggle="tab" href="#setting">Setting</a>
						</li>
					</ul>
				<br>
				</div>
				<div class="col-sm-10">
					<div class="tab-content">
						<div id="section1" class="tab-pane fade in active row">
						</div>
						<div id="section2" class="tab-pane fade row">
						</div>
						<div id="section3" class="tab-pane fade">
							<p>Average_Meal</p>
						</div>
						<!------------------------------------------------------------------
						* 파일 업로드 부분
						*
						-------------------------------------------------------------------->
						<div id="section4" class="tab-pane fade">
							<div class="jumbotron text-center">
								<p>Upload Your Meal</p>
								<img id="preview_img" src="#" width="400" height="300"/>
								<form role="form" id="mealfrm" name="mealfrm" enctype="multipart/form-data">
								<div class="form-group pull-left">
									<label class="pull-left">Subject</label>
									<br/>
									<input type="text" size="50" id="NAME" name="NAME"></input>
								</div>
								<br/><br/><br/>
								<input type="file" name="file" style="visibility:hidden;" id="pdffile" />
								<div class="form-group pull-left">
									<label class="pull-left">Upload Image</lavel>
									<br/>
									<input type="text" id="subfile" class="input-xlarge" size="50">
									<a class="btn btn-info" onclick="$('#pdffile').click();">Browse</a>
								</div>
								<br/><br/><br/><br/>
								<div class="form-group pull-left">
									<label class="pull-left">Date </label><br/>
									<input type="date" id="eatdate" name="eatdate" min="1979-12-31"></input>
								</div>
								<br/><br/><br/><br/>
								<div class="form-group pull-left">
									<label for="sel_Time" class="pull-left">Eat Time</label><br/>
									<select class="form-control pull-left" id="sel1" id="WHENEAT" name="WHENEAT">
									<option>Morning</option>
									<option>AfterNoon</option>
									<option>Evening</option>
									</select>
								</div>
								<br/><br/><br/><br/>
								<div class="form-group pull-left">
									<label class="pull-left">Food Category</label><br/>
									<input type="text" id="CATEGORY" name="CATEGORY"></input>
								</div><br/><br/><br/><br/>
								<div class="form-group ">
									<label class="pull-left">Comment</label>
									<textarea class="form-control" rows="5" id="CONTENT" name="CONTENT"></textarea>
								</div><br/><br/><br/>
								<div class="form-group checkbox">
									<label class="pull-left"><input type="checkbox" id="SHARE" name="SHARE" value="1"><span class="glyphicon glyphicon-share"></span>Share</label>
								</div>
								<button type="submit" class="btn btn-success btn-default" id="mealsubmit">Submit</button>
								<button type="submit" class="btn btn-danger btn-default">Cancel</button>
								</form>
							</div>
						</div>
						<!------------------------------------------------------------------
						* 파일 업로드 부분
						* 개인정보 수정
						-------------------------------------------------------------------->
						<div id="setting" class="tab-pane fade">
      						<p>Settings</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content" id="modalbody">
					<div class="modal-header">
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
          				<h4 class="modal-title" id="modalSubject"></h4>
          				<h4 id="modalUser"></h4>
        			</div>
        			<div class="modal-body" align="center">
          				<p>Some text in the modal.</p>
          				<img src ="" style="width:400px;height:400px" id="modalImage" /></br>
          				<p id="modalComment"></p>
      				</div>
        			<div class="modal-footer" id="modalFooter">
          				<button align="left" type="button" class="btn btn-default" onclick="delMyMeal()">Delete</button>
          				<button align="left" type="button" class="btn btn-default">Edit</button>
          				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/include/include-body.jspf" %>
		<script type="text/javascript">
		$("#logout").on("click", function(e){ //작성하기 버튼
		    e.preventDefault();
		    fn_userLogOut();
		});
		$("#mealsubmit").on("click", function(e){ //작성하기 버튼
		    e.preventDefault();
		    fn_insertMeal();
		});
		
		
		function fn_userLogOut(){
		      var comSubmit = new ComSubmit("");
		      comSubmit.setUrl("<c:url value='/meal/logout.do' />");
		      comSubmit.submit();
		  }
		function fn_insertMeal(){
		    var comSubmit = new ComSubmit("mealfrm");
		    comSubmit.setUrl("<c:url value='/meal/mealUploadList.do' />");
		    comSubmit.submit();
		}
		
		
		$(function(){
		    $( "#CATEGORY" ).autocomplete({
		        source : function( request, response ) {
		             $.ajax({
		                    type: 'post',
		                    url: "/meal/autocompleteMeal.do",
		                    dataType: "json",
		                    //request.term = $("#autocomplete").val()
		                    data: { value : request.term },
		                    success: function(data) {
		                        //서버에서 json 데이터 response 후 목록에 뿌려주기 위함
		                        response( 
		                            $.map(data, function(item) {
		                                return {
		                                    label: item.data,
		                                    value: item.data
		                                }
		                            })
		                        );
		                    }
		               });
		            },
		        //조회를 위한 최소글자수
		        minLength: 2,
		        select: function( event, ui ) {
		            // 만약 검색리스트에서 선택하였을때 선택한 데이터에 의한 이벤트발생
		        }
		    });
		})
		
		//사진 클릭시 전달되게 되는 idx 넘버 -- 사진 넘버
		//전역 변수
		var picIdx;
		var checkUser;
		//각 사진 클릭시 호출되는 함수
		function showImageModal(x){
			picIdx = x;
			alert("testFunc Call Alert");
			$("#exampleModal").modal("show");
		}
		
		//modal을 불러오기 전에 실행되는 함수
		$("#exampleModal").on('show.bs.modal', function(event){
			$.ajax({
				type:"GET",
				data:{"picIdx" : picIdx},
				url:'/meallog/meal/test3.do',
				success:function(result){
					alert("test3.do Succe");
					var subject = document.getElementById("modalSubject");
					var username = document.getElementById("modalUser");
					var imagePath = document.getElementById("modalImage");
					var comment = document.getElementById("modalComment");
					var modalfooter = document.getElementById("modalFooter");
					
					subject.innerHTML = "Subject : " + result.name;
					username.innerHTML = "Upload User :" + result.username;
					imagePath.src = result.picpath;
					comment.innerHTML = result.content;
					
				}
			})
			
		});
		</script>
	</body>
</html>