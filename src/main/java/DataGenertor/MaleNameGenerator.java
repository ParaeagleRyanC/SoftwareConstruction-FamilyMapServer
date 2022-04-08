package DataGenertor;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

public class MaleNameGenerator {

    static class MaleNames {
        String[] data;
    }

    public static String maleNameGenerator() throws IOException {
        try {
            Gson gson = new Gson();
            Reader reader = new FileReader("json/mnames.json");
            MaleNames maleNames = gson.fromJson(reader, MaleNames.class);

            Random random = new Random();
            int randomIndex = random.nextInt(maleNames.data.length);

            return maleNames.data[randomIndex];
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
