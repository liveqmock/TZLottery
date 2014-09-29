﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>排列五分布图</title>
<link href="../css/default.css" type="text/css" rel="stylesheet" />
<link href="../css/css.css" type="text/css" rel="stylesheet" />
<link href="../css/Trend.css" type="text/css" rel="stylesheet" />
<LINK href="../chart/favicon.ico" type=image/x-icon rel="shortcut icon">
<script type="text/javascript" language="javascript" src="../js/jquery-1.4.4.min.js"></script>
<script src="../js/lottery/chart.js"></script>
<!--nav js start-->
<script>
$(function(){
	$(".navBg li").click(function(){
		$(this).addClass("navCurrentBg").siblings().removeClass("navCurrentBg");
		$(this).next("a").css("color","#fff");		
	});
});

$(document).ready(function()
{var ltype="${type}";
var tt="${tt}";
var football="${football}";
var chartType="${chartType}"
	changeChartInfo(football,ltype,tt,chartType);
})
</script>
</head>

<body>
<!-- Web Top  start-->
<%@include file="/head.jsp"%>   
<!--Web Nav end-->
<div class="clear"></div>
<!--Web Body start-->
<div class="outer">
	<div class="Trend">
    	<div class="Trend_title_left">
        	<div class="Trend_online">
            	<div class="Titile">排列五在线分析统计</div>
            </div>
    		<div class="Trend_title_bg">
        		<div class="GGTitile">
					<ul id="Active">
                		<li class="GGTitile1 GGTCurrent_bg"><a href="/direction/fbt.htm?type=plw&tt=js">排列五分布图</a></li>
                    	<li class="GGTitile2"><a href="/direction/plt.htm?tt=${tt }&type=${type }>排列五频率图</a></li>
                    	<li class="GGTitile2"><a href="/direction/zst.htm?tt=${tt }&ttype=${type }&chartType=zst">排列五走势图</a></li>
               		</ul>
            	</div>
        	</div>
            <div class="Trend_Con">
        		<ul>
            		<li class="TC_li_Current">排列五分布图</li>
                	
            	</ul>
        	</div>
            <div class="Trend_intro" id="chart_intro">
            
            </div>
        </div>
        
        	
        <div class="Trend_title_right" >
        	<div class="Trend_soft">
            	<div class="Titile">排列五软件统计分析</div> 
            </div>
            <div class="softCon">
				<div class="T_softPic"><img src="../images/img/369software01.jpg" width="61" height="61" /></div>
              <div class="T_softText"></div>
          <div class="T_softBtn">
                   	<div class="btn_1"><a href="/software">免费下载</a></div>
                    <div class="btn_2"></div>
                    </div>
            </div>
        </div>
        <!--双色球表格图 start-->
        <div class="Trend_table">
        	<div class="Trend_Session">
        		<ul>
        		    <li class="TC_li_Current"><a href="/direction/fbt.htm?type=plw&num=10&tt=js"">最近10期</a></li>
            		<li ><a href="/direction/fbt.htm?type=plw&num=30&tt=js"">最近30期</a></li>
                	<li><a href="/direction/fbt.htm?type=plw&num=50&tt=js"">最近50期</a></li>
            	</ul>
        	</div>
            <div class="Trend_table_con">
       	  		<table width="996" cellpadding="0" cellspacing="0" bordercolor="#000" border="1" style="border:1px #fbcca5 solid;">
  <tr class="Trend_tr" >
    <td class="Trend_title_qiHao">期号</td>
    <td class="Trend_title_td">0</td>
    <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
    <td class="Trend_title_td">0</td>
    <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
    <td class="Trend_title_td">0</td>
    <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
     <td class="Trend_title_td">0</td>
     <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
     <td class="Trend_title_td">0</td>
     <td class="Trend_title_td">1</td>
    <td class="Trend_title_td">2</td>
    <td class="Trend_title_td">3</td>
    <td class="Trend_title_td">4</td>
    <td class="Trend_title_td">5</td>
    <td class="Trend_title_td">6</td>
    <td class="Trend_title_td">7</td>
    <td class="Trend_title_td">8</td>
    <td class="Trend_title_td">9</td>
  </tr>
   <s:iterator  value="lotteryResult" status="a" > 
  <tr>
    <td class="Trend_qiHao">${termNo }</td>
    <td class="${fn:contains(fn:substring(result,0,1),0)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,0,1),1)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,1),2)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,0,1),3)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,0,1),4)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,0,1),5)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,0,1),6)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,0,1),7)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,0,1),8)?'Trend_td_red Trend_ball_orange':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,0,1),9)?'Trend_td_red Trend_ball_orange Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,2,3),0)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,2,3),1)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,2,3),2)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,2,3),3)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,2,3),4)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,2,3),5)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,2,3),6)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,2,3),7)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,2,3),8)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,2,3),9)?'Trend_td_red Trend_ball_red Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,4,5),0)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,4,5),1)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,4,5),2)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,4,5),3)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,4,5),4)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,4,5),5)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,4,5),6)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,4,5),7)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,4,5),8)?'Trend_td_red Trend_ball_violet':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,4,5),9)?'Trend_td_red Trend_ball_violet Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,8,9),0)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,6,7),1)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,6,7),2)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,6,7),3)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,6,7),4)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,6,7),5)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,6,7),6)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,6,7),7)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,6,7),8)?'Trend_td_red Trend_ball_green':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,6,7),9)?'Trend_td_red Trend_ball_green Trend_border_z':'Trend_td_red Trend_border_z' }">09</td>
    <td class="${fn:contains(fn:substring(result,8,9),0)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">0</td>
    <td class="${fn:contains(fn:substring(result,8,9),1)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">01</td>
    <td class="${fn:contains(fn:substring(result,8,9),2)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">02</td>
    <td class="${fn:contains(fn:substring(result,8,9),3)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">03</td>
    <td class="${fn:contains(fn:substring(result,8,9),4)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">04</td>
    <td class="${fn:contains(fn:substring(result,8,9),5)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">05</td>
    <td class="${fn:contains(fn:substring(result,8,9),6)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">06</td>
    <td class="${fn:contains(fn:substring(result,8,9),7)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">07</td>
    <td class="${fn:contains(fn:substring(result,8,9),8)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">08</td>
    <td class="${fn:contains(fn:substring(result,8,9),9)?'Trend_td_red Trend_ball_red':'Trend_td_red' }">09</td>
  </tr>
   </s:iterator>   
</table>
			</div>

      </div>
        <!--双色球表格图 end-->
        
  </div>
   
</div>
<!--Web Body end-->
<div class="clear"></div>
 <div class="outer">
	<%@include file="/foot.jsp"%>   
	</div>    
</body>
</html>
