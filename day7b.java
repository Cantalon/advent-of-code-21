import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class day7b {

    public static final int NUM_POS = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(r.readLine(), ",");

        ArrayList<Integer> pos = new ArrayList<>();
        while (st.hasMoreTokens()) {
            pos.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(pos);

        // the mean is the minimum value for mean squared error, which is why we use it
        int mean = 0;
        for (Integer x : pos) {
            mean += x;
        }
        mean /= NUM_POS;

        long sum = 0;
        for (Integer x : pos) { // calculate fuel consumption
            int val = Math.abs(mean - x);
            // sum formula: 1 + 2 + ... + n = n * (n + 1) / 2
            sum += (long) val * (val + 1) / 2;
        }

        System.out.println(sum);
        r.close();
    }
}
