<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌列表</title>
</head>
<body>
<div align="center">
  <table border="1">
    <tr>
    <th></th>
    <th>品牌ID</th>
    <th>品牌名称</th>
    <th>品牌图片</th>    
    </tr>
 <s:iterator value="brand">
    <tr>
       <td><a href="<s:url action="add_editUi"><s:param name="code" value="code"></s:param></s:url>">修改</a></td>
       <td><s:property value="code"/></td>
       <td><a href="<s:url action="brandlist"></s:url>"><s:property value="name"/></a></td>
       <td><img src="${logopath }" width="100" /></td>       
    </tr>
 </s:iterator>
  </table>
     
<%--  <s:iterator value="productType">   
     <s:url id="url" action="list">
         <s:param name="parentid" value="typeId"/>
     </s:url>
 </s:iterator>  --%>    
     <input type="button" value="添加品牌" onclick="javascript:window.location.href='<s:url value="add_addUi.do"><s:param name="parentid" value="#parameters.parentId"></s:param></s:url>'" >
     <input type="button" value="查询" onclick="javascript:window.location.href='<s:url value="add_queryUi.do"></s:url>'" >
     <s:if test="#currentPage>0"><a href='<s:url action="brandlist"><s:param name="firstIndex" value="firstIndex-1"></s:param><s:param name="parentId" value="#parameters.parentId"></s:param><s:param name="query" value="query"></s:param><s:param name="name" value="name"></s:param></s:url>'>上一页</a></s:if>
     <s:else>上一页</s:else>     
     
    
     <s:if test="#totalPage>#currentPage+1"><a href='<s:url action="brandlist"><s:param name="firstIndex" value="firstIndex+1"></s:param><s:param name="parentId" value="#parameters.parentId"></s:param><s:param name="query" value="query"></s:param><s:param name="name" value="name"></s:param></s:url>'>下一页</a></s:if>
     <s:else>下一页</s:else> 
     当前页：${currentPage+1 }|总页数：${totalPage }
  </div>
request:<s:property value="#request.parentid" escapeHtml="false"/><br/>
session:<s:property value="#session.parentid"/><br/>
application:<s:property value="#application.parentid"/><br/>
parameters:<s:property value="#parameters.parentid"/><br/>
</body>
</html>