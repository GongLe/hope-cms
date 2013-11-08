<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>

<div class="modal-content" >
    <form action="role/update"  method="post" id="inputForm" name="inputForm" target="$iframe"
          class="no-margin form-horizontal offset-40 error-inline" >
        <div class="modal-header" style="padding:5px 20px 5px 20px;">
            <small class="grey">
                 正在<c:if test="${entity.isNew == true}" >新建角色</c:if><c:if test="${entity.isNew ==false}" >编辑角色“${entity.name}”</c:if>
            </small>
        </div>

<div class="modal-body ">
    <div class="row-fluid ">
      <div class="span9"  >

        <!--隐藏域-->
        <form:hidden path="entity.id" />

        <div class="control-group">
            <label class="control-label " for="name">角色名称</label>
            <div class="controls">
                <input  class="input-xlarge" type="text" id="name" name="name"   value="${entity.name}" placeholder="输入角色名称">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="code">角色代码</label>
            <div class="controls">
                <input class="input-xlarge" type="text" id="code" name="code" value="${entity.code}" placeholder="输入角色代码">
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="status">状态</label>
            <div class="controls">
                <form:select  path="entity.status" cssClass="input-xlarge" cssStyle="width:284px"  >
                    <form:options  items="${statusList}"  itemValue="code" itemLabel="name"/>
                </form:select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="description">描述</label>
            <div class="controls">
                <textarea class="input-xlarge" rows="3" id="description" name="description"   placeholder="输入描述信息" > ${entity.description}</textarea>
            </div>
        </div>
    </div>
     <div class="span3">
         <h5 class="header smaller lighter red no-margin-top no-margin-bottom no-border">所属权限</h5>
         <div class="well" style="min-height:230px;max-height:300px;">
             <ul id="permissionTree" ></ul>
         </div>
     </div>
    </div>
</div><!--/.modal-body-->
        <div class="modal-footer">
            <button type="button" class="btn btn-small" onclick="$.colorbox.close()">
                <i class="icon-remove"></i>
                关闭
            </button>

            <button type="submit" class="btn btn-small btn-primary" >
                <i class="icon-ok"></i>
                保存
            </button>
        </div>
    </form>
</div>
<script>
    $(function(){
        //from validater
        $('#inputForm').validate({rules: {
            name: {
                required: true,
                maxlength: 50
            },
            code : {
                required: true ,
                maxlength: 50,
                remote: {
                    url: 'role/validateRoleCode', //后台处理程序
                    type: 'post',               //数据发送方式
                    dataType: 'json',           //接受数据格式
                    data: {                     //要传递的数据
                      roleId  : function() {
                            return $('#id').val() ;
                        }
                    }
                }
            },
            status :{
                required:true
            },
            description :{
                maxlength : 200
            }
        }, messages: {
            code:{
                remote :'该值已被占用'
            }
        }
       }); //end validate

        //easyui loader
        using(['tree'], function () {
            $('#permissionTree').tree({
                url: 'role/getTreeJson',
                method: 'get',
                checkbox: true ,
                onLoadSuccess : function(node, data){

                },
                onCheck : function(node, checked){

                }
            });
        })

    })
</script>
</body>
</html>
