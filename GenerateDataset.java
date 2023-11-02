import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateDataset {
    private static Random random = new Random();

    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
        return array;
    }

    public static int[] generateSortedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    public static int[] generateReversedArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = size - 1 - i;
        }
        return array;
    }

    public static void saveArrayToFile(int[] array, String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 0; i < array.length; i++) {
                writer.write(Integer.toString(array[i]));
                if (i < array.length - 1) {
                    writer.write("\n"); // Pemisah baris baru
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] sizes = { (int) Math.pow(2, 9), (int) Math.pow(2, 13), (int) Math.pow(2, 16) };

        for (int size : sizes) {
            int[] randomArray = generateRandomArray(size);
            int[] sortedArray = generateSortedArray(size);
            int[] reversedArray = generateReversedArray(size);

            saveArrayToFile(randomArray, "random_array_" + size + ".txt");
            saveArrayToFile(sortedArray, "sorted_array_" + size + ".txt");
            saveArrayToFile(reversedArray, "reversed_array_" + size + ".txt");
        }

        System.out.println("Arrays have been saved to text files.");
    }
}
