import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class day7a {

    public static final int NUM_POS = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(r.readLine(), ",");

        ArrayList<Integer> pos = new ArrayList<>();
        while (st.hasMoreTokens()) {
            pos.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(pos);

        // the median is the minimum value for the absolute deviation, which is why we use it
        int median = (pos.get(NUM_POS / 2 - 1) + (pos.get(NUM_POS / 2))) / 2;

        int sum = 0;
        for (Integer x : pos) {
            sum += Math.abs(median - x);
        } // calculate fuel consumption

        System.out.println(sum);
        r.close();
    }
}
