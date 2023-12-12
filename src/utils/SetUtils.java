package utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtils {

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call intersection() if either set is null.");
        }

        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call union() if either set is null.");
        }

        Set<T> result = new HashSet<>();

        result.addAll(a);
        result.addAll(b);

        return result;
    }

    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call difference() if either set is null.");
        }

        return a.stream().filter(e -> !b.contains(e)).collect(Collectors.toSet());
    }
    
    public static <T> T pop(Set<T> s) {
        if (isNullOrEmpty(s)) {
            throw new IllegalArgumentException("Cannot call pop() on null or empty set.");
        }

        Iterator<T> iterator = s.iterator();
        T popped = iterator.next();
        iterator.remove();
        return popped;
    }

    public static <T> boolean isSubset(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call subset() if either set is null.");
        }

        return a.stream().allMatch(b::contains);
    }

    public static <T> boolean isDisjoint(Set<T> a, Set<T> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Cannot call isDisjoint() if either set is null.");
        }

        return intersection(a, b).isEmpty();
    }

    public static <T> boolean isNullOrEmpty(Set<T> s) {
        return s == null || s.isEmpty();
    }
}
