import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class day5b {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));

        final int MAX_COORD = 1000;
        final int NUM_VENTS = 500;
        int[][] map = new int[MAX_COORD][MAX_COORD];
        //map stores a map of the seabed

        for (int vent = 0; vent < NUM_VENTS; vent++) {
            //reading data
            StringTokenizer st = new StringTokenizer(r.readLine());
            String p1 = st.nextToken();
            st.nextToken(); //reads "->", a piece of undesired data
            String p2 = st.nextToken();

            int x1 = Integer.parseInt(p1.substring(0, p1.indexOf(",")));
            int y1 = Integer.parseInt(p1.substring(p1.indexOf(",") + 1));
            int x2 = Integer.parseInt(p2.substring(0, p2.indexOf(",")));
            int y2 = Integer.parseInt(p2.substring(p2.indexOf(",") + 1));
            //formatting the input for the program

            if (x1 == x2) { //if we have a vertical line
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                    map[x1][y]++;
                }
            } else if (y1 == y2) { //if we have a horizontal line
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                    map[x][y1]++;
                }
            } else { //diagonal
                if (x2 - x1 == y2 - y1) { //slope of 1
                    for (int i = 0; i <= Math.abs(x2 - x1); i++) {
                        map[Math.min(x1, x2) + i][Math.min(y1, y2) + i]++;
                    }
                } else { //slope of -1
                    for (int i = 0; i <= Math.abs(x2 - x1); i++) {
                        map[Math.max(x1, x2) - i][Math.min(y1, y2) + i]++;
                    }
                }
            }
        }

        //counts the desired answer
        int count = 0;
        for (int[] arr : map) {
            for (int val : arr) {
                if (val > 1) {
                    count++;
                }
            }
        }
        System.out.println(count);

        r.close();
    }
}
