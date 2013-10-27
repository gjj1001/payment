<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
<div align="center">
  <table border="1">  
    <tr>
    <th></th>
    <th>手机号</th>
    <th>用户名</th>
    <th>密码</th>    
    <th>性别</th>    
    <th>生日</th>    
    <th>省份</th>    
    <th>城市</th>    
    <th>积分值</th>    
    <th>功勋值</th>    
    <th>用户ID</th>    
    <th>注册时间</th>    
    <th>第三方登陆平台</th>    
    </tr>
 <s:iterator value="user">
    <tr>
       <td><a href="<s:url action="add_editUi"><s:param name="userid" value="userid"></s:param></s:url>">修改</a></td>
       <td><s:property value="mobile"/></td>
       <td><%-- <a href="<s:url action="userlist"></s:url>"> --%><s:property value="username"/></a></td>
       <%-- <td><img src="${logopath }" width="100" /></td>  --%>      
       <td><s:property value="pwd"/></td>
       <td><s:property value="sex"/></td>
       <td><s:property value="birthday"/></td>
       <td><s:property value="province"/></td>
       <td><s:property value="city"/></td>
       <td><s:property value="tm"/></td>
       <td><s:property value="tp"/></td>
       <td><s:property value="userid"/></td>
       <td><s:property value="regtime"/></td>
       <td><s:property value="platform"/></td>
    </tr>
 </s:iterator>
  </table>
     
<%--  <s:iterator value="productType">   
     <s:url id="url" action="list">
         <s:param name="parentid" value="typeId"/>
     </s:url>
 </s:iterator>  --%>    
     <%-- <input type="button" value="添加用户" onclick="javascript:window.location.href='<s:url value="add_addUi.do"><s:param name="parentid" value="#parameters.parentId"></s:param></s:url>'" > --%>
     <input type="button" value="查询" onclick="javascript:window.location.href='<s:url value="add_queryUi.do"></s:url>'" >
     <s:if test="#currentPage>0"><a href='<s:url action="userlist"><s:param name="firstIndex" value="firstIndex-1"></s:param><s:param name="parentId" value="#parameters.parentId"></s:param><s:param name="query" value="query"></s:param><s:param name="name" value="name"></s:param></s:url>'>上一页</a></s:if>
     <s:else>上一页</s:else>     
     
    
     <s:if test="#totalPage>#currentPage+1"><a href='<s:url action="userlist"><s:param name="firstIndex" value="firstIndex+1"></s:param><s:param name="parentId" value="#parameters.parentId"></s:param><s:param name="query" value="query"></s:param><s:param name="name" value="name"></s:param></s:url>'>下一页</a></s:if>
     <s:else>下一页</s:else> 
     当前页：${currentPage+1 }|总页数：${totalPage }
  </div>

</body>
</html>