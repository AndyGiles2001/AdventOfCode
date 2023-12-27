package main.year2023.day5;

import java.io.IOException;

import java.lang.Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String[] storedLines = InputOutputUtils.getStringArrayFromFile();

        List<Map> maps = new ArrayList<>();
        for (int i = 1; i < storedLines.length; i++) {
            String line = storedLines[i];
            if (!line.isEmpty() && line.charAt(line.length() - 1) == ':') {
                maps.add(new Map());
            } else if (!line.isEmpty()) {
                Map map = maps.get(maps.size() - 1);
                map.addEntry(line);
            }
        }

        String[] seedStrings = storedLines[0].split(": ")[1].split(" ");
        Set<Range> seedRanges = new HashSet<>();
        for (int i = 0; i < seedStrings.length; i += 2) {
            long rangeStart = Long.parseLong(seedStrings[i]);
            long rangeSize = Long.parseLong(seedStrings[i + 1]);
            seedRanges.add(new Range(rangeStart, rangeSize));
        }

        Set<Range> locations = getCompleteMapping(seedRanges, maps);

        long result = Collections.min(
            locations
                .stream()
                .map(range -> range.getStart())
                .collect(Collectors.toSet())
        );

        System.out.println(result);
    }

    private static Set<Range> getCompleteMapping(Set<Range> ranges, List<Map> maps) {
        for (Map map : maps) {
            ranges = map.getMapping(ranges);
        }

        return ranges;
    }

    private static class Map {
        private Set<MapEntry> mapEntries;

        public Map() {
            mapEntries = new HashSet<>();
        }

        public void addEntry(String serialized) {
            mapEntries.add(new MapEntry(serialized));
        }

        public Set<Range> getMapping(Set<Range> inputRanges) {
            System.out.println(String.format("In %d ranges.", inputRanges.size()));
            Set<Range> outputRanges = new HashSet<>();

            for (Range range : inputRanges) {
                for (MapEntry mapEntry : mapEntries) {
                    Range outputRange = mapEntry.generateMapping(range);
                    if (outputRange != null) {
                        outputRanges.add(outputRange);
                    }
                }
            }

            System.out.println(String.format("Out %d ranges.", outputRanges.size()));
            return outputRanges;
        }
    }

    private static class MapEntry {
        private long sourceStart;
        private long destinationStart;
        private long rangeSize;

        public MapEntry(String serialized) {
            String[] mapInfo = serialized.split(" ");
            this.sourceStart = Long.parseLong(mapInfo[1]);
            this.destinationStart = Long.parseLong(mapInfo[0]);
            this.rangeSize = Long.parseLong(mapInfo[2]);
        }

        public Range generateMapping(Range range) {
            long rangeStart = range.getStart();
            long rangeEnd = range.getEnd();
            long entryEnd = sourceStart + rangeSize;

            if (entryEnd <= rangeStart) {
                return null;
            }

            if (rangeEnd <= sourceStart) {
                return null;
            }

            long maxStart = Math.max(sourceStart, rangeStart);
            long minEnd = Math.min(entryEnd, rangeEnd);

            long mappingDistance = destinationStart - sourceStart;

            return new Range(maxStart + mappingDistance, minEnd - maxStart);
        }
    }

    private static class Range {
        private long start;
        private long rangeSize;

        public Range(long start, long rangeSize) {
            this.start = start;
            this.rangeSize = rangeSize;
        }

        public long getStart() {
            return start;
        }

        public long getEnd() {
            return start + rangeSize;
        }
    }
}
