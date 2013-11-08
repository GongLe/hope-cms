<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>菜单管理</title>
</head>

<body>

<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <a href="${ctx}/dashboard" class="grey"> <i class="icon-home home-icon"></i></a>
        </li>
        <li class="active">
            菜单管理
        </li>
    </ul>
    <!--.breadcrumb-->

</div>

<div class="page-content">
    <div class="row-fluid">
        <div class="span12">
            <div class="table-funtion-bar clear-both">
                <div class="btn-group">
                    <button class="btn no-border tooltips" id="create-function" data-original-title="新增" >
                        <i class="icon-plus"></i>
                    </button>
                    <button class="btn no-border tooltips" id="refresh-function" data-original-title="刷新">
                        <i class="icon-refresh"></i>
                    </button>
                    <button class="btn no-border tooltips" id="delete-function" style="display:none;" data-original-title="删除">
                        <i class="icon-trash"></i>
                    </button>
                </div>

            </div><!--/.table-funtion-bar-->

            <div class="row-fluid">
               <div class="span6 pull-left">
                   <table id="menuTreeGrid" style="width:505px;height:500px" ></table>
               </div>
                <div class="span6 pull-left" style="border: 1px solid #ccc;height: 500px;">
                    span5
                </div>
            </div>
        </div>
    </div>
</div>

<!--/.page-content-->
<script>
    var selectedMenuId;
$(function(){
    //表单提交后,iframe回调函数
    window.actionCallback = function () {
        $.colorbox.close();
        lework.notify('操作提示', this.message, this.type);
    };
    window.deleteCallback = function () {
        $.colorbox.close();
        lework.notify('操作提示', this.message, this.type);
    };

    using(['treegrid'], function () {
        $('#menuTreeGrid').treegrid({
            url: 'menu/getTreeGrid',
            method: 'post',
            rownumbers: false,
            idField: 'id',
            treeField: 'name',
            columns: [
                [
                    {field: 'name', title: '菜单名称', width: 150},
                    {field: 'code', title: '菜单代码', width: 150, align: 'left'},
                    {field: 'url', title: 'URL', width: 200},
                ]
            ],
            onClickRow : function(row){
                  console.log(row)
            },
            onDblClickRow : function(row){
                $.colorbox({
                    href :'menu/update?$SiteMesh=false&id=' + row.id  ,
                    adjustY:'40%',
                    width: '700px',
                    overlayClose: false,
                    scrolling: false
                })
            },
            onLoadSuccess : function(){
                $('.tooltips').tooltip();
            }
        });
    })
    //新增
    $('#create-function').click(function () {
        $(this).colorbox({
            href :'menu/update?$SiteMesh=false' ,
            adjustY:'40%',
            width: '700px',
            overlayClose: false,
            scrolling: false
        })
    });
    //刷新
    $('#refresh-function').click(function () {
        $('#menuTreeGrid').treegrid('reload');
    });
})
</script>

</body>
</html>