package de.holube.database.io;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class JSONFileReader {

    public static JSONDay readFile(String path) {
        JSONDay day = null;

        try (Reader reader = Files.newBufferedReader(Paths.get(path))) {
            Gson gson = new Gson();
            day = gson.fromJson(reader, JSONDay.class);
        } catch (IOException | JsonIOException e) {
            LOG.error("Error while reading File: " + path, e);
        } catch (JsonSyntaxException e) {
            LOG.error("Syntax Error in File: " + path, e);
        }

        if (day == null) {
            LOG.error("Unknown Error: Could not read File: " + path);
        }
        return day;
    }

}
