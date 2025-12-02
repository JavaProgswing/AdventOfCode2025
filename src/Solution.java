import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

abstract class Solution {
    protected String inputFile;
    protected String sampleInputFile;

    protected StringBuilder inputData;
    protected StringBuilder sampleInputData;

    public Solution(int input) {
        this.inputFile = Objects.requireNonNull(Solution.class.getResource("input_" + input + ".txt")).getPath();
        this.sampleInputFile = Objects.requireNonNull(Solution.class.getResource("sample_" + input + ".txt")).getPath();
        inputData = new StringBuilder();
        sampleInputData = new StringBuilder();
        readFile(inputFile, inputData);
        readFile(sampleInputFile, sampleInputData);
    }

    abstract String solvePartOne(String inputData);

    abstract String solvePartTwo(String inputData);

    protected void readFile(final String file, final StringBuilder data) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }


    public void testPartOne() {
        long startTime = System.currentTimeMillis();
        String output = solvePartOne(inputData.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("(" + (endTime - startTime) + " ms)Output 1: \n" + output);
    }

    public void testPartTwo() {
        long startTime = System.currentTimeMillis();
        String output = solvePartTwo(inputData.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("(" + (endTime - startTime) + " ms)Output 2: \n" + output);
    }

    public void testPartOneSample() {
        long startTime = System.currentTimeMillis();
        String output = solvePartOne(sampleInputData.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("(" + (endTime - startTime) + " ms)Output 1: \n" + output);
    }

    public void testPartTwoSample() {
        long startTime = System.currentTimeMillis();
        String output = solvePartTwo(sampleInputData.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("(" + (endTime - startTime) + " ms)Output 2: \n" + output);
    }
}