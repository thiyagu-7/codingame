package com.codingame.puzzles.medium;


import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://www.codingame.com/ide/puzzle/there-is-no-spoon-episode-1
 */
public class ThereIsNoSpoonEpisode1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        int height = in.nextInt(); // the number of cells on the Y axis
        if (in.hasNextLine()) {
            in.nextLine();
        }
        char[][] grid = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            for (int j = 0; j < width; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int x2 = -1, y2 = -1; //right neighbour
                int x3 = -1, y3 = -1; //bottom neighbour


                if (grid[i][j] == '0') {
                    int r, c;
                    r = i;
                    c = j + 1;
                    while (c < width) {
                        if (grid[r][c] == '0') {
                            x2 = r;
                            y2 = c;
                            break;
                        }
                        c++;
                    }
                    r = i + 1;
                    c = j;
                    while (r < height) {
                        if (grid[r][c] == '0') {
                            x3 = r;
                            y3 = c;
                            break;
                        }
                        r++;
                    }
                    //Flip row, column as codinggame speaks the other way around
                    System.out.println(Stream.of(j, i, y2, x2, y3, x3)
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")));
                }
            }
        }
    }
}