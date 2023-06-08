<%--
  Created by IntelliJ IDEA.
  User: 25331
  Date: 2023/4/13
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生管理系统</title>

    <!-- 引入 jQuery -->
    <script src="static/js/jquery-3.5.1.min.js"></script>

    <!-- (1)引入 Bootstrap -->
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">--%>

    <link rel="stylesheet" href="./static/css/bootstrap.min.css">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap-theme.min.css"
          integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>

    <!-- 引入主页面需要的css文件(dashboard.css) -->
    <link rel="stylesheet" href="static/css/dashboard.css">

    <%-- 表单验证 --%>
    <script>
        function validateForm() {
            var tel_patern = /^1[3456789]\d{9}$/;
            var manageType = [${pageInfo.manageType}]
            if (manageType == 0) {
                // 获取表单字段的值
                var xh = document.getElementById("xh").value;
                var name = document.getElementById("name").value;
                var idnum = document.getElementById("idnum").value;
                var tel = document.getElementById("tel").value;
                var xh_pattern = /^\d{11}$/;
                var idnum_pattern = /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
                // 进行验证
                if (xh === "") {
                    alert("请输入学号");
                    return false; // 验证失败，阻止表单提交
                } else if (!(xh_pattern.test(xh))) {
                    alert("学号错误");
                    return false; // 验证失败，阻止表单提交
                }

                if (name === "") {
                    alert("请输入姓名");
                    return false; // 验证失败，阻止表单提交
                }

                if (idnum === "") {
                    alert("请输入身份证号");
                    return false; // 验证失败，阻止表单提交
                } else if (!(idnum_pattern.test(idnum))) {
                    alert("身份证号位数错误");
                    return false; // 验证失败，阻止表单提交
                }

                if (tel === "") {
                    alert("请输入手机号");
                    return false; // 验证失败，阻止表单提交··
                } else if (!(tel_patern.test(tel))) {
                    alert("手机号错误");
                    return false; // 验证失败，阻止表单提交
                }

            } else if (manageType == 1) {
                // 获取表单字段的值
                var className = document.getElementById("className").value;
                var stdNum = document.getElementById("stdNum").value;
                // 进行验证
                if (className === "") {
                    alert("请输入班级名称");
                    return false; // 验证失败，阻止表单提交
                }

                if (stdNum === "") {
                    alert("请输入学生姓名");
                    return false; // 验证失败，阻止表单提交
                }
            } else if (manageType == 2) {
                var teacherName = document.getElementById("teacherName").value;
                var teaNo = document.getElementById("teaNo").value;
                var age = document.getElementById("age").value;
                var tel = document.getElementById("teaTel").value;
                var teaNo_pattern = /^\d{6}$/;
                var age_pattern = /^[1-9]\d{0,2}$/;
                if (teacherName === "") {
                    alert("请输入教师姓名");
                    return false; // 验证失败，阻止表单提交
                }

                if (teaNo === "") {
                    alert("请输入教师编号");
                    return false; // 验证失败，阻止表单提交
                } else if (teaNo_pattern.test(teaNo)) {
                    alert("教师编号错误");
                    return false; // 验证失败，阻止表单提交
                }

                if (age === "") {
                    alert("请输入年龄");
                    return false; // 验证失败，阻止表单提交
                } else if (!(age_pattern.test(age))) {
                    alert("年龄错误");
                    return false; // 验证失败，阻止表单提交
                }


                if (tel === "") {
                    alert("请输入手机号");
                    return false; // 验证失败，阻止表单提交
                } else if (!(tel_patern.test(tel))) {
                    alert("手机号错误");
                    return false; // 验证失败，阻止表单提交
                }
            }

        }
    </script>

</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">芜湖起飞学生管理系统下的按钮</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">芜湖起飞学生管理系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<%=request.getContextPath()%>/logout">退出</a></li>
            </ul>
