<%@ page contentType="text/html;charset=utf-8" language="java" %>
<html>
<body>
<h2>Hello World Jsp!</h2>

<%--jsp 表达式--%>
<%--作用：用来将程序的输出，输出到客户端--%>
<%= new java.util.Date()%>

<hr>

<%--jsp 脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
        sum += 1;
    }
    out.println("<h1>Sum=" + sum + "</h1>");
%>

</body>
</html>
