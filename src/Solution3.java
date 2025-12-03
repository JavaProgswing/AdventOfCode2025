import java.math.BigInteger;

public class Solution3 extends Solution {
    public Solution3() {
        super(3);
    }

    public static void main(String[] args) {
        Solution solution = new Solution3();
        solution.testPartTwoSample();
    }

    @Override
    String solvePartOne(String inputData) {
        long total = 0;

        for (String bank : inputData.split("\n")) {
            int n = bank.length();
            if (n < 2) continue; // defensive

            // build prefix max digits
            int[] prefMax = new int[n];
            prefMax[0] = bank.charAt(0) - '0';
            for (int i = 1; i < n; ++i) {
                int d = bank.charAt(i) - '0';
                prefMax[i] = Math.max(prefMax[i - 1], d);
            }

            int best = 0;
            for (int j = 1; j < n; ++j) {
                int ones = bank.charAt(j) - '0';
                int tens = prefMax[j - 1];      // must be from index < j
                int val = tens * 10 + ones;
                if (val > best) best = val;
            }
            total += best;
        }

        return String.valueOf(total);
    }

    @Override
    String solvePartTwo(String inputData) {
        final int K = 12;
        BigInteger total = BigInteger.ZERO;

        for (String bank : inputData.split("\n")) {
            int n = bank.length();
            if (n == 0) continue;

            int pick = Math.min(K, n); // if bank shorter than K, pick all digits
            StringBuilder chosen = new StringBuilder(pick);

            int start = 0;
            for (int pos = 0; pos < pick; ++pos) {
                int remaining = pick - pos;
                int last = n - remaining; // inclusive

                // find max digit in bank[start .. last]
                char maxChar = '0';
                int maxIndex = start;
                for (int i = start; i <= last; ++i) {
                    char c = bank.charAt(i);
                    if (c > maxChar) {
                        maxChar = c;
                        maxIndex = i;
                        if (maxChar == '9') break; // early exit
                    }
                }

                chosen.append(maxChar);
                start = maxIndex + 1;
            }

            BigInteger bankVal = new BigInteger(chosen.toString());
            total = total.add(bankVal);
        }

        return total.toString();
    }
}
