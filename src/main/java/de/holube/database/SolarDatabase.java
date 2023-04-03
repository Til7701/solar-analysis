package de.holube.database;

import de.holube.database.model.Day;

import java.util.List;

public interface SolarDatabase {

    void loadFrom(String path);

    public List<Day> getDays();

    public List<Day> getDays(String from, String to);

}
