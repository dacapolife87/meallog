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

function testFunc(){
	$.ajax({
		type:"POST",
		url:'/meallog/meal/test.do',
		success:function(result){
			var content = "";
			for(i=0; i<result.length; i++){
				content += '<div class="col-md-4">';
				content += '<p>' + result[i].name + '</p>';
				content += '<img src = "'+ result[i].picpath + '" style = "width:400px;height:400px">';
				content += '<p>' + result[i].eatdate + '</p>';
				content += '</div>';
			}
			$("#section1").html(content);
		}
	})
}
