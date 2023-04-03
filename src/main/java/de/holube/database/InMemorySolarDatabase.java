package de.holube.database;

import de.holube.database.io.JSONFileReader;
import de.holube.database.model.Day;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemorySolarDatabase implements SolarDatabase {

    private final List<Day> days = new ArrayList<>();

    @Override
    public void loadFrom(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist!");
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null) throw new IllegalArgumentException("Directory is empty!");
            Arrays.sort(files);
            for (File f : files) {
                if (f.isFile()) {
                    Day day = JSONFileReader.readFile(f.getAbsolutePath()).getData();
                    day.setDate(f.getName().substring(0, f.getName().length() - 5));
                    days.add(day);
                }
            }
        } else {
            Day day = JSONFileReader.readFile(file.getAbsolutePath()).getData();
            day.setDate(file.getName().substring(0, file.getName().length() - 5));
            days.add(day);
        }
    }

    /**
     * Returns a list of all days in the database.
     *
     * @return an unmodifiable list of all days in the database.
     */
    @Override
    public List<Day> getDays() {
        return List.copyOf(days);
    }

    @Override
    public List<Day> getDays(String from, String to) {
        List<Day> result = new ArrayList<>();
        for (int i = 0; i < days.size(); i++) {
            if (days.get(i).getDate().equals(from)) {
                for (int j = i; j < days.size(); j++) {
                    result.add(days.get(j));
                    if (days.get(j).getDate().equals(to)) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

}
