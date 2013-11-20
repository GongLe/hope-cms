<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>角色菜单</title>
</head>

<body>
<h5 style="margin:5px 10px;" class="header smaller lighter blue "> 角色授权-${role.name}(${role.code})</h5>
 <div class="tabbable" style="margin:15px 10px 5px 10px;">
     <ul class="nav nav-tabs" id="shouquanTab">
         <li >
             <a data-toggle="tab" data-url="roleControl/member" href="#member">
                 角色成员
             </a>
         </li>

         <li>
             <a data-toggle="tab" data-url="roleControl/modules" href="#module">
                模块权限
             </a>
         </li>

     </ul>

     <div class="tab-content">
         <!--角色成员-->
         <div id="member" class="tab-pane" style="min-height:430px;"> </div>
         <!--模块权限::菜单权限-->
         <div id="module" class="tab-pane" style="min-height:430px;"></div>
     </div>
 </div>
<script>

    $(function () {
        var roleId= '${role.id}' ;
        $('a[data-toggle="tab"]','#shouquanTab').on('shown', function (e) {
             //ajax load tab content
            $('#shouQuan').block({message:'加载中..'});
            $($(this).attr('href') ).load($(this).data('url'),{'roleId':roleId,'$SiteMesh':'false'},function(){
                $('#shouQuan').unblock();
            }) ;//load
        })
        //load first tab
        $('a[data-toggle="tab"]:first','#shouquanTab').tab('show')  ;

    })  //dom ready
</script>
</body>
</html>