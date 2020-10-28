package corejava.linkedlist;
import java.util.*;

public class LinkedListTest{
    public static void main(String[] args) {
        List<String> a = new LinkedList<>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // merge the words from b into a

        ListIterator<String> aIterator = a.listIterator();
        ListIterator<String> bIterator = b.listIterator();

        while (bIterator.hasNext()){
            if (aIterator.hasNext()) aIterator.next();
            aIterator.add(bIterator.next());
        }

        System.out.println(a);

        // remove every second word from b

        bIterator = b.listIterator();
        while (bIterator.hasNext()){
            bIterator.next(); // skip one element
            if (bIterator.hasNext()){
                bIterator.next();  // skip next element
                bIterator.remove(); // remove that element
            }
        }

        System.out.println(b);

        // bulk operation: remove all words in b from a

        a.removeAll(b);

        System.out.println(a);
    }
}