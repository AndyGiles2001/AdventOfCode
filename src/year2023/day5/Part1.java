package year2023.day5;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String[] storedLines = InputOutputUtils.getStringArrayFromFile();

        List<Long> seeds = new ArrayList<>();
        for (String seedString : storedLines[0].split(": ")[1].split(" ")) {
            seeds.add(Long.parseLong(seedString));
        }
        
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

        long minimumLocation = Long.MAX_VALUE;
        for (long seed : seeds) {
            long completeMapping = getCompleteMapping(seed, maps);
            if (completeMapping < minimumLocation) {
                minimumLocation = completeMapping;
            }
        }

        System.out.println(minimumLocation);
    }

    private static long getCompleteMapping(long value, List<Map> maps) {
        for (Map map : maps) {
            value = map.getMapping(value);
        }
        return value;
    }

    private static class Map {
        private List<MapEntry> mapEntries;

        public Map() {
            mapEntries = new ArrayList<>();
        }

        public void addEntry(String serialized) {
            mapEntries.add(new MapEntry(serialized));
        }

        public long getMapping(long sourceValue) {
            for (MapEntry entry : mapEntries) {
                if (entry.mappingApplies(sourceValue)) {
                    return entry.getMapping(sourceValue);
                }
            }

            return sourceValue;
        }
    }

    private static class MapEntry {
        private long sourceStart;
        private long destinationStart;
        private long range;

        public MapEntry(String serialized) {
            String[] mapInfo = serialized.split(" ");
            this.sourceStart = Long.parseLong(mapInfo[1]);
            this.destinationStart = Long.parseLong(mapInfo[0]);
            this.range = Long.parseLong(mapInfo[2]);
        }

        public boolean mappingApplies(long sourceValue) {
            return sourceValue >= sourceStart && sourceValue < sourceStart + range;
        }

        public long getMapping(long sourceValue) {
            return sourceValue + destinationStart - sourceStart;
        }
    }
}