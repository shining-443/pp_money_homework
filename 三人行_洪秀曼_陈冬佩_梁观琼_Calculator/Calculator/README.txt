架构：JavaBean + Servlet + JSP + Junit4


控制核心类:
com/servlet/CalculateServlet.java
用来将请求跳转到以下各类：

1.com/entity/CalcEqualPrinc.java 处理 等额本金 的计算方法

2.com/entity/CalEqualInPrice.java 处理 等额本息 的计算方法

3.com/entity/CalEqualInPrinc.java 处理 按月还息到期还本 的计算方法

4.com/entity/CalInfine.java 处理 到期还本息 的计算方法

Bean类：
1. com/entity/Condition.java     还款条件类

2. com/entity/Schedule.java      还款计划类

单元测试类：

1.test/TestCalcEqualPrinc.java  等额本金测试类

2.test/TestCalEqualInPrice.java  等额本息测试类

3.test/TestCalEqualInPrinc.java 月还息到期还本测试类

4.test/TestCalInfine.java 到期还本息测试类

补充说明：在本计算器中，月还息到期还本，等额本息，等额本金这三种计算方法中没有提供由借出期限以“天”为单位的计算。如果选择其项，则不做计算，直接返回本金。