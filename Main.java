package org.example;

import java.util.*;
import java.util.Map.Entry;
import static org.example.Mage.myComparator;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("no"))
            main(false);
        else if (args[0].equals("natural"))
            main(true);
        else
            main(true, myComparator);
    }
    // https://favtutor.com/blogs/java-optional-parameters
    public static void main(boolean with_sorting) {
        Set<Mage> mages;
        Map<Mage, Integer> statistic;
        if (!with_sorting) {
            mages = new HashSet<>();
            statistic = new HashMap<>();

            Mage mage0 = new Mage("Mikey", 10, 25, null);
            Set<Mage> set1 = new HashSet<>();
            set1.add(mage0);
            Mage mage1 = new Mage("Arun", 20, 29, set1);
            Mage mage2 = new Mage("Lisa", 5,  35, null);
            Set<Mage> set2= new HashSet<>();
            set2.add(mage1);
            set2.add(mage2);
            mages.add(new Mage("Pankaj", 1, 32, set2));
        }
        else {
            mages = new TreeSet<>();
            statistic = new TreeMap<>();

            Mage mage0 = new Mage("Mikey", 10, 25, null);
            Set<Mage> set1 = new TreeSet<>();
            set1.add(mage0);
            Mage mage1 = new Mage("Arun", 20, 29, set1);
            Mage mage2 = new Mage("Lisa", 5,  35, null);
            Set<Mage> set2= new TreeSet<>();
            set2.add(mage1);
            set2.add(mage2);
            mages.add(new Mage("Pankaj", 1, 32, set2));
        }
        main(mages, statistic);
    }

    public static void main(boolean with_sorting, Comparator<Mage> comparator) {
        if (!with_sorting) {
            main(false);
            return;
        }
        Set<Mage> mages = new TreeSet<>(comparator);
        Map<Mage, Integer> statistic = new TreeMap<>(comparator);

        Mage mage0 = new Mage("Mikey", 10, 25, null);
        Set<Mage> set1 = new TreeSet<>(comparator);
        set1.add(mage0);
        Mage mage1 = new Mage("Arun", 20, 29, set1);
        Mage mage2 = new Mage("Lisa", 5,  35, null);
        Set<Mage> set2= new TreeSet<>(comparator);
        set2.add(mage1);
        set2.add(mage2);
        mages.add(new Mage("Pankaj", 1, 32, set2));

        main(mages, statistic);
    }
    //        -Mage{name='Pankaj', level=, power=}
    //        --Mage{name='Arun', level=, power=}
    //        ---Mage{name='Mikey', level=, power=}
    //        --Mage{name='Lisa', level=, power=}
    // https://javastart.pl/baza-wiedzy/klasy/interfejs-set

    private static void printMageSet(Set<Mage> mages, int deep) {
        for (Mage mage : mages) {
            for (int i = 0; i <= deep; i++)
                System.out.print("-");
            System.out.println(mage);
            if (mage.getApprentices() != null)
                printMageSet(mage.getApprentices(), deep+1);
        }
    }

    // https://javastart.pl/baza-wiedzy/klasy/interfejs-map
    private static void generateStaticstics(Map<Mage, Integer> statistic, Set<Mage> mages) {
        for (Mage mage : mages) {
            statistic.put(mage, mage.getApprenticesNumber());
            if (mage.getApprentices() != null)
                generateStaticstics(statistic, mage.getApprentices());
        }
    }

    private static void printStatistics(Map<Mage, Integer> statistic) {
        Set<Entry<Mage, Integer>> entrySet = statistic.entrySet();
        for(Entry<Mage, Integer> entry: entrySet) System.out.println(entry.getKey() + " : " + entry.getValue());
    }

    private static void main(Set<Mage> mages, Map<Mage, Integer> statistic) {
        System.out.println("Mages set:");
        printMageSet(mages, 0);
        System.out.println("\nStatistics:");
        generateStaticstics(statistic, mages);
        printStatistics(statistic);
    }
}