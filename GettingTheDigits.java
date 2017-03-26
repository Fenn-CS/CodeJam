/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GettingTheDigits;

import java.util.*;
import java.io.*;

/**
 *
 * @author fenn
 */
public class GettingTheDigits {

    /**
     * @param args the command line arguments
     */
    String[] numberArray = new String[]{"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"};

    /**
     * getNumbers *
     */
//Gets a string and finds all the numbers containe in tha string
//By checking the occurence each character in the numberArray
//If the all the letters for a number in the numberArray array occurs in the string
//Then number is considered present
    public List<Integer> getNumbers(String string, List<Integer> initial) {
        List<Integer> Found = new ArrayList<>();
        for (int i = 0; i < this.numberArray.length; i++) {
            char[] temp = this.numberArray[i].toCharArray();
            String current_number = this.numberArray[i];
            List<Integer> rem_pos = new ArrayList<>();
            boolean number_found = true;

            for (int j = 0; j < temp.length; j++) {

                if (string.indexOf(temp[j]) >= 0) {
                    int pos = 0;
                    if (!(countOccurrences(current_number, temp[j]) > countOccurrences(string, temp[j]))) {

                        //  System.out.println("HEY :" + current_number + " has :" + countOccurrences(current_number, temp[j]) + " of " + temp[j]);
                        //System.out.println("AND string :" + string + " has :" + countOccurrences(string, temp[j]) + " of " + temp[j]);
                        pos = string.indexOf(temp[j]);
                        rem_pos.add(pos);

                    } else {
                        number_found = false;
                        break;
                    }

                } else {
                    number_found = false;
                    break;
                }
            }
            if (number_found) {
                Found.add(i);

                for (int k = 0; k < rem_pos.size(); k++) {
                    string = string.substring(0, rem_pos.get(k)) + ' ' + string.substring(rem_pos.get(k) + 1);
                }

            }
        }
        //Remove white spaces to check if string is empty.
        string = string.replaceAll("\\s+", "");
        if (string.length() < 3) {
            if (initial != null) {
                Found.addAll(initial);
            }
            return Found;
        }

        return getNumbers(string, Found);
    }

    public int countOccurrences(String haystack, char needle) {
        int count = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle) {
                count++;
            }
        }
        return count;
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
            for (int i = 1; i <= t; ++i) {
                String string = in.next();
                GettingTheDigits GTD = new GettingTheDigits();
                System.out.print("Case #" + i + ": ");
                List<Integer> numbers = GTD.getNumbers(string, null);
                for (int j = 0; j < numbers.size(); j++) {
                    System.out.print(numbers.get(j));

                }
                System.out.println("");
            }
        }
    }
}
