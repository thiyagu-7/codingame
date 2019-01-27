package com.codingame.puzzles.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * https://www.codingame.com/ide/puzzle/hunger-games
 */
public class HungerGames {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int tributes = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        Map<String, TreeSet<String>> killerDetails = new TreeMap<>();
        Map<String, String> killedByDetails = new HashMap<>();

        for (int i = 0; i < tributes; i++) {
            String playerName = in.nextLine();
            killerDetails.put(playerName, new TreeSet<>());
        }
        int turns = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        for (int i = 0; i < turns; i++) {
            String info = in.nextLine();

            int index = info.indexOf("killed");
            String winner = info.substring(0, index -1);
            String remaining = info.substring(index + "killed".length() + 1);
            Arrays.stream(remaining.split(","))
                    .map(String::trim)
                    .forEach(val -> {
                        killerDetails.get(winner).add(val);
                        killedByDetails.put(val, winner);
                    });
        }

        int i = 0;
        for (Map.Entry<String, TreeSet<String>> entry : killerDetails.entrySet()) {
            String name = entry.getKey();
            String killed, killer;
            killed = entry.getValue().isEmpty()
                    ? "None"
                    : entry.getValue().stream().collect(Collectors.joining(", "));
            killer = killedByDetails.getOrDefault(name, "Winner");
            System.out.println("Name: " + name);
            System.out.println("Killed: " + killed);
            System.out.println("Killer: " + killer);

            if (i != killerDetails.size() - 1) {
                System.out.println();
            }
            i++;
        };
    }
}
