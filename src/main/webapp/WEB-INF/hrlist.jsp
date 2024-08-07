<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jhyeong
  Date: 2024. 8. 7.
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>HR List Page</h1>

    <c:if test = "${result == null}">
        <h1>${message}</h1>
    </c:if>

    <c:if test = "${result != null}">

        <div>
            <label>${result.wname}</label>
            <br>
            <label>${result.in}</label>
            <br>
            <label>${result.out}</label>
            <br>
            <label>${result.dept}</label>
            <br>
            <label>${result.wid}</label>
        </div>

        <br>
        <br>
        <br>

        <c:set var = "hour" value = "${(time / 60).intValue()}" />
        <c:set var = "minute" value = "${(time % 60).intValue()}" />

        <div>
            <label>${hour}시간 ${minute}분</label>
            <br>
            <label>${salary}원</label>
        </div>

        <br>
        <br>
        <br>

        <a href = "/page/hr"><button>목록</button></a>

    </c:if>


</body>
</html>
