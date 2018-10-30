<!--  
此页面做为user.html弹出框中的子页面，使用href方式从外部引入，需将所有代码写在body标签中，
可省略body标签之外的所有内容。
保存按钮区域div放在放外层标签，不能以其他带样式标签包裹

以表格方式布局，设置table具体宽度，每列第一行单元格设置该列宽度
具体宽度根据内容而定，弹出框宽度根据table宽度而定
-->
<script>
$(function(){
	$("#addUser_cancel").on("click", function(){
		$("#parent_win").window("close");
	});
});
</script>
<!-- 内容 -->
<div class="content">
    <table width="280" border="0" align="center" cellpadding="3">
      <tr>
        <td width="80" align="right"><label for="userName"><span class="x">*</span>用户名：</label></td>
        <td width="200"><input type="text" name="userName" id="userName" /></td>
      </tr>
      <tr>
        <td align="right"><label for="password"><span class="x">*</span>密码：</label></td>
        <td><input type="password" name="password" id="password" /></td>
      </tr>
      <tr>
        <td align="right"><label for="name"><span class="x">*</span>姓名：</label></td>
        <td><input type="text" name="name" id="name" /></td>
      </tr>
      <tr>
        <td align="right"><label for="sex"><span class="x">*</span>性别：</label></td>
        <td><input type="text" name="sex" id="sex" /></td>
      </tr>
      <tr>
        <td align="right"><label for="department"><span class="x">*</span>部门：</label></td>
        <td><input type="text" name="department" id="department" /></td>
      </tr>
    </table>
</div>
<!-- 弹出框按钮 -->
<div class="windowButton">
     <a id="addUser_saveAndAdd" class="easyui-linkbutton my-dialog-button" plain="true" icon="icon-ok" href="javascript:void(0)" > 保存并新增</a> 
     <a id="addUser_save" class="easyui-linkbutton my-dialog-button" plain="true" icon="icon-save" href="javascript:void(0)" > 保存</a> 
     <a id="addUser_cancel" class="easyui-linkbutton my-dialog-button" plain="true" icon="icon-cancel" href="javascript:void(0)" >取消</a>
</div>