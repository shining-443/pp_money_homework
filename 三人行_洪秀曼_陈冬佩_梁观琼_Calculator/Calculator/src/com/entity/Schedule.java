package com.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 还款计划
 */
public class Schedule {

	/**
	 * 利息
	 */
	private double interest;

	/**
	 * 本金
	 */
	private double principal;
	/**
	 * 归还日期
	 */
	private String ReturnDate;
	/**
	 * 待收余额
	 */
	private double balance;
	/**
	 * 输出对象列表
	 */
	private List<Schedule> paymentschedule;

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Schedule> getPaymentschedule() {
		return paymentschedule;
	}

	public void setPaymentschedule(List<Schedule> paymentschedule) {
		this.paymentschedule = paymentschedule;
	}

	public String getReturnDate() {
		return ReturnDate;
	}

	public void setReturnDate(String returnDate) {
		ReturnDate = returnDate;
	}

}