<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.DecimalFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link href="skin/01/css/main.css" rel="stylesheet" type="text/css"/>
<script src="skin/01/js/common.js" type=text/javascript></script>
<style type="text/css">
<!--
.STYLE2 {color: #999999}
-->
</style>
</head>

<body>
<!--编辑部分开始 -->
<div id="edit">

  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td>&nbsp;</td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="red_xx table_all">
    <tr>
      <td width="93%"><span class="title">用户管理 ></span>  角色管理</td>
      <td width="7%" align="right"  nowrap class="operation"><span class="hui">管理导航</span><span class="red"> - </span> <a href="manageRole.aspx?action=addRole" class="blue">添加</a></td>
    </tr>
  </table>
  <table border="0" align="center" cellpadding="0" cellspacing="0" class="table_all">
    <tr>
      <td height="10"></td>
    </tr>
  </table>
  

  <form id="form" name="form" method="post" action="">

  <table width="90%" align="center" cellspacing="1" class="table table_all" id="table1">
    <tr>
      <td colspan="4" class="redbold">角色管理</td>
    </tr>
    <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
      <th width="65" align="center">序号</th>
      <th width="144" align="center">角色名</th>
      <th width="596" align="center">描述</th>
      <th width="102" align="center" nowrap="nowrap">操作</th>
      </tr>
 <s:iterator id="rolelist" value="roleList" status="st">
	  <tr onmouseover="over()" onclick="change()" onmouseout="out()" class="td_bg">
	    <td align="center">${rolelist.id}</td>
      <td align="center">${rolelist.name}</td>
      <td>${rolelist.description}</td>
      <td align="center">
       <a href='manageRole.aspx?action=addRole&roleId=${rolelist.id}'>改</a> | <a onclick="javascript:return getConfirm('该角色中的管理员将一并被删除且删除后将不能恢复！真的要删除吗？');" href='manageRole.aspx?action=delRole&roleId=${rolelist.id}'>删</a> <a href='manageRole.aspx?action=permlist&roleId=${rolelist.id}&chnId=${chnId}'>权</a></td>
	  </tr>
</s:iterator>
  </table>
  </form>
</div>
<!--编辑部分结束 -->
</body>
</html>