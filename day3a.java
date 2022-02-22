import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day3a {

    static final int inputLength = 1000;
    static final int stringLength = 12;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader is just a fast way to read input from a file

        String[] numbers = new String[inputLength];
        for (int i = 0; i < inputLength; i++) {
            numbers[i] = r.readLine();
        }

        String gamma = "";
        String epsilon = "";

        for (int i = 0; i < stringLength; i++) {
            //counts the number of zeros and ones for each position in the 12-digit numbers
            int zeros = 0;
            int ones = 0;

            for (int j = 0; j < inputLength; j++) {
                if (numbers[j].charAt(i) == '0') {
                    zeros++;
                } else if (numbers[j].charAt(i) == '1') {
                    ones++;
                }
            }

            //adding the right characters to gamma and epsilon binaries
            if (zeros > ones) {
                gamma += "0";
                epsilon += "1";
            } else if (ones > zeros) {
                gamma += "1";
                epsilon += "0";
            }
        }
        
        System.out.println(toInt(gamma) * toInt(epsilon));

        r.close();
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
