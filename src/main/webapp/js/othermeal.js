/**
  * 
  */
 
 function otherMealPage(){
 	$.ajax({
 		type:"POST",
 		url:'/meallog/meal/shareMealList.do',
 		success:function(result){
 			var content = "";
 			for(i=0; i<result.length; i++){
 				content += '<div class="col-md-4">';
 				content += '<p>제목:' + result[i].name + '</p>';
 				content += '<p>게시자 : ' + result[i].username + '</p>';
 				content += '<img src = "'+ result[i].picpath + '" style = "width:400px;height:400px">';
 				content += '<p>' + result[i].eatdate + '</p>';
 				content += '</div>';
 			}
 			$("#section2").html(content);
 		}
 	})
 } 