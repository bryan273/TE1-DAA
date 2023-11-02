public class MaxHeapSort {
    private int size;
    private int[] array;

    public void sort(int[] arr) {
        this.array = arr;
        this.size = arr.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(i);
        }

        for (int i = size - 1; i >= 0; i--) {
    
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            size--;
            heapify(0);
        }
    }

    void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            heapify(largest);
        }
    }

    public static void main(String args[]) {
        // Kode di bawah ini untuk testing
        // int[] arr = {12, 11, 13, 5, 6, 7};

        // MaxHeapSort heapSort = new MaxHeapSort();
        // heapSort.sort(arr);

        // for (int i = 0; i < arr.length; ++i) {
        //     System.out.print(arr[i] + " ");
        // }
    }
}
