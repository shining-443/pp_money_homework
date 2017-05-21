package test;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.entity.CalcEqualPrinc;
import com.entity.Condition;
import com.entity.Schedule;

/**
 * 
 * 测试等额本金
 *
 */
public class TestCalcEqualPrinc {

	Condition condition1;
	Condition condition2;

	@Before
	public void setUp() throws Exception {
		condition1 = new Condition();
		condition2 = new Condition();

		condition1.setPrincipal(1000);
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
	public void testCalcEqualPrinc() {
		CalcEqualPrinc CEPTest = new CalcEqualPrinc();
		List<Schedule> scheduleListTest = CEPTest.CalcEqualPrinc(condition1);
		int i = 0;
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");
		String Principle;
		String[] Interest = new String[condition1.getNumberPayment()];
		String[] Balance = new String[condition1.getNumberPayment()];
		for (Schedule schedule : scheduleListTest) {
			Principle = df.format(schedule.getPrincipal());
			Interest[i] = df.format(schedule.getInterest());
			Balance[i] = df.format(schedule.getBalance());
			// 这里只测试每月本金
			assertEquals("83.33", Principle);
			i++;
		}

		// 测试利息
		assertEquals("10.00", Interest[0]);
		assertEquals("9.17", Interest[1]);
		assertEquals("8.33", Interest[2]);
		assertEquals("7.50", Interest[3]);
		assertEquals("6.67", Interest[4]);
		assertEquals("5.83", Interest[5]);
		assertEquals("5.00", Interest[6]);
		assertEquals("4.17", Interest[7]);
		assertEquals("3.33", Interest[8]);
		assertEquals("2.50", Interest[9]);
		assertEquals("1.67", Interest[10]);
		assertEquals(".83", Interest[11]);

		// 测试待收（含本金）
		assertEquals("93.33", Balance[0]);
		assertEquals("92.50", Balance[1]);
		assertEquals("91.67", Balance[2]);
		assertEquals("90.83", Balance[3]);
		assertEquals("90.00", Balance[4]);
		assertEquals("89.17", Balance[5]);
		assertEquals("88.33", Balance[6]);
		assertEquals("87.50", Balance[7]);
		assertEquals("86.67", Balance[8]);
		assertEquals("85.83", Balance[9]);
		assertEquals("85.00", Balance[10]);
		assertEquals("84.17", Balance[11]);
	}

	@Test
	public void testCalcEqualPrinc_two() {
		CalcEqualPrinc CEPTest = new CalcEqualPrinc();
		List<Schedule> scheduleListTest = CEPTest.CalcEqualPrinc(condition2);
		int i = 0;
		// 用來保留兩位小數,df.format()返回String类型
		DecimalFormat df = new DecimalFormat("#.00");
		String Principle;
		String[] Interest = new String[condition2.getNumberPayment()];
		String[] Balance = new String[condition2.getNumberPayment()];
		for (Schedule schedule : scheduleListTest) {
			Principle = df.format(schedule.getPrincipal());
			Interest[i] = df.format(schedule.getInterest());
			Balance[i] = df.format(schedule.getBalance());
			// 这里只测试每月本金
			assertEquals("83.33", Principle);
			i++;
		}

		// 测试利息
		assertEquals("3600.00", Interest[0]);
		assertEquals("3300.01", Interest[1]);
		assertEquals("3000.02", Interest[2]);
		assertEquals("2700.04", Interest[3]);
		assertEquals("2400.05", Interest[4]);
		assertEquals("2100.06", Interest[5]);
		assertEquals("1800.07", Interest[6]);
		assertEquals("1500.08", Interest[7]);
		assertEquals("1200.10", Interest[8]);
		assertEquals("900.11", Interest[9]);
		assertEquals("600.12", Interest[10]);
		assertEquals("300.13", Interest[11]);

		// 测试待收（含本金）
		assertEquals("3683.33", Balance[0]);
		assertEquals("3383.35", Balance[1]);
		assertEquals("3083.36", Balance[2]);
		assertEquals("2783.37", Balance[3]);
		assertEquals("2483.38", Balance[4]);
		assertEquals("2183.39", Balance[5]);
		assertEquals("1883.41", Balance[6]);
		assertEquals("1583.42", Balance[7]);
		assertEquals("1283.43", Balance[8]);
		assertEquals("983.44", Balance[9]);
		assertEquals("683.45", Balance[10]);
		assertEquals("383.47", Balance[11]);
	}

}
