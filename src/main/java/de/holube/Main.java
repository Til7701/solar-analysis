package de.holube;

import de.holube.ana.Average;
import de.holube.database.InMemorySolarDatabase;
import de.holube.database.SolarDatabase;
import de.holube.database.model.Day;
import de.holube.plot.DayPlot;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SolarDatabase database = new InMemorySolarDatabase();
        database.loadFrom("/home/tilman/Documents/Solar/data");

        List<Day> days = database.getDays();
        System.out.println(days.get(0).getXAxis()[0]);
        System.out.println(days.get(days.size() - 1).getXAxis()[0]);

        Average average = new Average(days.get(0).getXAxis());
        for (Day day : days) {
            average.addDay(day);
        }
        Day averageDay = average.getAverageDay();

        DayPlot plot = new DayPlot(averageDay);
        BufferedImage img = plot.createImage();

        try {
            File outputfile = new File("plot.png");
            ImageIO.write(img, "png", outputfile);
        } catch (IOException e) {
            // handle exception
        }

    }
}