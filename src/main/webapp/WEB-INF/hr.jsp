<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jhyeong
  Date: 2024. 8. 6.
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>hr page</h1>


    <div>
        <label>name</label>
        <label>dept</label>
        <label>wid</label>
        <label>note</label>
    </div>

    <c:forEach items="${list}" var="schedule">
        <li>
            <div>
                <a href="/page/hr/list?wid=${schedule.wid}">${schedule.wname}</a>

                <label>${schedule.dept}</label>
                <label>${schedule.wid}</label>
            </div>
        </li>
    </c:forEach>


    <ui class = "pagination">
        <c:if test="${pageInfo.prev}">
            <li class = "page-item"><a class = "page-link" href="/page/hr?page=${pageInfo.start - 1}">Prev</a></li>
        </c:if>

        <c:forEach begin = "${pageInfo.start}" end = "${pageInfo.end}" var = "num">
            <li calss = "page-item" ${pageInfo.page == num ? 'active' : ''}><a class = "page-link" href = "/page/hr?page=${num}">${num}</a></li>
        </c:forEach>

        <c:if test="${pageInfo.next}">
            <li class = "page-item"><a class = "page-link" href="/page/hr?page=${pageInfo.end + 1}">Next</a></li>
        </c:if>
    </ui>

    <%@include file="../includes/logoutfooter.jsp"%>
