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
 
 
 // 클릭시 ohermeal에 올라온 사진에 추천 
 function recommendMeal(x){
		var IDX = x;
		var RECOMMEND; // 보내는 값이 true 일때 : 추천 해제 , 보내는 값이 false 일때 : 추천 
		
		
		if(checkRecommend == true){
			$("#recommendMeal").attr('class', 'glyphicon glyphicon-heart-empty pull-left');
			checkRecommend = false;
			RECOMMEND = true;
		}else{
			$("#recommendMeal").attr('class', 'glyphicon glyphicon-heart pull-left');
			checkRecommend = true;
			RECOMMEND = false;
		}
		
		
		$.ajax({
			type:"GET",
			data:{"IDX" : IDX, "checkRecommend" : RECOMMEND},
			url:"/meallog/meal/mealRecommend.do",
			success:function(result){
				
			}
		})
}