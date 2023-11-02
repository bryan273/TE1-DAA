import java.util.Random;

public class RandomizedShellSort {
    private static final int C = 1;  // number of region compare-exchange repetitions
    private static Random random = new Random();

    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void compareExchange(int[] a, int i, int j) {
        // System.out.println("i,j: " + i + "," + j);
        if ((i < j && a[i] > a[j]) || (i > j && a[i] < a[j])) {
            exchange(a, i, j);
        }
    }

    public static void permuteRandom(int[] a) {
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, i + random.nextInt(a.length - i));
        }
    }

    public static void compareRegions(int[] a, int s, int t, int offset) {
        int[] mate = new int[offset];
        // System.out.println("mate: " + java.util.Arrays.toString(mate));

        for (int _c = 0; _c < C; _c++) {
            for (int i = 0; i < offset; i++) {
                mate[i] = i;
            }
            permuteRandom(mate);
            for (int i = 0; i < offset; i++) {
                compareExchange(a, s + i, t + mate[i]);
            }
        }
        // System.out.println(java.util.Arrays.toString(a));
    }

    public static void randomizedShellSort(int[] a) {
        int n = a.length;
        for (int offset = n/2; offset > 0; offset /= 2) {
            // System.out.println("offset: " + offset);
            for (int i = 0; i < n - offset; i += offset) { // compare-exchange up
                // System.out.println("Section 1");
                // System.out.println("i: " + i);
                // System.out.println("i+offset: " + (i + offset));
                compareRegions(a, i, i + offset, offset);
                // System.out.println();
            }

            for (int i = n - offset; i >= offset; i -= offset) { // compare-exchange down
                // System.out.println("Section 2");
                // System.out.println("i-offset: " + (i - offset));
                // System.out.println("i: " + i);
                compareRegions(a, i - offset, i, offset);
                // System.out.println();
            }

            for (int i = 0; i < n - 3 * offset; i += offset) { // compare 3 hops up

                // System.out.println("Section 3");
                // System.out.println("i: " + i);
                // System.out.println("i+3*offset: " + (i + 3 * offset));
                compareRegions(a, i, i + 3 * offset, offset);
                // System.out.println();
            }

            for (int i = 0; i < n - 2 * offset; i += offset) { // compare 2 hops up
                // System.out.println("Section 4");
                // System.out.println("i: " + i);
                // System.out.println("i+2*offset: " + (i + 2 * offset));
                compareRegions(a, i, i + 2 * offset, offset);
                // System.out.println();
            }

            for (int i = 0; i < n; i += 2 * offset) { // compare odd-even regions
                // System.out.println("Section 5");
                // System.out.println("i: " + i);
                // System.out.println("i+offset: " + (i + offset));
                compareRegions(a, i, i + offset, offset);
                // System.out.println();
            }

            for (int i = offset; i < n - offset; i += 2 * offset) { // compare even-odd regions
                // System.out.println("Section 6");
                // System.out.println("i: " + i);
                // System.out.println("i+offset: " + (i + offset));
                compareRegions(a, i, i + offset, offset);
                // System.out.println();
            }
        }
    }

    public static void debugRandomizedShellSort() {
        int[] testArray = {5, 4, 2, 1, 3, 7, 8, 6};
        // System.out.println("Test Array: " + java.util.Arrays.toString(testArray));
        randomizedShellSort(testArray);
    }

    public static void main(String[] args) {
        // Jika ingin lihat step per step
        // 1. Uncomment semua System.out.println(..)
        // 2. Uncomment pemanggilan fungsi debugRandomizedShellSort
        // debugRandomizedShellSort();
    }
}
