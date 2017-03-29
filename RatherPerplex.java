/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratherperplex;

import java.util.*;
import java.io.*;
import java.util.Random;

/**
 *
 * @author fenn
 */
public class RatherPerplex {

    /**
     * @param args the command line arguments
     */
    int rounds;
    int numPlayers;

    int[] paperPlayers;
    int[] rockPlayers;
    int[] scissorsPlayers;
    int[] lineUp;
    String[] symbols = new String[]{"P", "R", "S"};

    public RatherPerplex(int rock, int paper, int scissors) {
        this.numPlayers = this.pow(2, rounds);
        this.paperPlayers = new int[paper];
        this.rockPlayers = new int[rock];
        this.scissorsPlayers = new int[scissors];
        this.lineUp = new int[paper + rock + scissors];
    }

    public void setPlayers() {
        for (int i = 0; i < this.paperPlayers.length; i++) {
            this.paperPlayers[i] = 1;
        }
        for (int i = 0; i < this.rockPlayers.length; i++) {
            this.rockPlayers[i] = 2;
        }
        for (int i = 0; i < this.scissorsPlayers.length; i++) {
            this.scissorsPlayers[i] = 3;
        }
    }

    public void play() {
        for (int i = 0; i < this.rounds; i++) {

        }
    }

    public void createArrangement() {
        int position = 0;
        for (int i = 0; i < this.lineUp.length; i++) {
            if (i < this.paperPlayers.length) {
                this.lineUp[position] = this.paperPlayers[i];
                position++;
            }
            if (i < this.rockPlayers.length) {
                this.lineUp[position] = this.rockPlayers[i];
                position++;
            }
            if (i < this.scissorsPlayers.length) {
                this.lineUp[position] = this.scissorsPlayers[i];
                position++;
            }

        }

    }

    public void cleanLineUp() {
        for (int i = 0; i < this.lineUp.length - 1; i++) {
            for (int j = 0; j < this.lineUp.length - 1; j++) {
                if (this.lineUp[j] == this.lineUp[j + 1]) {
                    Random rand = new Random();
                    int pos = j;
                    do {
                        pos = rand.nextInt(this.lineUp.length);
                        int temp = this.lineUp[j];
                        this.lineUp[j] = this.lineUp[pos];
                        this.lineUp[pos] = temp;
                    } while (this.lineUp[pos] == this.lineUp[j]);

                }

            }
        }
    }

  

    public boolean playerTiePossible() {
        boolean possible = false;
        if (this.paperPlayers.length == 0 && this.rockPlayers.length == 0) {
            possible = true;
        }
        if (this.rockPlayers.length == 0 && this.scissorsPlayers.length == 0) {
            possible = true;
        }
        if (this.scissorsPlayers.length == 0 && this.paperPlayers.length == 0) {
            possible = true;
        }

        return possible;
    }

    public boolean resultTiePossible() {
        boolean possible = false;
        if (this.paperPlayers.length > 1 && this.paperPlayers.length == this.rockPlayers.length && this.scissorsPlayers.length == 0) {
            possible = true;
        }
        if (this.rockPlayers.length > 1 && this.rockPlayers.length == this.scissorsPlayers.length && this.paperPlayers.length == 0) {
            possible = true;
        }
        if (this.scissorsPlayers.length > 1 && this.scissorsPlayers.length == this.paperPlayers.length && this.rockPlayers.length == 0) {
            possible = true;
        }

        return possible;
    }

    public int arrSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        return sum;
    }

    public int pow(int base, int index) {
        if (index == 1) {
            return base;
        }
        if (index == 0) {

            return 1;
        }
        return base * pow(base, index - 1);
    }

    public static void main(String[] args) {

        try {

            FileInputStream is = new FileInputStream(new File(args[0]));
            System.setIn(is);

        } catch (Exception e) {

            System.out.println(e);
        }

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        if (t >= 1 && t <= 100) {
            for (int i = 1; i <= t; i++) {
                int n = in.nextInt();
                int r = in.nextInt();
                int p = in.nextInt();
                int s = in.nextInt();

                RatherPerplex Game = new RatherPerplex(r, p, s);
                if (Game.playerTiePossible()) {
                    System.out.println("Case #" + i + ": IMPOSSIBLE");
                } else if (Game.resultTiePossible()) {

                    System.out.println("Case #" + i + ": IMPOSSIBLE");
                } else {
//                    System.out.println("Case #" + i + ": WORKING");
                    Game.setPlayers();
                    Game.createArrangement();
                    Game.cleanLineUp();
                    System.out.print("Case #" + i + ": ");
                    for (int k = 0; k < Game.lineUp.length; k++) {
                        System.out.print(Game.symbols[Game.lineUp[k] - 1]);
                    }
                    System.out.println();

                }

            }
        }

    }
}
