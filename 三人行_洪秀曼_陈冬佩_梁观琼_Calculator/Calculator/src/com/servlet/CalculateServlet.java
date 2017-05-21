package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.*;

/**
 * Servlet implementation class CalculateServlet
 */
@WebServlet(name = "CalculateServlet", urlPatterns = { "/CalculateServlet" })
public class CalculateServlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        //解决中文乱码问题
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");

		double interest, Monprincipal, balance;
		Condition condition = new Condition();// 创建还款条件对象condition
		Schedule paymentschedule = new Schedule();//创建还款计划对象paymentschedule
		List<Schedule> scheduleList1 = new ArrayList<>();//创建Schedule类型列表

		String platform = request.getParameter("platform");// 获取平台信息
		String loanYear = request.getParameter("year");// 获取年份
		String loanMon = request.getParameter("month");// 获取月份
		String loanDay = request.getParameter("day");// 获取日期
		double principal = Double.parseDouble(request.getParameter("principal"));// 获取本金
		double rate = Double.parseDouble(request.getParameter("rate"));// 获取利率
		String rateType = request.getParameter("timerate");// 获取利率方式
		int numberpayment = Integer.parseInt(request.getParameter("NumberPayment"));// 获取借出期限
		String numberpaymentType = request.getParameter("time");// 获取借出期限方式
		String repayType = request.getParameter("repayType");// 获取还款方式

		// 将获取的还款条件信息放进condition对象里
		condition.setLoanYear(loanYear);
		condition.setLoanMon(loanMon);
		condition.setLoanDay(loanDay);
		condition.setPlatform(platform);
		condition.setPrincipal(principal);
		condition.setRate(rate);
		condition.setRateType(rateType);
		condition.setNumberPayment(numberpayment);
		condition.setNumberPaymentType(numberpaymentType);
		condition.setRepayType(repayType);
	    /**
	     * 判断借出期限的类型，并赋予相关的值
	     */
		if (numberpaymentType.equals("0")) {
			condition.setNumberPaymentType("月");
		} else {
			condition.setNumberPaymentType("天");
		}
		/**
	     * 判断利率的类型，并赋予相关的值
	     */
		if (rateType.equals("0")) {
			condition.setRateType("年利率");
		} else {
			condition.setRateType("日利率");
		}

		
		/**
	     * 判断还款方式，并进入对应的计算方法类
	     */
		if (repayType.equals("到期还本息"))

		{
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			CalInfine calinfine = new CalInfine();
			scheduleList = calinfine.CalInfine(condition);
			paymentschedule.setPaymentschedule(scheduleList);

		} else if (repayType.equals("月还息到期还本")) {

			List<Schedule> scheduleList = new ArrayList<Schedule>();
			CalEqualInPrinc calequalinprinc = new CalEqualInPrinc();
            scheduleList = calequalinprinc.CalEqualInPrinc(condition);
			paymentschedule.setPaymentschedule(scheduleList);

		} else if (repayType.equals("按月还本息（等额本息）")) {

			List<Schedule> scheduleList = new ArrayList<Schedule>();
			CalEqualInPrice calequalinprice = new CalEqualInPrice();
			scheduleList = calequalinprice.CalEqualInPrice(condition);
			paymentschedule.setPaymentschedule(scheduleList);
		} else {

			List<Schedule> scheduleList = new ArrayList<Schedule>();
			CalcEqualPrinc calcequalprinc = new CalcEqualPrinc();
			scheduleList = calcequalprinc.CalcEqualPrinc(condition);
			paymentschedule.setPaymentschedule(scheduleList);

		}
        
		
		request.setAttribute("paymentschedule", paymentschedule);
		request.setAttribute("data1", condition);

		//跳转到新页面，显示计算结果
		RequestDispatcher dispatcher = request.getRequestDispatcher("NewFile.jsp");
		dispatcher.forward(request, response);

	}

}
