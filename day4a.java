import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class day4a {

    static int numBoards = 100;
    static int[] order;
    static int[][][] boards = new int[numBoards][5][5];
    static boolean[][][] marked = new boolean[numBoards][5][5];

    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st;
        //BufferedReader is just a fast way to read input from a file

        order = Arrays.stream(r.readLine().split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < numBoards; i++) {
            r.readLine(); //reads the blank line in between the boards
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(r.readLine());
                for (int k = 0; k < 5; k++) {
                    boards[i][j][k] = Integer.parseInt(st.nextToken()); //reads each value
                }
            }
        }

        int counter = 0;
        int winBoard = 0;
        for (int j : order) {
            //goes through every number, and simulates it
            simulate(j);

            //checking for a winning board
            if (check() != -1) {
                winBoard = check();
                break;
            }
            counter++;
        }
        System.out.println(calcScore(winBoard) * order[counter]);

        r.close();
    }

    public static void simulate(int num) {
        //simulates the calling of numbers from order
        for (int i = 0; i < numBoards; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (boards[i][j][k] == num) {
                        marked[i][j][k] = true;
                    }
                }
            }
        }
    }

    public static int check() {
        //returns the index of the board that has one, if it exists
        int board = -1;
        for (int i = 0; i < numBoards; i++) {
            if (checkRowCol(marked[i])) {
                board = i;
                break;
            }
        }
        return board;
    }

    public static boolean checkRowCol(boolean[][] board) {
        //finds if a bingo exists, at all
        boolean ans = false;
        for (int i = 0; i < 5; i++) {
            //checks the rows
            boolean temp = true;
            for (int j = 0; j < 5; j++) {
                temp = temp && board[i][j];
            }
            ans = ans || temp;
        }
        for (int i = 0; i < 5; i++) {
            //checks the columns
            boolean temp = true;
            for (int j = 0; j < 5; j++) {
                temp = temp && board[j][i];
            }
            ans = ans || temp;
        }
        return ans;
    }

    public static int calcScore(int boardNum) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!marked[boardNum][i][j]) {
                    sum += boards[boardNum][i][j];
                }
            }
        }
        return sum;
    }
}
