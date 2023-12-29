import java.util.*;

public class Correction {

    private  List<String> dictionary = new ArrayList<>();
    private HashMap<String, List<String>> dicoTrigramMap = new HashMap<>();

    public Correction(List<String> baseDictionary) {
        dictionary = baseDictionary;
        dicoTrigramMap = new HashMap<>();

    }


    public List<String> correct(String word, List<String> dictionary) {
        this.dictionary = dictionary;

        System.out.println("\nCorrecting the word : " + word);


        if (inDictionary(word)) {
            System.out.printf("\n --> the word \"%s\" is in the dictionary, no need to correct it ! \n", word);
            return new ArrayList<>();
        }

        List<String> wordTrigrams = TrigramUtils.createTrigram(word);

        TrigramUtils.fastTrigramFinder(dictionary, dicoTrigramMap);

        HashSet<String> commonTrigramsWords = findCommonTrigramList(wordTrigrams);

        Map<String, Integer> occurrenceCount = calculateOccurrences(commonTrigramsWords, wordTrigrams);

        List<String> selectedWords = selectWordsWithMaxTrigrams(occurrenceCount, 100);

        List<String> closestWords = selectClosetWords(word, selectedWords, 5);

        if(closestWords.isEmpty()) {
            System.out.printf("\n --> the word \"%s\" is not in the dictionary and no close word was found ! \n", word);
            return new ArrayList<>();
        }

        //System.out.printf(" --> the correction for the word \"%s\" is  : \"%s\" \n", word, closestWords.get(0));

        return closestWords;
    }

    private boolean inDictionary(String mot) {
        return dictionary.contains(mot);
    }


    private HashSet<String> findCommonTrigramList(List<String> trigramsM) {
        HashSet<String> communTrigramList = new HashSet<>();

        for (String trigram : trigramsM) {
            List<String> mots = dicoTrigramMap.get(trigram);
            if (dicoTrigramMap.get(trigram) != null) {
                communTrigramList.addAll(mots);
            }
        }

        return communTrigramList;
    }

    private Map<String, Integer> calculateOccurrences(HashSet<String> trigrammesDico, List<String> trigrammesCible) {
        Map<String, Integer> wordOccurrence = new HashMap<>();

        for (String trigram : trigrammesCible) {
            List<String> trigramWords = dicoTrigramMap.get(trigram);
            if (trigramWords != null) {
                for (String word : trigramWords) {
                    if (trigrammesDico.contains(word)) {
                        wordOccurrence.put(word, wordOccurrence.getOrDefault(word, 0) + 1);
                    }
                }
            }
        }

        return wordOccurrence;
    }


    private List<String> selectWordsWithMaxTrigrams(Map<String, Integer> occurrencesParMot, int limite) {
        List<String> selectedWords = new ArrayList<>();

        occurrencesParMot.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limite)
                .map(Map.Entry::getKey)
                .forEach(selectedWords::add);

        return selectedWords;
    }


    private  List<String> selectClosetWords(String word, List<String> words, int nombre) {
        words.sort((mot1, mot2) -> Integer.compare(calculateLevenshteinDistance(word, mot1), calculateLevenshteinDistance(word, mot2)));
        System.out.println("The closest words are : " + words.subList(0, Math.min(nombre, words.size())));
        return words.subList(0, Math.min(nombre, words.size()));
    }

    private int calculateLevenshteinDistance(String word1, String word2) {
        return Levenshtein.getDistance(word1, word2);
    }

    //public static void main(String[] args) {
    //    String minidico = "C:\\Users\\chris\\Downloads\\TP2\\minidico.txt";
    //    dictionary.addAll(Reader.readLines("C:\\Users\\chris\\Downloads\\TP2\\minidico.txt"));
    //    String motM = "reddation";
    //    List<String> motsProches = correction(motM);
    // }


}
