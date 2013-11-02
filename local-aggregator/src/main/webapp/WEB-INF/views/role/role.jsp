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
            <a href="${ctx}/dashboard" class="grey"> <i class="icon-home home-icon"></i></a>
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
                           class="table table-hover  table-nomargin table-bordered dataTable dataTable-nosort clear-both">
                    </table>
                    <div class="table-funtion-bar clear-both">
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn no-border dropdown-toggle">
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
                            <button class="btn no-border tooltips" id="create-function" data-original-title="新增"><i class="icon-plus"></i></button>
                            <button class="btn no-border tooltips" id="refresh-function" data-original-title="刷新"><i class="icon-refresh"></i></button>
                            <button class="btn no-border tooltips" id="delete-function" data-original-title="删除"><i class="icon-trash"></i></button>
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
        //表弟提交后,iframe回调函数
        window.actionCallback = function(){
            $.colorbox.close();
            lework.notify('操作提示', this.message  , this.type );
        } ;
        var oTable = $('#table-list');
        //新增
        $('#create-function').click(function () {

        });
        //刷新
        $('#refresh-function').click(function () {
            oTable.fnDraw();
        });
        //删除
        $('#delete-function').confirmDelete({text :'<span class="text-warning">确认删除多条记录?</span>',onDelete: function () {
            //TODO
            oTable.fnDraw(); //reload Datatables
            return true ;
         }
        });

        oTable.dataTable({
            "aoColumns": [
                { "mData": "name", 'sTitle': '名称' },
                { "mData": "code", 'sTitle': '值'}  ,
                { "mData": "createdDate", 'sTitle': '创建时间'}  ,
                { "mData": "id", 'sTitle': '操作'}
            ],
            "aoColumnDefs": [
                {
                    "mRender": function (data, type, full) {
                        console.log(data)
                        return  $('#tableActionTpl').render({id:data});
                    },
                    "aTargets": [3 ]
                },
                { bSortable: false,
                    aTargets: [3]
                },
                //   { "bVisible": false,  "aTargets": [ 1 ] },
                { "sClass": "center", "aTargets": [ 2 ] }
            ],
            "bStateSave": true, /**state saving **/
            'bProcessing': true ,
            'bServerSide': true,
            'fnServerData': lework.springDataJpaPageableAdapter,
            'sAjaxSource': '${ctx}/role/getDatatablesJson',
            'fnInitComplete': function () {     /**datatables ready**/

                lework.initDatatablesSearchHolder('名称/值');
                // bootstrap-tooltip
                $('.tooltips').tooltip();
                $('.confirmDelete').confirmDelete({onDelete: function () {
                    console.log('on confirm delete ');
                },
                    onCancel: function () {
                        console.log('on confirm canel');
                        return true;
                    }
                });
            }
        });//dataTables
        window.openModal = function(){};
    })  //dom ready


</script>

<!-- ===============JsRender template ===================
    @see http://www.jsviews.com/#samples/jsr/converters
-->

<!--table action template-->
<script id="tableActionTpl" type="text/x-jsrender">
    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
        <a class="grey tooltips" href="javascript:;" data-original-title="配置"  >
            <i class="icon-cog bigger-140"></i>
        </a>
        <a class="grey tooltips" href="http://www.baidu.com"   onclick="$(this).colorbox({ iframe :true  ,overlayClose:false});"   data-original-title="查看">
            <i class="icon-zoom-in bigger-140"></i>
        </a>
        <a class="grey tooltips" href="role/update?id={{:id}}&$SiteMesh=false"
           onclick="$(this).colorbox({ width:'50%',overlayClose:false,scrolling:false });" data-original-title="修改">
            <i class="icon-edit bigger-140"></i>
        </a>
        <a class="grey tooltips confirmDelete" href="javascript:;"  data-original-title="删除">
            <i class="icon-trash bigger-140"></i>
        </a>
    </div>
</script>

</body>
</html>