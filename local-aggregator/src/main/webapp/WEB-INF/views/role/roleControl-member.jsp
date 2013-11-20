<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>角色成员</title>
</head>

<body>
<div class="table-funtion-bar no-margin clear-both" id="memberBar"  >
    <div class="btn-group">
        <button data-toggle="dropdown" class="btn no-border dropdown-toggle">
            <i id="checkIcon" class="icon-check-empty bigger-120"></i>
            <span class="caret"></span>
        </button>

        <ul class="dropdown-menu dropdown-default">
            <li id="selectedAll">
                <a href="javascript:;"  >全选</a>
            </li>
            <li id="cancelSelected" >
                <a href="javascript:;" >取消</a>
            </li>
        </ul>
    </div>
    <div class="btn-group">
        <button class="btn  no-border tooltips" id="refresh-function" data-original-title="刷新">
            <i class="icon-refresh"></i>
        </button>
        <button class="btn btn-danger no-border tooltips" id="create-function" data-original-title="添加成员" >
            <i class="icon-plus"></i>
        </button>

        <button class="btn btn-error no-border tooltips" id="delete-function" style="display:none;" data-original-title="删除成员">
            <i class="icon-trash"></i>
        </button>
    </div>
    <div class="input-append no-margin-bottom pull-right">
        <!--自定义搜索-->
        <form id="searchForm" name="searchForm" class="no-margin no-padding">
            <span class="input-icon input-icon-right">
                <input class="input-medium" id="search" name="search" type="text" placeholder="名称/代码">
                <i class="icon-search blue" onclick="$('#searchForm').submit()" ></i>
            </span>
        </form> <!--/#searchForm-->
    </div>
</div> <!--/.table-funtion-bar-->
TODO user table
<script>

    $(function () {
        $('#memberBar .tooltips').tooltip({
            placement:'bottom'
        });
    })  //dom ready
</script>
</body>
</html>