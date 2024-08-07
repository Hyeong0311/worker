<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-08-07
  Time: 오후 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<style>
    h1{
        border-bottom: 8px solid gray;
        padding : 10px;
    }
</style>
<h1>remove page</h1>
<ul>

    <div>Worker ID : ${worker.wid}</div>


    <div>Worker NAME : ${worker.wname}</div>
</ul>

<form action="/page/normal/wremove" method="post">
    <input type="hidden" name="wno" value="${worker.wno}">
    <button>REMOVE</button>
</form>
<a href ="/page/normal?sid=${cookieSid}"><button>목록</button></a>
</body>
</html>
