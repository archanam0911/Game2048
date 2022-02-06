package com.ather;

import com.ather.model.Board;
import com.ather.model.Tile;
import com.ather.model.BotPlayer;
import com.ather.model.HumanPlayer;

import java.util.Scanner;

public class Game2048Runtime {

    public static void main(String[] args) {

        System.out.println("Please enter the grid size: Ex. 4, 8, etc.");
        Board board = new Board(new Scanner(System.in).nextInt());
        BotPlayer botPlayer = new BotPlayer();
        HumanPlayer humanPlayer = new HumanPlayer();

        board = botPlayer.move(board);
        printCurrentBoard(board);
        do {
            board = humanPlayer.move(board);
            System.out.println("Please Make Next Move!!!");
            board = botPlayer.move(board);
            printCurrentBoard(board);
        } while (!board.emptyTiles().isEmpty());

        System.out.println("Game OVER!!!!!");
    }

    private static void printCurrentBoard(Board board) {

        StringBuilder topLines = new StringBuilder();
        StringBuilder midLines = new StringBuilder();
        for (int x = 0; x < board.getSize(); ++x) {
            topLines.append("+--------");
            midLines.append("|        ");
        }
        topLines.append("+");
        midLines.append("|");


        for (int y = 0; y < board.getSize(); ++y) {
            System.out.println(topLines);
            System.out.println(midLines);
            for (int x = 0; x < board.getSize(); ++x) {
                Tile tile = new Tile(x, y);
                System.out.print("|");
                if (board.isEmpty(tile)) {
                    System.out.print("        ");
                } else {
                    StringBuilder output = new StringBuilder(Integer.toString(board.getTileValue(tile)));
                    while (output.length() < 8) {
                        output.append(" ");
                        if (output.length() < 8) {
                            output.insert(0, " ");
                        }
                    }
                    System.out.print(output);
                }
            }
            System.out.println("|");
            System.out.println(midLines);
        }
        System.out.println(topLines);
    }
}