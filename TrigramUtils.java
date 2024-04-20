import java.util.*;

public class TrigramUtils {

    public static void processDictionnary(Set<String> dictionary, HashMap<String, List<String>> trigramMap) {
        for (String word : dictionary) {
            StringBuilder motWithBoundaries = new StringBuilder("<").append(word).append(">");
            for (int i = 0; i < motWithBoundaries.length() - 2; i++) {
                String trigram = motWithBoundaries.substring(i, i + 3);
                trigramMap.computeIfAbsent(trigram, k -> new ArrayList<>()).add(word);
            }
        }
    }

    static List<String> createTrigram(String mot) {
        List<String> trigrams = new ArrayList<>();
        StringBuilder motWithBoundaries = new StringBuilder("<").append(mot).append(">");

        for (int i = 0; i < motWithBoundaries.length() - 2; i++) {
            if(trigrams.contains(motWithBoundaries.substring(i, i + 3))) {
                continue;
            }
            trigrams.add(motWithBoundaries.substring(i, i + 3));
        }
        return trigrams;
    }
}
