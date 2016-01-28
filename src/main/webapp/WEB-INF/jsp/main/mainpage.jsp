<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<title>Meal First Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script>
 	$(document).ready(function(){
 		// This is the simple bit of jquery to duplicate the hidden field to subfile
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
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Logout</a></li>
		</ul>
	</div>
</nav>

<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <h4>Category</h4>
      <ul class="nav nav-pills nav-stacked nav-tabs">
        <li class="active"><a data-toggle="tab" href="#section1">My_Meal</a></li>
        <li><a data-toggle="tab" href="#section2">Other_Meal</a></li>
        <li><a data-toggle="tab" href="#section3">Average_Meal</a></li>
        <li><a data-toggle="tab" href="#section4">Upload</a></li>
        <li><a data-toggle="tab" href="#setting">Setting</a></li>
      </ul><br>
    </div>

    <div class="col-sm-10">
      <div class="tab-content">
      	<div id="section1" class="tab-pane fade in active">
      		<p>My_Meal</p>
      	</div>
      	<div id="section2" class="tab-pane fade">
      		<p>Other_Meal</p>
      	</div>
      	<div id="section3" class="tab-pane fade">
      		<p>Average_Meal</p>
      	</div>
      	<div id="section4" class="tab-pane fade">
      		<div class="jumbotron text-center">
      			<p>Upload Your Meal</p>
      			<img id="preview_img" src="#" width="400" height="300"/>
      				<form role="form">
      					<input type="file" name="file" style="visibility:hidden;" id="pdffile" />
						<div class="form-group pull-left">
							<lavel>Upload Image</lavel>
							<input type="text" id="subfile" class="input-xlarge" size="50">
							<a class="btn btn-info" onclick="$('#pdffile').click();">Browse</a>
						</div>
						<br/><br/><br/>
						<div class="form-group pull-left">
							<label class="pull-left">Date </label><br/>
							<input type="date" name="bday" min="1979-12-31"></input>
						</div><br/><br/><br/><br/>
						<div class="form-group pull-left">
							<label class="pull-left">Eat Time</label><br/>
							<input type="text"></input>
						</div><br/><br/><br/><br/>
						<div class="form-group pull-left">
							<label class="pull-left">Food Category</label><br/>
							<input type="text"></input>
						</div><br/><br/><br/><br/>
						<div class="form-group ">
							<label class="pull-left">Comment</label>
							<textarea class="form-control" rows="5" id="comment"></textarea>
						</div><br/><br/><br/>
						<button type="submit" class="btn btn-success btn-default">Submit</button>
						<button type="submit" class="btn btn-danger btn-default">Cancel</button>
      				</form>
      		</div>
      	</div>
      	<div id="setting" class="tab-pane fade">
      		<p>Settings</p>
      	</div>
      </div>
    </div>
  </div>
</div>
</body>
</html>