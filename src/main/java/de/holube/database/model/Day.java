package de.holube.database.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Day {

    private String date;
    private String[] xAxis;
    private double[] productPower;
    private double[] usePower;
    private double[] selfUsePower;
    private double[] chargePower;
    private double[] dischargePower;

    public Day(String date, String[] xAxis) {
        this.date = date;
        this.xAxis = xAxis;
        this.productPower = new double[xAxis.length];
        this.usePower = new double[xAxis.length];
        this.selfUsePower = new double[xAxis.length];
        this.chargePower = new double[xAxis.length];
        this.dischargePower = new double[xAxis.length];
    }

    public List<Date> getDates() {
        List<Date> dates = new ArrayList<>();
        for (String s : xAxis) {
            String st = s.replace(" ", "T");
            st = st + ":00.00Z";
            dates.add(Date.from(Instant.parse(st)));
        }
        return dates;
    }

    public List<Date> getTimes() {
        List<Date> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (String s : xAxis) {
            String st = s.split(" ")[1];
            try {
                dates.add(sdf.parse(st));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return dates;
    }

    public List<Double> getProductPowerList() {
        List<Double> list = new ArrayList<>();
        for (double d : productPower) {
            list.add(d);
        }
        return list;
    }

    public List<Double> getUsePowerList() {
        List<Double> list = new ArrayList<>();
        for (double d : usePower) {
            list.add(d);
        }
        return list;
    }

    public List<Double> getSelfUsePowerList() {
        List<Double> list = new ArrayList<>();
        for (double d : selfUsePower) {
            list.add(d);
        }
        return list;
    }

    public List<Double> getChargePowerList() {
        List<Double> list = new ArrayList<>();
        for (double d : chargePower) {
            list.add(d);
        }
        return list;
    }

    public List<Double> getDischargePowerList() {
        List<Double> list = new ArrayList<>();
        for (double d : dischargePower) {
            list.add(d);
        }
        return list;
    }

}
