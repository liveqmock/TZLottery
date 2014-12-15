package com.xsc.lottery.admin.action.system;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.SimpleHibernateTemplate;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.SpreadChannel;
import com.xsc.lottery.entity.business.SystemParam;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.SysParamService;
import com.xsc.lottery.service.business.WalletService;
import com.xsc.lottery.web.action.json.JsonMsgBean;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("ourPerformance")
public class OurPerformanceAction extends AdminBaseAction
{
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private WalletService walletLogService;

	private Long regNumAll = new Long(0);

	private Long rechargeNumAll = new Long(0);

	private BigDecimal rechargeMonAll = new BigDecimal(0);

	private Long regNum = new Long(0);

	private Long rechargeNum = new Long(0);

	private BigDecimal rechargeMon = new BigDecimal(0);

	private ArrayList regNumA;

	private ArrayList rechargeNumA;

	private ArrayList rechargeMonA;

	private ArrayList dataA;

	private Long regNumM;

	private Long rechargeNumM;

	private BigDecimal rechargeMonM;

	private Long regNumW;

	private Long rechargeNumW;

	private BigDecimal rechargeMonW;

	/**
	 * 默认页
	 * @return
	 */
	public String index()
	{				
		Calendar curCalendarS = Calendar.getInstance();
		curCalendarS.set(curCalendarS.get(Calendar.YEAR), curCalendarS.get(Calendar.MONTH), curCalendarS.get(Calendar.DATE), 0, 0, 0);
		
		Calendar curCalendarE = Calendar.getInstance();
		curCalendarE.set(curCalendarE.get(Calendar.YEAR), curCalendarE.get(Calendar.MONTH), curCalendarE.get(Calendar.DATE), 23, 59, 59);
		
		// 总注册数
		regNumAll = customerService.getRecommendorsPageNum(null, null, null, null);
		
		// 充值人数
		rechargeNumAll = walletLogService.getRechargeNum(null, null, null);

		// 充值金额
		rechargeMonAll = walletLogService.getRechargeMon(null, null, null);
		
		curCalendarS.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY );

		// 周注册数
		regNumW = customerService.getRecommendorsPageNum(null, null, curCalendarS, curCalendarE);
		
		// 周充值人数
		rechargeNumW = walletLogService.getRechargeNum(curCalendarS, curCalendarE, null);

		// 周充值金额
		rechargeMonW = walletLogService.getRechargeMon(curCalendarS, curCalendarE, null);
		
		curCalendarS.set(Calendar.DATE, 1);

		// 月注册数
		regNumM = customerService.getRecommendorsPageNum(null, null, curCalendarS, curCalendarE);
		
		// 月充值人数
		rechargeNumM = walletLogService.getRechargeNum(curCalendarS, curCalendarE, null);

		// 月充值金额
		rechargeMonM = walletLogService.getRechargeMon(curCalendarS, curCalendarE, null);
		
