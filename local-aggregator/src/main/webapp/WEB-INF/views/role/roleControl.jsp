<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>角色权限控制</title>
</head>

<body>

<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <a href="${ctx}/dashboard" class="grey"> <i class="icon-home home-icon"></i></a>
        </li>
        <li class="active">
            角色权限控制
        </li>
    </ul>
    <!--.breadcrumb-->

</div>

<div class="page-content">
    <div class="row-fluid">
        <div class="span12">

            <div class="box  box-bordered-no ">
                <div class="box-title no-margin-top no-padding-top" style="border-bottom-style: dashed ; border-bottom-color: #c5d0dc;">
                    <h3 class="blue">角色权限控制</h3>
                </div>
                <div class="box-content no-padding ">

                    <div class="pull-left" style="width:15%;min-height:550px;border-right:1px dashed  #c5d0dc;">
                          <h5 style="margin:5px 10px;" class="header smaller lighter blue">  角色组</h5>
                          <ul id="orgTree" style="padding:5px 10px 0 5px;" ></ul>
                    </div>
                    <div class="pull-left" style="width:20%;min-height:550px;border-right:1px dashed  #c5d0dc;">
                        <h5 style="margin:5px 10px;" class="header smaller lighter blue ">角色</h5>
                        <ul id="roleTree" style="padding:5px 10px 0 5px;" ></ul>
                        <div id="alertNullRoleData" class="alert alert-warning" style="margin:0 8px;">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="icon-remove"></i>
                            </button>
                             暂无数据
                            <br>
                        </div><!--/.alert-->
                    </div>
                    <div class="pull-left" style="width:60%; padding:0 0 5px 10px;" id="shouQuan">
                            <h5 style="margin:5px 10px;" class="header smaller lighter blue "> 角色授权</h5>
                            <div class="alert alert-warning">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="icon-remove"></i>
                                </button>
                                 暂无数据
                                <br>
                            </div><!--/.alert-->
                    </div>
                </div>
            </div>
            <!--/.box-->
        </div>
    </div>
</div>

<!--/.page-content-->
<script>
    var $orgTree =  $('#orgTree') ,$roleTree = $('#roleTree') ;
    $(function () {
        /**
        * 加载角色组
         * 加载队列:角色组 ==>  角色 ==> 角色授权页面
         */
        var loadOrgTree = function () {
            $orgTree.tree({
                url: 'organization/getTree',
                method: 'get',
                checkbox: false,
                onLoadSuccess: function (node, data) {
                    //默认选择根节点.
                    var root =  $('#orgTree').tree('getRoot');
                    $('#orgTree').tree('select', root.target);
                },
                onSelect : function (node) {
                    loadRoleTree(node.id);
                }
            });
        }
        /**
         *根据角色组()加载所属角色
         * @param groupId
         */
        var loadRoleTree = function (groupId) {
            $roleTree.tree({
                url: 'roleControl/getRoleTreeByGroupId?1=1&' + $.param({'groupId': groupId }),
                method: 'get',
                checkbox: false,
                onLoadSuccess: function (node, data) {
                    //默认选择根节点.
                    var root =  $('#roleTree').tree('getRoot');
                    if(root){
                        $('#alertNullRoleData').hide();
                        $('#roleTree').tree('select', root.target);
                    }else{
                        $('#alertNullRoleData').show();
                    }
                },
                onSelect: function (node) {
                    loadShouQuan(node.id) ;
                }
            });
        }
        /**
         * 根据角色ID加载角色授权页面
         * @param roleId
         */
        var loadShouQuan = function (roleId) {
            $('#shouQuan').load('roleControl/shouquan?1=1&' + $.param({'roleId': roleId, '$SiteMesh': 'false' }));
        }

        using(['tree'], function () {
            loadOrgTree();
        }) //using

    })  //dom ready


</script>
</body>
</html>