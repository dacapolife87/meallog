/**
  * 
  */
 
 function otherMealPage(){
 	$.ajax({
 		type:"POST",
 		url:'/meallog/meal/shareMealList.do',
 		success:function(result){
 			var content = "";
 			var idx;
 			for(i=0; i<result.length; i++){
 				idx = result[i].idx;
 				content += '<div class="col-sm-4 text-center">';
				content += '<div class="thumbnail">';
				content += '<a onclick="showImageModal(' + idx + ')">';
				content += '<img src = "'+ result[i].picpath + '" style = "width:400;height:300"></img></a>';
				content += '<p><strong>' + result[i].name + '</strong></p>';
				content += '</div>';
				content += '</div>';
 			}
 			$("#section2").html(content);
 		}
 	})
 } 