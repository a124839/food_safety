/**
 * Created by ichinait on 2016/3/23.
 */

$(function(){
    initEcharts()
});


function initEcharts(){
   var jsonObj = $.evalJSON(analysisDatas);
   var myChart = echarts.init(document.getElementById('main'));
    var seriesObj = [];
    var xObj = [];
    var yObj = [];
    $.each(jsonObj,function(index,o){
        var series = {
            name:y,
            type:'line',
            data: o.yDatas
        };
        $.each(o.xDatas,function(index,o){
            xObj.push(x+'：'+o);
        });
        yObj.push(o.yDatas);
        seriesObj.push(series);
    });
   // option.series = seriesObj;
     //指定图表的配置项和数据
    option = {
        title: {
            text: x+'-'+y
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
            name:x,
            type: 'category',
            boundaryGap: false,
            data: xObj
        },
        yAxis: {
            name:y,
            type: 'value'
        },
        series:seriesObj

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


function toImportDataPage(){
    window.location.href = ctx + '/analysis/toImportDataPage?cacheKey='+cacheKey;
}

function saveAnalysisResult(){
    window.location.href = ctx + '/analysis/save?cacheKey='+cacheKey;
}

function toAnalysisListPage(){
    window.location.href = ctx + '/analysis/toAnalysisManagePage';
}
