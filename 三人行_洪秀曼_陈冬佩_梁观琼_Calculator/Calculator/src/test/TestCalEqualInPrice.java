package test;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.entity.CalEqualInPrice;
import com.entity.Condition;
import com.entity.Schedule;

/**
 * 测试等额本息 
 *
 */
public class TestCalEqualInPrice {

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

		condition2.setPrincipal(1000);
		condition2.setRate(12);
		condition2.setRateType("日利率");
		condition2.setNumberPayment(12);
		condition2.setNumberPaymentType("月");
	}

	@Test
	public void testCalEqualInPrice_one() {
		CalEqualInPrice CEIPTest = new CalEqualInPrice();
		List<Schedule> scheduleListTest = CEIPTest.CalEqualInPrice(condition1);
		int i = 0;
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");
		String Balance;
		String[] Interest = new String[condition1.getNumberPayment()];
		String[] Principle = new String[condition1.getNumberPayment()];
		for (Schedule schedule : scheduleListTest) {
			Balance = df.format(schedule.getBalance());
			Interest[i] = df.format(schedule.getInterest());
			Principle[i] = df.format(schedule.getPrincipal());
			// 只测试每月总供
			assertEquals("888.49", Balance);
			i++;
		}
		// 测试每月利息
		assertEquals("100.00", Interest[0]);
		assertEquals("92.12", Interest[1]);
		assertEquals("84.15", Interest[2]);
		assertEquals("76.11", Interest[3]);
		assertEquals("67.98", Interest[4]);
		assertEquals("59.78", Interest[5]);
		assertEquals("51.49", Interest[6]);
		assertEquals("43.12", Interest[7]);
		assertEquals("34.67", Interest[8]);
		assertEquals("26.13", Interest[9]);
		assertEquals("17.51", Interest[10]);
		assertEquals("8.80", Interest[11]);

		// 测试每月本金
		assertEquals("788.49", Principle[0]);
		assertEquals("796.37", Principle[1]);
		assertEquals("804.34", Principle[2]);
		assertEquals("812.38", Principle[3]);
		assertEquals("820.50", Principle[4]);
		assertEquals("828.71", Principle[5]);
		assertEquals("837.00", Principle[6]);
		assertEquals("845.37", Principle[7]);
		assertEquals("853.82", Principle[8]);
		assertEquals("862.36", Principle[9]);
		assertEquals("870.98", Principle[10]);
		assertEquals("879.69", Principle[11]);
	}

	// 测试月和日利率选项
	@Test
	public void testCalEqualInPrice_two() {
		CalEqualInPrice CEIPTest = new CalEqualInPrice();
		List<Schedule> scheduleListTest = CEIPTest.CalEqualInPrice(condition2);
		int i = 0;
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");
		String Balance;
		String[] Interest = new String[condition2.getNumberPayment()];
		String[] Principle = new String[condition2.getNumberPayment()];
		for (Schedule schedule : scheduleListTest) {
			Balance = df.format(schedule.getBalance());
			Interest[i] = df.format(schedule.getInterest());
			Principle[i] = df.format(schedule.getPrincipal());
			// 只测试每月总供
			assertEquals("3600.00", Balance);
			i++;
		}
		// 测试每月利息
		assertEquals("3600.00", Interest[0]);
		assertEquals("3600.00", Interest[1]);
		assertEquals("3600.00", Interest[2]);
		assertEquals("3600.00", Interest[3]);
		assertEquals("3599.98", Interest[4]);
		assertEquals("3599.92", Interest[5]);
		assertEquals("3599.62", Interest[6]);
		assertEquals("3598.25", Interest[7]);
		assertEquals("3591.96", Interest[8]);
		assertEquals("3563.01", Interest[9]);
		assertEquals("3429.87", Interest[10]);
		assertEquals("2817.39", Interest[11]);

		// 测试每月本金
		assertEquals(".00", Principle[0]);
		assertEquals(".00", Principle[1]);
		assertEquals(".00", Principle[2]);
		assertEquals(".00", Principle[3]);
		assertEquals(".02", Principle[4]);
		assertEquals(".08", Principle[5]);
		assertEquals(".38", Principle[6]);
		assertEquals("1.75", Principle[7]);
		assertEquals("8.04", Principle[8]);
		assertEquals("36.99", Principle[9]);
		assertEquals("170.13", Principle[10]);
		assertEquals("782.61", Principle[11]);
	}

}
