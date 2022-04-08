package DataGenertor;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

public class FemaleNameGenerator {

    static class FemaleNames {
        String[] data;
    }

    public static String femaleNameGenerator() throws IOException {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/fnames.json");
            FemaleNames femaleNames = gson.fromJson(reader, FemaleNames.class);

            Random random = new Random();
            int randomIndex = random.nextInt(femaleNames.data.length);

            return femaleNames.data[randomIndex];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
