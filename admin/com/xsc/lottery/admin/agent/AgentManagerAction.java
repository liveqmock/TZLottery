package com.xsc.lottery.admin.agent;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.dao.PagerHibernateTemplate;
import com.xsc.lottery.entity.admin.AdminUser;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.CpsDayReport;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.Order;
import com.xsc.lottery.entity.business.Part;
import com.xsc.lottery.entity.business.PaymentRequest;
import com.xsc.lottery.entity.business.SpreadChannel;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.entity.business.WalletLog;
import com.xsc.lottery.entity.business.SmsLog.SmsLogState;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.entity.enumerate.BusinessType;
import com.xsc.lottery.entity.enumerate.MoneyChannel;
import com.xsc.lottery.entity.enumerate.OrderStatus;
import com.xsc.lottery.entity.enumerate.WalletLogType;
import com.xsc.lottery.service.business.CpsReportService;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.util.MathUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("Agent.manager")
public class AgentManagerAction extends AdminBaseAction
{

	@Autowired
	private CpsReportService cpsReportService;

	@Autowired
	private CustomerService customerService;

	public SimpleHibernateTemplate<SpreadChannel, Long> spreadChannelDao;

	@Autowired
	public void setSessionFactory(@Qualifier("sessionFactory")
	SessionFactory sessionfactory)
	{
		this.spreadChannelDao = new SimpleHibernateTemplate<SpreadChannel, Long>(sessionfactory, SpreadChannel.class);
	}

	private String stime;

	private String etime;

	private Page<CpsDayReport> cpsDayReportPage;

	private int pageNo = 1;

	private int pageSize = 15;

	private int sumReg = 0;

	private int sumCharge = 0;

	private BigDecimal sumChargeMon = new BigDecimal(0);

	private BigDecimal sumPay = new BigDecimal(0);

	private BigDecimal sumComm = new BigDecimal(0);

	private Customer customer;

	private String realName;

	private String linkPhone;

	private String qq;

	private String email;

	private String name;

	private String message;
	
	private String tikuanMessage;

	private Bank[] banksList = Bank.values();

	private Bank bank;

	private String bindMessage;

	private String province;

	private String city;

	private String subbranch;

	private String bankNumber;

	private String bankName;

	private Page<Customer> recommenderPage;
	
	private Page<PaymentRequest> paymentRequestPage;

	private String f_statu;

	private BackMoneyStatus[] status = BackMoneyStatus.values();
	
	private Calendar f_starTime;

    private Calendar f_endTime;
    
    private BigDecimal tikuanJine = new BigDecimal(0);
    
    private Page<BackMoneyRequest> backMoneyRequestPage;
    
    private String f_timeName;

    private Calendar f_sTime;

    private Calendar f_eTime;

	public String getF_timeName()
	{
		return f_timeName;
	}

	public void setF_timeName(String f_timeName)
	{
		this.f_timeName = f_timeName;
	}

	public Calendar getF_sTime()
	{
		return f_sTime;
	}

	public void setF_sTime(Calendar f_sTime)
	{
		this.f_sTime = f_sTime;
	}

	public Calendar getF_eTime()
	{
		return f_eTime;
	}

	public void setF_eTime(Calendar f_eTime)
	{
		this.f_eTime = f_eTime;
	}

	public Page<BackMoneyRequest> getBackMoneyRequestPage()
	{
		return backMoneyRequestPage;
	}

	public void setBackMoneyRequestPage(Page<BackMoneyRequest> backMoneyRequestPage)
	{
		this.backMoneyRequestPage = backMoneyRequestPage;
	}

	public BigDecimal getTikuanJine()
	{
		return tikuanJine;
	}

	public void setTikuanJine(BigDecimal tikuanJine)
	{
		this.tikuanJine = tikuanJine;
	}

	public String getTikuanMessage()
	{
		return tikuanMessage;
	}

	public void setTikuanMessage(String tikuanMessage)
	{
		this.tikuanMessage = tikuanMessage;
	}

	public Calendar getF_starTime()
	{
		return f_starTime;
	}

	public void setF_starTime(Calendar f_starTime)
	{
		this.f_starTime = f_starTime;
	}

	public Calendar getF_endTime()
	{
		return f_endTime;
	}

	public void setF_endTime(Calendar f_endTime)
	{
		this.f_endTime = f_endTime;
	}

	public Page<PaymentRequest> getPaymentRequestPage()
	{
		return paymentRequestPage;
	}

	public void setPaymentRequestPage(Page<PaymentRequest> paymentRequestPage)
	{
		this.paymentRequestPage = paymentRequestPage;
	}

	public BackMoneyStatus[] getStatus()
	{
		return status;
	}

	public void setStatus(BackMoneyStatus[] status)
	{
		this.status = status;
	}

