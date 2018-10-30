<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>echarts1.html</title>
		<script type="text/javascript"
			src="../js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
	</head>
	
	<body>
	<div style="text-align:center"><form>
			<div><span style="font-family: '楷体';">请输入您想查询的报表年份：</span><input type="text" id="texts" style="width:130px;height:28px;border-radius:15px;"/></div><div style="padding-top: 20px;"><input type="button" id="btn" onclick="getChartData()" value="查询 " style="font-family: '楷体';"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="置空" onclick="resetValue()" style="font-family: '楷体';" ></div>
			</form></div>
			<div id="main" style="height: 400px;padding-top:45px;">
			</div>
			<script src="../js/echarts.js"></script>
			<script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main')); 
        var options = {
        title: {
            text: '"脱胎换轱"业绩分析图',
            subtext: '数据库已有数据'
        },
        tooltip: {
        trigger: 'axis'
       },
       toolbox: {
        feature: {
            dataView: {show: true, readOnly: false},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
    	data:[]
    },
    xAxis: [
        {
            type: 'category',
            data:[]
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '辆数',
            min: 0,
            max: 250,
            interval: 50,
            axisLabel: {
                formatter: '{value} 辆'
            }
        },
        {
            type: 'value',
            name: '数量',
            min: 0,
            max: 25,
            interval: 5,
            axisLabel: {
                formatter: '{value} 百万'
            }
        }
    ],
    series: [
        {
            name:'销售量',
            type:'bar',
            data:[]
        },
        {
            name:'维修量',
            type:'bar',
            data:[]
        },
        {
            name:'营业额',
            type:'line',
            yAxisIndex: 1,
            data:[]
        }
    ]
};
    myChart.setOption(options); 
    getChartData(myChart); 
	function getChartData() {
		//获得图表的options对象  
		//var options = myChart.getOption();  
		//alert(options),
		//通过Ajax获取数据  
		$.ajax( {
					url : "../EchartAction",//springmvc的controller的请求路径
					type : "post",
					//async : true, //同步执行  
					data : "str="+$("#texts").val(),
					dataType : "json", //返回数据形式为json  
					success : function(result) {
						if (result) {
							//请求成功将数据设置到相应的位置上
							options.legend.data = result.legend;
							options.xAxis[0].data = result.category;
							options.series[0].data = result.series[0].data;
							options.series[1].data = result.series[1].data;
							options.series[2].data = result.series[2].data;
							myChart.setOption(options);
						}
					},
					error : function(result){
						alert('出错了');
					}
				});
	}
	function resetValue(){
		$("#texts").val('');
		getChartData()
	}
</script>
	</body>
</html>