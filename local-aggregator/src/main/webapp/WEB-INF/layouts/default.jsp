<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
    <title><sitemesh:title/></title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <%@ include file="/WEB-INF/included/resource.jsp" %>

    <sitemesh:head/>
</head>

<body>

    <%@ include file="/WEB-INF/layouts/header.jsp" %>

        <sitemesh:body/>

    <%@ include file="/WEB-INF/layouts/footer.jsp" %>

</body>
</html>