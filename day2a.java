import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class day2a {

    static final int inputLength = 1000;

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;
        //BufferedReader is just a fast way to read input from a file

        int x_pos = 0;
        int y_pos = 0;

        for (int i = 0; i < inputLength; i++) {
            st = new StringTokenizer(r.readLine());

            String command = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());

            if (command.equals("forward")) {
                x_pos += amount;
            } else if (command.equals("down")) {
                y_pos += amount;
            } else if (command.equals("up")) {
                y_pos -= amount;
            }
        }

        System.out.println(x_pos * y_pos);
        r.close();
    }
}