<%--            <form class="navbar-form navbar-right" method="get" action="<%=request.getContextPath()%>/" >--%>
<%--                <input type="text" name="searchPerson" class="form-control" placeholder="输入姓名查询人员信息">--%>
<%--            </form>--%>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <c:if test="${pageInfo.manageType==0}">
                    <li class="active"><a href="<%=request.getContextPath()%>/stdMain?manageType=${0}">学生管理 <span
                            class="sr-only">(current)</span></a></li>
                    <li><a href="<%=request.getContextPath()%>/clsMain?manageType=${1}">班级管理</a></li>
                    <li><a href="<%=request.getContextPath()%>/teacherMain?manageType=${2}">教师管理</a></li>
                </c:if>
                <c:if test="${pageInfo.manageType==1}">
                    <li><a href="<%=request.getContextPath()%>/stdMain?manageType=${0}">学生管理 </a></li>
                    <li class="active"><a href="<%=request.getContextPath()%>/clsMain?manageType=${1}">班级管理<span
                            class="sr-only">(current)</span></a></li>
                    <li><a href="<%=request.getContextPath()%>/teacherMain?manageType=${2}">教师管理</a></li>
                </c:if>
                <c:if test="${pageInfo.manageType==2}">
                    <li><a href="<%=request.getContextPath()%>/stdMain?manageType=${0}">学生管理 </a></li>
                    <li><a href="<%=request.getContextPath()%>/clsMain?manageType=${1}">班级管理</a></li>
                    <li class="active"><a href="<%=request.getContextPath()%>/teacherMain?manageType=${2}">教师管理<span
                            class="sr-only">(current)</span></a></li>
                </c:if>
            </ul>
        </div>

        <!-- 学生管理 -->
        <%--@elvariable id="pageInfo" type="com.mcl.entity.PageInfo"--%>
        <c:if test="${pageInfo != null and pageInfo.manageType==0}">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">学生管理</h2>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    添加学生
                </button>
                <button type="button" class="btn btn-primary" onclick="batchDelObj()">
                    批量删除
                </button>
                <form id="delForm" action="<%=request.getContextPath()%>/batchDelStd" method="post">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><input id="chooseAll" type="checkbox"></th>
                            <th>学号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>生日</th>
                            <th>班级</th>
                            <th>身份证号</th>
                            <th>手机号</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                            <%--显示分页数据--%>
                        <c:if test="${pageInfo != null and pageInfo.list != null}">
                            <c:forEach items="${pageInfo.list}" var="std">
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${std.id}"></td>
                                    <td>${std.xh}</td>
                                    <td>${std.name}</td>
                                    <td>${std.gender}</td>
                                    <td>${std.birthday}</td>
                                    <td>${std.className}</td>
                                    <td>${std.idnum}</td>
                                    <td>${std.tel}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#myModal" data-stdid="${std.id}">
                                            修改
                                        </button>
                                        <button type="button" class="btn btn-primary" data-stdId="${std.id}"
                                                onclick="delObj(${std.id})">
                                            删除
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        </tbody>
                    </table>
                </form>

                <!-- 分页 -->
                <nav align="center" aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <!-- 当前页是第一页 -->
                            <%--@elvariable id="pageInfo" type="com.mcl.entity.PageInfo"--%>
                        <c:if test="${pageInfo.currentPage != 1}">
                            <li>
                                <a href="<%=request.getContextPath()%>/stdMain?currentPage=${pageInfo.currentPage-1}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是第一页 -->
                        <c:if test="${pageInfo.currentPage == 1}">
                            <li class="disabled">
                                <span aria-hidden="true">&laquo;</span>
                            </li>
                        </c:if>

                        <c:if test="${pageInfo != null and pageInfo.navigatePageNum != null}">
                            <c:forEach items="${pageInfo.navigatePageNum}" var="num">
                                <%-- 相等则选中此数字 --%>
                                <c:if test="${num==pageInfo.currentPage}">
                                    <li class="active"><span>${num}</span></li>
                                </c:if>
                                <%-- 不相等则不选中此数字 --%>
                                <c:if test="${pageInfo.currentPage != num}">
                                    <%-- request.getContextPath() 获取此项目名字(/stdMain) --%>
                                    <li><a href="<%=request.getContextPath()%>/stdMain?currentPage=${num}">${num}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <!-- 当前页是最后一页 -->
                        <c:if test="${pageInfo.currentPage != pageInfo.maxNum}">
                            <li>
                                <a href="<%=request.getContextPath()%>/stdMain?currentPage=${pageInfo.currentPage+1}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是最后一页 -->
                        <c:if test="${pageInfo.currentPage == pageInfo.maxNum}">
                            <li class="disabled">
                                <span aria-hidden="true">&raquo;</span>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </c:if>

        <!-- 班级管理 -->
        <c:if test="${pageInfo != null and pageInfo.manageType==1}">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">班级管理</h2>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    添加班级
                </button>
                <button type="button" class="btn btn-primary" onclick="batchDelObj()">
                    批量删除
                </button>
                <form id="delFormClasses" action="<%=request.getContextPath()%>/batchDelCls" method="post">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><input id="chooseAllClasses" type="checkbox"></th>
                            <th>班级名称</th>
                            <th>学生人数</th>
                            <th>班主任姓名</th>
                            <th>教师编号</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                            <%--显示分页数据--%>
                        <c:if test="${pageInfo != null and pageInfo.list != null}">
                            <c:forEach items="${pageInfo.list}" var="cls">
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${cls.id}"></td>
                                    <td>${cls.className}</td>
                                    <td>${cls.stdNum}</td>
                                    <td>${cls.manager}</td>
                                    <td>${cls.teacherNo}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#myModal" data-clsid="${cls.id}">
                                            修改
                                        </button>
                                        <button type="button" class="btn btn-primary" data-clsId="${cls.id}"
                                                onclick="delObj(${cls.id})">
                                            删除
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        </tbody>
                    </table>
                </form>

                <!-- 分页 -->
                <nav align="center" aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <!-- 当前页是第一页 -->
                        <c:if test="${pageInfo.currentPage != 1}">
                            <li>
                                <a href="<%=request.getContextPath()%>/clsMain?currentPage=${pageInfo.currentPage-1}&manageType=${pageInfo.manageType}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是第一页 -->
                        <c:if test="${pageInfo.currentPage == 1}">
                            <li class="disabled">
                                <span aria-hidden="true">&laquo;</span>
                            </li>
                        </c:if>

                        <c:if test="${pageInfo != null and pageInfo.navigatePageNum != null}">
                            <c:forEach items="${pageInfo.navigatePageNum}" var="num">
                                <%-- 相等则选中此数字 --%>
                                <c:if test="${num==pageInfo.currentPage}">
                                    <li class="active"><span>${num}</span></li>
                                </c:if>
                                <%-- 不相等则不选中此数字 --%>
                                <c:if test="${pageInfo.currentPage != num}">
                                    <%-- request.getContextPath() 获取此项目名字(/stdMain) --%>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/clsMain?currentPage=${num}&manageType=${pageInfo.manageType}">${num}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <!-- 当前页是最后一页 -->
                        <c:if test="${pageInfo.currentPage != pageInfo.maxNum}">
                            <li>
                                <a href="<%=request.getContextPath()%>/clsMain?currentPage=${pageInfo.currentPage+1}&manageType=${pageInfo.manageType}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是最后一页 -->
                        <c:if test="${pageInfo.currentPage == pageInfo.maxNum}">
                            <li class="disabled">
                                <span aria-hidden="true">&raquo;</span>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </c:if>

        <!-- 教师管理 -->
        <c:if test="${pageInfo != null and pageInfo.manageType==2}">
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <h2 class="sub-header">教师管理</h2>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    添加教师
                </button>
                <button type="button" class="btn btn-primary" onclick="batchDelObj()">
                    批量删除
                </button>
                <form id="delFormTeacher" action="<%=request.getContextPath()%>/batchDelTeacher" method="post">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th><input id="chooseAllTeacher" type="checkbox"></th>
                            <th>姓名</th>
                            <th>编号</th>
                            <th>年龄</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                            <%--显示分页数据--%>
                            <%--@elvariable id="teacher" type="com.mcl.entity.Teacher"--%>
                        <c:if test="${pageInfo != null and pageInfo.list != null}">
                            <c:forEach items="${pageInfo.list}" var="teacher">
                                <tr>
                                    <td><input type="checkbox" name="ids" value="${teacher.id}"></td>
                                    <td>${teacher.teacherName}</td>
                                    <td>${teacher.teacherNo}</td>
                                    <td>${teacher.age}</td>
                                    <td>${teacher.gender}</td>
                                    <td>${teacher.tel}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" data-toggle="modal"
                                                data-target="#myModal" data-teacherid="${teacher.id}">
                                            修改
                                        </button>
                                        <button type="button" class="btn btn-primary" data-teacherId="${teacher.id}"
                                                onclick="delObj(${teacher.id})">
                                            删除
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>

                        </tbody>
                    </table>
                </form>

                <!-- 分页 -->
                <nav align="center" aria-label="Page navigation">
                    <ul class="pagination pagination-lg">
                        <!-- 当前页是第一页 -->
                            <%--@elvariable id="pageInfo" type="com.mcl.entity.PageInfo"--%>
                        <c:if test="${pageInfo.currentPage != 1}">
                            <li>
                                <a href="<%=request.getContextPath()%>/teacherMain?currentPage=${pageInfo.currentPage-1}&manageType=${pageInfo.manageType}"
                                   aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是第一页 -->
                        <c:if test="${pageInfo.currentPage == 1}">
                            <li class="disabled">
                                <span aria-hidden="true">&laquo;</span>
                            </li>
                        </c:if>

                        <c:if test="${pageInfo != null and pageInfo.navigatePageNum != null}">
                            <c:forEach items="${pageInfo.navigatePageNum}" var="num">
                                <%-- 相等则选中此数字 --%>
                                <c:if test="${num==pageInfo.currentPage}">
                                    <li class="active"><span>${num}</span></li>
                                </c:if>
                                <%-- 不相等则不选中此数字 --%>
                                <c:if test="${pageInfo.currentPage != num}">
                                    <%-- request.getContextPath() 获取此项目名字(/stdMain) --%>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/teacherMain?currentPage=${num}&manageType=${pageInfo.manageType}">${num}</a>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <!-- 当前页是最后一页 -->
                        <c:if test="${pageInfo.currentPage != pageInfo.maxNum}">
                            <li>
                                <a href="<%=request.getContextPath()%>/teacherMain?currentPage=${pageInfo.currentPage+1}&manageType=${pageInfo.manageType}"
                                   aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        <!-- 当前页不是最后一页 -->
                        <c:if test="${pageInfo.currentPage == pageInfo.maxNum}">
                            <li class="disabled">
                                <span aria-hidden="true">&raquo;</span>
                            </li>
                        </c:if>
                    </ul>
                </nav>

            </div>
        </c:if>


    </div>
