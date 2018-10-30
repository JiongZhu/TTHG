<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>意向客户流失分析</title>
		<script type="text/javascript"
			src="../js/jquery-easyui-1.3.5/jquery-1.10.2.min.js"></script>
	</head>
	
	<body>
			<div id="main" style="height: 400px;padding-top:45px;">
			</div>
			<script src="../js/echarts.js"></script>
			<script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main')); 
        var options = {
        title: {
            text: '意向客户流失原因统计图',
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
            name: '人数',
            min: 0,
            max: 10,
            interval: 2,
            axisLabel: {
                formatter: '{value} 人'
            }
        }
    ],
    series: [
        {
            name:'流失原因',
            type:'bar',
            data:[]
        }
    ]
};
    myChart.setOption(options); 
    getChartData(myChart); 
	function getChartData() {
			$.ajax( {
					url : "../IntentionCustomerEchartAction",//springmvc的controller的请求路径
					type : "post",
					//async : true, //同步执行  
					data : "",
					dataType : "json", //返回数据形式为json  
					success : function(result) {
						if (result) {
							//请求成功将数据设置到相应的位置上
							options.legend.data = result.legend;
							options.xAxis[0].data = result.category;
							options.series[0].data = result.series[0].data;
							myChart.setOption(options);
						}
					},
					error : function(result){
						alert('出错了');
					}
				});
	}
</script>
	</body>
</html>