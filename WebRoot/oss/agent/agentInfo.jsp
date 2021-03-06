<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../skin/02/taglib.jsp" %>
<html>
	<head>
		<title>代理详情</title>
		<meta name="heading" content="代理详情" />
<link href="../skin/01/css/main.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/jquery-1.3.2.js" type="text/javascript"></script>
<link href="../styles/base.css" rel="stylesheet" type="text/css">
<script src="../skin/01/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script>
var postPath = "<%=request.getContextPath()%>";
	function save(pageNo) {
		$("#pageNo").val(pageNo);
		$("#getRecommenders").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("#getRecommenders").submit();
	}
	function yongjingChongzhi() {
		$("#yongjingChongzhiForm").submit();
	}
	function shenqingTikuan() {
		var str=prompt("提款金额","提款金额");
		if(str>"${customer.wallet.balance}"){
			alert("提款金额超出账户余额！");
			return;
		}
		$("#tikuanJine").val(str);
		$("#shenqingTikuanForm").submit();
	}
</script>
	</head>
	<body>
	<form id="yongjingChongzhiForm" action="/oss/agent/agentManager.htm" method="post">
	<input type="hidden" id="action" name="action" value="yongjingChongzhi">
	</form>
	<form id="shenqingTikuanForm" action="/oss/agent/agentManager.htm" method="post">
	<input type="hidden" id="action" name="action" value="shenqingTikuan">
	<input type="hidden" id="tikuanJine" name="tikuanJine">
	</form>
		
	<div class="tab" align="center"><label>代理详情</label>
		<br />
		<br />
		<form action="/oss/agent/agentManager.htm" method="post">
		<input type="hidden" id="action" name="action" value="saveAgentInfo">
		<table border="0" width="100%" align="center">
		<tr>
			<td>代理id</td>
			<td>${customer.channel.id}</td>
			<td>真实姓名</td>
			<td><input type="text" name="realName" id="realName" value="${customer.channel.realName }" /></td>
			<td>联系电话</td>
			<td><input type="text" name="linkPhone" id="linkPhone" value="${customer.channel.linkPhone }" /></td>
		</tr>
		<tr>
			<td>QQ:</td>
			<td><input type="text" name="qq" id="qq" value="${customer.channel.QQ }" /></td>
			<td>邮箱:</td>
			<td><input type="text" name="email" id="email" value="${customer.channel.email }" /></td>
			<td>渠道名称</td>
			<td><input type="text" name="name" id="name" value="${customer.channel.name }" /></td>
		</tr>
		<tr>
			<td>渠道地址:</td>
			<td>${customer.channel.url }</td>
			<td>一级推荐提成比例:</td>
			<td>${customer.superRatio }</td>
			<td>佣金:</td>
			<td>${customer.superCommission }(元)<input type="button" value="转账到余额" onclick="yongjingChongzhi()"/><font color="red">${message }</font></td>
		</tr>
		<tr>
		<td>余额:</td>
		<td>${customer.wallet.balance }(元)<input type="button" value="申请提款" onclick="shenqingTikuan()"/><font color="red">${tikuanMessage }</font></td>
		<td colspan="4" align="center"><input type="submit" value="保存"/></td>
		</tr>
		</table>
		</form>
		<br />
		<br />
		<label>绑定的银行卡信息</label>
		<br />
		<br />
		<form action="/oss/agent/agentManager.htm" method="post">
		<input type="hidden" id="action" name="action" value="bindBank">
		<table border="0" width="100%" align="center">
			<tr>
				<td>银行</td>
			<td>
			<s:select list="banksList" name="bank" id="bank" listValue="name()" headerValue="请选择..." headerKey="" value="customer.bank "></s:select>
			</td>
			<td>卡号</td>
			<td><input type="text" name="bankNumber" id="bankNumber" value="${customer.bankNumber }"/></td>
			<td>开户名</td>
			<td><input type="text" name="bankName" id="bankName" value="${customer.bankName }"/>
	           				<font color="red">开户名与您个人资料的真实姓名必须一致</font></td>
			</tr>
			<tr>
			<td>开户支行：省：</td>
			<td>
			<s:select list="{'北京','上海','福建','甘肃','广东','广西','贵州','海南','河北','河南','黑龙江','湖北','湖南','吉林','江苏','江西','辽宁','内蒙古','宁夏','青海','山东','山西','陕西','安徽','四川','天津','西藏','新疆','云南','浙江','重庆'}"
			 name="province" id="province" headerValue="请选择..." headerKey="" value="customer.province "></s:select>
			<%--<select name="province" id="province" value="customer.province ">
	              			<option value="">请选择</option><option value="北京">北京</option><option value="上海">上海</option>
	              			<option value="福建">福建</option><option value="甘肃">甘肃</option><option value="广东">广东</option>
	              			<option value="广西">广西</option><option value="贵州">贵州</option><option value="海南">海南</option>
	              			<option value="河北">河北</option><option value="河南">河南</option><option value="黑龙江">黑龙江</option>
	              			<option value="湖北">湖北</option><option value="湖南">湖南</option><option value="吉林">吉林</option>
	              			<option value="江苏">江苏</option><option value="江西">江西</option><option value="辽宁">辽宁</option>
	              			<option value="内蒙古">内蒙古</option><option value="宁夏">宁夏</option><option value="青海">青海</option>
	              			<option value="山东">山东</option><option value="山西">山西</option><option value="陕西">陕西</option>
	                        <option value="安徽">安徽</option><option value="四川">四川</option><option value="天津">天津</option>
	              			<option value="西藏">西藏</option><option value="新疆">新疆</option><option value="云南">云南</option>
	              			<option value="浙江">浙江</option><option value="重庆">重庆</option>
	                       	</select>
	                       	--%></td>
			<td>市：</td>
			<td><input type="text" id="city" name="city" value="${customer.city }"/></td>
			<td>开户支行：</td>
			<td><input type="text" name="subbranch" id="subbranch" value="${customer.subbranch }"/></td>
		
		</tr>
		<tr>
		<td colspan="6" align="center"><input type="submit" value="保存"/><font color="red">${bindMessage }</font></td>
		</tr>
		</table>
		</form>
		<br />
		<br />
		<label>我推荐的人</label>
		<br />
		<br />
		<form id="getRecommenders" action="/oss/agent/agentManager.htm" method="post">
		<input type="hidden" id="action" name="action" value="getAgentInfo">
		<table border="0" width="100%" align="center">
		<tr>
			<td>被推荐人</td>
			<td>级别</td>
		</tr>
		<s:iterator value="recommenderPage.result" status="s" id="rs">
			<tr>
				<td>${rs.nickName}</td>
				<c:if test="${rs.superior == customer}">
                	<td class="center">一级推荐</td>
                	
                </c:if>
                <c:if test="${rs.ssuperior == customer}">
                	<td class="center">二级推荐</td>
                	
                </c:if>
			</tr>
		</s:iterator>
	</table>
	<table width="90%" border="0" align="center">
	<tr><td>
		<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${recommenderPage.pageSize}条 共${recommenderPage.totalCount}条记录 第${recommenderPage.pageNo}/${recommenderPage.totalPages}页   
	<s:if test="recommenderPage.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${recommenderPage.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="recommenderPage.pageNo>=recommenderPage.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${recommenderPage.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${recommenderPage.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${recommenderPage.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
	</div>
	</td>
	</tr>
	</table>
	</form>
	</div></body>
</html>