</div>


<!-- 添加模态框 -->
<div id="myModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <!-- 保存manageType变量 -->
                <input type="hidden" value="${pageInfo.manageType}" id="manageType"/>
                <h4 class="modal-title" id="gridSystemModalLabel">
                    <c:if test="${pageInfo.manageType==0}">
                        添加学生
                    </c:if>
                    <c:if test="${pageInfo.manageType==1}">
                        添加班级
                    </c:if>
                    <c:if test="${pageInfo.manageType==2}">
                        添加教师
                    </c:if>
                </h4>
            </div>

            <!-- 学生 -->
            <c:if test="${pageInfo != null and pageInfo.manageType==0}">
                <form onsubmit="return validateForm()" action="<%=request.getContextPath()%>/addStudent" method="post"
                      id="stdForm">
                    <!-- 隐藏域:保存学生id -->
                    <input type="hidden" id="id" name="id" value="">
                    <div class="modal-body">
                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">学号</span>
                                <input type="text" id="xh" name="xh" class="form-control" placeholder="学号"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">姓名</span>
                                <input type="text" id="name" name="name" class="form-control" placeholder="姓名"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">性别</span>
                                <input type="radio" id="genderM" name="gender" value="男"> 男
                                <input type="radio" id="genderW" name="gender" value="女"> 女
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">生日</span>
                                <input type="date" id="birthday" name="birthday" class="form-control" placeholder="生日"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">班级</span>
                                <select id="classno" name="classno" class="form-control">
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">身份证号</span>
                                <input type="text" id="idnum" name="idnum" class="form-control" placeholder="身份证号"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">手机号</span>
                                <input type="text" id="tel" name="tel" class="form-control" placeholder="手机号"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>

            <!-- 班级 -->
            <c:if test="${pageInfo != null and pageInfo.manageType==1}">
                <form onsubmit="return validateForm()" action="<%=request.getContextPath()%>/addClass" method="post"
                      id="clsForm">
                    <!-- 隐藏域:保存班级id(用来区分时修改还是添加) -->
                    <input type="hidden" id="clsId" name="id" value="">
                    <div class="modal-body">
                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">班级名称</span>
                                <input type="text" id="className" name="className" class="form-control"
                                       placeholder="班级名称" aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">学生人数</span>
                                <input type="text" id="stdNum" readonly value="0" name="stdNum" class="form-control" placeholder="学生人数"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">班主任姓名</span>
                                <select id="teacherno" name="teacherNo" class="form-control">
                                </select>
                            </div>
                        </div>

                    </div>
                </form>
            </c:if>

            <!-- 教师 -->
            <c:if test="${pageInfo != null and pageInfo.manageType==2}">
                <form onsubmit="return validateForm()" action="<%=request.getContextPath()%>/addTeacher" method="post"
                      id="teacherForm">
                    <!-- 隐藏域:保存教师id -->
                    <input type="hidden" id="teacherId" name="id" value="">
                    <div class="modal-body">
                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">教师姓名</span>
                                <input type="text" id="teacherName" name="teacherName" class="form-control"
                                       placeholder="教师姓名" aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">教师编号</span>
                                <input type="text" id="teaNo" name="teacherNo" class="form-control" placeholder="教师编号"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">教师年龄</span>
                                <input type="text" id="age" name="age" class="form-control" placeholder="教师年龄"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">教师性别</span>
                                <input type="radio" id="teaGenderM" name="gender" value="男"> 男
                                <input type="radio" id="teaGenderW" name="gender" value="女"> 女
                            </div>
                        </div>

                        <div class="row">
                            <div class="input-group">
                                <span class="input-group-addon">教师电话</span>
                                <input type="text" id="teaTel" name="tel" class="form-control" placeholder="教师电话"
                                       aria-describedby="sizing-addon2">
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="btn-resetting" class="btn btn-light">重置</button>
                <button type="button" id="btn1" class="btn btn-primary">确定</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<%-- 判断学生是否添加成功的弹出框 --%>
