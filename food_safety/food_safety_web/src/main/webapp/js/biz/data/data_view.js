$(function() {
	showData();
});


function showData(){
	var url = ctx +'/data/showDatas';
	$.ajax({
		url:url,
		data:'id='+$("#datasId").val(),
		type:'post',
		success:function(data){
			if(data.success == 1){
				$('#xx').html(data.instrument.x);
				$('#yy').html(data.instrument.y);
				var nums = 18; //每页出现的数量
				var pages = Math.ceil(data.values.length/nums); //得到总页数

				var thisDate = function(curr){
					var str = '', last = curr*nums - 1;
					last = last >= data.values.length ? (data.values.length-1) : last;
					for(var i = (curr*nums - nums); i <= last; i++){
						str += '<tr><td>'+ data.values[i].x +'</td><td>'+data.values[i].y+'</td></tr>';
					}
					return str;
				};
				laypage.dir = ctx+'/css/laypage/laypage.css';//自定义样式路径
				//调用分页
				laypage({
					cont: 'pager',
					pages: pages,
					first:1,
					last:pages,
					prev:'<',
					next:'>',
					groups: 2, //连续显示分页数
					jump: function(obj){
						document.getElementById('datas').innerHTML = thisDate(obj.curr);
					}
				});
				
				
				initEcharts(data.values,data.instrument);
			}else{
				var content = '<tr><td colspan="2">暂无数据</td>></tr>';
				$('#datas').html(content);
			}
		},
		error:function(e){

		}
	})


}

function initEcharts(o,instrument){
	var xdata = [];
	var ydata = [];
	$.each(o,function(index,item){
		xdata.push(instrument.x+'：'+item.x);
		ydata.push(item.y);
	});
	var myChart = echarts.init(document.getElementById('main'));

	// 指定图表的配置项和数据
	option = {
		title: {
			text: instrument.x+'-'+instrument.y
		},
		tooltip: {
			trigger: 'axis'
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		toolbox: {
			feature: {
				saveAsImage: {}
			}
		},
		xAxis: {
			type: 'category',
			boundaryGap: false,
			data: xdata
		},
		yAxis: {
			type: 'value'
		},
		series: [
			{
				name:instrument.y,
				type:'line',
				//stack: '总量',
				data:ydata
			}

		]
	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
}


function downloadData(){
	window.location.href = ctx + '/data/export?id='+$("#datasId").val();
}


