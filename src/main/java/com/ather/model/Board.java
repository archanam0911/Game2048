package com.ather.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents the 2048 board
 * */
public class Board {

    private final int[][] arr;

    public Board(int size) {
        if (size <= 0) throw new IllegalArgumentException("Board size should be more than 0");

        this.arr = new int[size][];

        for (int x = 0; x < size; x++) {
            this.arr[x] = new int[size];
        }
    }

    private Board(int[][] arr) {
        this.arr = new int[arr.length][];

        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = new int[arr.length];
            for (int j = 0; j < arr.length; j++) {
                this.arr[i][j] = arr[i][j];
            }
        }
    }

    public int getSize() {
        return arr.length;
    }

    public int getTileValue(int x, int y) {
        return this.arr[x][y];
    }

    public int getTileValue(Tile tile) {
        int x = tile.getX();
        int y = tile.getY();
        return arr[x][y];
    }

    public boolean isEmpty(Tile tile) {
        int value = getTileValue(tile);
        if (value == 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Tile> emptyTiles() {
        List<Tile> emptyTiles = new ArrayList<>();
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                Tile tile = new Tile(x, y);
                if (isEmpty(tile)) {
                    emptyTiles.add(tile);
                }
            }
        }
        return emptyTiles;
    }

    public Board newNumber(Tile tile, int number) {
        if (!isEmpty(tile)) {
            throw new IllegalArgumentException("That tile is not empty");
        }

        Board result = new Board(this.arr);
        result.arr[tile.getX()][tile.getY()] = number;
        return result;
    }

    public Board move(Move move) {
        // Clone the board
        int[][] tiles = new int[this.arr.length][];
        for (int x = 0; x < this.arr.length; x++) {
            tiles[x] = Arrays.copyOf(this.arr[x], this.arr[x].length);
        }

        if (move == Move.LEFT || move == Move.RIGHT) {
            tiles = transpose(tiles);
        }
        if (move == Move.DOWN || move == Move.RIGHT) {
            tiles = reverse(tiles);
        }

        int[][] result = new int[tiles.length][];
        for (int x = 0; x < tiles.length; ++x) {
            LinkedList<Integer> thisRow = new LinkedList<>();
            for (int y = 0; y < tiles[0].length; ++y) {
                if (tiles[x][y] > 0) {
                    thisRow.add(tiles[x][y]);
                }
            }

            LinkedList<Integer> newRow = new LinkedList<>();
            while (thisRow.size() >= 2) {
                int first = thisRow.pop();
                int second = thisRow.peek();
                if (second == first) {
                    int newNumber = first * 2;
                    newRow.add(newNumber);
                    thisRow.pop();
                } else {
                    newRow.add(first);
                }
            }
            newRow.addAll(thisRow);

            result[x] = new int[tiles[0].length];
            for (int y = 0; y < tiles[0].length; ++y) {
                if (newRow.isEmpty()) {
                    result[x][y] = 0;
                } else {
                    result[x][y] = newRow.pop();
                }
            }
        }

        if (move == Move.DOWN || move == Move.RIGHT) {
            result = reverse(result);
        }
        if (move == Move.LEFT || move == Move.RIGHT) {
            result = transpose(result);
        }
        return new Board(result);
    }

    private static int[][] transpose(int[][] arr) {
        int[][] result = new int[arr.length][];

        for (int x = 0; x < arr.length; x++) {
            result[x] = new int[arr[0].length];
            for (int y = 0; y < arr[0].length; y++) {
                result[x][y] = arr[y][x];
            }
        }

        return result;
    }

    private static int[][] reverse(int[][] input) {
        int[][] result = new int[input.length][];

        for (int x = 0; x < input.length; x++) {
            result[x] = new int[input[0].length];
            for (int y = 0; y < input[0].length; y++) {
                result[x][y] = input[x][input.length - y - 1];
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(arr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Board board1 = (Board) o;
        return Arrays.deepEquals(arr, board1.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(arr);
    }
}

