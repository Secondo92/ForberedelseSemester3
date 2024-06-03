package Biler;

import java.util.HashMap;
import java.util.HashSet;

public class App {
    public static void main(String[] args) {
        HashSet<Bil> bilSet = new HashSet<>();
        bilSet.add(new Bil("DF3434234", "Alfa Romeo", "Tonale", "Rød"));
        bilSet.add(new Bil("sdgfgdfg3434", "Alfa Roretretmeo", "Ton5t4t45ale", "Rertød"));
        bilSet.add(new Bil("DF3434234", "Alfa Romeo", "Tonale", "Rød"));
        bilSet.add(new Bil("DF3434retret234", "Alfa Romerterteo", "Tonretertretale", ">Blå"));

        bilSet.forEach(bil -> System.out.println(bil));
    }
}
