<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>添加成员</title>
    <style type="text/css">
        .box-title {
            border-bottom: 1px dashed #c5d0dc !important;
        }

        #memberContainer .west {
            width: 28%;
            min-height: 300px;
            border-right: 1px dashed #c5d0dc;
        }

        #memberContainer  .west h6 {
            margin: 5px 10px;
        }

        #memberContainer  .middle {
            width: 70%;
            min-height: 300px;
            padding:0 0 0 5px;
            border-right: none;
            overflow: auto;
        }

        #memberContainer  .middle h6 {
            margin: 5px 10px;
        }
    </style>
</head>

<body>
 <div class="modal-content" >
     <form  method="post" id="inputForm" name="inputForm"
            class="no-margin form-horizontal offset-30 error-inline" >
         <div class="modal-header" style="padding:1px 15px">
             <small class="grey">
                 添加用户到角色
             </small>
         </div>

         <div class="modal-body ">
             <div class="row-fluid ">
                 <div class="span12"  >
                     <div class="box  box-bordered-no ">
                         <div class="box-title no-margin-top no-padding-top no-padding-left" >
                             <div class="alert no-margin-bottom">${role.name}(${role.code})</div>
                         </div>
                         <div class="box-content no-padding " id="memberContainer">

                             <div class="pull-left west" >
                                 <h6  class="smaller lighter blue">组织机构</h6>
                                 <ul id="orgTree" style="padding:5px 10px 0 5px;" ></ul>
                             </div>
                             <div class="pull-left middle"  >
                                 <h6  class="smaller lighter blue text-left">成员</h6>
                                 <div id="relatedUserContent">

                                     <div class="checkbuttonNo  panelcheck"  >
                                         <div class="checktext"> <i class="icon-user blue"></i>&nbsp;&nbsp;userName
                                         </div>
                                         <div class="triangleNo"></div>
                                     </div>
                                     <div class="checkbuttonNo  panelcheck"  >
                                         <div class="checktext"> <i class="icon-user red"></i>&nbsp;&nbsp;userName
                                         </div>
                                         <div class="triangleNo"></div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <!--/.box-->
                 </div>

             </div>
         </div><!--/.modal-body-->
         <div class="modal-footer">
             <button type="button" class="btn btn-small" onclick="$.colorbox.close()">
                 关闭
             </button>
         </div>
     </form>
 </div>
<script>
    var $orgTree =  $('#orgTree','#inputForm') ;
    $(function(){
        var loadOrgTree = function () {
            $orgTree.tree({
                url: 'organization/getTree',
                method: 'get',
                checkbox: false,
                onLoadSuccess: function (node, data) {
                    //默认选择根节点.
                    var root = $orgTree.tree('getRoot');
                    $orgTree.tree('select', root.target);
                },
                onSelect : function (node) {

                }
            });
        }
        using(['tree'], function () {
            loadOrgTree();
        }) //using
    });
</script>
</body>
</html>