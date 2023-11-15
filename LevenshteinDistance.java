public class LevenshteinDistance {

    public static void main(String[] args) {
        String word1 = "logarytmique";
        String word2 = "algorithmique";

        int distance = CalculateDistanceLevenshtein(word1, word2);

        System.out.println("Distance de Levenshtein entre " + word1 + " et " + word2 + " : " + distance);
    }

    public static int CalculateDistanceLevenshtein(String word1, String word2) {
        int[][] distanceMatrix = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            distanceMatrix[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            distanceMatrix[0][j] = j;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                int cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                distanceMatrix[i][j] = minimum(
                        distanceMatrix[i - 1][j] + 1,
                        distanceMatrix[i][j - 1] + 1,
                        distanceMatrix[i - 1][j - 1] + cost
                );
            }
        }

        return distanceMatrix[word1.length()][word2.length()];
    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}
