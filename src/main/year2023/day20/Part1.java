package main.year2023.day20;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final String BROADCASTER = "broadcaster";
    private static final Pattern pattern = Pattern.compile("(%|&|)(\\w+) -> (.+)");
    private static final AtomicInteger lowSignals = new AtomicInteger();
    private static final AtomicInteger highSignals = new AtomicInteger();

    public static void main(String[] args) throws IOException {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        Map<String, Transmitter> transmittersById = new HashMap<>();
        Map<String, List<String>> destinationsById = new HashMap<>();
        Map<String, List<String>> sourcesById = new HashMap<>();

        Arrays
            .stream(lines)
            .forEach(line -> {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.find()) {
                    throw new RuntimeException();
                }

                String type = matcher.group(1);
                String id = matcher.group(2);
                List<String> destinations = getListFromCommaString(matcher.group(3));

                transmittersById.put(id, new Transmitter(id, type));
                destinationsById.put(id, destinations);
                for (String destination : destinations) {
                    sourcesById
                        .computeIfAbsent(destination, k -> new ArrayList<>())
                        .add(id);
                }
            });

        transmittersById.forEach((id, transmitter) -> {
            if (sourcesById.containsKey(id)) {
                for (String source : sourcesById.get(id)) {
                    transmitter.addSource(transmittersById.get(source));
                }
            }

            for (String destination : destinationsById.get(id)) {
                Transmitter destinationTransmitter = transmittersById.get(destination);
                transmitter.addDestination(
                    destinationTransmitter != null
                        ? destinationTransmitter
                        : new Transmitter(destination)
                );
            }
        });

        Queue<Transmission> transmissions = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            transmissions.offer(new Transmission(null, transmittersById.get(BROADCASTER), false));

            while (!transmissions.isEmpty()) {
                Transmission oldTransmission = transmissions.poll();
                List<Transmission> newTransmissions = oldTransmission.transmit();
                transmissions.addAll(newTransmissions);
            }
        }

        System.out.println(lowSignals.get() * highSignals.get());
    }

    private static List<String> getListFromCommaString(String s) {
        return Arrays.stream(s.split(" *, *")).toList();
    }

    private static class Transmitter {

        private String id;
        private Character type;
        private boolean state;
        private Map<Transmitter, Boolean> sources;
        private List<Transmitter> destinations;

        public Transmitter(String id, String type) {
            this.id = id;
            this.type = type.isEmpty() ? null : type.charAt(0);
            this.state = false;
        }

        public Transmitter(String id) {
            this.id = id;
            this.type = null;
            this.state = false;
        }

        public void addSource(Transmitter source) {
            if (sources == null) {
                sources = new HashMap<>();
            }

            sources.put(source, false);
        }

        public void addDestination(Transmitter destination) {
            if (destinations == null) {
                destinations = new ArrayList<>();
            }

            destinations.add(destination);
        }

        public List<Transmission> processSignal(Transmitter source, boolean signal) {
            if (type == null) {
                if (destinations == null) {
                    return Collections.emptyList();
                }

                return destinations
                    .stream()
                    .map(destination -> new Transmission(this, destination, signal))
                    .toList();
            }

            if (type == '%') {
                if (signal) {
                    return Collections.emptyList();
                } else {
                    state = !state;
                    return destinations
                        .stream()
                        .map(destination -> new Transmission(this, destination, state))
                        .toList();
                }
            }

            if (type == '&') {
                sources.put(source, signal);
                boolean outputSignal = sources.values().stream().anyMatch(Boolean.FALSE::equals);
                return destinations
                    .stream()
                    .map(destination -> new Transmission(this, destination, outputSignal))
                    .toList();
            }

            throw new RuntimeException();
        }
    }

    private static class Transmission {

        private Transmitter source;
        private Transmitter destination;
        private boolean signal;

        public Transmission(Transmitter source, Transmitter destination, boolean signal) {
            this.source = source;
            this.destination = destination;
            this.signal = signal;
            if (signal) {
                highSignals.incrementAndGet();
            } else {
                lowSignals.incrementAndGet();
            }
        }

        public List<Transmission> transmit() {
            return destination.processSignal(source, signal);
        }
    }
}
