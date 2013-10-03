<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp"%>
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
<le:javascript src="/assets/js/html5shiv.js"/>
<![endif]-->
<!--bootstrap css-->
<le:stylesheet src="/plugins/bootstrap/2.3.2/css/non-icon/bootstrap.css"/>
<le:stylesheet src="/plugins/bootstrap/2.3.2/css/bootstrap-responsive.css"/>
<!-- google code prettify css-->
<le:stylesheet src="/plugins/google-code-prettify/prettify.css"/>
<!--font icons :: font-awesome-->
<le:stylesheet src="/plugins/font-awesome/css/font-awesome.css"/>
<!--[if IE 7]>
<le:stylesheet src="/plugins/font-awesome/css/font-awesome-ie7.min.css"/>
<![endif]-->


<!--utils-->
<le:stylesheet src="/assets/ace/css/ace-utils.css"/>
<!--layout-->
<le:stylesheet src="/assets/ace/css/ace-layout.css"/>
<!--components-->
<le:stylesheet src="/assets/ace/css/ace-components.css"/>
<!--typography-->
<le:stylesheet src="/assets/ace/css/ace-typography.css"/>
<!--elements-->
<le:stylesheet src="/assets/ace/css/ace-elements.css"/>
<!--forms-->
<le:stylesheet src="/assets/ace/css/ace-forms.css"/>
<!--navbar-->
<le:stylesheet src="/assets/ace/css/ace-navbar.css"/>
<!--left-sidebar-->
<le:stylesheet src="/assets/ace/css/ace-left-sidebar.css"/>
<!--widgets-->
<le:stylesheet src="/assets/ace/css/ace-widget.css"/>
<!--table-->
<le:stylesheet src="/assets/ace/css/table.css"/>
<!--tree-->
<le:stylesheet src="/assets/ace/css/ace-tree.css"/>
<!--gallery-->
<le:stylesheet src="/assets/ace/css/ace-gallery.css"/>

<!--[if lt IE 9]>
<le:stylesheet src="/assets/ace/css/ace-ie.min.css"/>
<![endif]-->


<!--jquery -->
<le:javascript src="/assets/js/jquery-1.10.2.js"/>
<le:javascript src="/assets/js/jquery-migrate-1.2.1.js"/>
<script>
    if ("ontouchend" in document) document.write("<script src=${ctx}/static/assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");

    jQuery(function () {
        if (!("ace" in window)) {
            window.ace = {}
        }
        window.ace.click_event = $.fn.tap ? "tap" : "click";
    });
</script>
<!--bootstrap js-->
<le:javascript src="/plugins/bootstrap/2.3.2/js/bootstrap.js"/>
<!--ace js-->
<le:javascript src="/assets/ace/js/ace.min2.js"/>

