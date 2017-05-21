<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import = "com.entity.*" %>
<%@ page import = "com.servlet.*" %>
<jsp:useBean id="paymentschedule" class="com.entity.Schedule" scope="request"/>
<title>网贷秘书计算器  输入</title>

 <script language="javascript">
function check() {
	if(document.form1.day.value==""){
		alert("日期不能为空");
		return false;
	}
	if(document.form1.principal.value==""){
		alert("借出金额不能为空");
		return false;
	}

	if(document.form1.NumberPayment.value=="" ){
		alert("借出期限不能为空");
		return false;
	}

	if(document.form1.rate.value==""){
		alert("利率不能为空");
		return false;
	}
	
	
	
}

</script> 

<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

<div class="main_page">
  <div class="main_top_frame">网贷秘书计算器  </div>
  <div class="main_content_frame">
    <div class="content_left">
      <form id="form1" name="form1" method="post" action="CalculateServlet" onSubmit="return check();">
        <table width="408" height="495" border="0" align="left">
          <tr>
            <td>平台：</td>
 
                 <td colspan="3">
              <select name="platform" id="platform">
                <option value="PPmoney">PPmoney</option>
            </select></td>
          </tr>
          <tr>
            <td>借出日期：</td>
            
              <td width="96">
               <select name="year" id="year">
                <option  value="2017">2017</option>
                <option  value="2018">2018</option>
                <option value="2019">2019</option>
            </select>
                            年</td>
            <td width="96">
            <select name="month" id="month">
                <option  value="1">1</option>
                <option  value="2">2</option>
                <option value="3">3</option>
                <option  value="4">4</option>
                <option  value="5" selected="selected">5</option>
                <option  value="6">6</option>
                <option  value="7">7</option>
                <option  value="8">8</option>
                <option  value="9">9</option>
                <option  value="10">10</option>
                <option  value="11">11</option>
                <option  value="12">12</option>
            </select>
            
            月</td>
            <td width="98">
            <select name="day" id="day">
                <option  value="1">1</option>
                <option  value="2">2</option>
                <option value="3">3</option>
                <option  value="4">4</option>
                <option  value="5">5</option>
                <option  value="6">6</option>
                <option  value="7">7</option>
                <option  value="8">8</option>
                <option  value="9">9</option>
                <option  value="10">10</option>
                <option  value="11">11</option>
                <option  value="12">12</option>
                <option  value="13">13</option>
                <option  value="14">14</option>
                <option  value="15">15</option>
                <option  value="16">16</option>
                <option  value="17">17</option>
                <option  value="18">18</option>
                <option  value="19">19</option>
                <option  value="20">20</option>
                <option  value="21">21</option>
                <option  value="22">22</option>
                <option  value="23">23</option>
                <option  value="24">24</option>
                <option  value="25">25</option>
                <option  value="26">26</option>
                <option  value="27">27</option>
                <option  value="28">28</option>
                <option  value="29">29</option>
                <option  value="30">30</option>
               
            </select>
            日</td> 
          </tr>
          <tr>
            <td width="99"><span style="color: #F00">*</span>借出金额：</td>
            <td colspan="3">
            <!-- 该项只能填入正整数，否则自动清空文本框     --> 
            <input type="text" name="principal" id="principal"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/></td>
          </tr>
          <tr>
            <td><span style="color: #F00">*</span>借出期限：</td>
            <td>
              <!-- 该项只能填入正整数，否则自动清空文本框     --> 
              <input type="text" name="NumberPayment"  id="NumberPayment" size="10"  onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> 
                      
         
            <td><input type="radio" name="time" id="time" value="0" checked="checked" onclick="text1()" />
            个月</td>
            <td><input type="radio" name="time"  id="time"  value="1" onclick="text()" />
              天 </td>
          </tr>
          <tr>
            <td><span style="color: #F00">*</span>利率：</td>
            <td>
            <!-- 该项只能填入正整数，否则自动清空文本框     --> 
            <input name="rate" type="text" id="rate" size="6" onKeyUp="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>%</td>
            <td><input type="radio" name="timerate"  value="0" checked="checked" />
            年利率
            <label for="yearRate"></label></td>
            <td><input type="radio" name="timerate" value="1" />
              日利率</td>
          </tr>
          <tr>
            <td>还款方式：</td>
            <td colspan="3">
              <select name="repayType" id="repayType">
                <option value="到期还本息" >到期还本息</option>
                <option value="月还息到期还本" >月还息到期还本</option>
                <option value="按月还本息（等额本息）">按月还本息（等额本息）</option>
                <option  value="等额本金">等额本金</option>
                
            </select></td>
          </tr>
          <tr>
            <td colspan="4" style="text-align: center"><input type="submit" name="submit" id="submit" value="计算" /></td>
          </tr>
        </table>
      </form>
    </div>
  
    
  </div>
  <div class="main_botten_frame">
    <p>组员：141542108梁观琼，141542201陈冬佩，141542205洪秀曼</p>
  </div>
  
</div>
</body>
</html>