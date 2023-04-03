package de.holube.ana;

import de.holube.database.model.Day;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Average {

    private final Day averageDay;
    private int count = 0;
    private boolean isAveraged = false;

    public Average(String[] xAxis) {
        averageDay = new Day("Average", xAxis);
    }

    public void addDay(Day day) {
        if (isAveraged) {
            throw new IllegalStateException("Already averaged");
        }
        if (day.getDates().size() != averageDay.getDates().size()) {
            LOG.info("Day {} has {} entries, average has {}", day.getDate(), day.getDates().size(), averageDay.getDates().size());
            return;
        }

        for (int i = 0; i < averageDay.getXAxis().length; i++) {
            averageDay.getProductPower()[i] += day.getProductPower()[i];
            averageDay.getUsePower()[i] += day.getUsePower()[i];
            averageDay.getSelfUsePower()[i] += day.getSelfUsePower()[i];
            averageDay.getChargePower()[i] += day.getChargePower()[i];
            averageDay.getDischargePower()[i] += day.getDischargePower()[i];
        }

        count++;
    }

    public Day getAverageDay() {
        isAveraged = true;

        for (int i = 0; i < averageDay.getXAxis().length; i++) {
            averageDay.getProductPower()[i] /= count;
            averageDay.getUsePower()[i] /= count;
            averageDay.getSelfUsePower()[i] /= count;
            averageDay.getChargePower()[i] /= count;
            averageDay.getDischargePower()[i] /= count;
        }

        return averageDay;
    }

}
