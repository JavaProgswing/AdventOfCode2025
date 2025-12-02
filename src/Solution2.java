import java.util.StringTokenizer;

public class Solution2 extends Solution {
    public Solution2() {
        super(2);
    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        solution.testPartTwo();
    }

    private boolean hasRepeatedDigitsTwice(long number) {
        String s = String.valueOf(number);
        int n = s.length();

        // must have even number of digits
        if (n % 2 != 0) return false;

        String left = s.substring(0, n / 2);
        String right = s.substring(n / 2);

        return left.equals(right);
    }

    @Override
    String solvePartOne(String inputData) {
        StringTokenizer sb = new StringTokenizer(inputData, ",\n");
        long sum = 0;
        while (sb.hasMoreTokens()) {
            String token = sb.nextToken();
            System.out.println(token);
            Long lowerBound = Long.parseLong(token.split("-")[0]);
            Long upperBound = Long.parseLong(token.split("-")[1]);
            System.out.println("In range: " + lowerBound + " to " + upperBound);
            for (long i = lowerBound; i <= upperBound; i++) {
                if (hasRepeatedDigitsTwice(i)) {
                    System.out.println("+" + i);
                    sum += i;
                }
            }
        }
        return Long.toString(sum);
    }

    private boolean hasRepeatedDigits(long number) {
        String s = String.valueOf(number);
        int n = s.length();

        // Try every possible pattern length
        for (int len = 1; len <= n / 2; len++) {

            // total length must be divisible by pattern length
            if (n % len != 0) continue;

            String pattern = s.substring(0, len);
            int repeatCount = n / len;

            // Build repeated pattern
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < repeatCount; i++) {
                sb.append(pattern);
            }

            // If equal and repeated at least twice then invalid
            if (sb.toString().equals(s) && repeatCount >= 2) {
                return true;
            }
        }

        return false;
    }

    @Override
    String solvePartTwo(String inputData) {
        StringTokenizer sb = new StringTokenizer(inputData, ",\n");
        long sum = 0;
        while (sb.hasMoreTokens()) {
            String token = sb.nextToken();
            System.out.println(token);
            Long lowerBound = Long.parseLong(token.split("-")[0]);
            Long upperBound = Long.parseLong(token.split("-")[1]);
            System.out.println("In range: " + lowerBound + " to " + upperBound);
            for (long i = lowerBound; i <= upperBound; i++) {
                if (hasRepeatedDigits(i)) {
                    System.out.println("+" + i);
                    sum += i;
                }
            }
        }
        return Long.toString(sum);
    }
}
