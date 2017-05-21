<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "com.entity.*" %>
<%@ page import = "com.servlet.*" %>

<%@ page import="java.util.*" %>

<jsp:useBean id="paymentschedule" type="com.entity.Schedule" scope="request"/>
<jsp:useBean id="data1" type="com.entity.Condition" scope="request"/>


<link href="style.css" rel="stylesheet" type="text/css" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网贷秘书计算器  计算</title>
</head>
<body>
<div class="main_page">
  <div class="main_top_frame">网贷秘书计算器  </div>
  <div class="main_content_frame">
    <div class="content_left">
      <form id="form1" name="form1" method="post" action="CalculateServlet">
        <table width="408" height="495" border="0" align="left">
          <tr>
            <td>平台：</td>
            <td colspan="3"> <jsp:getProperty name="data1" property="platform"/>
          </tr>
          <tr>
            <td>借出日期：</td>
            <td width="97"><jsp:getProperty name="data1" property="loanYear"/>年</td>
            <td width="96">
            <jsp:getProperty name="data1" property="loanMon"/>月</td>
            <td width="98"><jsp:getProperty name="data1" property="loanDay"/>日</td>
          </tr>
          <tr>
            <td width="99"><span style="color: #F00">*</span>借出金额：</td>
            <td colspan="3"><jsp:getProperty name="data1" property="principal"/>元</td>
          </tr>
          <tr>
            <td><span style="color: #F00">*</span>借出期限：</td>
            <td><jsp:getProperty name="data1" property="numberPayment"/>
                <jsp:getProperty name="data1" property="numberPaymentType"/>
            
          </tr>
          <tr>
            <td><span style="color: #F00">*</span>利率：</td>
            <td><jsp:getProperty name="data1" property="rate"/>%
                <jsp:getProperty name="data1" property="rateType"/>
            
            </td>
          </tr>
          <tr>
            <td>还款方式：</td>
            <td colspan="3"><jsp:getProperty name="data1" property="repayType"/></td>
          </tr>
         
        </table>
      </form>
    </div>
    
    
    
    
    <div class="content_right">
      <form id="form2" name="form2" method="post" action="CalculateServlet">
        
        <p>回款明细预览 </p>
        <hr />
        <table width="498" border="0">
          <tr>
            <td width="60"> 期号</td>
            <td width="143">待收（含本金）</td>
            <td width="92">本金</td>
            <td width="60">利息</td>
            <td width="75">归还日期</td>
          </tr>
          <%
              Schedule p_schedule=(Schedule)request.getAttribute("paymentschedule");
              Schedule schedule=new Schedule();
              List<Schedule> list= new ArrayList<Schedule>();
              list=(List<Schedule>)p_schedule.getPaymentschedule();
            
                for (int i = 0; i < list.size(); i ++) {
                	schedule = list.get(i);	
                	
           
           %>
           <tr>
             <td width="60"> <%=i+1 %> </td>
            <td   width="143"><%=schedule.getBalance() %></td>
            <td   width="92"><%=schedule.getPrincipal()%></td>
            <td  width="60"><%=schedule.getInterest() %></td>
       		<td width="75"><%=schedule.getReturnDate() %></td>
         </tr>
         <%
        }
         %> 
           </table>
        <hr />
        
        
       
       </form>
    </div>
    
  </div>
  <div class="main_botten_frame">
    <p>组员：141542108梁观琼，141542201陈冬佩，141542205洪秀曼</p>
  </div>
  
</div>
</body>
</html>