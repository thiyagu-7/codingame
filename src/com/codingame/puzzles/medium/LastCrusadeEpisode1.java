package com.codingame.puzzles.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.codingame.com/ide/puzzle/the-last-crusade-episode-1
 */
public class LastCrusadeEpisode1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // number of columns.
        int H = in.nextInt(); // number of rows.
        if (in.hasNextLine()) {
            in.nextLine();
        }
        List<String[]> grid = new ArrayList<>();
        for (int i = 0; i < H; i++) {
            String LINE = in.nextLine(); // represents a line in the grid and contains W integers. Each integer represents one room of a given type.
            grid.add(LINE.split(" "));
        }
        int EX = in.nextInt(); // the coordinate along the X axis of the exit (not useful for this first mission, but must be read).

        Map<Integer, Map<String, Pair>> mappings = makeMappings();

        // game loop
        while (true) {
            int XI = in.nextInt();
            int YI = in.nextInt();
            String POS = in.next();

            //Flipping it as it is reversed in the problem
            int row = YI;
            int col = XI;
            Integer gridType = Integer.parseInt(grid.get(row)[col]);
            Pair displacement = mappings.get(gridType).get(POS);
            // One line containing the X Y coordinates of the room in which you believe Indy will be on the next turn.
            System.out.println((col + displacement.getY())
                    + " " + (row + displacement.getX()));
        }
    }

    private static Map<Integer, Map<String, Pair>> makeMappings() {
        Map<Integer, Map<String, Pair>> mappings = new HashMap<>();
        Map<String, Pair> routes = new HashMap<>();
        routes.put("LEFT", new Pair(1, 0));
        routes.put("RIGHT", new Pair(1, 0));
        routes.put("TOP", new Pair(1, 0));
        mappings.put(1, routes);

        routes = new HashMap<>();
        routes.put("LEFT", new Pair(0, 1));
        routes.put("RIGHT", new Pair(0, -1));
        mappings.put(2, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(1, 0));
        mappings.put(3, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(0, -1));
        routes.put("RIGHT", new Pair(1, 0));
        mappings.put(4, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(0, 1));
        routes.put("LEFT", new Pair(1, 0));
        mappings.put(5, routes);

        routes = new HashMap<>();
        routes.put("LEFT", new Pair(0, 1));
        routes.put("RIGHT", new Pair(0, -1));
        mappings.put(6, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(1, 0));
        routes.put("RIGHT", new Pair(1, 0));
        mappings.put(7, routes);

        routes = new HashMap<>();
        routes.put("LEFT", new Pair(1, 0));
        routes.put("RIGHT", new Pair(1, 0));
        mappings.put(8, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(1, 0));
        routes.put("LEFT", new Pair(1, 0));
        mappings.put(9, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(0, -1));
        mappings.put(10, routes);

        routes = new HashMap<>();
        routes.put("TOP", new Pair(0, 1));
        mappings.put(11, routes);

        routes = new HashMap<>();
        routes.put("RIGHT", new Pair(1, 0));
        mappings.put(12, routes);

        routes = new HashMap<>();
        routes.put("LEFT", new Pair(1, 0));
        mappings.put(13, routes);

        return mappings;
    }

    private static class Pair {
        private int x;
        private int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int getX() {
            return x;
        }
        int getY() {
            return y;
        }
    }
}
