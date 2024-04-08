package com.example.textadventuregame.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MapGenerator {
    private final int[][] map = new int[30][30];
    private final List<Integer> id_array = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));

    private enum Direction {

        NORTH(-1, 0),
        SOUTH(1, 0),
        WEST(0, -1),
        EAST(0, 1);
        public final int x;
        public final int y;

        Direction(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public void generateMap() {
        int[] currentRoomXY = {15, 15};
        int id = 15;
        map[15][15] = 1;
        map[16][15] = -1;
        int n = 0;
        while (n<14) {
            Direction newDirection = chooseRandomDirection();
            if (map[currentRoomXY[0] + newDirection.y][currentRoomXY[1] + newDirection.x] == 0) {
                map[currentRoomXY[0] + newDirection.y][currentRoomXY[1] + newDirection.x] = id;
                n++;
                if (numberOfNeighbors(currentRoomXY) == 4) {
                    currentRoomXY[0] = currentRoomXY[0] + newDirection.y;
                    currentRoomXY[1] = currentRoomXY[1] + newDirection.x;
                } else if (Math.random() > 0.5) {
                    currentRoomXY[0] = currentRoomXY[0] + newDirection.y;
                    currentRoomXY[1] = currentRoomXY[1] + newDirection.x;
                }

                if(!id_array.isEmpty()) {
                    Collections.shuffle(id_array);
                    id = id_array.get(0);
                    id_array.remove(0);
                }
            }
        }
//        for (int[] row : map) {
//            System.out.println(Arrays.toString(row));
//        }
    }

    private Direction chooseRandomDirection() {
        switch ((int) (Math.random() * 4)) {
            case 0: {
                return Direction.NORTH;
            }
            case 1: {
                return Direction.SOUTH;
            }
            case 2: {
                return Direction.WEST;
            }
            default: {
                return Direction.EAST;
            }
        }
    }

    private int numberOfNeighbors(int[] currentRoom) {
        int n = 0;
        if (map[currentRoom[0] + 1][currentRoom[1]] != 0) n++;
        if (map[currentRoom[0] - 1][currentRoom[1]] != 0) n++;
        if (map[currentRoom[0]][currentRoom[1] + 1] != 0) n++;
        if (map[currentRoom[0]][currentRoom[1] - 1] != 0) n++;
        return n;
    }

    public int[][] getMap() {
        return map;
    }
}