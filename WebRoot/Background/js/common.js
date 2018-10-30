/*
	公共方法文件
	@eric
*/
var $parent = self.parent.$;

$(function(){
	//隐藏显示查询条件区域
	$('#openOrClose').on("click",function(){
		$('#conditon').toggle(80);
		setTimeout(domresize,100);//条件隐藏，改变表格高度
	});	
	
	
})