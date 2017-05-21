package com.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.entity.*;

/**
 * 到期还本息
 * 
 */
public class CalInfine {

	public List<Schedule> CalInfine(Condition condition) {
		
		double interest, balance, monprincipal;
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
			interest = (condition.getPrincipal() * (condition.getRate() / 100.0) * (condition.getNumberPayment()))
					/ 12.0;
			c.add(Calendar.MONTH, condition.getNumberPayment());
			monprincipal = condition.getPrincipal();
			balance = interest + monprincipal;
		} else if (condition.getNumberPaymentType().equals("月") && condition.getRateType().equals("日利率")) {
			interest = condition.getPrincipal() * (condition.getRate() / 100.0) * condition.getNumberPayment() * 30;
			monprincipal = condition.getPrincipal();
			balance = interest + monprincipal;
			c.add(Calendar.MONTH, condition.getNumberPayment());

		} else if (condition.getNumberPaymentType().equals("天") && condition.getRateType().equals("年利率")) {
			interest = condition.getPrincipal() * ((condition.getRate() / 100.0) / 360.0)
					* condition.getNumberPayment();
			monprincipal = condition.getPrincipal();
			balance = interest + monprincipal;
			c.add(Calendar.DAY_OF_MONTH, condition.getNumberPayment());
		} else {
			interest = condition.getPrincipal() * (condition.getRate() / 100) * condition.getNumberPayment();
			monprincipal = condition.getPrincipal();
			balance = interest + monprincipal;
			c.add(Calendar.DAY_OF_MONTH, condition.getNumberPayment());
		}

		List<Schedule> scheduleList = new ArrayList<Schedule>();

		BigDecimal in = new BigDecimal(interest);
		interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		BigDecimal mp = new BigDecimal(monprincipal);
		monprincipal = mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		BigDecimal bl = new BigDecimal(balance);
		balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

		String returnDate = sdf.format(c.getTime());
		Schedule paymentschedule = new Schedule();
		paymentschedule.setReturnDate(returnDate);
		paymentschedule.setInterest(interest);
		paymentschedule.setPrincipal(monprincipal);
		paymentschedule.setBalance(balance);
		scheduleList.add(paymentschedule);

		return scheduleList;

	}
}
