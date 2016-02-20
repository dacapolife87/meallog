/**
 * 
 */

function otherMealPage(){
	$.ajax({
		type:"POST",
		url:'/meallog/meal/test2.do',
		success:function(result){
			var content = "";
			for(i=0; i<result.length; i++){
				content += '<div class="col-md-4">';
				content += '<p>' + result[i].name + '</p>';
				content += '<img src = "'+ result[i].picpath + '" style = "width:400px;height:400px">';
				content += '<p>' + result[i].eatdate + '</p>';
				content += '<p>' + result[i].picpath + '</p>';
				content += '</div>';
			}
			$("#section2").html(content);
		}
	})
}