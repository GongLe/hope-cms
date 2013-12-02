<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<body>
<div id="menuTreeGridContainer" style="max-height:500px;">
 <%--   <div  >
        <button  class="btn btn-minier btn-danger">
           <i class="icon-save"></i> 保存</button>
    </div>--%>
    <table id="menuTreeGrid" style="width:640px;height:450px;" ></table>
</div>
<script>
  $(function(){
      var checkedIdsArr = ${checkedIds} ;
     var  $menuTreeGrid = $('#menuTreeGrid');
      var hasCheck ;
      var saveBtn = ' <button  class="btn btn-minier btn-danger" id="saveMenu" title="保存"><i class="icon-save"></i>保存</button>' ;
      using(['treegrid'], function () {
          $menuTreeGrid.treegrid({
              url: 'menu/getTreeGrid',
              method: 'post',
              rownumbers: false,
              idField: 'id',
              treeField: 'name',
              columns: [
                  [
                      {field: 'id', title: saveBtn, width: 60, align: 'center', formatter: function (value, row, index) {
                          hasCheck = checkedIdsArr.indexOf(value) != -1 ? 'checked="checked"' : '';
                          return '<input type="checkbox" {0} name="checkMenuId"  id="{1}" />'.format(hasCheck, value);
                      }},
                      {field: 'name', title: '菜单名称', width: 160},
                      {field: 'code', title: '菜单代码', width: 115, align: 'left'},
                      {field: 'url', title: 'URL', width: 270}
                  ]
              ],
              onClickRow: function (row) {

              },
              onSelect: function (node) {

              },
              onLoadSuccess: function () {
                  $('.tooltips').tooltip();
                  //修复 treegrid IE下border-right不可见bug.
                  var $wrap = $('.datagrid-wrap');
                  $wrap.width($wrap.width() - 2);
                  //默认选择根节点.
                  var root = $menuTreeGrid.treegrid('getRoot');

              }
          });

      }); //using
      $( '#menuTreeGridContainer').on('click','#saveMenu',function(e){
          e.preventDefault();
          var selectedNodes = $('input[name="checkMenuId"]:checked', '#menuTreeGridContainer')
                  .map(function () {
                      return this.id;
                  })
                  .get();


      })
  }) //dom ready
</script>
</body>
</html>