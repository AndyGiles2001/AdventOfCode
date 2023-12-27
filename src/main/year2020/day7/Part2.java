package main.year2020.day7;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part2 {

    private static final String START = "shiny gold";
    private static final String CONTAINS_NO_BAGS = "no other bags";
    private static final Pattern LINE_PATTERN = Pattern.compile("^([a-zA-Z ]+) bags contain (.+)\\.$");
    private static final Pattern INDIVIDUAL_PATTERN = Pattern.compile("^(\\d+) ([a-zA-z ]+) bags?$");

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Map<String, Map<String, Integer>> childrenByParent = new HashMap<>();

        reader.lines().forEach(line -> {
            Matcher lineMatcher = LINE_PATTERN.matcher(line);
            if (!lineMatcher.find()) {
                throw new RuntimeException("Could not match line.");
            }

            String parent = lineMatcher.group(1);
            String children = lineMatcher.group(2);
            if (CONTAINS_NO_BAGS.equals(children)) {
                return;
            }

            for (String childClause : children.split(", ")) {
                Matcher individualMatcher = INDIVIDUAL_PATTERN.matcher(childClause);
                if (!individualMatcher.find()) {
                    throw new RuntimeException("Could not match containment description.");
                }

                String number = individualMatcher.group(1);
                String child = individualMatcher.group(2);
                childrenByParent
                    .computeIfAbsent(parent, k -> new HashMap<>())
                    .put(child, Integer.valueOf(number));
            }
        });

        InputOutputUtils.closeReader(reader);

        System.out.println(getTotal(childrenByParent, START));
    }

    private static int getTotal(Map<String, Map<String, Integer>> data, String key) {
        return data.containsKey(key)
            ? data
                .get(key)
                .entrySet()
                .stream()
                .collect(
                    Collectors.summingInt(
                        entry -> entry.getValue() * (1 + getTotal(data, entry.getKey()))
                    )
                )
            : 0;
    }
}
