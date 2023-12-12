package test.utils;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import main.utils.SetUtils;

public class SetUtilsTest {
    
    @Test
    public void intersection_test() {
        // Mock
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6, 7));

        // Act
        Set<Integer> result = SetUtils.intersection(a, b);

        // Assert
        assertEquals(3, result.size());
    }
}
