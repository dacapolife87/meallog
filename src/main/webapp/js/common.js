function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;    
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}
function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
     
    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){
        $("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
        var frm = $("#"+this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();   
    };
}

function kakao() {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
     
    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){
        $("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
        var frm = $("#"+this.formId)[0];
        frm.action = this.url;
        frm.method = "post";
        frm.submit();   
    };
}


function myMealPage(){
	var array = new Array();
	$.ajax({
		type:"POST",
		url:'/meallog/meal/userMealList.do',
		success:function(result){
			var content = "";
			var username;
			var idx;
			for(i=0; i<result.length; i++){
				username = result[i].username;
				idx = result[i].idx;
				content += '<div class="col-sm-4 text-center">';
				content += '<div class="thumbnail">';
				content += '<a onclick="showImageModal(' + idx+ ')">';
				content += '<img src = "'+ result[i].picpath + '" style = "width:400;height:300"></img></a>';
				content += '<p><strong>' + result[i].name + '</strong></p>';
				content += '</div>';
				content += '</div>';

			}
			$("#section1").html(content);
		}
	})
}

function delMyMeal(){
	alert("delMyMeal Click");
}


