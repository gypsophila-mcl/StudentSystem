<%--
  Created by IntelliJ IDEA.
  User: 25331
  Date: 2023/4/14
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生管理系统</title>
    <!-- (1)引入 Bootstrap -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css" integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

    <!-- 引入主页面需要的css文件(dashboard.css) -->
    <link rel="stylesheet" href="static/css/dashboard.css">

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">芜湖起飞学生管理系统下的按钮</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">芜湖起飞学生管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="#">学生管理 <span class="sr-only">(current)</span></a></li>
                <li><a href="#">班级管理</a></li>
            </ul>

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h2 class="sub-header">学生管理</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>学号</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>生日</th>
                        <th>身份证号</th>
                        <th>手机号</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1001</td>
                        <td><%=request.getAttribute("name")%></td>
                        <td>${name}</td>
                        <td>dolor</td>
                        <td>sit</td>
                        <td>1,001</td>
                    </tr>
                    <%--
                    JSTL表达式：
                        <c:forEach item="集合" var="变量">
                            数据的使用
                        </c:forEach>
                    --%>
                    <c:forEach items="students" var="student">
                        <tr>
                            <td>${student.xh}</td>
                            <td>${student.name}</td>
                            <td>${student.gender}</td>
                            <td>${student.birthday}</td>
                            <td>${student.idnum}</td>
                            <td>${student.tel}</td>
                        </tr>
                    </c:forEach>
                    <%--
                    格式一：
                        <%
                        int a = 1;
                            System.out.println("在 jsp 中定义的a=" + a);
                        %>
                    这种形式的 java 代码可以理解为写在 doGet 或者 doPost 中的 java 代码
                    --%>
                    <%--
                    格式二：
                        <%!
                        int a = 11;
                        int b = 12;
                        public int sum(int a, int b){
                            return a+b;
                        }
                        %>
                    这种就是定义在类中的代码:
                        class 类名{
                            int a = 11;
                            int b = 12;
                            public int sum(int a,int b){
                                return a+b;
                            }
                         }
                    --%>
                    <%--
                    格式三：
                        <%!
                            int a = 11;
                            int b = 12;
                            public int sum(int a, int b){
                                return a+b;
                            }
                        %>
                        age=<%=a%>
                    age=<%=a%>这种可以在页面展示数据
                    --%>
                    <%--
                    EL表达式：
                        ${变量名(保存的key)}
                        .操作符：访问属性
                        []操作符：访问属性，或者索引
                    --%>
                    <%--
                    JSTL表达式：

                    --%>
                    <tr>
                        <td>1,002</td>
                        <td>amet</td>
                        <td>consectetur</td>
                        <td>adipiscing</td>
                        <td>elit</td>
                        <td>1,002</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>Integer</td>
                        <td>nec</td>
                        <td>odio</td>
                        <td>Praesent</td>
                        <td>1,003</td>
                    </tr>
                    <tr>
                        <td>${std.xh}</td>
                        <td>${std.name}</td>
                        <td>${std.gender}</td>
                        <td>${std.birthday}</td>
                        <td>${std.idnum}</td>
                        <td>${std.tel}</td>
                    </tr>
                    <tr>
                        <td>${std["xh"]}</td>
                        <td>${std["name"]}</td>
                        <td>${std["gender"]}</td>
                        <td>${std["birthday"]}</td>
                        <td>${std["idnum"]}</td>
                        <td>${std["tel"]}</td>
                    </tr>
                    <tr>
                        <td>1,005</td>
                        <td>Nulla</td>
                        <td>quis</td>
                        <td>sem</td>
                        <td>at</td>
                        <td>1,005</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- 引入 jQuery -->
<script src="static/js/jquery-3.5.1.min.js"></script>
</body>
</html>

