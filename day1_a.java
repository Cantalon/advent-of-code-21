import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class day1_a {

    static final int inputLength = 2000;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader is just a fast way to read input from a file

        int[] depths = new int[inputLength];

        for (int i = 0; i < inputLength; i++) {
            depths[i] = Integer.parseInt(r.readLine());
        } //reading to an array

        int counter = 0;
        for (int i = 1; i < inputLength; i++) {
            if (depths[i] > depths[i - 1]) {
                counter++;
            }
        }
        System.out.println(counter);
        r.close();
    }
}
