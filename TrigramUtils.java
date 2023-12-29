import java.util.*;

public class TrigramUtils {

    private static Boolean processedWords = false;

    public static HashMap<String, List<String>> fastTrigramFinder(List<String> dictionary, HashMap<String, List<String>> trigramMap) {

        if(processedWords) {
            return trigramMap;
        }

        for (String word : dictionary) {
                createTrigrams(word, trigramMap);
        }

        processedWords = true;

        return trigramMap;
    }

    static void createTrigrams(String mot, HashMap<String, List<String>> trigramMap) {
        StringBuilder motWithBoundaries = new StringBuilder("<").append(mot).append(">");
        for (int i = 0; i < motWithBoundaries.length() - 2; i++) {
            String trigram = motWithBoundaries.substring(i, i + 3);
            trigramMap.computeIfAbsent(trigram, k -> new ArrayList<>()).add(mot);
        }
    }

    static List<String> createTrigram(String mot) {
        List<String> trigrammes = new ArrayList<>();
        String motWithBoundaries = "<" + mot + ">";

        for (int i = 0; i < motWithBoundaries.length() - 2; i++) {
            if(trigrammes.contains(motWithBoundaries.substring(i, i + 3))) {
                continue;
            }
            trigrammes.add(motWithBoundaries.substring(i, i + 3));
        }
        return trigrammes;
    }

    private static void printTrigram(Map<String, List<String>> trigramAssociations) {
        for (Map.Entry<String, List<String>> entry : trigramAssociations.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
