<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售订单登记</title>
<link rel="stylesheet" type="text/css" href="../../css/default.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/jquery-easyui-1.3.5/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="../../js/jquery-easyui-1.3.5/themes/icon.css" />
<script type="text/javascript"
	src="../../js/jquery-easyui-1.3.5/src/jquery.form.js"></script>
<script type="text/javascript"
	src="../../js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="../../js/jquery-easyui-1.3.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../../js/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/extends.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script>
	$(function() {
		//动态加载标题和数据
		function getData() {
			var json_data;
			$.ajax({
				async : false,
				type : "post",
				url : "../../BackgroundOrderAction!orderList",
				dataType : "json",
				success : function(data) {
					json_data = data;
				},
				failure : function(data) {
					json_data = "";
				}
			});
			return json_data;
		}
		$("#tt").datagrid({
			height : $("#body").height() - $('#search_area').height() - 5,
			width : $("#body").width(),
			idField : 'id',
			singleSelect : false,
			selectOnCheck : true,
			checkOnSelect : true,
			nowrap : true,
			fitColumns : false,
			rownumbers : true,
			showPageList : false,
			frozenColumns : [ [ {
				field : 'ck',
				checkbox : true
			} ] ],
			columns : [ getData().title ], //动态取标题	
			toolbar : '#tb',
			pagination : true,
			pageSize : 3,
			pageList : [ 3, 6, 9 ],
			onDblClickRow : function(rowIndex, rowData) {
				viewDetail(rowData.id);
			},
			onLoadSuccess : function(data) {
			},
			onSortColumn : function(sort, order) {
				datagridUtils.onSortColumn($("#tt"), sort);
			}
		});

		$("#tt").datagrid({
			loadFilter : pagerFilter
		}).datagrid('loadData', getData().rows);//动态取数据
		function pagerFilter(data) {
			if (typeof data.length == 'number'
					&& typeof data.splice == 'function') {// 判断数据是否是数组
				data = {
					total : data.length,
					rows : data
				};
			}
			var dg = $(this);
			var opts = dg.datagrid('options');
			var pager = dg.datagrid('getPager');
			pager.pagination({
				onSelectPage : function(pageNum, pageSize) {
					opts.pageNumber = pageNum;
					opts.pageSize = pageSize;
					pager.pagination('refresh', {
						pageNumber : pageNum,
						pageSize : pageSize
					});
					dg.datagrid('loadData', data);
				}
			});
			if (!data.originalRows) {
				data.originalRows = (data.rows);
			}
			var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
			var end = start + parseInt(opts.pageSize);
			data.rows = (data.originalRows.slice(start, end));
			return data;
		}
		$("#dlg").dialog({
			collapsible : true,
			minimizable : true,
			maximizable : true,
			resizable : true,
			width : 560,
			height : 220,
			cache : false,
			buttons : "#dlg-buttons",
			inline : false,
			closed : true,
			shadow : false,
			model : true,
			onMinimize : function() {
				$("#dlg").dialog('move', {
					left : "52%",
					top : "90%"
				}).dialog('collapse').dialog('open');
			}
		});		
		//表单数据转json数据格式
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
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
	$.ajax({
			async : false,
			type : "post",
			data : "",
			url : "../../BackgroundOrderAction!zState",
			dataType : "json",
			success : function(data) {
				if (data.result) {
					alert(data.msg);
				} else {
				}
			},
			error : function() {
			}
		});			
	});
	var url;
	//查询订单
	function searchOrder() {
		$("#tt").datagrid('options').url = "../../BackgroundOrderAction!searchOrder";
		$("#tt").datagrid('load', {
			"order.customerName" : $("#customerName").val(),
			"order.orTime" : $("#dd").datebox("getValue"),
			"order.state":''
		});
	}
	//打开新增订单窗口
	function openOrderAddDialog() {
		$("input[type=reset]").trigger("click");
		$("#dlg").dialog("open").dialog("setTitle", "添加订单信息");
		url = "../../BackgroundOrderAction!addOrder";
	}
	//打开修改订单窗口
	function openOrderModifyDialog() {
		$("input[type=reset]").trigger("click");
		var selectedRows = $("#tt").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要编辑的数据！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑订单信息");
		$("#fm").form("load", row);
		url = "../../BackgroundOrderAction!updateOrder";
	}
	//ajax方式提交form表单	
	function saveOrder() {
	alert($("#fm").serializeObject());
		$.ajax({
			type : "POST",
			dataType : "json",
			url : url,
			data : "str=" + $("#fm").serializeObject(),
			success : function(data) {
				$.messager.alert("系统提示", "保存成功！");
				$("#dlg").dialog("close");
				$("#tt").datagrid({
					url : "../../BackgroundOrderAction!orderList"
				});
				$("input[type=reset]").trigger("click");
			},
			error : function(data) {
				$.messager.alert("系统提示", "保存失败！");
			}
		});
	}
	//关闭窗口
	function closeOrderDialog() {
		$("#dlg").dialog("close");
		$("input[type=reset]").trigger("click");
	}
	function resetValue() {
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
<body class="easyui-layout">
	<div id="body" data-options="region:'center'">
		<!-- 查询条件区域 -->
		<div id="search_area">
			<div id="conditon">
				<table id="bb" border="0">
					<tr>
						<td>客户姓名：</td>
						<td><input name="customerName" id="customerName" />
						</td>
						<td>&nbsp;签订日期：</td>
						<td><input id="dd" type="text" class="easyui-datebox" />
						</td>
						<td><a href="javascript:searchOrder()"
							class="easyui-linkbutton"
							data-options="plain:true,iconCls:'icon-search'">查询</a> <a
							href="javascript:resetValue()" class="easyui-linkbutton"
							data-options="plain:true,iconCls:'icon-reset'">重置</a></td>
					</tr>
				</table>
			</div>
			<span id="openOrClose">111</span>
		</div>
		<!-- 数据表格区域 -->
		<table id="tt" style="table-layout:fixed;"></table>
		<!-- 表格顶部工具按钮 -->
		<div id="tb">
			<a href="javascript:openOrderAddDialog()" id="ddd"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-add'">新增订单</a> <a
				href="javascript:openOrderModifyDialog()"
				class="easyui-linkbutton"
				data-options="plain:true,iconCls:'icon-edit'">修改订单</a>
		</div>
		<!-- 添加信息及修改信息弹出框 -->
		<div id="dlg" class="easyui-dialog" data-options="closed:true">
			<form id="fm">
				<table cellspacing="8px;">
					<tr>
						<td>订单编号：</td>
						<td><input type="text" name="orderNo" id="orderNo"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>客户姓名：</td>
						<td><input type="text" name="customerName" id="customerName"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td>身份证号：</td>
						<td><input type="text" name="cardId" id="cardId"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>联系电话：</td>
						<td><input type="text" name="telephone" id="telephone"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td>车辆编号：</td>
						<td><input type="text" name="carId" id="carId"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>订购协议编号：</td>
						<td><input type="text" name="sid" id="sid"
							class="easyui-validatebox" data-options="required:true" />
							&nbsp;<span style="color: red">*</span>
						</td>
					</tr>
					<tr>
						<td>签订日期：</td>
						<td><input type="text" name="orTime"
							id="orTime" class="easyui-datebox" /> 
							&nbsp;<span style="color: red">*</span>
						</td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td>审核状态：</td>
						<td><input type="text" name="state" id="state" 
						class="easyui-validatebox" readonly="readonly"/>
      					&nbsp;<span style="color: red">*</span></td>
					</tr>
				</table>
				<!--隐藏文本框,用于传递id-->
				<input type="hidden" name="id" id="id" />
				<!--隐藏按钮,用于清空表单 -->
				<input type="reset" style="display:none;" />
			</form>
		</div>

		<div id="dlg-buttons">
			<a href="javascript:saveOrder()" class="easyui-linkbutton"
				data-options="iconCls:'icon-ok'">保存</a> <a
				href="javascript:closeOrderDialog()" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel'">关闭</a>
		</div>
	</div>
</body>
</html>