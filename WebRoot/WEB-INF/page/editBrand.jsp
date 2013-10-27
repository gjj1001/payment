<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
</head>
<body>

<%-- <form action="add_add.do" method="post">
类别名称：<input type="text" name="name" ><br/>
备注：<input type="text" name="note"><br/>
<s:hidden name="parentid" value="#parameters.parentid"/>
<s:hidden name="typeId" value="#parameters.typeId"/>
<input type="submit" value="添加" >
</form> --%>
<form action="add_edit.do" method="post" enctype="multipart/form-data">
用户名称：<s:textfield name="name" value="%{name}"/><br/>
用户积分：<s:textfield name="tm" value="%{tm}"/><br/>
用户功勋：<s:textfield name="tp" value="%{tp}"/><br/>
<%-- 用户头像：<s:file name="upload" theme="simple"/><br/>
<img src="${logopath }" width="100"/> --%>
<s:hidden name="userid" value="%{#parameters.userid}"/>
<input type="submit" value="修改" >
</form>
<%-- 前request:<s:property value="#request.parentid" escapeHtml="false"/><br/>
<s:set name="parentid" value="" scope="request"></s:set>
后request:<s:property value="#request.parentid" escapeHtml="false"/><br/>
session:<s:property value="#session.parentid"/><br/>
application:<s:property value="#application.parentid"/><br/>
parameters:<s:property value="#parameters.parentid"/><br/> --%>

</body>
</html>