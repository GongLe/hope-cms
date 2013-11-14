<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>用户管理</title>
</head>

<body>

<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <a href="${ctx}/dashboard" class="grey"> <i class="icon-home home-icon"></i></a>
        </li>
        <li class="active">
            用户管理
        </li>
    </ul>
    <!--.breadcrumb-->

</div>

<div class="page-content">
    <div class="row-fluid">
        <div class="span12">

            <div class="box box-color box-bordered-no ">
                <div class="box-title no-margin-top">
                    <h3>
                        <i class="icon-list-ul"></i>
                        用户列表
                    </h3>
                </div>
                <div class="box-content no-padding ">
                    <table id="table-list"
                           class="table table-hover  table-nomargin table-bordered dataTable dataTable-nosort clear-both">
                    </table>
                    <div class="table-funtion-bar clear-both">
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
                            <button class="btn no-border tooltips" id="create-function" data-original-title="新建" >
                                <i class="icon-plus"></i>
                            </button>
                            <button class="btn no-border tooltips" id="refresh-function" data-original-title="刷新">
                                <i class="icon-refresh"></i>
                            </button>
                            <button class="btn no-border tooltips" id="delete-function" style="display:none;" data-original-title="删除">
                                <i class="icon-trash"></i>
                            </button>
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
        //表单提交后,iframe回调函数
        window.actionCallback = function (resp) {
            $.colorbox.close();
            oTable.fnDraw();
            lework.notify(resp.attributes.title, resp.attributes.message, resp.attributes.type);
        };
        window.deleteCallback = function (resp) {
            $.colorbox.close();
            oTable.fnDraw();
            lework.notify(resp.attributes.title, resp.attributes.message, resp.attributes.type);
        };
        var oTable = $('#table-list');

        oTable.dataTable({
            'aoColumns': [
                { 'mData': 'name', 'sTitle': '姓名' },
                { 'mData': 'loginName', 'sTitle': '用户名'}  ,
                { 'mData': 'email', 'sTitle': 'Email'}  ,
                { 'mData': 'status', 'sTitle': '状态'}  ,
                { 'mData': 'createdBy', 'sTitle': '创建人'}  ,
                { 'mData': 'id', 'sTitle': '操作'}
            ],
            'aoColumnDefs': [
                {
                    'mRender': function (data, type, full) {
                        //  console.log(data)
                        if (data == 'enable') {
                            return   '<i class="icon-flag bigger-130 green" title="启用的"></i>';
                        }
                        return    '<i class="icon-flag bigger-130 red" title="禁用的"></i>';
                    },
                    'aTargets': [3 ]
                },
                {
                    'mRender': function (data, type, full) {
                      //  console.log(data)
                        return  $('#tableActionTpl').render({id: data});
                    },
                    'aTargets': [5 ]
                },
                { bSortable: false,
                    aTargets: [5]
                } ,
                //   { 'bVisible': false,  'aTargets': [ 1 ] },
                { 'sClass': 'center', 'aTargets': [3] }
            ],
            'bStateSave': true , /**state saving **/
            'bProcessing': true ,
            'bServerSide': true,
            'fnServerData': lework.springDataJpaPageableAdapter,
            'sAjaxSource': '${ctx}/user/getDatatablesJson',
            // 'fnServerParams' :function(aoData ){ aoData.push( { "name": "more_data", "value": "my_value" } ); },
            'fnInitComplete': function () {     /**datatables ready**/

                lework.initDatatablesSearchHolder('用户名/姓名');

            } ,
            fnDrawCallback :function(oSettings ){
                // bootstrap-tooltip
                $('.tooltips').tooltip();
                $('.confirmDelete').confirmDelete({onDelete: function () {
                    var id = $(this).data('id') ;
                    $.hiddenSubmit({
                        formAction: 'user/delete',
                        data: [  {name: 'deleteId', value:  id } ],
                        complete : function(){  checkFunbarStatus(false); }
                    })
                    return true;
                 }
                });

            }
        });//dataTables

        //多行选择
        oTable.tableMutilDelete({
            afterSelect: function () {
                var size = oTable.find('tr.selected').size();
                checkFunbarStatus(size > 0);
            }
        });

        //新建
        $('#create-function').click(function () {
            $(this).colorbox({
                href :'user/update?$SiteMesh=false' ,
                adjustY:'40%',
                width: '800px',
                overlayClose: false,
                scrolling: false
            })
        });
        //刷新
        $('#refresh-function').click(function () {
            oTable.fnDraw();
            //重置function bar状态
            checkFunbarStatus(false);
        });
        //双击进入修改页面
        oTable.on('dblclick','tbody>tr',function(event){
            event.preventDefault();
            $(this).find('.update').trigger('click');
        });

        //多行删除
        $('#delete-function').confirmDelete({text: '<span class="text-warning">确认删除多条记录？</span>',
            onDelete: function () {
                //TODO
                var ids = [];
                oTable.find('tr.selected .confirmDelete').each(function () {
                    ids.push($(this).data('id'))
                });
                ids = ids.join(',');
                $.hiddenSubmit({
                    formAction: 'user/delete',
                    data: [  {name: 'deleteIds', value: ids } ] ,
                    complete : function(){  checkFunbarStatus(false); }
                })
            return true;
        }
        });
        //取消选择
        $('#cancelSelected').click(function () {
            oTable.find('tbody>tr').removeClass('selected warning');
            checkFunbarStatus(false);
        });
        //全选行
        $('#selectedAll').click(function () {
            oTable.find('tbody>tr').addClass('selected warning')
            checkFunbarStatus(true);
        });

        //根据所选行,修改function bar状态.
        function checkFunbarStatus(hasSelected) {
            if (hasSelected == true) {
                $('#checkIcon').removeClass('icon-check-empty').addClass('icon-check')
                $('#delete-function').show();
            } else {
                $('#checkIcon').removeClass('icon-check').addClass('icon-check-empty');
                $('#delete-function').hide();
            }
        }

    })  //dom ready


</script>

<!-- ===============JsRender template ===================
    @see http://www.jsviews.com/#samples/jsr/converters
-->

<!--table action template-->
<script id="tableActionTpl" type="text/x-jsrender">
    <div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
        <a class="light-blue tooltips filterSelected" href="javascript:;" data-original-title="配置角色|菜单|权限"  >
            <i class="icon-cog bigger-140 filterSelected"></i>
        </a>
        <a class="green tooltips view"  href="user/view?id={{:id}}&$SiteMesh=false"   data-original-title="查看"
           onclick="$(this).colorbox({adjustY:'40%',width:'900px',overlayClose:false,scrolling:true,scrolling:false });" >
            <i class="icon-zoom-in bigger-140 filterSelected"></i>
        </a>
        <a class="blue tooltips update" href="user/update?id={{:id}}&$SiteMesh=false" data-original-title="编辑基本信息"
           onclick="$(this).colorbox({ adjustY:'40%',width:'800px',overlayClose:false,scrolling:false });" >
            <i class="icon-edit bigger-140 filterSelected"></i>
        </a>
        <a class="red tooltips confirmDelete" href="javascript:;" data-id="{{:id}}"  data-original-title="删除">
            <i class="icon-trash bigger-140 filterSelected"></i>
        </a>
    </div>
</script>

</body>
</html>