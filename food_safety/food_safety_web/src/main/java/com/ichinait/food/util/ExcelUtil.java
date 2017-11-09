package com.ichinait.food.util;

import com.ichinait.food.db.entity.Sample;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.charts.XSSFChartLegend;

import java.util.List;

/**
 * Created by xiao on 2016/04/26.
 */
public class ExcelUtil {

    public static void DrawLineChart(XSSFSheet sheet, XSSFSheet dataSheet, List<Sample> chartY, int rowIndex){
        /* At the end of this step, we have a worksheet with test data, that we want to write into a chart */
        /* Create a drawing canvas on the worksheet */
        XSSFDrawing xssfDrawing = sheet.createDrawingPatriarch();
		/* Define anchor points in the worksheet to position the chart */
        //XSSFClientAnchor anchor = xssfDrawing.createAnchor(0, 0, 0, 0, 0, Integer.valueOf(chartY.get(0).getId())+5, 20, Integer.valueOf(chartY.get(0).getId())+50);
        XSSFClientAnchor anchor = xssfDrawing.createAnchor(0, 0, 0, 0, 0, rowIndex, 7, rowIndex+13);
        /* Create the chart object based on the anchor point */
        XSSFChart my_line_chart = xssfDrawing.createChart(anchor);
 	    /* Define legends for the line chart and set the position of the legend */
        XSSFChartLegend legend = my_line_chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        /* Create data for the chart */
        LineChartData data = my_line_chart.getChartDataFactory().createLineChartData();
        /* Define chart AXIS */
        ChartAxis bottomAxis = my_line_chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = my_line_chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        /* Define Data sources for the chart */
        /* Set the right cell range that contain values for the chart */
        /* Pass the worksheet and cell range address as inputs */
        /* Cell Range Address is defined as First row, last row, first column, last column */
        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(1, Integer.valueOf(chartY.get(0).getId()), 0, 0));

//	    ChartDataSource<Number> ys2 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, 4));
//	    ChartDataSource<Number> ys3 = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(3, 3, 0, 4));
        /* Add chart data sources as data to the chart */
//        LineChartSeries chartSerie = data.addSeries(xs, ys1);
//        chartSerie.setTitle("test");
//	    data.addSeries(xs, ys2);
//	    data.addSeries(xs, ys3);

        for(int i=0; i<chartY.size(); i++){
            int rowNum = Integer.valueOf(chartY.get(i).getId());
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(1,rowNum, i+1, i+1));
            LineChartSeries chartSerie = data.addSeries(xs, ys1);
            chartSerie.setTitle(chartY.get(i).getName());
        }
	    /* Plot the chart with the inputs from data and chart axis */
        my_line_chart.plot(data, bottomAxis, leftAxis);
    }
}
