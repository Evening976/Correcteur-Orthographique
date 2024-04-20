import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> minidico = Reader.readLines("minidico.txt");
        Set<String> dico = Reader.readLines("dico.txt");
        Set<String> fautes = Reader.readLines("fautes.txt");

        if(args.length > 0){
            fautes = new HashSet<>();
            fautes.addAll(Arrays.asList(args));
        }

        //oneWordCorrection(minidico); //8ms
        //fourWordCorrection(dico); //94 ms car il faut traiter le gros dictionnaire

        fullCorrection(dico, fautes); //3795ms
    }

    private static void fourWordCorrection(Set<String> dictionary){
        Correction correcter = new Correction(dictionary);


        List<String> faultList = List.of("plusieura", "recammindation", "cezte", "pfrase");

        long startTime = System.currentTimeMillis();
        for (String toCorrect : faultList) {
            correcter.correct(toCorrect);
        }
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of four words is : " + elapsedTime + " ms");
    }

    private static void oneWordCorrection(Set<String> dictionary){
        Correction correcter = new Correction(dictionary);

        long startTime = System.currentTimeMillis();
        correcter.correct("recammindation");
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of one word is : " + elapsedTime + " ms");
    }

    private static void fullCorrection(Set<String> dictionary, Set<String> faultList){
        Correction correcter = new Correction(dictionary);

        long startTime = System.currentTimeMillis();
        for (String word : faultList) {
            correcter.correct(word);
        }
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;

        System.out.println("Time taken to find the correction of all words is : " + elapsedTime + " ms");
    }
}
