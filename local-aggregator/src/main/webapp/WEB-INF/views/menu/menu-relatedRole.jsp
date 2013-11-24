<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
</head>

<body>
<div id="roleCheckItems">
  <c:forEach items="${roles}" var="role">
      <c:if test="${role.selected == true}">
          <div class="checkbuttonOk  panelcheck">
              <div data-id="${role.id}" class="checktext"> <i class="icon-user blue"></i>&nbsp;&nbsp;${role.name}
              </div>
              <div class="triangleOk"></div>
          </div>
      </c:if>
      <c:if test="${role.selected == false}">
          <div class="checkbuttonNo  panelcheck">
              <div data-id="${role.id}" class="checktext"> <i class="icon-user blue"></i>&nbsp;&nbsp;${role.name}
              </div>
              <div class="triangleNo"></div>
          </div>
      </c:if>
  </c:forEach>
</div>
<script>

    $(function () {
        $('#roleCheckItems').on('click', '.panelcheck', function () {
            if ($(this).hasClass('checkbuttonNo')) {
                $(this).removeClass('checkbuttonNo')
                        .addClass('checkbuttonOk')
                        .children('.triangleNo')
                        .removeClass('triangleNo')
                        .addClass('triangleOk')
            } else {
                $(this).removeClass('checkbuttonOk')
                        .addClass('checkbuttonNo')
                        .children('.triangleOk')
                        .removeClass('triangleOk')
                        .addClass('triangleNo')
            }
        })
    })  //dom ready
</script>
</body>
</html>
