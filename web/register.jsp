<%--
  Created by IntelliJ IDEA.
  User: 25331
  Date: 2023/4/11
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>register</title>
    <!-- (1)引入 Bootstrap -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>
    <!-- (2)引入 jQuery -->
    <script src="static/js/jquery-3.5.1.min.js"></script>

    <!-- (3)引入登录页面需要的css文件(signin.css) -->
    <link rel="stylesheet" href="static/css/signin.css">

  </head>
  <body>

  <div class="container">

    <form class="form-signin" onsubmit="return dataValidation()" action="<%=request.getContextPath()%>/register" method="post">
        <!-- metnod:提交方式
          post:不会再页面上显示提交的内容，比较安全
          get:会在页面上显示提交的内容，不安全      -->
      <h2 class="form-signin-heading" align="center">注册</h2>
      <%--
      JSTL表达式：
      表达式一：
         <c:if test="判断条件">
          满足条件执行的代码
         </c:if>
       表达式二：
         <c:chose>
            <c:when test="判断代码">
              满足执行条件代码
            </c:when>
            <c:when test="判断代码">
              满足执行条件代码
            </c:when>
            <c:otherwise>
              所有条件都不满足执行代码
            </c:otherwise>
         </c:chose>
       --%>
      <c:if test="${errMsg != null}">
        <script>
          alert("${errMsg}")
        </script>
      </c:if>
      <label for="inputEmail" class="sr-only">账号</label>
      <input type="text" id="inputEmail" class="form-control" name="username" placeholder="账号" required autofocus>
      <label for="inputPassword" class="sr-only">密码</label>
      <input type="password" id="inputPassword" class="form-control" name="password" placeholder="密码" required>
      <button class="btn btn-lg btn-primary btn-block" type="submit">确认注册</button>
    </form>

  </div> <!-- /container -->

  <script>
    function dataValidation() {
      var userName = $("#inputEmail").val()
      var password = $("#inputPassword").val()
      if (userName==null || userName==""){
        alert("用户名不能为空")
        return false;
      }else if (password==null || password==""){
        alert("密码不能为空")
        return false;
      }
    }
  </script>

  </body>
</html>
