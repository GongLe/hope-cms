<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>

</head>

<body>

<div class="modal-content" >
    <form action="user/update"  method="post" id="inputForm" name="inputForm" target="$iframe"
          class="no-margin form-horizontal offset-20 error-inline" >
        <div class="modal-header" style="padding:5px 20px 5px 20px;">
            <small class="grey">
                正在<c:if test="${entity.isNew == true}" >新建用户</c:if><c:if test="${entity.isNew ==false}" >编辑用户“${entity.loginName}”</c:if>
            </small>
        </div>

<div class="modal-body ">
    <div class="row-fluid ">
      <div class="span12" >

        <!--隐藏域-->
        <form:hidden path="entity.id" />
          <div class="control-group">
              <label class="control-label " for="name">姓名</label>
              <div class="controls">
                  <input  class="input-xlarge" type="text" id="name" name="name"   value="${entity.name}" placeholder="输入姓名">
              </div>
          </div>

          <div class="control-group">
              <label class="control-label " for="loginName">用户名</label>
              <div class="controls">
                  <input  class="input-xlarge" type="text" id="loginName" name="loginName"   value="${entity.loginName}" placeholder="输入用户名">
              </div>
          </div>

          <div class="control-group">
              <label class="control-label " for="plainPassword">密码</label>
              <div class="controls">
                  <div class="input-prepend">
                      <span class="add-on"><i class="icon-key"></i></span>
                      <input style="width: 245px;" type="password" placeholder="输入密码" name="plainPassword" id="plainPassword"
                        <c:if test="${entity.isNew == true }">  value="123456" title="默认密码为123456"  </c:if>    >
                  </div>
              </div>
          </div>


          <div class="control-group">
              <label class="control-label " for="email">Email</label>
              <div class="controls">
                  <input  class="input-xlarge" type="text" id="email" name="email"   value="${entity.email}" placeholder="输入email">
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

          <c:if test="${entity.isNew == false }">
              <div class="control-group">
                  <label class="control-label"  >创建日期</label>
                  <div class="controls">
                      <input class="input-xlarge" type="text"   disabled="disabled"
                             value=" <fmt:formatDate value="${entity.createdDate}" pattern="yyyy/MM/dd HH:mm:ss" />" >

                  </div>
              </div>
              <div class="control-group">
                  <label class="control-label"  >创建人</label>
                  <div class="controls">
                      <input class="input-xlarge" disabled="disabled" type="text"  value="${entity.createdBy}" >
                  </div>
              </div>
          </c:if>

      </div>

    </div>
</div><!--/.modal-body-->
        <div class="modal-footer">
            <button type="button" class="btn btn-small" onclick="$.colorbox.close()">
                <i class="icon-remove"></i>
                关闭
            </button>                                                                                                      <%--仅修改时可见--%>
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
                normalChar :true,
                maxlength: 50
            },
            loginName : {
                required: true ,
                maxlength: 50,
                account :true,
                remote: {
                    url: 'user/validateLoginName', //后台处理程序
                    type: 'post',               //数据发送方式
                    dataType: 'json',           //接受数据格式
                    data: {                     //要传递的数据
                      userId  : function() {
                            return $('#id').val() ;
                        }
                    }
                }
            },
            email : {
                required: true ,
                maxlength: 50,
                remote: {
                    url: 'user/validateEmail', //后台处理程序
                    type: 'post',               //数据发送方式
                    dataType: 'json',           //接受数据格式
                    data: {                     //要传递的数据
                      userId  : function() {
                            return $('#id').val() ;
                        }
                    }
                }
            },
            plainPassword :{
                maxlength: 16
            },
            status :{
                required:true
            },
            description :{
                maxlength : 200
            }
        }, messages: {
            loginName :{
                remote :'该用户名已被注册'
            }   ,
            email:{
                remote :'该Email已被注册'
            }
        }
       }); //end validate
    })
</script>
</body>
</html>
