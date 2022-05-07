import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class day4b {
    public static void main(String[] args) throws IOException {

        final int NUM_BOARDS = 100;

        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        int[] order = Stream.of(r.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        ArrayList<Board> boards = new ArrayList<>();

        //reading in the data
        for (int i = 0; i < NUM_BOARDS; i++) {

            int[][] temp = new int[5][5];
            r.readLine(); //reads blank line in between data

            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(r.readLine());
                for (int k = 0; k < 5; k++) {
                    temp[j][k] = Integer.parseInt(st.nextToken());
                }
            } //adds data to the ArrayList of boards
            boards.add(new Board(temp));
        }

        for (int call : order) {
            for (Board x : boards) {
                //simulates the calling of numbers
                x.simulate(call);
            }
            Iterator<Board> it = boards.iterator();
            while (it.hasNext()) {
                Board b = it.next();
                //iterates through the ArrayList, removing any boards that have bingoed
                if (b.bingo()) {
                    if (boards.size() == 1) {
                        System.out.println(b.getScore(call));
                        //prints the score of the last board
                        break;
                    }
                    it.remove();
                    //otherwise, it just removes it
                }
            }
        }

        r.close();
    }


    public static class Board {

        int[][] board = new int[5][5];
        boolean[][] marked = new boolean[5][5];

        public Board(int[][] b) {
            System.arraycopy(b, 0, board, 0, 5);
            //reads in the board data, initializes marked to false
        }

        public void simulate(int call) { //simulates the marking on the board
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (board[r][c] == call) {
                        marked[r][c] = true;
                    }
                }
            }
        }

        public boolean bingo() { //checks for bingo
            for (int r = 0; r < 5; r++) { //5 in a row
                boolean good = true;
                for (int c = 0; c < 5; c++) {
                    good = marked[r][c] && good;
                }
                if (good) {
                    return true;
                }
            }
            for (int c = 0; c < 5; c++) { // 5 in a column
                boolean good = true;
                for (int r = 0; r < 5; r++) {
                    good = marked[r][c] && good;
                }
                if (good) {
                    return true;
                }
            }
            return false;
        }

        public int getScore(int calledNum) { //gets the score, as computed
            int sum = 0;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    if (!marked[r][c]) {
                        //sum of the unmarked squares
                        sum += board[r][c];
                    }
                }
            }
            //times the last called number (passed in)
            return sum * calledNum;
        }
    }
}
