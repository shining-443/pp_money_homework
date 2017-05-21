package test;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.entity.CalInfine;
import com.entity.Condition;
import com.entity.Schedule;

/**
 * 到期还本息测试类
 *
 */
public class TestCalInfine {

	Condition condition1;
	Condition condition2;
	Condition condition3;
	Condition condition4;

	@Before
	public void setUp() throws Exception {

		condition1 = new Condition();
		condition2 = new Condition();
		condition3 = new Condition();
		condition4 = new Condition();
		condition1.setPrincipal(1234);
		condition1.setRate(12);
		condition1.setRateType("年利率");
		condition1.setNumberPayment(12);
		condition1.setNumberPaymentType("月");

		condition2.setPrincipal(1234);
		condition2.setRate(12);
		condition2.setRateType("日利率");
		condition2.setNumberPayment(12);
		condition2.setNumberPaymentType("月");

		condition3.setPrincipal(1234);
		condition3.setRate(12);
		condition3.setRateType("年利率");
		condition3.setNumberPayment(12);
		condition3.setNumberPaymentType("天");

		condition4.setPrincipal(1234);
		condition4.setRate(12);
		condition4.setRateType("日利率");
		condition4.setNumberPayment(12);
		condition4.setNumberPaymentType("天");

	}

	@Test
	public void testCalInfine_one() {
		CalInfine ci_test = new CalInfine();
		List<Schedule> schedulelist = ci_test.CalInfine(condition1);
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");

		String interest, principle, balance;
		Schedule schedule = schedulelist.get(0);

		interest = df.format(schedule.getInterest());
		principle = df.format(schedule.getPrincipal());
		balance = df.format(schedule.getBalance());
		assertEquals("148.08", interest);
		assertEquals("1234.00", principle);
		assertEquals("1382.08", balance);

	}

	@Test
	public void testCalInfine_two() {
		CalInfine ci_test = new CalInfine();
		List<Schedule> schedulelist = ci_test.CalInfine(condition2);
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");

		String interest, principle, balance;
		Schedule schedule = schedulelist.get(0);

		interest = df.format(schedule.getInterest());
		principle = df.format(schedule.getPrincipal());
		balance = df.format(schedule.getBalance());
		assertEquals("53308.80", interest);
		assertEquals("1234.00", principle);
		assertEquals("54542.80", balance);

	}

	@Test
	public void testCalInfine_three() {
		CalInfine ci_test = new CalInfine();
		List<Schedule> schedulelist = ci_test.CalInfine(condition3);
		// 用來保留三位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.000");

		String interest, principle, balance;
		Schedule schedule = schedulelist.get(0);

		interest = df.format(schedule.getInterest());
		principle = df.format(schedule.getPrincipal());
		balance = df.format(schedule.getBalance());
		assertEquals("4.940", interest);
		assertEquals("1234.000", principle);
		assertEquals("1238.940", balance);

	}

	@Test
	public void testCalInfine_four() {
		CalInfine ci_test = new CalInfine();
		List<Schedule> schedulelist = ci_test.CalInfine(condition4);
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");

		String interest, principle, balance;
		Schedule schedule = schedulelist.get(0);

		interest = df.format(schedule.getInterest());
		principle = df.format(schedule.getPrincipal());
		balance = df.format(schedule.getBalance());
		assertEquals("1776.96", interest);
		assertEquals("1234.00", principle);
		assertEquals("3010.96", balance);

	}

}
