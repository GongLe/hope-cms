<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="lt-ie9 lt-ie8 lt-ie7" lang="zh-CN"> <![endif]-->
<!--[if IE 7]> <html class="lt-ie9 lt-ie8" lang="zh-CN"> <![endif]-->
<!--[if IE 8]> <html class="lt-ie9" lang="zh-CN"> <![endif]-->
<!--[if gt IE 8]><!--><html lang="zh-CN"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <%@ include file="/WEB-INF/included/resource.jsp" %>
    <le:stylesheet src="/assets/ace/css/login.css" />

</head>
<body class="login">
<div class="wrapper">
    <h1  style="color:#ffffff"><i class="icon-leaf"></i>&nbsp;lework</h1>
    <div class="login-body">
        <h2>账号登陆</h2>
        <form action="${ctx}/login" method="post" class="form-validate" >
            <div class="control-group">
                <div class="email controls">
                    <input type="text" name="username" placeholder="用户名" class="input-block-level"  >
                </div>
            </div>
            <div class="control-group">
                <div class="pw controls">
                    <input type="password" name="password" placeholder="密码" class="input-block-level"  >
                </div>
            </div>
            <div class="submit">
                <shiro:user>
                <p class="padding-10">
                    <strong>
                        <shiro:principal/>
                    </strong>检测到你已经登录，<a href="${ctx }/logout">注销</a> 或者
                    <a href="${ctx }/dashboard">进入系统</a>
                </p>
                </shiro:user>
                <shiro:guest>
                    <div class="remember">
                        <label for="rememberMe">记住密码&nbsp;&nbsp;</label><input type="checkbox" name="rememberMe" id="rememberMe"/>
                    </div>
                    <input type="submit" value="登陆" class="btn btn-primary" >
                </shiro:guest>
            </div>
        </form>
        <div class="forget">
            <a href="#"><span>忘记密码？</span></a>
        </div>
    </div>
</div>
</body>
</html>
