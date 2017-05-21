package com.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

/**
 * 等额本息
 */
public class CalEqualInPrice {
	public List<Schedule> CalEqualInPrice(Condition condition) {
		
		double totalRepayment = 0, balance = 0, interest, monprincipal;
		
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 构造日期字符串
		String d_str = condition.getLoanYear() + "-" + condition.getLoanMon() + "-" + condition.getLoanDay();
		// 获取日期对象
		Calendar c = Calendar.getInstance();
		try {
			Date date = sdf.parse(d_str);
			c.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (condition.getNumberPaymentType().equals("月") && condition.getRateType().equals("年利率")) {
			List<Schedule> scheduleList = new ArrayList<>();
			for (int i = 0; i < condition.getNumberPayment(); i++) {
				c.add(Calendar.MONTH, 1);
				// 月利率
				double perMonthRate = (condition.getRate() / 100.0) / 12.0;
				// 还款月数
				double months = condition.getNumberPayment();

				// 每月还款额=贷款本金×(月利率×(1+月利率) ^ 贷款月数) ÷ (1+月利率)贷款月数 - 1
				balance = (condition.getPrincipal() * perMonthRate * Math.pow(1 + perMonthRate, months))
						/ (Math.pow((1 + perMonthRate), months) - 1);
				// 每月利息
				interest = condition.getPrincipal() * perMonthRate
						* (Math.pow(1 + perMonthRate, months) - Math.pow(1 + perMonthRate, i))
						/ (Math.pow(1 + perMonthRate, months) - 1);
				monprincipal = balance - interest;

				String returnDate = sdf.format(c.getTime());
				Schedule paymentschedule = new Schedule();
				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal  mp = new BigDecimal(monprincipal);
				monprincipal =  mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				paymentschedule.setReturnDate(returnDate);
				paymentschedule.setBalance(balance);
				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				scheduleList.add(paymentschedule);
			}
			return scheduleList;

		} else if (condition.getNumberPaymentType().equals("月") && condition.getRateType().equals("日利率")) {
			List<Schedule> scheduleList = new ArrayList<>();
			for (int i = 0; i <= condition.getNumberPayment() - 1; i++) {

				c.add(Calendar.MONTH, 1);
				// 月利率
				double perMonthRate = (condition.getRate() / 100) * 30;
				// 还款月数
				double months = condition.getNumberPayment();

				// 每月还款额=贷款本金×(月利率×(1+月利率) ^ 贷款月数) ÷ (1+月利率)贷款月数 - 1
				balance = (condition.getPrincipal() * perMonthRate * Math.pow(1 + perMonthRate, months))
						/ (Math.pow((1 + perMonthRate), months) - 1);
				// 每月利息
				interest = condition.getPrincipal() * perMonthRate
						* (Math.pow(1 + perMonthRate, months) - Math.pow(1 + perMonthRate, i))
						/ (Math.pow(1 + perMonthRate, months) - 1);
				monprincipal = balance - interest;

				String returnDate = sdf.format(c.getTime());
				Schedule paymentschedule = new Schedule();
				
				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal  mp = new BigDecimal(monprincipal);
				monprincipal =  mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				paymentschedule.setReturnDate(returnDate);
				paymentschedule.setBalance(balance);
				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				scheduleList.add(paymentschedule);
			}
			return scheduleList;

		} else {
			// 其他情况一律不计算
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			Schedule paymentschedule = new Schedule();

			String returnDate = sdf.format(c.getTime());
			paymentschedule.setBalance(0);
			paymentschedule.setPrincipal(condition.getPrincipal());
			paymentschedule.setInterest(0);
			paymentschedule.setReturnDate(returnDate);
			scheduleList.add(paymentschedule);
			return scheduleList;
		}
	}

}
