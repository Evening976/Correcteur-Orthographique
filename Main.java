import java.util.List;

public class Main {
    public static void main(String[] args) {
        String minidico = "C:\\Users\\chris\\Downloads\\TP2\\minidico.txt";
        String dico = "C:\\Users\\chris\\Downloads\\TP2\\dico.txt";
        String fautes = "C:\\Users\\chris\\Downloads\\TP2\\fautes.txt";

        //oneWordCorrection(minidico); //27ms alone using minidico, 500ms using dico
        //fourWordCorrection(dico); //36ms alone, 10ms if executed after oneWordCorrection() using minidico, 1680ms using dico alone, 1400ms after oneWordCorrection()
        fullCorrection(); //88280ms-70000 alone dico, 962000 after oneWordCorrection() and fourWordCorrection()




    }

    private static void oneWordCorrection(String path){
        long startTime = System.currentTimeMillis();
        Correction.correction("recammindation", path);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of one word is : " + elapsedTime + " ms");
    }

    private static void fourWordCorrection(String path){
        long startTime = System.currentTimeMillis();
        List<String> toCorrect = List.of("plusieura", "recammindation", "cezte", "pfrase");

        for (String word : toCorrect) {
            Correction.correction(word, path);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of four words is : " + elapsedTime + " ms");
    }

    private static void fullCorrection(){
        String dico = "C:\\Users\\chris\\Downloads\\TP2\\dico.txt";
        long startTime = System.currentTimeMillis();
        List<String> toCorrect = Reader.readLines("C:\\Users\\chris\\Downloads\\TP2\\fautes.txt");

        for (String word : toCorrect) {
            Correction.correction(word, dico);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of all words is : " + elapsedTime + " ms");
    }
}
