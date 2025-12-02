public class Solution1 extends Solution {

    public Solution1() {
        super(1);
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        solution.testPartTwo();
    }


    @Override
    String solvePartOne(String inputData) {
        int safePosition = 50;
        int steps = 0;
        for (String line : inputData.split("\n")) {
            switch (line.charAt(0)) {
                case 'L':
                    safePosition -= Integer.parseInt(line.substring(1));
                    break;
                case 'R':
                    safePosition += Integer.parseInt(line.substring(1));
                    break;
                default:
                    break;
            }
            safePosition = (safePosition + 100) % 100;
            steps += safePosition == 0 ? 1 : 0;
        }
        return String.valueOf(steps);
    }

    @Override
    String solvePartTwo(String inputData) {
        int safePosition = 50;
        int steps = 0;

        for (String line : inputData.split("\n")) {
            char dir = line.charAt(0);
            int dist = Integer.parseInt(line.substring(1));

            for (int i = 0; i < dist; i++) {
                if (dir == 'L')
                    safePosition = (safePosition + 99) % 100;
                else
                    safePosition = (safePosition + 1) % 100;

                if (safePosition == 0)
                    steps++;
            }
        }
        return String.valueOf(steps);
    }
}
