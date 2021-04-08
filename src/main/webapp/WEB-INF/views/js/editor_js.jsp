<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
var oEditors = []; 
nhn.husky.EZCreator.createInIFrame({
	oAppRef : oEditors, 
	elPlaceHolder : "content", //저는 textarea의 id와 똑같이 적어줬습니다.
	sSkinURI : $('#contextPath').data('path') + "../../resources/smarteditor/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요! 
	fCreator : "createSEditor2", 
	htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
	bUseToolbar : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
	bUseVerticalResizer : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
	bUseModeChanger : false 
	} 
}); 
	
$(function() { $("#savebutton").click(function() { 
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다.
	var title = $("#title").val(); 
	var writer = $("#writer").val(); 
	var content = document.getElementById("content").value;
	var result = confirm("발행 하시겠습니까?"); 
	if(result){ 
		alert("발행 완료!"); 
		$("#WriteForm").submit(); 
		}else{ 
			return; 
		} 
	}); 
})
</script>