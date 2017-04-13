/**
 * ajax文件上传, domainUrl可以跨域
 * 服务端mvc中不要有 @ResponseBody，无返回值，response.getWriter().write(data);
 * */
function ajaxFileUpload(domainUrl, fileElement, jsonData, callback){
	var $fe = $(fileElement);
    var formData = new FormData();  //H5中可以使用FormData进行文件上传
    formData.append($fe.attr("name"),$fe[0].files[0]);    //将文件转成二进制形式
    if(jsonData){
    	for(var p in jsonData){
    		formData.append(p, jsonData[p]);
    	}
    }
    $.ajax({
        type:"post",
        url:domainUrl,
        async:false,
        contentType: false, //跨域一定要写
        processData: false, //跨域一定要写
        data:formData,
        dataType:'text',    //返回类型：json，text，HTML。没有jsonp
        success:function(data){
            if(callback) callback(data);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown, data){
            console.error(textStatus);
            console.error(errorThrown);
            console.error(data);
        }            
    });
}