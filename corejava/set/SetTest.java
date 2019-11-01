package set;

import java.util.*;

public class SetTest{
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        try (Scanner in = new Scanner(System.in)){
            while (in.hasNext()){
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime; 
            }
        }

        Iterator<String> iterator = words.iterator();
        for (int i = 0; i < 20 && iterator.hasNext(); ++i){
            System.out.println(iterator.next());
        }
        System.out.println(". . .");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds");
    }
}