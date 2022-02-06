package com.ather.model;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private Scanner scanner = new Scanner(System.in);
    public Board move(Board input) {

        Move move = null;
        while(move == null) {
            System.out.println("Enter move - (left 1, right 2, up 3 and down 4)");
            int value = scanner.nextInt();
            move = Move.getMove(value);
            if (move == null) {
                System.out.println("Invalid move, please enter correct move.");
            }
        }

        return input.move(move);
    }

}