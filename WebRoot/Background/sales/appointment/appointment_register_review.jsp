<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>展厅接待登记</title>
<link rel="stylesheet" type="text/css" href="../../css/default.css"/>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.5/themes/gray/easyui.css"/>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.3.5/themes/icon.css"/>
<script type="text/javascript" src="../../js/jquery-easyui-1.3.5/src/jquery.form.js"></script>
<script type="text/javascript" src="../../js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="../../js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/extends.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script>
$(function(){
	//动态加载标题和数据
	function getData(){
		var json_data;
		$.ajax({
			async:false,
			type:"post",
			data:"",
			url:"../../AppointmentAction!appointmentList",						
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
		$("#inten,#cname").combobox({
			url : "../../AppointmentAction!combobox",
			editable : false, //不可编辑状态
			cache : false,
			panelHeight : "auto",//自动高度适合
			valueField : "vid",
			textField : "vname"
		});	        
     $("#dlg").dialog({
		collapsible:true,
		minimizable:true,
		maximizable:true,
		resizable:true,
		width:560,
		height:260,
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
//查询试乘试驾预约
	function searchAppointment() {
		$("#tt").datagrid('options').url="../../AppointmentAction!searchAppointment";
		$("#tt").datagrid('load',{
			"appointment.customerName":'',
			"appointment.consultantName": $("#consultantName").combobox('getValue'),
			"appointment.carStyleId":$("#inten").combobox('getValue'),
			"appointment.scheduledTime":$("#dd").datebox("getValue"),
			"appointment.state":$("#state").combobox('getValue')
		});
	}
//打开修改试乘试驾预约窗口
	function openAppointmentModifyDialog() {
		$("input[type=reset]").trigger("click");
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑试乘试驾预约信息");
		$("#fm").form("load", row);
		url = "../../AppointmentAction!updateAppointment";
	}
//ajax方式提交form表单	
	function saveAppointment() {
		alert($("#fm").serializeObject());
		$.ajax({
			type : "POST",
			dataType : "json",
			url : url,
			data : "str="+$("#fm").serializeObject(),
			success : function(data) {
				$.messager.alert("系统提示", "保存成功！");							
				$("#dlg").dialog("close");
				$("#tt").datagrid({url:"../../AppointmentAction!appointmentList"});							
				$("input[type=reset]").trigger("click");
			},
			error : function(data) {
				$.messager.alert("系统提示", "保存失败！");
			}
	});
	}
//关闭窗口
	function closeAppointmentDialog() {		
		$("#dlg").dialog("close");	
		$("input[type=reset]").trigger("click");	
	}
//删除试乘试驾预约
	function deleteAppointment() {
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var appointmentIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			appointmentIds.push(selectedRows[i].id);
		}
		var ids = appointmentIds.join(",");
		$.messager.confirm("系统提示", "您确定要删除这 <font color=red>"
				+ selectedRows.length + "</font> 条数据吗？", function(r) {
			if (r) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../../AppointmentAction!deleteAppointment",
			data : "ids="+ids,
			success : function(data) {
				$.messager.alert("系统提示", "数据已成功删除！");				
				$("#tt").datagrid({url:"../../AppointmentAction!appointmentList"});
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
	function resetValue(){
		$("#bb input").val("");
	}
	function viewDetail(date, id) {
		$parent.messager.alert("提示", "查询详细", "info");
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
<div id="body" data-options="region:'center'"> 
  <!-- 查询条件区域 -->
  <div id="search_area" >
    <div id="conditon">
      <table id="bb" border="0">
        <tr>
          <td>销售顾问：</td>
          <td><input type="text" name="consultantName" id="consultantName" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'value',textField:'text',data:[{'value':'李四','text':'李四'},{'value':'王五','text':'王五'},{'value':'马六','text':'马六'}]" /></td>
          <td>&nbsp;意向车型：</td>
		  <td><input class="easyui-combobox" id="inten" /></td>
          <td>&nbsp;预约日期：</td>
          <td><input id="dd" type="text" class="easyui-datebox"/></td>
          <td>&nbsp;审核状态：</td>
          <td><input type="text" name="state" id="state" 
      					class="easyui-combobox" data-options="panelHeight:'auto',valueField:'value',textField:'text',data:[{'value':'未审核','text':'未审核'},{'value':'已审核','text':'已审核'}]" /></td>
          <td>
              <a href="javascript:searchAppointment()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-search'">查询</a> 
              <a href="javascript:resetValue()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reset'">重置</a>
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
        <a href="javascript:openAppointmentModifyDialog()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改预约</a> 
        <a href="javascript:deleteAppointment()" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'">删除预约</a>
    </div>
   <!-- 添加预约信息及修改预约信息弹出框 -->
       <div id="dlg" class="easyui-dialog" data-options="closed:true">
            <form id="fm">
                <table cellspacing="8px;">
                 <tr>
     <td>预约编号：</td>
      <td><input type="text" name="appointmentNo" id="appointmentNo" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>客户姓名：</td>
      <td><input type="text" name="customerName" id="customerName" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
      <td>联系电话：</td>
      <td><input type="text" name="telephone" id="telephone" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>  
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>意向车型：</td>
      <td><input class="easyui-combobox" id="cname" name="cname" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td> 
      </tr>
      <tr>
      <td>预约日期：</td>
      <td><input type="text" name="scheduledTime" id="scheduledTime" class="easyui-datebox"/> 
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>预约试驾人数：</td>
      <td><input type="text" name="scheduledPersonNum" id="scheduledPersonNum" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
      <td>销售顾问：</td>
      <td><input type="text" name="consultantName" id="consultantName" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'value',textField:'text',data:[{'value':'李四','text':'李四'},{'value':'王五','text':'王五'},{'value':'马六','text':'马六'}]" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>备注：</td>
      <td><input type="text" name="remarks" id="remarks" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
      <td>意向编号：</td>
      <td><input type="text" name="iid" id="iid" class="easyui-validatebox" data-options="required:true" />
      &nbsp;<span style="color: red">*</span></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>审核状态：</td>
      <td><input type="text" name="state" id="state" 
      					class="easyui-combobox" data-options="panelHeight:'auto',valueField:'value',textField:'text',data:[{'value':'未审核','text':'未审核'},{'value':'已审核','text':'已审核'}]" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
    </table>
    		<!--隐藏文本框,用于传递id-->
			<input type="hidden" name="id" id="id" />
            <!--隐藏按钮,用于清空表单-->
            <input type="reset" style="display:none;" />
            </form>
        </div>
        
		<div id="dlg-buttons">
            <a href="javascript:saveAppointment()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a> 
            <a href="javascript:closeAppointmentDialog()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
        </div>
</div>
</body>
</html>