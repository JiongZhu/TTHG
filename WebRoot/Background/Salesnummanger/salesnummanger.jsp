<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售信息</title>
<link rel="stylesheet" type="text/css" href="../css/default.css"/>
<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.3.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../js/jquery-easyui-1.3.5/themes/icon.css"/>
<script type="text/javascript" src="../js/jquery-easyui-1.3.5/src/jquery.form.js"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/extends.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script>
$(function(){
	//动态加载标题和数据
	function getData(){
		var json_data;
		$.ajax({
			async:false,
			type:"post",
			data:"",
			url:"../SalesnummangerAction!salesnummanList",						
			dataType:"json",
			success:function(data){
				json_data=data;
			},
			error:function(){
				json_data = "";
			}
		});	
		return json_data;	
	}
	
	$("#tt").datagrid({	
				height:$("#body").height()-$('#search_area').height()-5,
				width:$("#body").width(),
				idField:'id',
				singleSelect:false,
				selectOnCheck:true,
                checkOnSelect:true,
				nowrap:true,
				fitColumns:false,
				rownumbers:true,
				showPageList:false,
				frozenColumns: [[{field:'ck',checkbox:true}]], 
				columns:[getData().title],	//动态取标题	
				toolbar:'#tb',
				pagination:true,
				pageSize:3,
				pageList:[3,6,9],  
				onDblClickRow:function(rowIndex, rowData){
					viewDetail(rowData.id);
				},
				onLoadSuccess:function(data){}, 
                onSortColumn:function(sort,order){ datagridUtils.onSortColumn($("#tt"),sort);}					
		});	
	
 	$("#tt").datagrid({loadFilter:pagerFilter}).datagrid('loadData',getData().rows);//动态取数据
 	
        function pagerFilter(data){
        	if (typeof data.length == 'number' && typeof data.splice == 'function'){// 判断数据是否是数组
				data = {
						total: data.length,
						rows: data
						};
				}
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage:function(pageNum, pageSize){
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh',{
                        pageNumber:pageNum,
                        pageSize:pageSize
                    });
                    dg.datagrid('loadData',data);
                }
            });
            if (!data.originalRows){
                data.originalRows = (data.rows);
            }
            var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }
        
     $("#dlg").dialog({
		collapsible:true,
		minimizable:true,
		maximizable:true,
		resizable:true,
		width:530,
		height:300,
		cache:false,
		buttons:"#dlg-buttons",
		inline:false,
		closed:true,
		shadow:false,
		model:true,
		onMinimize:function(){
			$("#dlg").dialog('move',{left:"52%",top:"90%"}).dialog('collapse').dialog('open');
		}
	});
});
//表单数据转json数据格式
	$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return JSON.stringify(o);
	};
	var url;
//查询客户
    function searchSalesnum() {
        $("#tt").datagrid('options').url="../SalesnummangerAction!searchSalesnum";
		$("#tt").datagrid('load',{
			"salesnum.id":$("#id").val()
		});
    }
//打开新增客户窗口
	function openSalesnummangerAddDialog() {
		$("input[type=reset]").trigger("click");
		$("#dlg").dialog("open").dialog("setTitle", "添加销售信息");
		url = "../SalesnummangerAction!addSalesnumman";
	}
//打开修改客户窗口
	function openSalesnummangerModifyDialog() {
		$("input[type=reset]").trigger("click");
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑销售信息");
		$("#fm").form("load", row);
		url = "../SalesnummangerAction!updateSalesnumman";
	}
//ajax方式提交form表单	
	function saveSalesnummanger() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : url,
			data : "str="+$("#fm").serializeObject(),
			success : function(data) {
				$.messager.alert("系统提示", "保存成功！");							
				$("#dlg").dialog("close");
				$("#tt").datagrid({url:"../SalesnummangerAction!salesnummanList"});							
				$("input[type=reset]").trigger("click");
			},
			error : function(data) {
				$.messager.alert("系统提示", "保存失败！");
			}
	});
	}
//关闭窗口
	function closeSalesnummangerDialog() {		
		$("#dlg").dialog("close");	
		$("input[type=reset]").trigger("click");	
	}
