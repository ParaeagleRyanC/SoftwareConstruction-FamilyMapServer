package DataGenertor;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

public class SurnameGenerator {

    static class Surnames {
        String[] data;
    }

    public static String surnameGenerator() throws IOException {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/snames.json");
            Surnames surnames = gson.fromJson(reader, Surnames.class);

            Random random = new Random();
            int randomIndex = random.nextInt(surnames.data.length);

            return surnames.data[randomIndex];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
