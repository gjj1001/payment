<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询产品品牌</title>
</head>
<body>

<form action="brandlist.do" method="post">
类别名称：<input type="text" name="name"><br/>
<s:hidden name="query" value="true"/>
<input type="submit" value="查询" >
</form>
<%-- <form action="add_update.do" method="post">
类别名称：<input type="text" name="name" ><br/>
备注：<input type="text" name="note"><br/>
<s:if test="#parameters.parentid!=null"><s:hidden name="parentid" value="#parameters.parentId"/></s:if>
<s:hidden name="typeId" value="#parameters.typeId"/>
<input type="submit" value="修改" >
</form> --%>
前request:<s:property value="#request.parentid" escapeHtml="false"/><br/>
<s:set name="parentid" value="" scope="parameters"></s:set>
后request:<s:property value="#request.parentid" escapeHtml="false"/><br/>
session:<s:property value="#session.parentid"/><br/>
application:<s:property value="#application.parentid"/><br/>
parameters:<s:property value="#parameters.parentid"/><br/>

</body>
</html>