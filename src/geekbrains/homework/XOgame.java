package geekbrains.homework;

import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

public class XOgame {
    static final int SIZE = 5;

    static final char DOT_X = 'X';
    static final char DOT_O = '0';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Вы победили! Поздравляем!");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Компьютер победил.");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%c ", map[i][j]);
            }
            System.out.println();
        }
    }

    public static void humanTurn() {
        int x;
        int y;

        do {
            System.out.println("input X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {
        if (y < 0 || x < 0 || y >= SIZE || x >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x;
        int y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char c) {
        boolean checkHorizontal;
        boolean checkVertical;
        boolean checkDiagonal_1;
        boolean checkDiagonal_2;
        for (int i = 0; i < map.length; i++) {
            checkHorizontal = true;
            checkVertical = true;
            checkDiagonal_1 = true;
            checkDiagonal_2 = true;
            for (int j = 0; j < map[i].length; j++) {
                checkHorizontal &= (map[i][j] == c);
                checkVertical &= (map[j][i] == c);
                checkDiagonal_1 &= (map[j][j] == c);
                checkDiagonal_2 &= (map[map.length - j - 1][j] == c);
            }
            if (checkHorizontal || checkVertical || checkDiagonal_1 || checkDiagonal_2)
                return true;
        }
        return false;
    }
    //метод для поля 5*5 и 4 фишек
  /*  public static boolean checkWin(char c) {
        boolean direction_1;
        boolean direction_2;
        boolean direction_3;
        boolean direction_4;
        int chic = SIZE - 1;
        for (int i = 0; i < map.length - 1; i++) {
            direction_1 = true;
            direction_2 = true;
            direction_3 = true;
            direction_4 = true;
            for (int j = 0; j < map[i].length - 1; j++) {
                direction_1 &= (map[i][j] == c);
                direction_2 &= (map[j][j] == c);
                direction_3 &= (map[j][i] == c);
                direction_4 &= (map[chic - j - 1][j] == c);
            }
            if (direction_1 || direction_2 || direction_3 || direction_4)
                return true;
        }
        for (int i = 0; i < map.length; i++) {
            direction_1 = true;
            direction_2 = true;
            direction_3 = true;
            direction_4 = true;
            for (int j = 1; j < map[i].length; j++) {
                direction_1 &= (map[i][j] == c);
                direction_2 &= (map[j][j] == c);
                direction_3 &= (map[j][i] == c);
                direction_4 &= (map[chic - j][j] == c);
            }
            if (direction_1 || direction_2 || direction_3 || direction_4)
                return true;
        }
        for (int i = 1; i < map.length - 1; i++) {
            direction_1 = true;
            direction_2 = true;
            direction_3 = true;
            direction_4 = true;
            for (int j = 0; j < map.length - 1; j++) {
                direction_1 &= (map[i][j] == c);
                direction_2 &= (map[j][j] == c);
                direction_3 &= (map[j][i] == c);
                direction_4 &= (map[chic - j][j] == c);
            }
            if (direction_1 || direction_2 || direction_3 || direction_4)
                return true;
        }
        for (int i = 1; i < map.length; i++) {
            direction_1 = true;
            direction_2 = true;
            direction_3 = true;
            direction_4 = true;
            for (int j = 1; j < map[i].length; j++) {
                direction_1 &= (map[i][j] == c);
                direction_2 &= (map[j][j] == c);
                direction_3 &= (map[j][i] == c);
                direction_4 &= (map[chic - j + 1][j] == c);
            }
            if (direction_1 || direction_2 || direction_3 || direction_4)
                return true;
        }
        return false;
    }*/
}
