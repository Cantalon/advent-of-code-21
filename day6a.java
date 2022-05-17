import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class day6a {

    public static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(r.readLine(), ",");

        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        } //read initial values
        for (int day = 0; day < 80; day++) {
            simulate();
        }
        
        System.out.println(list.size());
        r.close();
    }

    public static void simulate() {
        int len = list.size();
        for (int i = 0; i < len; i++) {
            if (list.get(i) == 0) {
                list.set(i, 6);
                list.add(8);
                continue;
            }
            list.set(i, list.get(i) - 1);
        }
    }
}
