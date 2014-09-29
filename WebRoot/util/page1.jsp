<%@ page language="java" import="java.util.*" pageEncoding="GBK" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script>
	function jumpPage(pageNo) {
		$("#pageNo").val(pageNo);
		$("#zjmx_form").submit();
	}
	function jumpPage1() {
		$("#pageNo").val($("#pageNum").val());
		$("#zjmx_form").submit();
	}
</script>

<div>
	<input type="hidden" name="pageNo" id="pageNo" value="1" />
	每页${page.pageSize}条 共${page.totalCount}条记录 第${page.pageNo}/${page.totalPages}页   
	<s:if test="page.pageNo==1">
		<span class="disabled">首页</span> 
		<span class="disabled">前一页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(1)" class="cr_link">首页</a>
		<a href="javascript:jumpPage(${page.pageNo-1})" class="cr_link">前一页</a>
	</s:else>
	
	<s:if test="page.pageNo>=page.totalPages">
		<span class="disabled">后一页</span> 
		<span class="disabled">末页</span>
	</s:if>
	<s:else>
		<a href="javascript:jumpPage(${page.pageNo+1})" class="cr_link">后一页</a>
		<a href="javascript:jumpPage(${page.totalPages})" class="cr_link">末页</a>
	</s:else>
	转 第<input type="text" name="pageNum" style="width: 30px" id="pageNum" value="${page.pageNo}" />页  
	<input type="button" value="跳转" onclick="jumpPage1();"/>
</div>