	public String getF_statu()
	{
		return f_statu;
	}

	public void setF_statu(String f_statu)
	{
		this.f_statu = f_statu;
	}

	public Page<Customer> getRecommenderPage()
	{
		return recommenderPage;
	}

	public void setRecommenderPage(Page<Customer> recommenderPage)
	{
		this.recommenderPage = recommenderPage;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getSubbranch()
	{
		return subbranch;
	}

	public void setSubbranch(String subbranch)
	{
		this.subbranch = subbranch;
	}

	public String getBankNumber()
	{
		return bankNumber;
	}

	public void setBankNumber(String bankNumber)
	{
		this.bankNumber = bankNumber;
	}

	public String getBankName()
	{
		return bankName;
	}

	public void setBankName(String bankName)
	{
		this.bankName = bankName;
	}

	public String getBindMessage()
	{
		return bindMessage;
	}

	public void setBindMessage(String bindMessage)
	{
		this.bindMessage = bindMessage;
	}

	public Bank[] getBanksList()
	{
		return banksList;
	}

	public void setBanksList(Bank[] banksList)
	{
		this.banksList = banksList;
	}

	public Bank getBank()
	{
		return bank;
	}

	public void setBank(Bank bank)
	{
		this.bank = bank;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public String getLinkPhone()
	{
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone)
	{
		this.linkPhone = linkPhone;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public int getSumReg()
	{
		return sumReg;
	}

	public void setSumReg(int sumReg)
	{
		this.sumReg = sumReg;
	}

	public int getSumCharge()
	{
		return sumCharge;
	}

	public void setSumCharge(int sumCharge)
	{
		this.sumCharge = sumCharge;
	}

	public BigDecimal getSumChargeMon()
	{
		return sumChargeMon;
	}

	public void setSumChargeMon(BigDecimal sumChargeMon)
	{
		this.sumChargeMon = sumChargeMon;
	}

	public BigDecimal getSumPay()
	{
		return sumPay;
	}

	public void setSumPay(BigDecimal sumPay)
	{
		this.sumPay = sumPay;
	}

	public BigDecimal getSumComm()
	{
		return sumComm;
	}

	public void setSumComm(BigDecimal sumComm)
	{
		this.sumComm = sumComm;
	}

	public Page<CpsDayReport> getCpsDayReportPage()
	{
		return cpsDayReportPage;
	}

	public void setCpsDayReportPage(Page<CpsDayReport> cpsDayReportPage)
	{
		this.cpsDayReportPage = cpsDayReportPage;
	}

	public int getPageNo()
	{
		return pageNo;
	}

	public void setPageNo(int pageNo)
	{
		this.pageNo = pageNo;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public String getStime()
	{
		return stime;
	}

	public void setStime(String stime)
	{
		this.stime = stime;
	}

	public String getEtime()
	{
		return etime;
	}

	public void setEtime(String etime)
	{
		this.etime = etime;
	}

	/**
	 * 默认页
	 * @return
	 */
	public String index()
	{
		AdminUser au = this.getCurAdminUser();
		Customer customer = au.getCustomer();
		cpsDayReportPage = new Page<CpsDayReport>();
		cpsDayReportPage.setPageNo(pageNo);
		cpsDayReportPage.setPageSize(pageSize);
		cpsDayReportPage.setAutoCount(true);

		Map m = cpsReportService.getCommissionPage(cpsDayReportPage, stime, etime, customer);
		sumReg = (Integer) m.get("registerNum");
		sumCharge = (Integer) m.get("rechargeNum");
		sumChargeMon = (BigDecimal) m.get("rechargeMon");
		sumPay = (BigDecimal) m.get("total");
		sumComm = (BigDecimal) m.get("commission");

		cpsDayReportPage = (Page<CpsDayReport>) m.get("page");
		return "list";
	}

	public String getAgentInfo()
	{
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		recommenderPage = new Page<Customer>();
		recommenderPage.setPageNo(pageNo);
		recommenderPage.setPageSize(pageSize);
		recommenderPage.setAutoCount(true);
		recommenderPage = customerService.getRecommendorsPage2(recommenderPage, customer);
		return "info";
	}

	public String saveAgentInfo()
	{
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		SpreadChannel sc = customer.getChannel();
		sc.setRealName(realName);
		sc.setLinkPhone(linkPhone);
		sc.setQQ(qq);
		sc.setEmail(email);
		sc.setName(name);
		customerService.save(customer);
		return getAgentInfo();
		// return "saveAgentInfo";
	}

	public String yongjingChongzhi()
	{

		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		if (customer.getSuperCommission().intValue() > 0)
		{
			synchronized (au)
			{
				PaymentRequest payment = new PaymentRequest();
				String p2_Order = MathUtil.getSerialNumber(16);
				String desc = "佣金充值";
				WalletLogType logType = WalletLogType.佣金充值;
				payment = new PaymentRequest(p2_Order, customer, customer.getSuperCommission(), MoneyChannel.佣金充值, au);
				payment.setOptionTime(Calendar.getInstance());
				WalletLog walletLog = new WalletLog(BusinessType.收入, customer.getSuperCommission(), BigDecimal.ZERO,
						BigDecimal.ZERO, BigDecimal.ZERO, desc, logType, payment.getSerialNumber());
				payment.setFinish(true);
				customerService.addHandMoney(payment, walletLog);

				// 佣金置0
				customer.setSuperCommission(new BigDecimal(0));
				customerService.update(customer);
			}
			message = "佣金充值成功!";
		}
		else
		{
			message = "佣金不足!";
		}

		return getAgentInfo();
	}

	public String yongjinChongzhiChaxun()
	{
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		paymentRequestPage = new Page<PaymentRequest>();
		paymentRequestPage.setPageNo(pageNo);
		paymentRequestPage.setPageSize(pageSize);
		paymentRequestPage.setAutoCount(true);
		paymentRequestPage = customerService.getPaymentRequestPage(paymentRequestPage, MoneyChannel.佣金充值.name(),
                customer.getNickName(), null, f_starTime, f_endTime, f_statu, null,
                null, null, null);
		
		return "yongjinChongzhiChaxun";
	}
	
	public String shenqingTikuan(){
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
//		paymentRequestPage = new Page<PaymentRequest>();
//		paymentRequestPage.setPageNo(pageNo);
//		paymentRequestPage.setPageSize(pageSize);
//		paymentRequestPage.setAutoCount(true);
//		paymentRequestPage = customerService.getPaymentRequestPage(paymentRequestPage, MoneyChannel.佣金充值.name(),
//                customer.getNickName(), null, f_starTime, f_endTime, f_statu, null,
//                null, null, null);
		
		
		if (tikuanJine.intValue() > 0) {
            if (StringUtils.isBlank(customer.getBankNumber())) {
            	tikuanMessage = "请先绑定银行卡!"; 
            	return getAgentInfo();
            }
//            CityNo cn = cityNoService.getByCity(customer.getCity());
            String cityCode = "";
//            if (cn != null) {
//				cityCode = cn.getNo();
//			}
            BackMoneyRequest backMoneyRequest = new BackMoneyRequest(customer,
                    customer.getRealName(), customer.getBank(), 
                    customer.getBankNumber(), tikuanJine, customer.getSubbranch(), cityCode);
            customerService.saveBackMoneyRequest(backMoneyRequest);
            tikuanMessage = "提款成功，待审核!"; 
        }else{
        	tikuanMessage = "提款金额为0！";
        }
		
		return getAgentInfo();
	}
	
	public String tikuanChaxun(){
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		
		backMoneyRequestPage = new Page<BackMoneyRequest>();
		backMoneyRequestPage.setPageNo(pageNo);
		backMoneyRequestPage.setPageSize(pageSize);
		backMoneyRequestPage.setAutoCount(true);
		backMoneyRequestPage = customerService.getBackMoneyRequestPage(backMoneyRequestPage, f_statu,
				customer.getNickName(), null, f_timeName, f_sTime, f_eTime, null,
                null, null);
		return "tikuanChaxun";
	}

	public String bindBank()
	{
		AdminUser au = this.getCurAdminUser();
		customer = au.getCustomer();
		boolean bool = true;
		if (bool && StringUtils.isEmpty(province) && StringUtils.isEmpty(city))
		{

			bindMessage = "地区不能为空!";
			bool = false;
		}
		if (bool && bank == null)
		{
			bindMessage = "开户银行不能为空!";
			bool = false;
		}
		if (bool && StringUtils.isEmpty(subbranch))
		{
			bindMessage = "开户银行支行不能为空!";
			bool = false;
		}
		if (bool && StringUtils.isEmpty(bankNumber))
		{
			bindMessage = "银行卡号错误!";
			bool = false;
		}
		if (StringUtils.isEmpty(bankName))
		{
			bindMessage = "开户名不能为空!";
			bool = false;
		}
		if (!bankName.equals(customer.getRealName()))
		{
			bindMessage = "开户名与真实姓名不一至!";
			bool = false;
		}

		if (bool)
		{
			customer.setCity(city);
			customer.setProvince(province);
			customer.setBank(bank);
			customer.setSubbranch(subbranch);
			customer.setBankNumber(bankNumber);
			customer.setBankName(bankName);
			customerService.update(customer);
			bindMessage = "修改成功!";
		}
		return getAgentInfo();
		// return "saveAgentInfo";
	}

}