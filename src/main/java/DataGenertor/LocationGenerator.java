package DataGenertor;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

public class LocationGenerator {

    static class Location {
        String country;
        String city;
        String latitude;
        String longitude;
    }

    static class LocationData {
        Location[] data;
    }

    public static Location locationGenerator() throws IOException {

        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/locations.json");
            LocationData locData = gson.fromJson(reader, LocationData.class);

            Random random = new Random();
            int randomIndex = random.nextInt(locData.data.length);
            return locData.data[randomIndex];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
