import java.util.ArrayList;
import java.util.List;

public class    Main {
    public static void main(String[] args) {
        String minidico = "minidico.txt";
        List<String> dico = Reader.readLines("dico.txt");
        List<String> fautes = Reader.readLines("fautes.txt");

        //oneWordCorrection(minidico); //27ms alone using minidico, 500ms using dico
        //fourWordCorrection(dico); //36ms alone, 10ms if executed after oneWordCorrection() using minidico, 1680ms using dico alone, 1400ms after oneWordCorrection()

        fullCorrection(dico, fautes); //88280ms-70000 alone dico, 962000 after oneWordCorrection() and fourWordCorrection()
    }

    private void oneWordCorrection(String path){
        Correction correcter = new Correction(new ArrayList<String>());
        long startTime = System.currentTimeMillis();
        //correcter.correct("recammindation", path);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of one word is : " + elapsedTime + " ms");
    }

    private static void fourWordCorrection(List<String> dictionary){
        Correction correcter = new Correction(dictionary);
        
        long startTime = System.currentTimeMillis();
        List<String> faultList = List.of("plusieura", "recammindation", "cezte", "pfrase");

        for (String toCorrect : faultList) {
            correcter.correct(toCorrect, dictionary);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of four words is : " + elapsedTime + " ms");
    }

    private static void fullCorrection(List<String> dictionary, List<String> faultList){
        Correction correcter = new Correction(dictionary);

        long startTime = System.currentTimeMillis();
        for (String word : faultList) {
            correcter.correct(word, dictionary);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of all words is : " + elapsedTime + " ms");
    }
}
