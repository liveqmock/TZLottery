package com.xsc.lottery.entity.partner;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.xsc.lottery.entity.BaseObject;
import com.xsc.lottery.entity.active.Activity;
import com.xsc.lottery.entity.business.Article;
import com.xsc.lottery.entity.business.Customer;
import com.xsc.lottery.entity.enumerate.UserType;

@Entity
@Table(name = "business_partner")
@SuppressWarnings("serial")
public class Partner extends BaseObject
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false)
    private String password;
    
    private UserType userType;
    
    private Integer loginNum = 0;
    
    private Calendar lastLoginTime;
    
    private String phone;
    
    private boolean active = false;
    
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @Cascade(value=CascadeType.SAVE_UPDATE)  
    @ManyToMany(mappedBy = "partners", fetch = FetchType.LAZY)  
    java.util.Set<Article> articles;  
    
    @Cascade(value=CascadeType.SAVE_UPDATE)  
    @ManyToMany(mappedBy = "partners", fetch = FetchType.LAZY)  
    java.util.Set<Activity> activitys;  
    
    public java.util.Set<Activity> getActivitys()
	{
		return activitys;
	}

	public void setActivitys(java.util.Set<Activity> activitys)
	{
		this.activitys = activitys;
	}

	public java.util.Set<Article> getArticles()
	{
		return articles;
	}

	public void setArticles(java.util.Set<Article> articles)
	{
		this.articles = articles;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public Long getId() 
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserType getUserType()
    {
        return userType;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }

    public Calendar getLastLoginTime()
    {
        return lastLoginTime;
    }

    public void setLastLoginTime(Calendar lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Integer getLoginNum()
    {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum)
    {
        this.loginNum = loginNum;
    }
    
    public void addLoginNum(Integer loginNum)
    {
        if (this.loginNum == null)
            this.loginNum = 0;
        this.loginNum += loginNum;
    }
}
