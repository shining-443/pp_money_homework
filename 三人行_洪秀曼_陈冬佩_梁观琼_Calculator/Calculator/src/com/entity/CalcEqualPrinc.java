package com.entity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 等额本金
 * 
 */
public class CalcEqualPrinc {
	public List<Schedule> CalcEqualPrinc(Condition condition) {

		double interest, monprincipal, balance, remainPrincipal;
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
			monprincipal = 0;
			remainPrincipal = condition.getPrincipal();
			for (int i = 0; i < condition.getNumberPayment(); i++) {

				c.add(Calendar.MONTH, 1);
				// 剩余本金
				remainPrincipal -= monprincipal;
				monprincipal = condition.getPrincipal() / condition.getNumberPayment();
				interest = remainPrincipal * (condition.getRate() / 100 / 12);
				balance = interest + monprincipal;

				Schedule paymentschedule = new Schedule();
				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal mp = new BigDecimal(monprincipal);
				monprincipal = mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

				String returnDate = sdf.format(c.getTime());

				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				paymentschedule.setBalance(balance);
				paymentschedule.setReturnDate(returnDate);
				scheduleList.add(paymentschedule);

			}
			return scheduleList;
			
		} else if (condition.getNumberPaymentType().equals("月") && condition.getRateType().equals("日利率")) {
			List<Schedule> scheduleList = new ArrayList<>();
			monprincipal = 0;
			remainPrincipal = condition.getPrincipal();
			for (int i = 0; i < condition.getNumberPayment(); i++) {
				// 剩余本金
				c.add(Calendar.MONTH, 1);
				remainPrincipal -= monprincipal;
				monprincipal = condition.getPrincipal() / condition.getNumberPayment();
				interest = remainPrincipal * ((condition.getRate() / 100) * 30);
				balance = interest + monprincipal;

				BigDecimal in = new BigDecimal(interest);
				interest = in.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal mp = new BigDecimal(monprincipal);
				monprincipal = mp.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				BigDecimal bl = new BigDecimal(balance);
				balance = bl.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				Schedule paymentschedule = new Schedule();

				String returnDate = sdf.format(c.getTime());

				paymentschedule.setInterest(interest);
				paymentschedule.setPrincipal(monprincipal);
				paymentschedule.setBalance(balance);
				paymentschedule.setReturnDate(returnDate);
				scheduleList.add(paymentschedule);
			}
			return scheduleList;
		} else {
			// 其余选择不做计算
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			Schedule schedule = new Schedule();
			String returnDate = sdf.format(c.getTime());

			schedule.setBalance(0);
			schedule.setInterest(0);
			schedule.setPrincipal(condition.getPrincipal());
			schedule.setReturnDate(returnDate);
			scheduleList.add(schedule);
			return scheduleList;

		}

	}
}
