<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>控制面板</title>
</head>

<body>
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">Home</a>

							<span class="divider">
								<i class="icon-angle-right arrow-icon"></i>
							</span>
        </li>

        <li>
            <a href="#">Other Pages</a>

							<span class="divider">
								<i class="icon-angle-right arrow-icon"></i>
							</span>
        </li>
        <li class="active">Grid</li>
    </ul> <!--.breadcrumb-->

</div>

<div class="page-content">
    <div class="page-header position-relative">
        <h1>
            Blank
            <small>
                <i class="icon-double-angle-right"></i>
                bootstrap grid sizing
            </small>
        </h1>
    </div><!--/.page-header-->

    <div class="row-fluid">
        <div class="span12">span12 span12 span12 </div> <!--/.span12-->
    </div> <!--/.row-fluid-->
</div><!--/.page-content-->

</body>
</html>