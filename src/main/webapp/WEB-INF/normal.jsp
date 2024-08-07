<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet">

<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 class="head"> ${cksCookie} Supervisor page</h1><a href="/page/normal/wregister"><button>register</button></a>


<style>
    .head{
        border-bottom: 8px solid gray;
        padding : 10px;
    }
    @keyframes showHide {
        0% {
            display: block; /* Make it visible */
            opacity: 0;
        }
        10% {
            opacity: 1;
        }
        90% {
            opacity: 1;
        }
        100% {
            opacity: 0;
            display: none; /* Hide it again */
        }
    }
</style>
<h1>Worker List</h1>
<ul>
    <c:forEach items = "${workvoList}" var="worker">
        <li>
            <div>
                <div> name : <a href="/page/normal/wremove?wno=${worker.wno}">${worker.wname}</a></div>
                <div> id : ${worker.wid}</div>
<%--                <div> time : ${worker.time}</div>--%>
            </div>
        </li>
    </c:forEach>
</ul>

<%--<ul class="pagination">--%>

<%--    <c:if test="${pageInfo.prev}"> <!-- 이전페이지 --> <!-- 현재 11에서 20페이지가 보인다고할때 start=11-->--%>
<%--        <li class="page-item"><a class="page-link" href="/normal?page=${pageInfo.start-1}">Previous</a></li>--%>
<%--    </c:if>--%>


<%--    <c:forEach begin="${pageInfo.start}" end="${pageInfo.end}" var="num">--%>
<%--        <li class="page-item ${pageInfo.page == num ? 'active':''}"><a class="page-link" href="/normal?page=${num}">${num}</a></li>--%>
<%--    </c:forEach> <!-- 현재 page와 num이 같으면 파란색으로 변경-->--%>


<%--    <c:if test="${pageInfo.next}"> <!-- 다음페이지 --> <!-- 현재 1에서 10페이지가 보인다고할때 end=10-->--%>
<%--        <li class="page-item"><a class="page-link" href="/normal?page=${pageInfo.end+1}">Next</a></li>--%>
<%--    </c:if>--%>

<%--</ul>--%>

</body>
</html>
