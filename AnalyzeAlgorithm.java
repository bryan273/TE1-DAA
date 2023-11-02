import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnalyzeAlgorithm {
    public static int[] readFileToArray(String filename) throws FileNotFoundException {
        ArrayList<Integer> numbers = new ArrayList<>();
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        scanner.close();

        int[] array = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            array[i] = numbers.get(i);
        }  
        return array;
    }   

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) return false;
        }
        return true;
    }

    public static void analyzeSortingAlgorithm(int[] array, String algorithmName) {
        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        if (algorithmName.equals("RandomizedShellSort")) {
            RandomizedShellSort.randomizedShellSort(array);
        } else if (algorithmName.equals("MaxHeapSort")) {
            MaxHeapSort heapSort = new MaxHeapSort();
            heapSort.sort(array);
        }

        if (isSorted(array)) {
            System.out.println("Sorting successful");
        } else {
            System.out.println("Sorting failed");
        }

        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long timeTaken = endTime - startTime;
        long memoryUsed = endMemory - startMemory;

        System.out.println("Time taken for " + algorithmName + ": " + timeTaken / 1e6 + " ms");
        System.out.println("Memory used for " + algorithmName + ": " + memoryUsed / 1024 + " KB");
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        String[] types = { "random_array_", "sorted_array_", "reversed_array_" };
        int[] sizes = { (int) Math.pow(2, 9), (int) Math.pow(2, 13), (int) Math.pow(2, 16) };
        String[] algorithms = { "RandomizedShellSort", "MaxHeapSort" };

        for (String type : types) {
            for (int size : sizes) {
                String filename = type + size + ".txt";
                System.out.println("Processing file: " + filename);
                
                try {
                    int[] array = readFileToArray(filename);

                    for (String algorithm : algorithms) {
                        analyzeSortingAlgorithm(array.clone(), algorithm);
                    }

                } catch (FileNotFoundException e) {
                    System.out.println("Error: File " + filename + " not found.");
                }
            }
        }
    }
}
