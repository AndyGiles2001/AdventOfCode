package main.year2020.day4;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        int validDocuments = 0;
        Document document = new Document();
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.isEmpty()) {
                if (document.isValid()) {
                    validDocuments++;
                }

                document.clear();
            } else {
                document.addLine(line);
            }
        }

        if (document.isValid()) {
            validDocuments++;
        }

        System.out.println(validDocuments);
    }

    private static class Document {

        private static final Pattern PATTERN = Pattern.compile("(.+):.+");
        private static final Set<String> REQUIRED_FIELDS = Set.of(
            "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
        );

        private Set<String> fields;

        public Document() {
            fields = new HashSet<>();
        }

        public boolean isValid() {
            return fields.containsAll(REQUIRED_FIELDS);
        }

        public void addLine(String line) {
            String[] splitLine = line.split(" +");
            for (int i = 0; i < splitLine.length; i++) {
                Matcher matcher = PATTERN.matcher(splitLine[i]);
                if (matcher.find()) {
                    fields.add(matcher.group(1));
                }
            }
        }

        public void clear() {
            fields.clear();
        }
    }
}
