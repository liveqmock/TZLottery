<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="../../oss/skin/02/taglib.jsp" %>
<%@taglib prefix="ec" uri="http://www.extremecomponents.org"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>我发过的邮件</title>
<link href="../../oss/skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../../oss/styles/base.css" rel="stylesheet" type="text/css">
<script src="../../oss/skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script src="../../util/jsAndCss/div.js" type=text/javascript></script>
<link href="../../util/jsAndCss/div.css" rel="stylesheet" type="text/css">
<script>
var postPath = "<%=request.getContextPath()%>";
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#page").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("#page").submit();
	}
	function goToPayment(date){
		$("#f_sTime").val(date.substr(0,10)+" 00:00:00");
		$("#f_eTime").val(date.substr(0,10)+" 23:59:59");
		$("#gotopay").submit();
	}
	function goToRegister(date){
		$("#f_sTime2").val(date.substr(0,10)+" 00:00:00");
		$("#f_eTime2").val(date.substr(0,10)+" 23:59:59");
		$("#gotoreg").submit();
	}goToRegister
</script>
</head>
<body>

<div class="tab" align="center">
<h3><strong>我发过的邮件</strong></h3>
	<form id="page" action="mySendEmail.htm" method="post" >
	
	<br/>
	接受客户:<input type="text" name="acceptCust" value="${acceptCust}">
	是否被查看:<select name="isOpened" id="isOpened">
	<s:if test="isOpened.length()>0">
		<s:if test="isOpened==1">
		<option value="${isOpened}" selected="selected">是</option>
		</s:if>
		<s:if test="isOpened==0">
		<option value="${isOpened}" selected="selected">否</option>
	</s:if>
	</s:if>
     <option value="" >请选择..</option>
    <option value="1" >是</option>
    <option value="0" >否</option>
    </select>
	发送时间:<input type="text" name="sTime" value="<s:date name="sTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd 00:00:00',readOnly:true});"/>-
    <input type="text" name="eTime" value="<s:date name="eTime" format="yyyy-MM-dd HH:mm:ss"/>"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true});"/>
		发送状态：<s:select list="emailStatelist" name="emailState" id="emailState" listValue="text" headerValue="请选择..." headerKey=""></s:select>
		<!-- <input type="text" name="state" id="state" />  -->
		<input type="submit" value="查询" />
		<br/>
		<br/>
	<table border="0" width="100%" align="center">
		<tr>
			<td>接收的客户</td>
			<td>标题</td>
			<td>内容</td>
			<td>状态</td>
			<td>已被查看</td>
			<td>发送时间</td>
			<td>成功时间</td>
		</tr>
		<s:iterator value="emailLogPage.result" status="s" id="rs">
			<tr>
				<%--<td><s:if test="#rs.mobile!=''">
				<s:property value="#rs.mobile.substring(0,7)"/>****
				</s:if></td>
				--%>
				<td><s:property value="#rs.username.substring(1,#rs.username.length()-1)"/></td>
				<td>${rs.title}</td>
				<td><div class="dragCss" style="width:600px; height:80px;overflow-y:scroll;">${rs.content}</div>
				</td>
				<td>${rs.state.text}</td>
				<td><s:if test="#rs.opened==1">是</s:if><s:else>否</s:else></td>
				<td>
				<s:date name="#rs.sendTime" format="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
				<s:date name="#rs.successTime" format="yyyy-MM-dd HH:mm"/>
				</td>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${emailLogPage.pageSize}条 共${emailLogPage.totalCount}条记录 第${emailLogPage.pageNo}/${emailLogPage.totalPages}页   
	<s:if test="emailLogPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${emailLogPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="emailLogPage.pageNo>=emailLogPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${emailLogPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${emailLogPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${emailLogPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
</div>
</body>
