package main.utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtils {

    /**
     * Returns a set of every element which is present in both provided sets.
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call intersection() if either set is null.");
        }

        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }

    /**
     * Returns a set of every element which is present in at least one of the provided sets.
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call union() if either set is null.");
        }

        Set<T> result = new HashSet<>();

        result.addAll(a);
        result.addAll(b);

        return result;
    }

    /**
     * Returns a set of every element present in a which is not present in b.
     */
    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call difference() if either set is null.");
        }

        return a.stream().filter(e -> !b.contains(e)).collect(Collectors.toSet());
    }
    
    /**
     * Removes an undetermined element from the set and returns it.
     */
    public static <T> T pop(Set<T> s) {
        if (isNullOrEmpty(s)) {
            throw new IllegalArgumentException("Cannot call pop() on null or empty set.");
        }

        Iterator<T> iterator = s.iterator();
        T popped = iterator.next();
        iterator.remove();
        return popped;
    }

    /**
     * Returns whether the first set is a subset of the second.
     */
    public static <T> boolean isSubset(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call isSubset() if either set is null.");
        }

        return a.stream().allMatch(b::contains);
    }

    /**
     * Returns whether the two sets do not share any common elements.
     */
    public static <T> boolean isDisjoint(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call isDisjoint() if either set is null.");
        }

        return intersection(a, b).isEmpty();
    }

    /**
     * Returns whether the set is null or empty.
     */
    public static <T> boolean isNullOrEmpty(Set<T> s) {
        return s == null || s.isEmpty();
    }

    public static void addAll(Set<Character> s, char[] arr) {
        for (char c : arr) {
            s.add(c);
        }
    }

    public static void retainAll(Set<Character> s, char[] arr) {
        Set<Character> other = setFromArr(arr);
        s.retainAll(other);
    }

    public static Set<Character> setFromArr(char[] arr) {
        Set<Character> s = new HashSet<>();
        for (char c : arr) {
            s.add(c);
        }
        return s;
    }
}
