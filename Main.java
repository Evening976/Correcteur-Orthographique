import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> toCorrect = List.of("plusieura", "recammindation", "dpns", "cezte", "pfrase");

        for (String word : toCorrect) {
            Correction.correction(word);
        }
    }

}
