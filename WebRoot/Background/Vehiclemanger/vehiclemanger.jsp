<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车型管理界面</title>
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
			url:"../VehiclemangerAction!vehiclemangerList",						
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
		width:300,
		height:400,
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
	function searchVehiclemanger() {
		$("#tt").datagrid('options').url="../VehiclemangerAction!searchVehiclemanger";
		$("#tt").datagrid('load',{
			vehiclemangerNo:$("#vehiclemangerNo").val(),
			vehiclemangerName:$("#vehiclemangerName").val()
		});
	}
//打开新增客户窗口
	function openVehiclemangerAddDialog() {
		$("input[type=reset]").trigger("click");
		$("#dlg").dialog("open").dialog("setTitle", "添加车辆信息");
		url = "../VehiclemangerAction!addVehiclemanger";
	}
//打开修改客户窗口
	function openVehiclemangerModifyDialog() {
		$("input[type=reset]").trigger("click");
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑车辆信息");
		$("#fm").form("load", row);
		url = "../VehiclemangerAction!updateVehiclemanger";
	}
//ajax方式提交form表单	
	function saveVehiclemanger() {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : url,
			data : "str="+$("#fm").serializeObject(),
			success : function(data) {
				$.messager.alert("系统提示", "保存成功！");							
				$("#dlg").dialog("close");
				$("#tt").datagrid({url:"../VehiclemangerAction!vehiclemangerList"});							
				$("input[type=reset]").trigger("click");
			},
			error : function(data) {
				$.messager.alert("系统提示", "保存失败！");
			}
	});
	}
//关闭窗口
	function closeVehiclemangerDialog() {		
		$("#dlg").dialog("close");	
		$("input[type=reset]").trigger("click");	
	}