		return SUCCESS;
	}
	
	public String getData(){
		Calendar curCalendarS = Calendar.getInstance();
		curCalendarS.set(curCalendarS.get(Calendar.YEAR), curCalendarS.get(Calendar.MONTH), curCalendarS.get(Calendar.DATE), 0, 0, 0);
		
		Calendar curCalendarE = Calendar.getInstance();
		curCalendarE.set(curCalendarE.get(Calendar.YEAR), curCalendarE.get(Calendar.MONTH), curCalendarE.get(Calendar.DATE), 23, 59, 59);
				
		regNumA = new ArrayList();
		rechargeNumA = new ArrayList();
		rechargeMonA = new ArrayList();
		dataA = new ArrayList();
		curCalendarS.add(Calendar.DATE, -7);
		curCalendarE.add(Calendar.DATE, -7);
		for(int i=-6;i<1;i++){
			curCalendarS.add(Calendar.DATE, 1);
			curCalendarE.add(Calendar.DATE, 1);
			dataA.add((curCalendarS.get(Calendar.MONTH)+1)+"月"+curCalendarS.get(Calendar.DATE)+"日");
			// 注册数
			regNumA.add(customerService.getRecommendorsPageNum(null, null, curCalendarS, curCalendarE));
			// 充值人数
			rechargeNumA.add(walletLogService.getRechargeNum(curCalendarS, curCalendarE, null));
			// 充值金额
			rechargeMonA.add(walletLogService.getRechargeMon(curCalendarS, curCalendarE, null));
		}
		
		Map map = new HashMap();
		map.put("regNumA", regNumA);
		map.put("rechargeNumA", rechargeNumA);
		map.put("rechargeMonA", rechargeMonA);
		map.put("dataA", dataA);
        setJsonString(JsonMsgBean.MapToJsonString(map));
        return AJAXJSON;
	}

	public ArrayList getDataA()
	{
		return dataA;
	}

	public void setDataA(ArrayList dataA)
	{
		this.dataA = dataA;
	}

	public Long getRegNumM()
	{
		return regNumM;
	}

	public void setRegNumM(Long regNumM)
	{
		this.regNumM = regNumM;
	}

	public Long getRechargeNumM()
	{
		return rechargeNumM;
	}

	public void setRechargeNumM(Long rechargeNumM)
	{
		this.rechargeNumM = rechargeNumM;
	}

	public BigDecimal getRechargeMonM()
	{
		return rechargeMonM;
	}

	public void setRechargeMonM(BigDecimal rechargeMonM)
	{
		this.rechargeMonM = rechargeMonM;
	}

	public Long getRegNumW()
	{
		return regNumW;
	}

	public void setRegNumW(Long regNumW)
	{
		this.regNumW = regNumW;
	}

	public Long getRechargeNumW()
	{
		return rechargeNumW;
	}

	public void setRechargeNumW(Long rechargeNumW)
	{
		this.rechargeNumW = rechargeNumW;
	}

	public BigDecimal getRechargeMonW()
	{
		return rechargeMonW;
	}

	public void setRechargeMonW(BigDecimal rechargeMonW)
	{
		this.rechargeMonW = rechargeMonW;
	}

	public ArrayList getRechargeNumA()
	{
		return rechargeNumA;
	}

	public void setRechargeNumA(ArrayList rechargeNumA)
	{
		this.rechargeNumA = rechargeNumA;
	}

	public ArrayList getRechargeMonA()
	{
		return rechargeMonA;
	}

	public void setRechargeMonA(ArrayList rechargeMonA)
	{
		this.rechargeMonA = rechargeMonA;
	}

	public ArrayList getRegNumA()
	{
		return regNumA;
	}

	public void setRegNumA(ArrayList regNumA)
	{
		this.regNumA = regNumA;
	}

	public Long getRegNumAll()
	{
		return regNumAll;
	}

	public void setRegNumAll(Long regNumAll)
	{
		this.regNumAll = regNumAll;
	}

	public Long getRechargeNumAll()
	{
		return rechargeNumAll;
	}

	public void setRechargeNumAll(Long rechargeNumAll)
	{
		this.rechargeNumAll = rechargeNumAll;
	}

	public BigDecimal getRechargeMonAll()
	{
		return rechargeMonAll;
	}

	public void setRechargeMonAll(BigDecimal rechargeMonAll)
	{
		this.rechargeMonAll = rechargeMonAll;
	}

	public Long getRegNum()
	{
		return regNum;
	}

	public void setRegNum(Long regNum)
	{
		this.regNum = regNum;
	}

	public Long getRechargeNum()
	{
		return rechargeNum;
	}

	public void setRechargeNum(Long rechargeNum)
	{
		this.rechargeNum = rechargeNum;
	}

	public BigDecimal getRechargeMon()
	{
		return rechargeMon;
	}

	public void setRechargeMon(BigDecimal rechargeMon)
	{
		this.rechargeMon = rechargeMon;
	}
	

}
