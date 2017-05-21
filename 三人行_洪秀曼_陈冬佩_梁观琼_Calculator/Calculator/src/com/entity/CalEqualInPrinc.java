package com.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 月还息到期还本
 * 
 */
public class CalEqualInPrinc {

	public List<Schedule> CalEqualInPrinc(Condition condition) {

		double interest, monprincipal, balance;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d_str = condition.getLoanYear() + "-" + condition.getLoanMon() + "-" + condition.getLoanDay();
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
				// 每月利息 = (本金* 年利率/100) / 12;
				interest = condition.getPrincipal() * (condition.getRate() / 100) / 12;
				// 最后一月前
				if (i < condition.getNumberPayment() - 1) {
					monprincipal = 0;
				}
				// 最后一月加上本金
				else {
					monprincipal = condition.getPrincipal();
				}

				// 结果都保留两位小数
				balance = interest + monprincipal;

				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				BigDecimal mp = new BigDecimal(monprincipal);
				monprincipal = mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				String returnDate = sdf.format(c.getTime());

				// 保存结果
				Schedule paymentschedule = new Schedule();
				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				paymentschedule.setBalance(balance);
				paymentschedule.setReturnDate(returnDate);
				scheduleList.add(paymentschedule);
			}
			return scheduleList;

		} else if (condition.getNumberPaymentType().equals("月") && condition.getRateType().equals("日利率")) {
			List<Schedule> scheduleList = new ArrayList<>();
			for (int i = 0; i < condition.getNumberPayment(); i++) {
				c.add(Calendar.MONTH, 1);
				// 每月利息 = 本金 * (利率 /100) * 30
				interest = condition.getPrincipal() * (condition.getRate() / 100 * 30);
				// 最后一月前
				if (i < condition.getNumberPayment() - 1) {
					monprincipal = 0;
				}
				// 最后一月加上本金
				else {
					monprincipal = condition.getPrincipal();
				}

				// 结果都保留两位小数
				balance = interest + monprincipal;

				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				BigDecimal mp = new BigDecimal(monprincipal);
				monprincipal = mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				String returnDate = sdf.format(c.getTime());

				// 保存结果
				Schedule paymentschedule = new Schedule();
				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				paymentschedule.setBalance(balance);
				paymentschedule.setReturnDate(returnDate);
				scheduleList.add(paymentschedule);
			}
			return scheduleList;
		}
		// 其它选择不允许,默认只返回本金
		else {
			List<Schedule> schedulelist = new ArrayList<Schedule>();
			String returnDate = sdf.format(c.getTime());

			Schedule schedule = new Schedule();
			schedule.setInterest(0);
			schedule.setBalance(0);
			schedule.setPrincipal(condition.getPrincipal());
			schedule.setReturnDate(returnDate);
			schedulelist.add(schedule);
			return schedulelist;
		}
	}
}
