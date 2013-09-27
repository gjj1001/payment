<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加成功</title>
</head>
<body>
<h1><font color="#009900">${message }</font></h1>
<input type="button" onclick="javascript:window.location.href='${url}'" value="确定"/>
${name}<hr/> ${note}<br/>
前request:<s:property value="#request.parentid" /><br/>
<s:set name="parentid" value="" scope="request"></s:set>
后request:<s:property value="#request.parentid" /><br/>
</body>
</html>