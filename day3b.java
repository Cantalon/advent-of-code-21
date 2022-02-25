import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day3b {

    static final int inputLength = 1000;
    static final int stringLength = 12;

    static String[] bits;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader is just a fast way to read input from a file

        bits = new String[inputLength];
        for (int i = 0; i < inputLength; i++) {
            bits[i] = r.readLine();
        }

        System.out.println(toInt(findOxy()) * toInt(findCO2()));
    }

    public static String findOxy() {

        String ans = "";
        boolean[] eliminated = new boolean[bits.length];

        for (int i = 0; i < stringLength; i++) {
            //first, finds the most common digit among the non-eliminated ones
            int zeros = 0;
            int ones = 0;

            for (int j = 0; j < inputLength; j++) {
                if (bits[j].charAt(i) == '0' && !eliminated[j]) {
                    zeros++;
                } else if (bits[j].charAt(i) == '1' && !eliminated[j]) {
                    ones++;
                }
            }
            char mcv;
            if (ones >= zeros) {
                mcv = '1';
            } else {
                mcv = '0';
            }

            //eliminates any ones that disagree
            for (int j = 0; j < inputLength; j++) {
                if (bits[j].charAt(i) != mcv) {
                    eliminated[j] = true;
                }
            }
        }
        //finds the answer
        for (int i = 0; i < inputLength; i++) {
            if (!eliminated[i]) {
                ans = bits[i];
            }
        }

        return ans;
    }

    public static String findCO2() {

        String ans = "";
        boolean[] eliminated = new boolean[bits.length];

        for (int i = 0; i < stringLength; i++) {
            int zeros = 0;
            int ones = 0;

            for (int j = 0; j < inputLength; j++) {
                if (bits[j].charAt(i) == '0' && !eliminated[j]) {
                    zeros++;
                } else if (bits[j].charAt(i) == '1' && !eliminated[j]) {
                    ones++;
                }
            }
            char lcv;
            if (zeros <= ones) {
                lcv = '0';
            } else {
                lcv = '1';
            }

            for (int j = 0; j < inputLength; j++) {
                if (bits[j].charAt(i) != lcv) {
                    eliminated[j] = true;
                }
            }

            if (checkArr(eliminated)) {
                break;
            }
        }

        for (int i = 0; i < inputLength; i++) {
            if (!eliminated[i]) {
                ans = bits[i];
            }
        }

        return ans;
    }

    public static boolean checkArr(boolean[] arr) { //checks if arr has only 1 value left
        int counter = 0;
        for (boolean value : arr) {
            if (!value) {
                counter++;
            }
        }
        if (counter == 1) {
            return true;
        }
        return false;
    }

    public static int toInt(String s) { //converts a base 2 String into a base 10 integer
        int power = s.length() - 1;
        int result = 0;

        for (int i = 0; i < s.length(); i++) {
            //iterates through each digit, and finds the value of that digit
            result += (int) Math.pow(2, power) * Integer.parseInt(s.substring(i, i + 1));
            power--;
        }

        return result;
    }
}
