package environment;

import java.awt.GraphicsEnvironment;

public class Test {
    public static void main(String[] args) {
        int count = 0;
        String[] fontNames = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            System.out.println(fontName);
            ++count;
        }
        System.out.println(count);
    }
} 