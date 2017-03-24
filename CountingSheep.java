/**
 * CountingSheep Bleatrix Trotter the sheep has devised a strategy that helps
 * her fall asleep faster. First, she picks a number N. Then she starts naming
 * N, 2 × N, 3 × N, and so on. Whenever she names a number, she thinks about all
 * of the digits in that number. She keeps track of which digits (0, 1, 2, 3, 4,
 * 5, 6, 7, 8, and 9) she has seen at least once so far as part of any number
 * she has named. Once she has seen each of the ten digits at least once, she
 * will fall asleep. **Algorithm** Keep all numbers(0-9) in a list. Remove each
 * number once found. Repeat until all numbers are found( Keeping all rules in
 * mind).
 */
package CountingSheep;

import java.util.*;
import java.io.*;

/**
 *
 * @author fenn
 */
public class CountingSheep {

    protected List<Integer> List = new ArrayList<>();

    public void createList() {
        for (int i = 0; i < 10; i++) {
            this.List.add(i);
        }
    }

    public List<Integer> getDigits(int number) {
        List<Integer> Digits = new ArrayList<>();
        while (number > 0) {
            int quotien = number / 10;
            int current_digit = number - quotien * 10;
            number = quotien;
//            System.out.println(current_digit);
            Digits.add(current_digit);
        }
        return Digits;
    }

    public boolean checkDigit(int digit) {

        if (this.List.contains(digit)) {
            return true;
        }
        return false;

    }

    public boolean allFound() {
        if (this.List.isEmpty()) {
            return true;
        }
        return false;
    }

    //Main
    public static void main(String[] args) {
        //Set stdin to read from files
        try {

            FileInputStream is = new FileInputStream(new File(args[0]));
            System.setIn(is);

        } catch (Exception e) {

            System.out.println(e);
        }

        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        if (t >= 1 && t <= 100) {
            for (int i = 1; i <= t; ++i) {
                int n = in.nextInt();
                if (n == 0) {
                    System.out.println("Case #" + i + ": INSOMNIA");
                    continue;
                } else {
                    CountingSheep One = new CountingSheep();
                    One.createList();
                    int count = 1;
                    int nextNumber = n;
                    while (!One.allFound()) {
                        nextNumber = n * count++;
                        List<Integer> digits = One.getDigits(nextNumber);
                        One.List.removeAll(digits);

                    }

                    System.out.println("Case #" + i + ": " + nextNumber);
                }

            }
        } else {
            //Do nothing and end the program or output a message
            System.out.println("Range incorrect! must be from 1 upto and including 100");
        }

    }
}
