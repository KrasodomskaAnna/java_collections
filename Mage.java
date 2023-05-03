package org.example;

import java.util.Objects;
import java.util.Set;
import java.util.Comparator;


public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private double power;
    private Set<Mage> apprentices;

    public Set<Mage> getApprentices() {return this.apprentices;};

    public Integer getApprenticesNumber() {
        if (this.apprentices == null) return 0;
        int nmb = 0;
        for (Mage child : this.apprentices)
            nmb += child.getApprenticesNumber() + 1;
        return nmb;
    }

    public Mage(String name, int level, double power, Set<Mage> apprentices) {
        this.name = name;
        this.level = level;
        this.power = power;
        this.apprentices = apprentices;
    }

    // https://javastart.pl/baza-wiedzy/programowanie-obiektowe/metoda-equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Double.compare(mage.power, power) == 0 && Objects.equals(name, mage.name) && Objects.equals(apprentices, mage.apprentices);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power, apprentices);
    }

    // https://www.geeksforgeeks.org/comparable-interface-in-java-with-examples/
    @Override
    public int compareTo(Mage a)
    {
        if (this.name.compareTo(a.name) != 0)
            return this.name.compareTo(a.name);
        if (this.level != a.level)
            return Integer.compare(this.level, a.level);
        return Double.compare(this.power, a.power);
    }

    // https://www.digitalocean.com/community/tutorials/comparable-and-comparator-in-java-example
    public static Comparator<Mage> myComparator = new Comparator<Mage>() {
        @Override
        public int compare(Mage e1, Mage e2) {
            if (e1.level != e2.level)
                return Integer.compare(e1.level, e2.level);
            if (e1.name.compareTo(e2.name) != 0)
                return e1.name.compareTo(e2.name);
            return Double.compare(e1.power, e2.power);
        }
    };
}
