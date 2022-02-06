package com.ather.model;

public enum Move {
    UP(3),
    DOWN(4),
    LEFT (1),
    RIGHT (2);

    private int value;

    Move(int value) {
        this.value = value;
    }

    public static Move getMove(int value) {
        for (Move move : Move.values())  {
            if (move.value == value) return move;
        }
        return null;
    }
}