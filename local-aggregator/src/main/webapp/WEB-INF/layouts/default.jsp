<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:title/></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="${ctx}/static/assets/js/html5shiv.js"></script>
    <![endif]-->
    <!--bootstrap css-->
    <link href="${ctx}/static/plugins/bootstrap/2.3.2/css/non-icon/bootstrap.css" type="text/css" rel="stylesheet"/>
    <link href="${ctx}/static/plugins/bootstrap/2.3.2/css/bootstrap-responsive.css" type="text/css" rel="stylesheet"/>
    <!-- google code prettify css-->
    <link href="${ctx}/static/plugins/google-code-prettify/prettify.css" type="text/css" rel="stylesheet"/>
    <!--font icons :: fort-awesome-->
    <link href="${ctx}/static/plugins/fort-awesome/css/font-awesome.css" type="text/css" rel="stylesheet"/>
    <!--[if IE 7]>
    <link href="${ctx}/static/plugins/fort-awesome/css/font-awesome-ie7.min.css" type="text/css" rel="stylesheet"/>
    <![endif]-->

    <link href="${ctx}/static/css/top-nav.css" type="text/css" rel="stylesheet"/>

    <!--jquery -->
    <script src="${ctx}/static/assets/js/jquery-1.10.2.js"></script>
    <script src="${ctx}/static/assets/js/jquery-migrate-1.2.1.js"></script>

    <!--bootstrap js-->
    <script src="${ctx}/static/plugins/bootstrap/2.3.2/js/bootstrap.js"></script>

    <sitemesh:head/>
</head>

<body>

    <%@ include file="/WEB-INF/layouts/header.jsp" %>

        <sitemesh:body/>

    <%@ include file="/WEB-INF/layouts/footer.jsp" %>

</body>
</html>