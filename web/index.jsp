<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 25331
  Date: 2023/5/25
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.108.0">
    <title>登录界面</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">

    <%--    <link href="./static/css/bootstrap.min.css" rel="stylesheet">--%>

    <!-- Custom styles for this template -->
    <link href="static/css/register.css" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin w-100 m-auto">
    <form onsubmit="return dataValidation()" action="/stuSys/login" method="post">
        <img class="mb-4" src="./static/img/logo.png" alt="" width="72" height="57">
        <h1 class="h3 mb-3 fw-normal">登录</h1>

        <c:if test="${errMsg != null}">
            <script>
                if (${errMsg=='账号密码有误，请重新输入'}) {
                    alert("${errMsg}")
                } else if (${errMsg!=''}) {
                    alert("${errMsg}")
                }
            </script>
        </c:if>

        <div class="form-floating">
            <input type="text" id="inputUserName" class="form-control" name="username" placeholder="账号" required
                   autofocus>
            <label for="inputUserName" class="sr-only">账号</label>
        </div>
        <div class="form-floating">
            <input type="password" id="floatingPassword" class="form-control" name="password" placeholder="密码" required>
            <label for="floatingPassword" class="sr-only">密码</label>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="true" name="rememberMe">记住我
            </label>
        </div>
        <%--        <button type="button" class="btn btn-link">立即注册</button>--%>
        <a href="<%=request.getContextPath()%>/register.jsp">立即注册</a>
        <button class="w-100 btn btn-lg btn-primary" type="submit">登录</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2023–2024 MCL</p>
    </form>
</main>


<script>
    function dataValidation() {
        var userName = $("#inputUserName").val()
        var password = $("#floatingPassword").val()
        if (userName == null || userName == "") {
            alert("用户名不能为空")
            return false;
        } else if (password == null || password == "") {
            alert("密码不能为空")
            return false;
        }
    }
</script>


</body>
</html>