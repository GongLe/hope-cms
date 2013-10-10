<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>角色管理</title>
</head>

<body>

<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="${ctx}/dashboard">home</a>
        </li>

        <li class="active">
            角色管理
        </li>
    </ul>
    <!--.breadcrumb-->

</div>

<div class="page-content">
    <div class="row-fluid">
        <div class="span12">

            <div class="box box-color box-bordered ">
                <div class="box-title">
                    <h3>
                        <i class="icon-list-ul"></i>
                        角色列表
                    </h3>
                </div>
                <div class="box-content no-padding ">
                    <table id="table-list"
                           class="table table-hover table-nomargin table-bordered dataTable dataTable-nosort clear-both"></table>
                    <div class="table-funtion-bar clear-both">
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn dropdown-toggle">
                                <i class="icon-check-empty bigger-120"></i>
                                <span class="caret"></span>
                            </button>

                            <ul class="dropdown-menu dropdown-default">
                                <li>
                                    <a href="#">全选</a>
                                </li>

                                <li>
                                    <a href="#">启用的</a>
                                </li>

                                <li>
                                    <a href="#">停用</a>
                                </li>

                            </ul>
                        </div>
                        <div class="btn-group">
                            <button class="btn" data-original-title="新增"><i class="icon-plus"></i></button>
                            <button class="btn" data-original-title="刷新"><i class="icon-refresh"></i></button>
                            <button class="btn" data-original-title="删除"><i class="icon-trash"></i></button>
                        </div>
                    </div>
                </div>
            </div>
            <!--/.box-->

        </div>
    </div>
</div>

<!--/.page-content-->
<script>
    $(function () {

        $('#table-list').dataTable({
            "aoColumns": [
                { "mData": "name", 'sTitle': '名称' },
                { "mData": "code", 'sTitle': '值'}  ,
                { "mData": "createdDate", 'sTitle': '创建时间'}  ,
                { "mData": "id", 'sTitle': '操作'}
            ],
            "aoColumnDefs": [
                {
                    "mRender": function (data, type, full) {

                        return  $('#tableActionTpl').render() ;
                    },
                    "aTargets": [3 ]
                },
                { bSortable: false,
                    aTargets: [3]
                },
                //   { "bVisible": false,  "aTargets": [ 1 ] },
                { "sClass": "center", "aTargets": [ 2 ] }
            ],
            "bStateSave": true , /**state saving **/
            'bProcessing': false ,
            'bServerSide': true,
            'fnServerData': lework.springDataJpaPageableAdapter,
            'sAjaxSource': '${ctx}/role/getDatatablesJson' ,
            'fnInitComplete': function () {      /**datatables ready**/

                // bootstrap-tooltip
                $('.action-buttons a','#table-list').tooltip() ;
                /**
                $('.action-buttons .delete-confirm', '#table-list').popover({html: true, placement: 'bottom'  ,
                    content  :'confirm?'
                });
                $('.action-buttons .delete-confirm', '#table-list').blur(function(){
                        $(this).popover('hide');
                }) ;    **/
            }
        });
    })  //dom ready


</script>

<!-- ===============JsRender template ===================
    @see http://www.jsviews.com/#samples/jsr/converters
-->

<!--table action template-->
<script id="tableActionTpl" type="text/x-jsrender">
    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
        <a class="grey" href="javascript:;" data-original-title="配置" title="">
            <i class="icon-cog bigger-120"></i>
        </a>
        <a class="grey" href="javascript:;" title="" data-original-title="查看">
            <i class="icon-zoom-in bigger-120"></i>
        </a>
        <a class="grey" href="javascript:;" title="" data-original-title="修改">
            <i class="icon-edit bigger-120"></i>
        </a>
        <a class="grey delete-confirm" href="javascript:;" title="" data-original-title="删除">
            <i class="icon-trash bigger-120"></i>
        </a>
    </div>
</script>

</body>
</html>