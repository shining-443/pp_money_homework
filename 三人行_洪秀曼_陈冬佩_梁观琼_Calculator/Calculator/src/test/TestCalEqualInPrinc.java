package test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.entity.CalEqualInPrinc;
import com.entity.Condition;
import com.entity.Schedule;

/**
 * 月还息到期还本测试类
 * 
 */
public class TestCalEqualInPrinc {

	Condition condition1;
	Condition condition2;

	@Before
	public void setUp() throws Exception {
		condition1 = new Condition();
		condition2 = new Condition();

		condition1.setPrincipal(10000);
		condition1.setRate(12);
		condition1.setRateType("年利率");
		condition1.setNumberPayment(12);
		condition1.setNumberPaymentType("月");

		condition2.setPrincipal(10000);
		condition2.setRate(12);
		condition2.setRateType("日利率");
		condition2.setNumberPayment(12);
		condition2.setNumberPaymentType("月");
	}

	@Test
	public void testCalcEqualPrinc() {
		CalEqualInPrinc CEIPTest = new CalEqualInPrinc();
		List<Schedule> scheduleListTest = CEIPTest.CalEqualInPrinc(condition1);
		int i = 0;
		String Balance, Interest, Principal;
		for (Schedule schedule : scheduleListTest) {
			Balance = String.valueOf(schedule.getBalance());
			Interest = String.valueOf(schedule.getInterest());
			if (i < condition1.getNumberPayment() - 1) {
				// 测试每月待收(含本金)
				assertEquals("100.0", Balance);
				// 测试每月利息
				assertEquals("100.0", Interest);
			} else {
				// 最后一月要加上本金
				assertEquals("10100.0", Balance);
				// 利息
				assertEquals("100.0", Interest);
				// 本金
				Principal = String.valueOf(schedule.getPrincipal());
				assertEquals("10000.0", Principal);
			}

			i++;

		}
	}

	@Test
	public void testCalcEqualPrinc_two() {
		CalEqualInPrinc CEIPTest = new CalEqualInPrinc();
		List<Schedule> scheduleListTest = CEIPTest.CalEqualInPrinc(condition2);
		int i = 0;
		String Balance, Interest, Principal;
		for (Schedule schedule : scheduleListTest) {
			Balance = String.valueOf(schedule.getBalance());
			Interest = String.valueOf(schedule.getInterest());
			if (i < condition1.getNumberPayment() - 1) {
				// 测试每月待收(含本金)
				assertEquals("36000.0", Balance);
				// 测试每月利息
				assertEquals("36000.0", Interest);
			} else {
				// 最后一月要加上本金
				assertEquals("46000.0", Balance);
				// 利息
				assertEquals("36000.0", Interest);
				// 本金
				Principal = String.valueOf(schedule.getPrincipal());
				assertEquals("10000.0", Principal);
			}

			i++;

		}
	}

}
