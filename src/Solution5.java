import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution5 extends Solution {
    public Solution5() {
        super(5);
    }

    public static void main(String[] args) {
        Solution solution = new Solution5();
        solution.testPartTwo();
    }

    @Override
    String solvePartOne(String inputData) {
        String[] lines = inputData.split("\n");

        List<long[]> ranges = new ArrayList<>();
        int i = 0;

        // Read only the first section (ranges)
        for (; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                i++;
                break;
            }

            String[] parts = line.split("-");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            ranges.add(new long[]{a, b});
        }

        // Sort by start
        ranges.sort(Comparator.comparingLong(r -> r[0]));

        // Merge intervals
        List<long[]> merged = new ArrayList<>();
        for (long[] r : ranges) {
            if (merged.isEmpty()) {
                merged.add(r);
                continue;
            }

            long[] last = merged.get(merged.size() - 1);

            if (r[0] <= last[1] + 1) {
                last[1] = Math.max(last[1], r[1]);
            } else {
                merged.add(r);
            }
        }

        long count = 0;
        for (; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;

            long id = Long.parseLong(line);

            if (isInRanges(id, merged)) {
                count++;
            }
        }

        return String.valueOf(count);
    }

    private boolean isInRanges(long id, List<long[]> merged) {
        int left = 0, right = merged.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            long[] r = merged.get(mid);

            if (id < r[0]) {
                right = mid - 1;
            } else if (id > r[1]) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    String solvePartTwo(String inputData) {
        String[] lines = inputData.split("\n");

        List<long[]> ranges = new ArrayList<>();
        int i = 0;

        // Read only the first section (ranges)
        for (; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) break;

            String[] parts = line.split("-");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            ranges.add(new long[]{a, b});
        }

        // Sort by start
        ranges.sort(Comparator.comparingLong(r -> r[0]));

        // Merge intervals
        List<long[]> merged = new ArrayList<>();
        for (long[] r : ranges) {
            if (merged.isEmpty()) {
                merged.add(new long[]{r[0], r[1]});
                continue;
            }

            long[] last = merged.get(merged.size() - 1);

            if (r[0] <= last[1] + 1) {
                last[1] = Math.max(last[1], r[1]);
            } else {
                merged.add(new long[]{r[0], r[1]});
            }
        }

        // Count total IDs across merged ranges
        long count = 0;
        for (long[] r : merged) {
            count += (r[1] - r[0] + 1);
        }

        return String.valueOf(count);
    }
}
