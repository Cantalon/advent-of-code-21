import java.io.*;
import java.util.StringTokenizer;

public class day6b {

    public static long[] fish = new long[9];
    //bookkeeping array

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(r.readLine(), ",");

        while (st.hasMoreTokens()) {
            fish[Integer.parseInt(st.nextToken())]++;
        } //read initial values

        for (int day = 0; day < 256; day++) {
            simulate();
        } //simulate!

        long total = 0;
        for (long x : fish) {
            total += x;
        } //count the number of fish at the end

        System.out.println(total);
        r.close();
    }

    public static void simulate() {
        long numNew = fish[0];
        for (int i = 0; i < 8; i++) {
            fish[i] = fish[i + 1];
        }
        fish[8] = numNew;
        fish[6] += numNew;
    }
}
