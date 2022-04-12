<%--
  Created by IntelliJ IDEA.
  User: nayeon
  Date: 2022/04/12
  Time: 11:37 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="me.hellonayeon.servlet.domain.member.Member" %>
<%@ page import="me.hellonayeon.servlet.domain.member.MemberRepository" %>
<%
    // request, response 사용 가능 (import 필요 X)
    // JSP는 결국 Serlvet 으로 변환되서 사용되기 떄문에,

    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
