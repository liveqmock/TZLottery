package com.xsc.lottery.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Plan;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil
{
	private static Configuration cfg = new Configuration();

	;
	
	//获取订单内容的url 
	private static String ORDER_DETAIL_EMAIL_CONTENT_URL = "http://"+com.xsc.lottery.util.Configuration.getInstance().getValue("requestPhp")+"/mailcontent/order_detail.php";
	//获取开奖内容的url
	private static String LOTTERY_DETAIL_EMAIL_CONTENT_URL = "http://"+com.xsc.lottery.util.Configuration.getInstance().getValue("requestPhp")+"/mailcontent/order_detail.php";
	
	//跟php对接用的密钥
	public static String privateKey = "560faeed92c4bb9def698e9ee3817e5a";
	//获取月度账单内容
	private static String MONTHLY_BILL_CONTENT_URL = "http://"+com.xsc.lottery.util.Configuration.getInstance().getValue("requestPhp")+"/mailcontent/monthly_bills.php";
	//获取提现内容
	static String BACK_MONEY_REQUEST_CONTENT_URL = "http://"+com.xsc.lottery.util.Configuration.getInstance().getValue("requestPhp")+"/mailcontent/withdraw_notice.php";
	//获取提现结果内容
	static String BACK_MONEY_REQUEST_CONTENT_SUCCESS_OR_FAIL_URL = "http://"+com.xsc.lottery.util.Configuration.getInstance().getValue("requestPhp")+"/mailcontent/withdraw_notice.php";
	
	/**192.168.0.183
	 * <pre>
	 * 生成模板信息
	 * </pre>
	 * @param username 用户名
	 * @param activeUrl url
	 * @param siteName 站点名称
	 * @param domain 域名
	 * @param reserved 预留字段
	 * @param templateFile 模板名称
	 * @return
	 * @throws Exception
	 */
	public static String makeTemplateContent(String username, String storeId, String activeUrl, String siteName, String domain,
			String reserved, String templateFile) throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("username", username);
		root.put("activeUrl", activeUrl);
		root.put("siteName", siteName);
		root.put("domain", domain);
		root.put("storeId", storeId);
		root.put("reserved", reserved);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	/**
	 * <pre>
	 * 生成短信模板信息
	 * </pre>
	 * @param validCode 验证码
	 * @param siteName 站点名称
	 * @param reserved 预留字段
	 * @param templateFile 模板名称
	 * @return
	 * @throws Exception
	 */
	public static String makeSMSTemplateContent(String validCode, String siteName, String reserved, String templateFile)
			throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("validCode", validCode);
		root.put("siteName", siteName);
		root.put("reserved", reserved);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}
	
	public static String getMonthlyBill(String customerid,String year,String month){
		//obj[0]代表用户id,obj[1]代表总花费,obj[2]代表总中奖,obj[3]表示用户名,obj[4]表示邮箱,obj[5]表示真实姓名
			if(customerid==null||"".equals(customerid)||year==null||"".equals(year)
					||month==null||"".equals(month)){
				return "";
			}
			Map m = new HashMap();
			m.put("customerid", customerid);
			m.put("year", year);
			m.put("month", month);
			m.put("sign", MD5.digest(customerid+""+year+""+month+""+TemplateUtil.privateKey));
//			return HttpClientUtil.getContentFromUrl(MONTHLY_BILL_CONTENT_URL ,m);
			return NetWorkUtil.getHttpUrl(MONTHLY_BILL_CONTENT_URL, MapUtil.allAttrToString(m),"");
	}
	
	public static String getWakeUpEmailTemplate(String customerid){
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("wakeupLink", getWakeUpLink(customerid));
		Template t;
		try
		{
			t = cfg.getTemplate("wakeupEmail.ftl", "utf-8");
			StringWriter writer = new StringWriter();
			t.process(root, writer);
			writer.flush();
			writer.close();
			return writer.toString();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}
		catch (TemplateException e)
		{
			e.printStackTrace();
			return "";
		}
	
	}
	
	public static String getWakeUpLink(String customerid){
		if(customerid!=null&&!"".equals(customerid)){
	    	String sign = "CP22d359#5f56!174Mc_5cde2CS";
	    	String baseCode = new String(Base64.encode(customerid.getBytes()));
	    	sign = MD5.digest(customerid+sign);
	    	
	    	String content = com.xsc.lottery.util.Configuration.getInstance().getValue("wakeUpEmailJoinUrl")+"?code="+baseCode+"&sign="+sign+"";
	    	return content;
		}
		return "";
	}
	
	public static String getWinEmailContent(Order order){
			if(order==null){
				return "";
			}
			Map mm = new HashMap();
			mm.put("orderid", order.getId());
			mm.put("sign", MD5.digest(order.getId()+""+privateKey));
//			return HttpClientUtil.getContentFromUrl(LOTTERY_DETAIL_EMAIL_CONTENT_URL,mm);
			return NetWorkUtil.getHttpUrl(LOTTERY_DETAIL_EMAIL_CONTENT_URL, MapUtil.allAttrToString(mm),"");
		
	}
	
	public static String getOrderDetailEmailContent(Order order){
			if(order==null){
				return "";
			}
			String ftlFile = "";
			Map m = new HashMap();
			m.put("orderid", order.getId());
			m.put("customerid", order.getCustomer().getId());
			if(order.getStatus()!=null&&!"".equals(order.getStatus())){
				m.put("status", order.getStatus().ordinal());
				m.put("sign", MD5.digest(order.getId().toString()+""+order.getCustomer().getId().toString()+""+order.getStatus().ordinal()+""+privateKey));
//				return HttpClientUtil.getContentFromUrl(ORDER_DETAIL_EMAIL_CONTENT_URL,m);
				return NetWorkUtil.getHttpUrl(ORDER_DETAIL_EMAIL_CONTENT_URL, MapUtil.allAttrToString(m),"");
			}
			return "";
		
	}

	/**
	 * <pre>
	 *  生成出票和中奖模版
	 * </pre>
	 * @param domain
	 * @param storeId
	 * @param username
	 * @param sitename
	 * @param gameName
	 * @param gameId
	 * @param issueName
	 * @param anteCode
	 * @param bonusMoney
	 * @param amount
	 * @param money
	 * @param successNum
	 * @param totalNum
	 * @param multiple
	 * @param bonusFollow
	 * @param drawTime
	 * @param winCode
	 * @param state
	 * @param autoIsServerRandom
	 * @param templateFile
	 * @return
	 * @throws Exception
	 */
	public static String makeBonusAndPrintTicketTemplateContent(String domain, String storeId, String username,
			String sitename, String gameName, String gameId, String issueName, String anteCode, String bonusMoney,
			String buy306Name, String money, String successNum, String totalNum, String multiple, String bonusFollow,
			String drawTime, String winCode, String state, String autoIsServerRandom, String templateFile)
			throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/subscribe/");
		Map<String, String> root = new HashMap<String, String>();
		root.put("domain", domain);
		root.put("sitename", sitename);
		root.put("storeId", storeId);
		root.put("username", username);
		root.put("gameName", gameName);
		root.put("gameId", gameId);
		root.put("issueName", issueName);
		root.put("bonusMoney", bonusMoney);
		root.put("buy306Name", buy306Name);
		root.put("money", money);
		root.put("successNum", successNum);
		root.put("totalNum", totalNum);
		root.put("multiple", multiple);
		root.put("bonusFollow", bonusFollow);
		root.put("drawTime", drawTime);
		root.put("winCode", winCode);
		root.put("anteCode", anteCode);
		root.put("state", state);
		root.put("autoIsServerRandom", autoIsServerRandom);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	/**
	 * <pre>
	 *  生成金额不足模版
	 * </pre>
	 * @param domain
	 * @param storeId
	 * @param username
	 * @param sitename
	 * @param money
	 * @param list
	 * @param templateFile
	 * @return
	 * @throws Exception
	 */
	public static String makeNotEnoughMoneyTemplateContent(String domain, String storeId, String username,
			String sitename, String money, List<Plan> list, String templateFile) throws Exception
	{
		cfg.setClassForTemplateLoading(TemplateUtil.class, "/template_ftl/subscribe/");
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("domain", domain);
		root.put("sitename", sitename);
		root.put("storeId", storeId);
		root.put("username", username);
		root.put("money", money);
		root.put("list", list);
		Template t = cfg.getTemplate(templateFile, "utf-8");
		StringWriter writer = new StringWriter();
		t.process(root, writer);
		writer.flush();
		writer.close();
		return writer.toString();
	}

	public static void main(String args[]) throws Exception
	{
		
		Map m = new HashMap();
		m.put("withdrawid", 111);
		m.put("sign", MD5.digest(111+""+privateKey));
		
//		Map m = new HashMap();
//		m.put("orderid","2010");
//		m.put("customerid","1603");
//		m.put("sign","33");
//		//http://www.yicp.com/customer/mailcontent/order_detail.php?orderid=2010&customerid=1603&sign=
//		String content = getContentFromUrl("http://www.yicp.com/customer/mailcontent/order_detail.php",m);
//		System.out.println(content);
		// System.out.print(makeTemplateContent("zjt","www.baidu.com","registerActive.ftl"));
//		System.out.println(makeBonusAndPrintTicketTemplateContent("www.", "100", "100", "100", "100", "11", "100",
//				"01,02,03,04,05,06#07", "100", "100", "100", "100", "100", "100", "100", "100", "01,02,03,04,05,06#07",
//				"100", "是", "followPrintTicket.ftl"));
		// List<Plan> list = new ArrayList<Plan>();
		// for (int i = 0; i < 2; i++)
		// {
		// Plan plan = new Plan();
		// plan.setGameId(11);
		// plan.setSuccessCount(10);
		// plan.setTotalIssueCount(13);
		// plan.setBetMoney(2);
		// plan.setAutoLastIssueName("2012001");
		// plan.setIssueName("2012002");
		// plan.setStatusNote("2010-10-18 19：30（星期四）");
		// list.add(plan);
		//			
		// }
		// System.out.println(makeNotEnoughMoneyTemplateContent("www.happycp.com", "100", "test", "开心彩票", "1.26", list,
		// "not_enough_money.ftl"));
	}
	
	public static String getBackMoneyRequestContentSuccessOrFail(Long id,String res,String reason)
	{
			if(id==null||id.equals(new Long(0))||res==null||"".equals(res)||reason==null){
				return "";
			}
			Map m = new HashMap();
			m.put("withdrawid", id);
			m.put("status", res);
			m.put("reason", reason);
			m.put("sign", MD5.digest(id+""+res+""+privateKey));
			
//			return HttpClientUtil.getContentFromUrl(BACK_MONEY_REQUEST_CONTENT_URL ,m);
			return NetWorkUtil.getHttpUrl(BACK_MONEY_REQUEST_CONTENT_SUCCESS_OR_FAIL_URL, MapUtil.allAttrToString(m),"","UTF-8");
	}

	public static String getBackMoneyRequestContent(Long id)
	{
			if(id==null||id.equals(new Long(0))){
				return "";
			}
			Map m = new HashMap();
			m.put("withdrawid", id);
			m.put("sign", MD5.digest(id+""+privateKey));
//			return HttpClientUtil.getContentFromUrl(BACK_MONEY_REQUEST_CONTENT_URL ,m);
			return NetWorkUtil.getHttpUrl(BACK_MONEY_REQUEST_CONTENT_URL, MapUtil.allAttrToString(m),"");
	}

}
