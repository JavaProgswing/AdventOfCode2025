import java.math.BigInteger;

public class Solution6 extends Solution {
    public Solution6() {
        super(6);
    }

    public static void main(String[] args) {
        Solution6 solution = new Solution6();
        solution.testPartTwoSample();
    }

    private int[] parseLineToIntArray(String line, String delimiter) {
        String[] parts = line.split(delimiter);
        int[] nums = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            nums[i] = Integer.parseInt(parts[i]);
        }
        return nums;
    }

    @Override
    String solvePartOne(String inputData) {
        String[] lines = inputData.split("\n");
        int rows = lines.length - 1;
        int[][] values = new int[rows][];
        String[] operators;
        for (int i = 0; i < rows; i++) {
            String line = lines[i].trim();
            values[i] = parseLineToIntArray(line, "\\s+");
        }
        operators = lines[rows].split("\\s+");

        BigInteger sum = BigInteger.ZERO;
        int cols = values[0].length;
        for (int i = 0; i < cols; i++) {
            long result;
            if (operators[i].equals("+")) {
                result = 0L;
            } else {
                result = 1L;
            }
            for (int j = 0; j < rows; j++) {
                if (operators[i].equals("+")) {
                    result += values[j][i];
                } else {
                    result *= values[j][i];
                }
            }
            sum = sum.add(BigInteger.valueOf(result));
        }
        return sum.toString();
    }

    @Override
    String solvePartTwo(String inputData) {
        return null;
    }
}
