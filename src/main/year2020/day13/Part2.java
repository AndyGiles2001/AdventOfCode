package main.year2020.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        String[] busIdStrings = lines[1].split(",");
        List<Integer> busIds = new ArrayList<>();
        List<Integer> moduli = new ArrayList<>();
        for (int i = 0; i < busIdStrings.length; i++) {
            String busIdString = busIdStrings[i];
            Integer busId = busIdString.equals("x") ? null : Integer.valueOf(busIdString);
            if (busId != null) {
                busIds.add(busId);
                moduli.add(i);
            }
        }

        long offset = moduli.get(0);
        long increment = busIds.get(0);

        for (int i = 1; i < busIds.size(); i++) {
            int busId = busIds.get(i);
            while (true) {
                if ((offset + moduli.get(i)) % busId == 0) {
                    increment *= busId;
                    break;
                }
                offset += increment;
            }
        }

        System.out.println(offset);
    }
}