//删除用户
	function deleteVehiclemanger() {
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的数据！");
			return;
		}
		var vehiclemangerIds = [];
		for ( var i = 0; i < selectedRows.length; i++) {
			vehiclemangerIds.push(selectedRows[i].id);
		}
		var ids = vehiclemangerIds.join(",");
		$.messager.confirm("系统提示", "您确定要删除这 <font color=red>"
				+ selectedRows.length + "</font> 条数据吗？", function(r) {
			if (r) {
		$.ajax({
			type : "POST",
			dataType : "json",
			url : "../VehiclemangerAction!deleteVehiclemanger",
			data : "ids="+ids,
			success : function(data) {
				$.messager.alert("系统提示", "数据已成功删除！");				
				$("#tt").datagrid({url:"../VehiclemangerAction!vehiclemangerList"});
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
 function resetValue(){
 	$("#bb input").val("");
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
      <table id="bb" border="0">
        <tr>
          <td>车辆编号：</td>
          <td ><input  name="vehiclemangerNo" id="vehiclemangerNo"/></td>
          <td>&nbsp;车辆名称：</td>
          <td><input  name="vehiclemangerName" id="vehiclemangerName"/></td>
          <td>
              <a href="javascript:searchVehiclemanger()" class="easyui-linkbutton" iconCls="icon-search" plain="true">查询</a> 
              <a href="javascript:resetValue()" class="easyui-linkbutton" iconCls="icon-reset" plain="true" >重置</a>
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
        <a href="javascript:openVehiclemangerAddDialog()" id="ddd" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a> 
        <a href="javascript:openVehiclemangerModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
        <a href="javascript:deleteVehiclemanger()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
   <!-- 添加客户信息及修改客户信息 弹出框 -->
       <div id="dlg" class="easyui-dialog" closed="true">
            <form id="fm">
                <table cellspacing="8px;">
                 <tr>
      <td>ID：</td>
      <td><input type="text" name="id" id="id" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
        <tr>
      <td>车辆编号：</td>
      <td><input type="text" name="vehicleNo" id="vehicleNo" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
      <td>首字母：</td>
      <td><input type="text" name="fletter" id="fletter" panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'A','text':'A'},{'value':'B','text':'B'},{'value':'C','text':'C'},{'value':'D','text':'D'},{'value':'E','text':'E'},{'value':'F','text':'F'},{'value':'G','text':'G'},{'value':'H','text':'H'},{'value':'I','text':'I'},{'value':'J','text':'J'},{'value':'K','text':'K'},{'value':'L','text':'L'},{'value':'M','text':'M'},{'value':'N','text':'N'},{'value':'O','text':'O'},{'value':'P','text':'P'},{'value':'Q','text':'Q'},{'value':'R','text':'R'},{'value':'S','text':'S'},{'value':'T','text':'T'},{'value':'U','text':'U'},{'value':'V','text':'V'},{'value':'W','text':'W'},{'value':'X','text':'X'},{'value':'Y','text':'Y'},{'value':'Z','text':'Z'}]" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>牌子：</td>
        <td><input type="text" name="brand" id="brand" panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'雷克萨斯','text':'雷克萨斯'},{'value':'宝马','text':'宝马'},{'value':'奔驰','text':'奔驰'},{'value':'布加迪威龙','text':'布加迪威龙'},{'value':'玛莎拉蒂','text':'玛莎拉蒂'},{'value':'劳斯莱斯','text':'劳斯莱斯'}]" required="true"  />
      &nbsp;<span style="color: red">*</span></td>
        </tr>
        <tr>
        <td>车系：</td>
        <td><input type="text" name="series" id="series" panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'A系','text':'A系'},{'value':'B系','text':'B系'},{'value':'C系','text':'C系'},{'value':'D系','text':'D系'},{'value':'E系','text':'E系'},{'value':'F系','text':'F系'},{'value':'G系','text':'G系'},{'value':'H系','text':'H系'},{'value':'I系','text':'I系'},{'value':'J系','text':'J系'},{'value':'K系','text':'K系'},{'value':'L系','text':'L系'},{'value':'M系','text':'M系'},{'value':'N系','text':'N系'},{'value':'O系','text':'O系'},{'value':'P系','text':'P系'},{'value':'Q系','text':'Q系'},{'value':'R系','text':'R系'},{'value':'S系','text':'S系'},{'value':'T系','text':'T系'},{'value':'U系','text':'U系'},{'value':'V系','text':'V系'},{'value':'W系','text':'W系'},{'value':'X系','text':'X系'},{'value':'Y系','text':'Y系'},{'value':'Z系','text':'Z系'}]" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>车名：</td>
        <td><input type="text" name="vehicleName" id="vehicleName" panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'雷克萨斯','text':'雷克萨斯'},{'value':'宝马','text':'宝马'},{'value':'奔驰','text':'奔驰'},{'value':'布加迪威龙','text':'布加迪威龙'},{'value':'玛莎拉蒂','text':'玛莎拉蒂'},{'value':'劳斯莱斯','text':'劳斯莱斯'}]" required="true"  />
      &nbsp;<span style="color: red">*</span></td>
        </tr>
        <tr>
        <td>指导价：</td>
        <td><input type="text" name="guidePrice" id="guidePrice" class="easyui-validatebox" required="true" />
         &nbsp;<span style="color: red">*</span></td>
         </tr>
        <tr>
        <td>厂商：</td>
        <td><input type="text" name="mj" id="mj" panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'雷克萨斯','text':'雷克萨斯'},{'value':'宝马','text':'宝马'},{'value':'奔驰','text':'奔驰'},{'value':'布加迪威龙','text':'布加迪威龙'},{'value':'玛莎拉蒂','text':'玛莎拉蒂'},{'value':'劳斯莱斯','text':'劳斯莱斯'}]" required="true"  />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>制造商：</td>
        <td><input type="text" name="manufacturea" id="manufacturea"  panelHeight="100" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'雷克萨斯','text':'雷克萨斯'},{'value':'宝马','text':'宝马'},{'value':'奔驰','text':'奔驰'},{'value':'布加迪威龙','text':'布加迪威龙'},{'value':'玛莎拉蒂','text':'玛莎拉蒂'},{'value':'劳斯莱斯','text':'劳斯莱斯'}]" required="true"  />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>级别：</td>
        <td><input type="text" name="rank" id="rank" panelHeight="50" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'大型车','text':'大型车'},{'value':'中型车','text':'中型车'},{'value':'小型车','text':'小型车'}]" required="true"/>
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>发动机：</td>
        <td><input type="text" name="engine" id="engine" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>变速箱：</td>
        <td><input type="text" name="gearbox" id="gearbox" panelHeight="50" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'6挡手动挡','text':'6挡手动挡'},{'value':'8挡自动挡','text':'8挡自动挡'},{'value':'6档混合档','text':'6档混合档'},{'value':'8档混合档','text':'8档混合档'}]" required="true"/>
      &nbsp;<span style="color: red">*</span></td>
    </tr>
    <tr>
        <td>尺寸：</td>
        <td><input type="text" name="lengthWh" id="lengthWh" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>最大速度：</td>
        <td><input type="text" name="maxSpeed" id="maxSpeed" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
     </tr>
     <tr>
        <td>耗油量：</td>
        <td><input type="text" name="fuelConsumption" id="fuelConsumption" panelHeight="50" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'大','text':'大'},{'value':'适中','text':'适中'},{'value':'小','text':'小'}]" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>质保：</td>
        <td><input type="text" name="warranty" id="warranty" panelHeight="50" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'一年','text':'一年'},{'value':'两年','text':'两年'},{'value':'三年','text':'三年'}]" required="true"  />
      &nbsp;<span style="color: red">*</span></td>
   </tr>
   <tr>
        <td>颜色：</td>
        <td><input type="text" name="color" id="color" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
        <td>油箱量：</td>
        <td><input type="text" name="displacement" id="displacement" class="easyui-validatebox" required="true" />
      &nbsp;<span style="color: red">*</span></td>
     </tr>
     <tr>
        <td>汽缸数：</td>
        <td><input type="text" name="numberofCylinders" id="numberofCylinders" panelHeight="50" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'6','text':'6'},{'value':'8','text':'8'},{'value':'10','text':'10'}]" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
      <tr>
      <td>详细信息：</td>
        <td><input type="text" name="photo" id="photo" panelHeight="20" class="easyui-combobox" data-options="valueField:'value',textField:'text',data:[{'value':'暂无','text':'暂无'}]" required="true" />
      &nbsp;<span style="color: red">*</span></td>
      </tr>
                </table>
                 <!--隐藏按钮,用于清空表单-->
                <input type="reset" style="display:none;" />
            </form>
         </div>
		<div id="dlg-buttons">
            <a href="javascript:saveVehiclemanger()" class="easyui-linkbutton" iconCls="icon-ok">保存</a> 
            <a href="javascript:closeVehiclemangerDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
        </div>
</div>
</body>
</html>