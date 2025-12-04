import java.util.ArrayList;
import java.util.Arrays;

public class Solution4 extends Solution {
    public Solution4() {
        super(4);
    }

    public static void main(String[] args) {
        Solution solution = new Solution4();
        solution.testPartTwo();
    }

    @Override
    String solvePartOne(String inputData) {
        ArrayList<String> in = new ArrayList<>(Arrays.asList(inputData.split("\n")));
        int rows = in.size();
        int cols = in.get(0).length();
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = in.get(i).toCharArray();
        }

        int sum = 0;
        char rollChar = '@';
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char current = matrix[r][c];
                if (current == rollChar) {
                    int rollCount = 0;
                    rollCount += (r - 1 >= 0 && c - 1 >= 0 && matrix[r - 1][c - 1] == rollChar) ? 1 : 0;
                    rollCount += (r - 1 >= 0 && matrix[r - 1][c] == rollChar) ? 1 : 0;
                    rollCount += (r - 1 >= 0 && c + 1 < cols && matrix[r - 1][c + 1] == rollChar) ? 1 : 0;

                    rollCount += (c - 1 >= 0 && matrix[r][c - 1] == rollChar) ? 1 : 0;
                    rollCount += (c + 1 < cols && matrix[r][c + 1] == rollChar) ? 1 : 0;

                    rollCount += (r + 1 < rows && c - 1 >= 0 && matrix[r + 1][c - 1] == rollChar) ? 1 : 0;
                    rollCount += (r + 1 < rows && matrix[r + 1][c] == rollChar) ? 1 : 0;
                    rollCount += (r + 1 < rows && c + 1 < cols && matrix[r + 1][c + 1] == rollChar) ? 1 : 0;

                    if (rollCount < 4) {
                        sum += 1;
                    }
                }
            }
        }

        return String.valueOf(sum);
    }

    @Override
    String solvePartTwo(String inputData) {
        ArrayList<String> in = new ArrayList<>(Arrays.asList(inputData.split("\n")));
        int rows = in.size();
        int cols = in.get(0).length();
        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = in.get(i).toCharArray();
        }

        int sum = 0, removals;
        char rollChar = '@';
        char[][] matrixCopy = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrixCopy[i] = Arrays.copyOf(matrix[i], cols);
        }

        do {
            removals = 0;
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    char current = matrix[r][c];
                    if (current == rollChar) {
                        int rollCount = 0;
                        rollCount += (r - 1 >= 0 && c - 1 >= 0 && matrix[r - 1][c - 1] == rollChar) ? 1 : 0;
                        rollCount += (r - 1 >= 0 && matrix[r - 1][c] == rollChar) ? 1 : 0;
                        rollCount += (r - 1 >= 0 && c + 1 < cols && matrix[r - 1][c + 1] == rollChar) ? 1 : 0;

                        rollCount += (c - 1 >= 0 && matrix[r][c - 1] == rollChar) ? 1 : 0;
                        rollCount += (c + 1 < cols && matrix[r][c + 1] == rollChar) ? 1 : 0;

                        rollCount += (r + 1 < rows && c - 1 >= 0 && matrix[r + 1][c - 1] == rollChar) ? 1 : 0;
                        rollCount += (r + 1 < rows && matrix[r + 1][c] == rollChar) ? 1 : 0;
                        rollCount += (r + 1 < rows && c + 1 < cols && matrix[r + 1][c + 1] == rollChar) ? 1 : 0;

                        if (rollCount < 4) {
                            matrixCopy[r][c] = 'x';
                            removals += 1;
                            sum += 1;
                        }
                    }
                }
            }

//            System.out.printf("Remove %d rolls of paper: \n", removals);
//            for (int i = 0; i < rows; i++) {
//                System.out.println(new String(matrixCopy[i]));
//                for (int j = 0; j < cols; j++) {
//                    if (matrixCopy[i][j] == 'x') {
//                        matrixCopy[i][j] = '.';
//                    }
//                }
//            }
//            System.out.println();
            for (int i = 0; i < rows; i++) {
                matrix[i] = Arrays.copyOf(matrixCopy[i], cols);
            }
        } while (removals > 0);

        return String.valueOf(sum);
    }
}
