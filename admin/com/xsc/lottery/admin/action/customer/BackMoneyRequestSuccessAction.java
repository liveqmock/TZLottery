package com.xsc.lottery.admin.action.customer;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.hibernate.Page;

import com.xsc.lottery.admin.action.AdminBaseAction;
import com.xsc.lottery.entity.business.BackMoneyRequest;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.business.EmailLog;
import com.xsc.lottery.entity.business.EmailLog.EmailState;
import com.xsc.lottery.entity.enumerate.BackMoneyStatus;
import com.xsc.lottery.entity.enumerate.Bank;
import com.xsc.lottery.service.business.CustomerService;
import com.xsc.lottery.service.business.EmailLogService;
import com.xsc.lottery.service.business.PayOutRequestService;
import com.xsc.lottery.util.TemplateUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("customer.backMoneyRequestSuccess")
public class BackMoneyRequestSuccessAction extends AdminBaseAction
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PayOutRequestService payOutRequestService;
    
    @Autowired
    private EmailLogService emailLogService;
    
    private Page<BackMoneyRequest> page;

    private Bank[] banks = Bank.values();

    private BackMoneyStatus[] status = BackMoneyStatus.values();

    private Long bmid;

    private String f_nikname;

    private String f_raelname;

    private String f_timeName;

    private Calendar f_sTime;

    private Calendar f_eTime;

    private String f_bank;

    private String f_bankCard;

    private String f_openSpace;

    private String f_statu = "二级审核";

    private int pageNo = 1;

    private int pageSize = 10;

    private int totalPages;
    
    private String message;
    
    private String memo;

    public String index()
    {
        page = new Page<BackMoneyRequest>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setAutoCount(true);
        page = customerService.getBackMoneyRequestPage(page, f_statu,
                f_nikname, f_raelname, f_timeName, f_sTime, f_eTime, f_bank,
                f_bankCard, f_openSpace);
        return "list";
    }

    public String exit()
    {
        BackMoneyRequest bmr = customerService.findBackMoneyRequest(bmid);
        /*
        PayOutRequest outRequest = payOutRequestService.getPayOutRequestByBMR(bmr);
        if (outRequest.getState() == 3) {
	        bmr.setUser(this.getCurAdminUser());
	        bmr.setSendTime(Calendar.getInstance());
	        bmr.setStatus(BackMoneyStatus.已取消);
	        customerService.updateBackMoneyRequestBackMoney(bmr);
	        logger.info("手动补单通知冲值成功!");
	        return index();
        }
	    else {
	    	message = "银行正在处理中，暂时不能取消！";
			return index();
		}
		*/
        if(bmr == null)
        {
        	message ="编号为{"+bmid+"}提款申请不存在,请刷新重试";
        }
        if(BackMoneyStatus.二级审核 != bmr.getStatus())
        {
        	message = "编号为{"+bmid+"}提款申请状态为"+bmr.getStatus()+",您没有审核权限";
        }
        else
        {
        	bmr.setUser(this.getCurAdminUser());
            bmr.setSendTime(Calendar.getInstance());
            bmr.setStatus(BackMoneyStatus.已取消);
            bmr.setMemo(memo);
            customerService.updateBackMoneyRequestBackMoney(bmr);
            message = "编号为{"+bmid+"}提款申请取消成功";
            
          //提款成功发邮件通知
            Customer customer = bmr.getCustomer();
         	if(customer.getEmail()!=null&&!"".equals(customer.getEmail())){
         		EmailLog el = new EmailLog();
        		el.setContent(TemplateUtil.getBackMoneyRequestContentSuccessOrFail(bmid,bmr.getStatus().ordinal()+"","失败原因是"+bmr.getMemo()));//1表示成功
        		el.setEmail(customer.getEmail());
        		el.setTitle("您的提款申请审核不通过");
        		el.setUsername(customer.getNickName());
        		el.setSendUserName("一彩票");
        		el.setState(EmailState.NOTSEND);
        		el.setSendTime(new Date());
        		emailLogService.saveOrUpdate(el);
         	}
        }
        return index();
    }

    public String option()
    {
        BackMoneyRequest bmr = customerService.findBackMoneyRequest(bmid);
        if(bmr == null)
        {
        	message ="编号为{"+bmid+"}提款申请不存在,请刷新重试";
        }
        if(BackMoneyStatus.二级审核 != bmr.getStatus())
        {
        	message = "编号为{"+bmid+"}提款申请状态为"+bmr.getStatus()+",您没有审核权限";
        }
        else
        {
           bmr.setUser(this.getCurAdminUser());
           bmr.setSendTime(Calendar.getInstance());
           bmr.setStatus(BackMoneyStatus.已成功);
           customerService.updateBackMoneyRequestMoney(bmr);
           message = "编号为{"+bmid+"}提款申请处理成功";
           
         //提款成功发邮件通知
           Customer customer = bmr.getCustomer();
        	if(customer.getEmail()!=null&&!"".equals(customer.getEmail())){
        		EmailLog el = new EmailLog();
       		el.setContent(TemplateUtil.getBackMoneyRequestContentSuccessOrFail(bmid,bmr.getStatus().ordinal()+"","提现审核通过"));
       		el.setEmail(customer.getEmail());
       		el.setTitle("您的提款申请审核通过");
       		el.setUsername(customer.getNickName());
       		el.setSendUserName("一彩票");
       		el.setState(EmailState.NOTSEND);
       		el.setSendTime(new Date());
       		emailLogService.saveOrUpdate(el);
        	}
        }
        return index();
    }

    public String getF_timeName()
    {
        return f_timeName;
    }

    public void setF_timeName(String fTimeName)
    {
        f_timeName = fTimeName;
    }

    public String getF_nikname()
    {
        return f_nikname;
    }

    public void setF_nikname(String fNikname)
    {
        f_nikname = fNikname;
    }

    public String getF_raelname()
    {
        return f_raelname;
    }

    public void setF_raelname(String fRaelname)
    {
        f_raelname = fRaelname;
    }

    public Calendar getF_sTime()
    {
        return f_sTime;
    }

    public void setF_sTime(Calendar fSTime)
    {
        f_sTime = fSTime;
    }

    public Calendar getF_eTime()
    {
        return f_eTime;
    }

    public void setF_eTime(Calendar fETime)
    {
        f_eTime = fETime;
    }

    public String getF_bank()
    {
        return f_bank;
    }

    public void setF_bank(String fBank)
    {
        f_bank = fBank;
    }

    public String getF_bankCard()
    {
        return f_bankCard;
    }

    public void setF_bankCard(String fBankCard)
    {
        f_bankCard = fBankCard;
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

    public int getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(int totalPages)
    {
        this.totalPages = totalPages;
    }

    public Page<BackMoneyRequest> getPage()
    {
        return page;
    }

    public Bank[] getBanks()
    {
        return banks;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public String getF_openSpace()
    {
        return f_openSpace;
    }

    public void setF_openSpace(String fOpenSpace)
    {
        f_openSpace = fOpenSpace;
    }

    public void setBmid(Long bmid)
    {
        this.bmid = bmid;
    }

    public BackMoneyStatus[] getStatus()
    {
        return status;
    }

    public String getF_statu()
    {
        return f_statu;
    }

    public void setF_statu(String fStatu)
    {
        f_statu = fStatu;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMemo()
	{
		return memo;
	}

	public void setMemo(String memo)
	{
		this.memo = memo;
	}

}
