import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Correction {

    private static List<String> dictionary = new ArrayList<>();
    private static HashMap<String, List<String>> dicoTrigramMap = new HashMap<>();

    static List<String> correction(String word, String path) {

        if (inDictionary(word)) {
            System.out.printf("\n --> the word \"%s\" is in the dictionary, no need to correct it ! \n", word);
            return new ArrayList<>();
        }

        List<String> trigramsM = Reader.createTrigram("<" + word + ">");

        dicoTrigramMap = Reader.triGramFinder(path);

        List<String> commonTrigramsWords = findCommunTrigramList(trigramsM);

        Map<String, Integer> occurrenceCount = calculateOccurrences(commonTrigramsWords, trigramsM);

        List<String> selectedWords = selectWordsWithMaxTrigrams(occurrenceCount, 100);

        List<String> closestWords = selectClosetWords(word, selectedWords, 5);

        if(closestWords.isEmpty()) {
            System.out.printf("\n --> the word \"%s\" is not in the dictionary and no close word was found ! \n", word);
            return new ArrayList<>();
        }

        //System.out.println("top 5 words found : " + closestWords);
        System.out.printf("\n --> the correction for the word \"%s\" is  : \"%s\" \n", word, closestWords.get(0));

        return closestWords;
    }

    private static boolean inDictionary(String mot) {
        return dictionary.contains(mot);
    }


    private static List<String> findCommunTrigramList(List<String> trigramsM) {
        List<String> communTrigramList = new ArrayList<>();
        for (String trigram : trigramsM) {
            List<String> mots = dicoTrigramMap.get(trigram);
            if (mots != null) {
                communTrigramList.addAll(mots);
            }
        }
        return communTrigramList;
    }

    private static Map<String, Integer> calculateOccurrences(List<String> mots, List<String> trigrammesM) {
        Map<String, Integer> wordOccurrence = new HashMap<>();

        for (String mot : mots) {
            for (String trigram : trigrammesM) {
                List<String> trigramWords = dicoTrigramMap.getOrDefault(trigram, new ArrayList<>());
                wordOccurrence.put(mot, wordOccurrence.getOrDefault(mot, 0) + trigramWords.size());
            }
        }

        return wordOccurrence;
    }


    private static List<String> selectWordsWithMaxTrigrams(Map<String, Integer> occurrencesParMot, int limite) {
        List<String> selectedWords = new ArrayList<>();

        occurrencesParMot.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limite)
                .map(Map.Entry::getKey)
                .forEach(selectedWords::add);

        return selectedWords;
    }


    private static List<String> selectClosetWords(String word, List<String> words, int nombre) {
        words.sort((mot1, mot2) -> Integer.compare(calculateLevenshteinDistance(word, mot1), calculateLevenshteinDistance(word, mot2)));
        return words.subList(0, Math.min(nombre, words.size()));
    }

    private static int calculateLevenshteinDistance(String word1, String word2) {
        return LevenshteinDistance.CalculateDistanceLevenshtein(word1, word2);
    }

    //public static void main(String[] args) {
    //    String minidico = "C:\\Users\\chris\\Downloads\\TP2\\minidico.txt";
    //    dictionary.addAll(Reader.readLines("C:\\Users\\chris\\Downloads\\TP2\\minidico.txt"));
    //    String motM = "reddation";
    //    List<String> motsProches = correction(motM);
    // }


}
