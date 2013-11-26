<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>
<%--右侧基本信息,关联信息ajax页面--%>
<table class="table table-condensed table-hover" style="min-width:300px;border:1px solid #e3e3e3">
    <thead>
    <tr style="background: none;">
        <th colspan="4"><i class="icon-pushpin blue"></i>&nbsp;&nbsp;基本信息</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="table-lable">菜单名称：</td>
        <td >${menu.name}</td>
        <td class="table-lable">菜单代码：</td>
        <td >${menu.code}</td>
    </tr>
    <tr>
        <td class="table-lable">URL：</td>
        <td colspan="3">${menu.url}</td>
    </tr>
    <tr>
        <td class="table-lable">icon：</td>
        <td >  <i class="bigger-140 ${menu.icon}"></i> </td>
        <td class="table-lable">状态：</td>
        <td >  ${statusName} </td>
    </tr>
    <tr>
        <td class="table-lable">上级菜单：</td>
        <td colspan="3"> ${menu.parentName} </td>
    </tr>
    <tr>
        <td class="table-lable">创建人：</td>
        <td>${menu.createdBy}1</td>
        <td class="table-lable">创建时间：</td>
        <td><fmt:formatDate value="${menu.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td class="table-lable">最后修改人：</td>
        <td>${menu.lastModifiedBy}</td>
        <td class="table-lable">最后修改时间：</td>
        <td><fmt:formatDate value="${menu.lastModifiedDate}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
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
                      <%--  <div class="clearfix">
                            <div class="pull-right">
                                <button class="btn btn-white btn-minier ">添加菜单到角色</button>
                            </div>
                        </div>--%>

                          <div class="input-append no-margin-bottom pull-right">
                              <!--自定义搜索-->
                              <form id="searchForm" name="searchForm" class="no-margin no-padding"  >
                                    <span class="input-icon input-icon-right">
                                        <input class="input-medium" id="search" name="search" type="text" placeholder="名称/代码">
                                        <i class="icon-search blue" onclick="$('#searchForm').submit()"></i>
                                    </span>
                              </form> <!--/#searchForm-->
                          </div>
                        <table id="menuRelatedRoleTable"
                               class="table table-hover  table-nomargin table-bordered dataTable dataTable-nosort clear-both">
                        </table>
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
        var oTable = $('#menuRelatedRoleTable');

        oTable.dataTable({
            'aoColumns': [
                { 'mData': 'name', 'sTitle': '角色名称' },
                { 'mData': 'code', 'sTitle': '角色代码'}  ,
                { 'mData': 'type', 'sTitle': '类别'}  ,
                { 'mData': 'id', 'sTitle': '操作'}
            ],
            'aoColumnDefs': [
                {
                    'mRender': function (data, type, full) {
                        return  full['typeName'] ;
                    },
                    'aTargets': [2 ]
                },
                {
                    'mRender': function (data, type, full) {
                        return '<a href="javascript:;" data-id="{1}">解除</a>'.format(data) ;
                    },
                    'aTargets': [3 ]
                },
                { bSortable: false,
                    aTargets: [3]
                },
                { 'sClass': '', 'aTargets': [3 ] }
            ],
            'sDom': 'rtip',
            sPaginationType:'two_button',
            'iDisplayLength':5,
            'bStateSave': false  , /**state saving **/
            'bProcessing': true ,
            'bServerSide': true,
            'fnServerData': lework.springDataJpaPageableAdapter,
            'sAjaxSource': '${ctx}/menu/getMenuRelatedRoleJson',
            'fnServerParams' :function(aoData ){
                 aoData.pushArray({name:'menuId',value:'${menu.id}'})
            },
            'fnInitComplete': function () {     /**datatables ready**/
            } ,
            fnDrawCallback :function(oSettings ){
            }
        });//dataTables

    })  //dom ready
</script>
</body>
</html>
