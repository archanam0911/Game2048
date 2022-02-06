package com.ather.model;


import java.util.StringJoiner;

public class Tile {
    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tile.class.getSimpleName() + "[", "]").add("x=" + x).add("y=" + y).toString();
    }
}