package main.year2020.day4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part2 {

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

        private static final Pattern BASE_PATTERN = Pattern.compile("^(.+):(.+)$");
        private static final Pattern HEIGHT_PATTERN = Pattern.compile("^(\\d+)(cm|in)$");
        private static final Pattern HAIR_COLOR_PATTERN = Pattern.compile("^#[0-9a-f]{6}$");
        private static final Pattern EYE_COLOR_PATTERN = Pattern.compile("^(?:amb|blu|brn|gry|grn|hzl|oth)$");
        private static final Pattern PASSPORT_ID_PATTERN = Pattern.compile("^\\d{9}$");

        private static final String BIRTH_YEAR = "byr";
        private static final String ISSUE_YEAR = "iyr";
        private static final String EXPIRATION_YEAR = "eyr";
        private static final String HEIGHT = "hgt";
        private static final String HAIR_COLOR = "hcl";
        private static final String EYE_COLOR = "ecl";
        private static final String PASSPORT_ID = "pid";

        private static final Set<String> REQUIRED_FIELDS = Set.of(
            BIRTH_YEAR,
            ISSUE_YEAR,
            EXPIRATION_YEAR,
            HEIGHT,
            HAIR_COLOR,
            EYE_COLOR,
            PASSPORT_ID
        );

        private Map<String, String> fields;

        public Document() {
            fields = new HashMap<>();
        }

        public void addLine(String line) {
            String[] splitLine = line.split(" +");
            for (int i = 0; i < splitLine.length; i++) {
                Matcher matcher = BASE_PATTERN.matcher(splitLine[i]);
                if (matcher.find()) {
                    fields.put(matcher.group(1), matcher.group(2));
                }
            }
        }

        public void clear() {
            fields.clear();
        }

        public boolean isValid() {
            if (!fields.keySet().containsAll(REQUIRED_FIELDS)) {
                return false;
            }

            return hasValidBirthYear()
                && hasValidIssueYear()
                && hasValidExpirationYear()
                && hasValidHeight()
                && hasValidHairColor()
                && hasValidEyeColor()
                && hasValidPassportId();
        }

        private boolean hasValidBirthYear() {
            String birthYear = fields.get(BIRTH_YEAR);
            return validateLength(birthYear, 4)
                && validateInRange(birthYear, 1920, 2002);
        }

        private boolean hasValidIssueYear() {
            String issueYear = fields.get(ISSUE_YEAR);
            return validateLength(issueYear, 4)
                && validateInRange(issueYear, 2010, 2020);
        }

        private boolean hasValidExpirationYear() {
            String expirationYear = fields.get(EXPIRATION_YEAR);
            return validateLength(expirationYear, 4)
                && validateInRange(expirationYear, 2020, 2030);
        }

        private boolean hasValidHeight() {
            String height = fields.get(HEIGHT);
            Matcher matcher = HEIGHT_PATTERN.matcher(height);
            if (!matcher.find()) {
                return false;
            }

            switch (matcher.group(2)) {
                case "cm":
                    return validateInRange(matcher.group(1), 150, 193);
                
                case "in":
                    return validateInRange(matcher.group(1), 50, 76);
                
                default:
                    return false;
            }
        }

        private boolean hasValidHairColor() {
            String hairColor = fields.get(HAIR_COLOR);
            return HAIR_COLOR_PATTERN.matcher(hairColor).matches();
        }

        private boolean hasValidEyeColor() {
            String eyeColor = fields.get(EYE_COLOR);
            return EYE_COLOR_PATTERN.matcher(eyeColor).matches();
        }

        private boolean hasValidPassportId() {
            String passportId = fields.get(PASSPORT_ID);
            return PASSPORT_ID_PATTERN.matcher(passportId).matches();
        }

        private boolean validateLength(String s, int length) {
            return s.length() == length;
        }

        private boolean validateInRange(String yearString, int lowerBound, int upperBound) {
            try {
                int year = Integer.parseInt(yearString);
                return lowerBound <= year && year <= upperBound;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}
