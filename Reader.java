import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reader {

    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Map<String, List<String>> triGramFinder (String filePath) {
        List<String> dictionary = readLines(filePath);

        Map<String, List<String>> trigramAssociations = new HashMap<>();

        for (String word : dictionary) {
            String specialChar = "<" + word + ">";

            List<String> trigrammes = createTrigram(specialChar);

            for (String trigramme : trigrammes) {
                trigramAssociations.computeIfAbsent(trigramme, k -> new ArrayList<>()).add(word);
            }
        }

       printTrigram(trigramAssociations);

        return trigramAssociations;
    }

    static List<String> createTrigram(String mot) {
        List<String> trigrammes = new ArrayList<>();
        for (int i = 0; i < mot.length() - 2; i++) {
            trigrammes.add(mot.substring(i, i + 3));
        }
        return trigrammes;
    }

    private static void printTrigram(Map<String, List<String>> trigramAssociations) {
        for (Map.Entry<String, List<String>> entry : trigramAssociations.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }


    public static void main(String[] args) {
        triGramFinder("C:\\Users\\chris\\Downloads\\TP2\\minidico.txt");
    }
}