//删除用户
	function deleteSalesnummanger() {
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var salesnummanListIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			salesnummanListIds.push(selectedRows[i].id);
		}
		var ids = salesnummanListIds.join(",");
		$.messager.confirm("系统提示", "您确定要删除这 <font color=red>"
				+ selectedRows.length + "</font> 条数据吗？", function(r) {
			if (r) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../SalesnummangerAction!deleteSalesnumman",
			data : "ids="+ids,
			success : function(data) {
				$.messager.alert("系统提示", "数据已成功删除！");				
				$("#tt").datagrid({url:"../SalesnummangerAction!salesnummanList"});
				selectedRows.length=0;
			},
			error:function(data) {
				$.messager.alert("系统提示", "数据删除失败，请联系系统管理员！");
				selectedRows.length=0;
			}
	});
			}
		});
	}
	function viewDetail(date, id) {
		$parent.messager.alert("提示", "查询详细", "info");
	}
//查询条件清空
//清空值
    function resetValue() {
    	$(".easyui-validatebox").val("");
       }
	//监听窗口大小变化
	window.onresize = function() {
		setTimeout(domresize, 300);
	};
	//改变表格宽高
	function domresize() {
		$('#tt').datagrid('resize', {
			height : $("#body").height() - $('#search_area').height() - 5,
			width : $("#body").width()
		});
	};
</script>
</head>
<body class="easyui-layout" >
<div id="body" region="center" > 
  <!-- 查询条件区域 -->
  <div id="search_area" >
    <div id="conditon">
      <table border="0">
        <tr>
          <td>年份：</td>
          <td><input  name="id" id="id" class="easyui-validatebox"/></td>
          <td>
              <a  href="javascript:searchSalesnum()" class="easyui-linkbutton my-search-button" iconCls="icon-search" plain="true">查询</a> 
              <a  href="javascript:resetValue()" class="easyui-linkbutton my-search-button" iconCls="icon-reset" plain="true" >重置</a>
          </td>
        </tr>
      </table>
    </div>
    <span id="openOrClose">111</span> 
  </div>
  <!-- 数据表格区域 -->
  <table id="tt" style="table-layout:fixed;" ></table>
  <!-- 表格顶部工具按钮 -->
    <div id="tb">
        <a href="javascript:openSalesnummangerAddDialog()" id="ddd" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a> 
        <a href="javascript:openSalesnummangerModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
        <a href="javascript:deleteSalesnummanger()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
   <!-- 添加客户信息及修改客户信息 弹出框 -->
       <div id="dlg" class="easyui-dialog" closed="true">
            <form id="fm">
                <table cellspacing="8px;">
                 <tr>
      <td>年份：</td>
      <td><input type="text" name="id" id="id" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td></tr>
                      <tr>
      <td>一月：</td>
      <td><input type="text" name="januarynum" id="januarynum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>二月：</td>
      <td><input type="text" name="februarynum" id="februarynum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>三月：</td>
        <td><input type="text" name="marchnum" id="marchnum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>四月：</td>
        <td><input type="text" name="aprilnum" id="aprilnum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>五月：</td>
        <td><input type="text" name="maynum" id="maynum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>六月：</td>
        <td><input type="text" name="junenum" id="junenum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>七月：</td>
        <td><input type="text" name="julynum" id="julynum"class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>八月：</td>
        <td><input type="text" name="augustnum" id="augustnum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr> 
        <td>九月：</td>
        <td><input type="text" name="septembernum" id="septembernum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
       <td>十月：</td>
        <td><input type="text" name="octobernum" id="octobernum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>十一月：</td>
        <td><input type="text" name="novembernum" id="novembernum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
       <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <td>十二月：</td>
        <td><input type="text" name="decembernum" id="decembernum" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
       </table>
                 <!--隐藏按钮,用于清空表单-->
                <input type="reset" style="display:none;" />
            </form>
        </div>
        
		<div id="dlg-buttons">
            <a href="javascript:saveSalesnummanger()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> 
            <a href="javascript:closeSalesnummangerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
        </div>
</div>
</body>
</html>