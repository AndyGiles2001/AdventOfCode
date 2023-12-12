package utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class SetUtils {

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        return a.stream().filter(b::contains).collect(Collectors.toSet());
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        return a.stream().filter(element -> !b.contains(element)).collect(Collectors.toSet());
    }
    
    public static <T> T pop(Set<T> s) {
        if (s.isEmpty()) {
            return null;
        }

        T popped = s.iterator().next();
        s.remove(popped);

        return popped;
    }
}
