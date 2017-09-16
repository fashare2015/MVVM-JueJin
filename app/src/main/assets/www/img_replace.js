function img_replace(source, replaceSource) {
    $('img[zhimg-src*="'+source+'"]').each(function () {
        $(this).attr('src', replaceSource);
    });
}

function img_replace_by_url(url, localPath) {
	var objs = document.getElementsByTagName("img");
	for(var i=0;i<objs.length;i++) {
	    var imgUrl = objs[i].getAttribute("src");
	    if (imgUrl == url) {
	        objs[i].setAttribute("src", localPath);
	    }
	}

    /*
	for(var i=0;i<objs.length;i++) {
		var imgSrc = objs[i].getAttribute("src_link");     
		var imgOriSrc = objs[i].getAttribute("ori_link");  
		if(imgOriSrc == url) {
   			objs[i].setAttribute("src",imgSrc);
		}
	}
	*/
}

function header_img_replace_by_url(imgUrl) {
    document.getElementById("gold-web-header-img").setAttribute("style",
        "background-image: linear-gradient(rgba(0, 0, 0, .1), rgba(0, 0, 0, 0)), url(" + imgUrl + ")");
}

function img_replace_all() {
	
	var result = '';
	
	var objs = document.getElementsByTagName("img"); 
	for(var i=0;i<objs.length;i++) {    
		var imgSrc = objs[i].getAttribute("src_link");
		objs[i].setAttribute("src", imgSrc );
		
		result = result + imgSrc;
		result = result + ","; 
	}
	
	return result;
}

function openImage(url) {
	
	if(window.injectedObject){
		injectedObject.openImage(url);
	}
}