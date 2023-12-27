package main.year2020.day7;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;
import main.utils.SetUtils;

public class Part1 {

    private static final String START = "shiny gold";
    private static final String CONTAINS_NO_BAGS = "no other bags";
    private static final Pattern LINE_PATTERN = Pattern.compile("^([a-zA-Z ]+) bags contain (.+)\\.$");
    private static final Pattern INDIVIDUAL_PATTERN = Pattern.compile("^\\d+ ([a-zA-z ]+) bags?$");

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Map<String, Set<String>> parentsByChild = new HashMap<>();

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

                String child = individualMatcher.group(1);
                parentsByChild.computeIfAbsent(child, k -> new HashSet<>()).add(parent);
            }
        });

        InputOutputUtils.closeReader(reader);

        System.out.println(getTotal(parentsByChild, START));
    }

    private static int getTotal(Map<String, Set<String>> m, String start) {
        Set<String> toExplore = new HashSet<>();
        Set<String> explored = new HashSet<>();
        toExplore.add(start);

        while (!toExplore.isEmpty()) {
            String curr = SetUtils.pop(toExplore);
            explored.add(curr);
            if (!m.containsKey(curr)) {
                continue;
            }

            Set<String> parents = m.get(curr);
            for (String parent : parents) {
                if (!explored.contains(parent) && !toExplore.contains(parent)) {
                    toExplore.add(parent);
                }
            }
        }

        return explored.size() - 1;
    }
}
