public class Levenshtein {

    public static void test(String[] args) {
        String word1 = "logarytmique";
        String word2 = "algorithmique";

        int distance = getDistance(word1, word2);

        System.out.println("Distance de Levenshtein entre " + word1 + " et " + word2 + " : " + distance);
    }

    public static int getDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];


        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = min(dp[i - 1][j - 1] + cost,
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);

                    // Ajout de la transposition
                    if (i > 1 && j > 1 && word1.charAt(i - 1) == word2.charAt(j - 2) && word1.charAt(i - 2) == word2.charAt(j - 1)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + cost);
                    }

                    // Ajout de l'oubli d'une lettre
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + 1);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
