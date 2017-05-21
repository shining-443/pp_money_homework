package com.entity;

/**
 * 还款条件
 */

public class Condition {
	/**
	 * 平台名称
	 */
	protected String platform;

	/**
	 * 借出日期(年，月，日)
	 */
	protected String loanYear;
	protected String loanMon;
	protected String loanDay;
	/**
	 * 本金
	 */
	protected double principal;
	/**
	 * 利率
	 */
	protected double rate;

	/**
	 * 利率方式：0->年利率；1->日利率
	 */
	protected String rateType;
	/**
	 * 借出期限
	 */
	protected int NumberPayment;
	/**
	 * 期限方式（月、天），每月算30天，一年算360天：0->月;1->天
	 */
	protected String NumberPaymentType;
	/**
	 * 还款方式
	 */
	protected String repayType;

	public String getLoanYear() {
		return loanYear;
	}

	public void setLoanYear(String loanYear) {
		this.loanYear = loanYear;
	}

	public String getLoanMon() {
		return loanMon;
	}

	public void setLoanMon(String loanMon) {
		this.loanMon = loanMon;
	}

	public String getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}

	public double getPrincipal() {
		return principal;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public int getNumberPayment() {
		return NumberPayment;
	}

	public void setNumberPayment(int numberPayment) {
		NumberPayment = numberPayment;
	}

	public String getNumberPaymentType() {
		return NumberPaymentType;
	}

	public void setNumberPaymentType(String numberPaymentType) {
		NumberPaymentType = numberPaymentType;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
