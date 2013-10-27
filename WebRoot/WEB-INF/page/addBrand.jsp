<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加产品品牌</title>
<script language="JavaScript">
function checkfn(form) {
	var logofile = form.upload.value;
	if(logofile!="") {
		var ext =logofile.substring(logofile.length-3).toLowerCase();
		if(ext!="jpg" && ext!="png" && ext!="gif" && ext!="bmp") {
			alert("只允许上传jpg,png,gif,bmp格式文件!");
			return false;
		}
	}
	return true;
}
</script>

</head>


<body>

<form action="add_add.do" method="post" enctype="multipart/form-data" onsubmit="javascript:checkfn(this)">
品牌名称：<input type="text" name="name"><br/>
品牌图片：<s:file name="upload" theme="simple"/><br/>
<s:hidden name="parentid" value="%{#parameters.parentid}"/>
<%-- <s:hidden name="typeId" value="#parameters.typeId"/> --%>
<input type="submit" value="添加" >
</form>
<%-- <form action="add_update.do" method="post">
类别名称：<input type="text" name="name" ><br/>
备注：<input type="text" name="note"><br/>
<s:if test="#parameters.parentid!=null"><s:hidden name="parentid" value="#parameters.parentId"/></s:if>
<s:hidden name="typeId" value="#parameters.typeId"/>
<input type="submit" value="修改" >
</form> --%>


</body>
</html>