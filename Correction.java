import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Correction {

    private static List<String> dictionary = new ArrayList<>();
    private static Map<String, List<String>> trigramList = new HashMap<>();

    public static void main(String[] args) {
        dictionary.addAll(Reader.readLines("C:\\Users\\chris\\Downloads\\TP2\\minidico.txt"));

        String motM = "algorythmique";

        if (!inDictionary(motM)) {
            System.out.println("the word :  " + motM + " is not in the dictionary.");
            return;
        }

        List<String> trigramsM = findTrigram("<" + motM + ">");

        List<String> commonTrigrams = findCommunTrigramList(trigramsM);

        Map<String, Integer> occurrenceCount = calculateOccurrences(commonTrigrams, trigramsM);

        List<String> motsSelectionnes = selectWordsWithMaxTrigrams(occurrenceCount, 100);

        List<String> motsProches = selectClosetWords(motM, motsSelectionnes, 5);

        System.out.println("Mots suggérés : " + motsProches);
    }

    private static boolean inDictionary(String mot) {
        return dictionary.contains(mot);
    }

    private static List<String> findTrigram(String mot) {
        List<String> trigrammes = new ArrayList<>();
        for (int i = 0; i < mot.length() - 2; i++) {
            trigrammes.add(mot.substring(i, i + 3));
        }
        return trigrammes;
    }

    private static List<String> findCommunTrigramList(List<String> trigrammesM) {
        List<String> wordsCommonTrigrams = new ArrayList<>();
        for (String trigram : trigrammesM) {
            wordsCommonTrigrams.addAll(trigramList.getOrDefault(trigram, new ArrayList<>()));
        }
        return wordsCommonTrigrams;
    }

    private static Map<String, Integer> calculateOccurrences(List<String> mots, List<String> trigrammesM) {
        Map<String, Integer> wordOccurrence = new HashMap<>();
        for (String mot : mots) {
            for (String trigram : trigrammesM) {
                wordOccurrence.put(mot, wordOccurrence.getOrDefault(mot, 0) + trigramList.getOrDefault(trigram, new ArrayList<>()).size());
            }
        }
        return wordOccurrence;
    }

    private static List<String> selectWordsWithMaxTrigrams(Map<String, Integer> occurrencesParMot, int limite) {
        occurrencesParMot.entrySet().stream()
                .sorted((entry1, entry2) -> Integer.compare(entry2.getValue(), entry1.getValue()))
                .limit(limite)
                .map(Map.Entry::getKey)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return new ArrayList<>(occurrencesParMot.keySet());
    }

    private static List<String> selectClosetWords(String mot, List<String> mots, int nombre) {
        mots.sort((mot1, mot2) -> Integer.compare(calculateLevenshteinDistance(mot, mot1), calculateLevenshteinDistance(mot, mot2)));
        return mots.subList(0, Math.min(nombre, mots.size()));
    }

    private static int calculateLevenshteinDistance(String word1, String word2) {
        return LevenshteinDistance.CalculateDistanceLevenshtein(word1, word2);
    }
}