<c:if test="${addMsg != null}">
    <script>
        alert("${addMsg}")
    </script>
</c:if>

<%-- 判断学生是否修改成功的弹出框 --%>
<c:if test="${updateMsg != null}">
    <script>
        alert("${updateMsg}")
    </script>
</c:if>


<%-- 判断学生是否删除成功的弹出框 --%>
<c:if test="${batchDelMsg != null}">
    <script>
        alert("${batchDelMsg}")
    </script>
</c:if>

<!-- 引入 jQuery -->
<script src="static/js/jquery-3.5.1.min.js"></script>
<!-- 引入 js 文件 -->
<script src="static/js/bootstrap.js"></script>

<script>
    // function addStudent() {
    //     $('#myModal').modal('show')
    // }

    //重置按钮
    $('#btn-resetting').on('click', function (event) {
        var manageType = [${pageInfo.manageType}]
        if (manageType == 0) {
            //点击按钮后，清空学生模态框
            $("#stdForm")[0].reset()
        } else if (manageType == 1) {
            //点击按钮后，清空班级模态框
            $("#clsForm")[0].reset()
        } else if (manageType == 2) {
            //点击按钮后，清空教师模态框
            $("#teacherForm")[0].reset()
        }
    });


    //模态框函数
    $('#myModal').on('show.bs.modal', function (event) {
        var modal = $(this)  //拿到模态框

        var manageType = [${pageInfo.manageType}]
        // console.log(manageType);

        if (manageType == 0) {
            //发送 ajax请求，获取所有班级数据
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/findAllClasses",
                dataType: "json",
                success: function (rs) {
                    //清空下拉框
                    $("#classno option").remove()
                    //拿到数据进行循环
                    for (var i = 0; i < rs.length; i++) {
                        // console.log(rs[i])  // rs[i] -->表示班级信息
                        //在页面进行渲染
                        var classId = rs[i].id
                        var className = rs[i].className
                        //把班级信息添加到 select 中
                        $("#classno").append("<option value='" + classId + "'>" + className + "</option>")
                    }
                }
            })
        } else if (manageType == 1) {
            //发送 ajax请求，获取所有教师数据
            $.ajax({
                type: "post",
                url: "<%=request.getContextPath()%>/findAllTeachers",
                dataType: "json",
                success: function (rs) {
                    //清空下拉框
                    $("#teacherno option").remove()
                    //拿到数据进行循环
                    for (var i = 0; i < rs.length; i++) {
                        // console.log(rs[i])  // rs[i] -->表示教师信息
                        //在页面进行渲染
                        var teacherNo = rs[i].teacherNo
                        var teacherName = rs[i].teacherName
                        //把班级信息添加到 select 中
                        $("#teacherno").append("<option value='" + teacherNo + "'>" + teacherName + "</option>");
                    }
                }
            })
        }

        var button = $(event.relatedTarget) // 触发模态的按钮
        if (manageType == 0) {
            var id = button.data('stdid') //拿到学生id
            if (id) {//如果 id 存在则是修改
                modal.find('.modal-title').text('修改学生')
                //拿到 form 表单进行修改
                $("#stdForm").attr("action", "<%=request.getContextPath()%>/updateStudent")
                //查询到学生信息，展示到模态框中
                //发送请求给后台，拿到相应信息
                /*
                方式一:(通用方式)
                    $.ajax({
                        type:"请求方式：get/post",
                        url:"请求地址",
                        data:{key:value,key:value},
                        dataType:"返回数据类型:text/json",
                        success:function () {
                            请求成功后会执行这个函数中内容
                            (进行页面的局部刷新)
                        },
                        error: function () {
                            请求失败的回调函数
                        }
                    })
                 */
                //发送 ajax 请求
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/findStdById",
                    data: {id: id},
                    dataType: "json",
                    success: function (rs) {
                        // console.log(rs)
                        var xh = rs.xh;
                        var name = rs.name;
                        var gender = rs.gender;
                        var birthday = rs.birthday;
                        var idnum = rs.idnum;
                        var tel = rs.tel;
                        var classno = rs.classno;
                        //在模态框进行渲染:获取元素进行属性修改
                        $("#id").val(id)
                        $("#xh").val(xh)
                        $("#xh").prop("readOnly",true)
                        $("#name").val(name)
                        if (gender == "男") { //radio 被选中
                            $("#genderM").prop("checked", true)
                        } else {
                            $("#genderW").prop("checked", true)
                        }
                        $("#birthday").val(birthday)
                        // select 中 option 被选中
                        $("#classno option[value='" + classno + "']").prop("selected", true)
                        $("#idnum").val(idnum)
                        $("#tel").val(tel)
                    }
                })
            } else {//添加
                //点击按钮后，清空模态框
                $("#stdForm")[0].reset()
                modal.find('.modal-title').text('添加学生')
                //拿到 form 表单进行提交
                $("#stdForm").attr("action", "<%=request.getContextPath()%>/addStudent")
                //添加个隐藏标签
                $('<input>').attr({
                    type: 'hidden',
                    name: 'manageType',
                    value: manageType
                }).appendTo('#stdForm');
            }
        } else if (manageType == 1) {
            var clsid = button.data('clsid') //拿到班级id
            $("#clsForm")[0].reset()//清除模态框内容
            if (clsid) {
                //修改班级信息
                modal.find('.modal-title').text('修改班级')
                //拿到 form 表单修改提交路径
                $("#clsForm").attr("action", "<%=request.getContextPath()%>/updateClass")
                //发送 ajax 请求
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/findClsById",
                    data: {clsid: clsid},
                    dataType: "json",
                    success: function (rs) {
                        // console.log(rs)
                        var className = rs.className;
                        var teacherno = rs.teacherNo;
                        var stdNum = rs.stdNum;
                        // console.log('teacherNo=',teacherNo);
                        // console.log(typeof teacherNo)
                        //在模态框进行渲染:获取元素进行属性修改
                        $("#clsId").val(clsid)
                        $("#className").val(className)
                        $("#stdNum").val(stdNum)
                        // select 中 option 被选中
                        $("#teacherno option[value='" + teacherno + "']").prop("selected", true)
                        // console.log($("#teacherno").val())
                    }
                })
            } else {//添加
                modal.find('.modal-title').text('添加班级')
                //拿到form表单进行修改
                $("#clsForm").attr("action", "<%=request.getContextPath()%>/addClass")
                // //添加个隐藏标签
                // $('<input>').attr({
                //     type: 'hidden',
                //     name: 'manageType',
                //     value: manageType
                // }).appendTo('#clsForm');
            }
        } else if (manageType == 2) {
            var teacherId = button.data('teacherid') //拿到教师 id
            if (teacherId) {//如果 id 存在则是修改
                modal.find('.modal-title').text('修改教师')
                //拿到 form 表单进行修改
                $("#teacherForm").attr("action", "<%=request.getContextPath()%>/updateTeacher")
                //查询到教师信息，展示到模态框中
                //发送 ajax 请求
                $.ajax({
                    type: "post",
                    url: "<%=request.getContextPath()%>/findTeacherById",
                    data: {id: teacherId},
                    dataType: "json",
                    success: function (rs) {
                        // console.log(rs)
                        var teacherNo = rs.teacherNo;
                        var teacherName = rs.teacherName;
                        var age = rs.age;
                        var gender = rs.gender;
                        var tel = rs.tel;
                        //在模态框进行渲染:获取元素进行属性修改
                        $("#teacherId").val(teacherId)
                        $("#teaNo").val(teacherNo)
                        //只读状态
                        $("#teacherNo").prop("readOnly",true)
                        $("#teacherName").val(teacherName)
                        $("#age").val(age)
                        if (gender == "男") { //radio 被选中
                            $("#teaGenderM").prop("checked", true)
                        } else {
                            $("#teaGenderW").prop("checked", true)
                        }
                        $("#teaTel").val(tel)
                    }
                })
            } else {
                //添加
                $("#teacherForm")[0].reset()
                modal.find('.modal-title').text('添加教师')
                //拿到form表单进行修改
                $("#teacherForm").attr("action", "<%=request.getContextPath()%>/addTeacher")
            }
        }

    })

    //获取确定按钮，添加点击事件
    $("#btn1").click(function () {
        var manageType = [${pageInfo.manageType}]
        console.log(manageType);
        if (manageType == 0) {
            //提交学生表单
            $("#stdForm").submit();
        } else if (manageType == 1) {
            //添加个隐藏标签
            $('<input>').attr({
                type: 'hidden',
                name: 'manageType',
                value: manageType
            }).appendTo('#clsForm');
            //提交班级表单
            $("#clsForm").submit();
        } else if (manageType == 2) {
            //添加个隐藏标签
            $('<input>').attr({
                type: 'hidden',
                name: 'manageType',
                value: manageType
            }).appendTo('#teacherForm');
            //提交教师表单
            $("#teacherForm").submit();
        }
    })

    //删除按钮点击处理函数
    function delObj(id) {
        //拿到当前管理类型（学生管理、班级管理、老师管理）
        var manageType = [${pageInfo.manageType}]
        if (manageType == 0) {
            //删除学生
            //发送 ajax 请求，从数据库删除数据
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/delStudentById',
                data: {stdId: id},
                dataType: 'text',
                success: function (rs) {
                    // 请求成功，在页面中删除数据
                    console.log(rs)
                    // $("button[data-stdId='" + id + "']").parent().parent().remove();
                    alert("删除成功！")
                }
            })
        }
        else if (manageType == 1) {
            //删除班级
            //发送 ajax 请求，从数据库删除数据
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/delClassById',
                data: {clsId: id},
                dataType: 'text',
                success: function (rs) {
                    // 请求成功，在页面中删除数据
                    console.log(rs)
                    // $("button[data-clsId='" + id + "']").parent().parent().remove();
                    alert(rs)
                }
            })

        }
        else if (manageType == 2) {
            //删除教师
            //发送 ajax 请求，从数据库删除数据
            $.ajax({
                type: 'post',
                url: '<%=request.getContextPath()%>/delTeacherById',
                data: {teacherId: id},
                dataType: 'text',
                success: function (rs) {
                    // 请求成功，在页面中删除数据
                    console.log(rs)
                    // $("button[data-teacherId='" + id + "']").parent().parent().remove();
                    alert(rs)
                }
            })
        }
        var url_this = window.location.href;
        location.replace(url_this)
    }

    //批量删除函数
    function batchDelObj() {
        //拿到当前管理类型（学生管理、班级管理、老师管理）
        var manageType = [${pageInfo.manageType}]
        if (manageType == 0) {
            //提交 delForm 表单
            $("#delForm").submit();
        } else if (manageType == 1) {
            //添加个隐藏标签
            $('<input>').attr({
                type: 'hidden',
                name: 'manageType',
                value: manageType
            }).appendTo('#delFormClasses');
            //提交 delForm 表单
            $("#delFormClasses").submit();
        } else if (manageType == 2) {
            //添加个隐藏标签
            $('<input>').attr({
                type: 'hidden',
                name: 'manageType',
                value: manageType
            }).appendTo('#delFormTeacher');
            //提交 delForm 表单
            $("#delFormTeacher").submit();
        }
    }

    //全选
    $("#chooseAll").click(function () {
        var checked = $("#chooseAll").prop("checked");
        if (checked == true) {
            //全选
            $("input[name=ids]").prop("checked", true)
        } else {
            //全不选
            $("input[name=ids]").prop("checked", false)
        }
    })

</script>

</body>
</html>
