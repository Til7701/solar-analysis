package de.holube.plot;

import de.holube.database.model.Day;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;

public class DayPlot {

    private static final int plotWidth = 1600;
    private static final int plotHeight = 1000;

    private final Day day;

    private final XYChart chart;

    public DayPlot(Day day) {
        this.day = day;
        chart = new XYChartBuilder()
                .width(plotWidth)
                .height(plotHeight)
                .title(day.getDate())
                .xAxisTitle("Time")
                .yAxisTitle("Power")
                .build();
        chart.getStyler().setChartBackgroundColor(Color.WHITE);

        List<Date> xData = day.getTimes();

        XYSeries product = chart.addSeries("PV Output", xData, day.getProductPowerList());
        product.setLineColor(Color.GREEN);
        product.setMarker(SeriesMarkers.NONE);
        XYSeries use = chart.addSeries("Consumption", xData, day.getUsePowerList());
        use.setLineColor(Color.RED);
        use.setMarker(SeriesMarkers.NONE);
        XYSeries selfUse = chart.addSeries("Consumption from PV", xData, day.getSelfUsePowerList());
        selfUse.setLineColor(Color.GREEN.darker());
        selfUse.setMarker(SeriesMarkers.NONE);
        XYSeries charge = chart.addSeries("Battery Charge", xData, day.getChargePowerList());
        charge.setLineColor(Color.BLUE);
        charge.setMarker(SeriesMarkers.NONE);
        XYSeries discharge = chart.addSeries("Battery Discharge", xData, day.getDischargePowerList());
        discharge.setLineColor(Color.LIGHT_GRAY);
        discharge.setMarker(SeriesMarkers.NONE);
    }

    public BufferedImage createImage() {
        return BitmapEncoder.getBufferedImage(chart);
    }

}
