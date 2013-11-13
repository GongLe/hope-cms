<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
<%--右侧基本信息,关联信息ajax页面--%>
<table class="table table-bordered table-condensed table-hover" style="min-width:300px;">
    <thead>
    <tr style="background: none;">
        <th colspan="4"><i class="icon-pushpin blue"></i>&nbsp;&nbsp;基本信息</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>菜单名称：</td>
        <td >${entity.name}</td>
        <td>菜单代码：</td>
        <td >${entity.code}</td>
    </tr>
    <tr>
        <td>URL：</td>
        <td colspan="3">${entity.url}</td>
    </tr>
    <tr>
        <td>icon：</td>
        <td >  <i class="bigger-140 ${entity.icon}"></i> </td>
        <td>状态：</td>
        <td >  ${statusName} </td>
    </tr>
    <tr>
        <td>上级菜单：</td>
        <td colspan="3"> ${entity.parentName} </td>
    </tr>
    <tr>
        <td>创建人：</td>
        <td>${entity.createdBy}1</td>
        <td>创建时间：</td>
        <td><fmt:formatDate value="${entity.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>最后修改人：</td>
        <td>${entity.lastModifiedBy}</td>
        <td>最后修改时间：</td>
        <td><fmt:formatDate value="${entity.lastModifiedDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
    </tr>

    </tbody>
</table>
<table class="table  table-condensed " style="min-width:300px;">

    <tbody>
    <tr>
        <td colspan="8" rowspan="5" class="no-border no-padding-left">
            <div class="tabbable">
                <ul class="nav nav-tabs" id="myTab3">

                    <li class="active">
                        <a data-toggle="tab" href="#profile3">
                          <%--  <i class="blue icon-user bigger-110"></i>--%>
                            关联的角色
                        </a>
                    </li>
                </ul>

                <div class="tab-content">
                    <div id="profile3" class="tab-pane in active">
                        <div class="clearfix">
                            <div class="pull-right">
                                <button class="btn btn-white btn-minier ">添加菜单到角色</button>
                            </div>
                        </div>
                        <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid.</p>

                        <p>Raw denim you probably haven't heard of them jean shorts Austin.</p>
                    </div>
                </div>
            </div>
            <!--/.tabbable-->
        </td>
    </tr>

    </tbody>
</table>
<script>
    $(function () {

    })  //dom ready
</script>
</body>
</html>
